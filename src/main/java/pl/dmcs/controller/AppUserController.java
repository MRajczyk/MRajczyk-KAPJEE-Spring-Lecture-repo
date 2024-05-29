package pl.dmcs.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dmcs.domain.AppUser;
import pl.dmcs.service.*;
import pl.dmcs.validator.AppUserValidator;

@Controller
public class AppUserController {

    @Autowired
    AddressService addressService;

    @Autowired
    AppUserRoleService appUserRoleService;

    private final AppUserService appUserService;
    private final EmailService emailService;
    private final AppUserValidator appUserValidator = new AppUserValidator();

    @Autowired
    public AppUserController(AppUserService appUserService, EmailService emailService) {
        this.appUserService = appUserService;
        this.emailService = emailService;
    }

    @Autowired
    public void setReCaptchaService(ReCaptchaService reCaptchaService) {
        this.reCaptchaService = reCaptchaService;
    }

    ReCaptchaService reCaptchaService;

    @RequestMapping(value = "/appUsers")
    public String showAppUsers(Model model, HttpServletRequest request) {
        int appUserId = ServletRequestUtils.getIntParameter(request, "appUserId", -1);
        if (appUserId > 0) {
            AppUser appUser = appUserService.getAppUser(appUserId);
            appUser.setPassword("");
            appUser.setAddress(addressService.getAddress(appUserService.getAppUser(appUserId).getAddress().getId()));
            model.addAttribute("selectedAddress", appUserService.getAppUser(appUserId).getAddress().getId());
            model.addAttribute("appUser", appUser);
        }
        else {
            model.addAttribute("appUser", new AppUser());
        }
        model.addAttribute("appUserList", appUserService.listAppUser());
        model.addAttribute("appUserRoleList",appUserRoleService.listAppUserRole());
        model.addAttribute("addressesList", addressService.listAddress());
        model.addAttribute("captchaSiteKey", tempEnvironmentVars.SITE_KEY);

        return "appUsers";
    }

    @RequestMapping(value = "/addAppUser", method = RequestMethod.POST)
    public String addAppUser(@Valid @ModelAttribute("appUser") AppUser appUser, BindingResult result, Model model,
                             HttpServletRequest request) {
        System.out.println("First Name: " + appUser.getFirstName() +
                " Last Name: " + appUser.getLastName() +
                " Tel.: " + appUser.getTelephone() +
                " Email: " + appUser.getEmail());

        appUserValidator.validate(appUser, result);

        if (result.getErrorCount() == 0  && reCaptchaService.verify(request.getParameter("g-recaptcha-response")))  {
            if (appUser.getId() == 0)
                appUserService.addAppUser(appUser);
            else
                appUserService.editAppUser(appUser);


            emailService.sendMail(appUser.getEmail(),"Hello in the app!", "Account created - confirmation email");
            return "redirect:appUsers";
        }

        model.addAttribute("appUserList", appUserService.listAppUser());
        return "appUsers";
    }

    @RequestMapping(value = "/delete/{appUserId}")
    public String deleteUser(@PathVariable("appUserId") Long appUserId) {
        appUserService.removeAppUser(appUserId);

        return "redirect:/appUsers";
    }
}
