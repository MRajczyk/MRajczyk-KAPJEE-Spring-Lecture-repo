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
import pl.dmcs.service.AppUserService;
import pl.dmcs.validator.AppUserValidator;

@Controller
public class AppUserController {

    private final AppUserService appUserService;
    private final AppUserValidator appUserValidator = new AppUserValidator();

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @RequestMapping(value = "/appUsers")
    public String showAppUsers(Model model, HttpServletRequest request) {
        int appUserId = ServletRequestUtils.getIntParameter(request, "appUserId", -1);
        if (appUserId > 0) {
            model.addAttribute("appUser", appUserService.getAppUser(appUserId));
        }
        else {
            model.addAttribute("appUser", new AppUser());
        }
        model.addAttribute("appUserList", appUserService.listAppUser());

        return "appUsers";
    }

    @RequestMapping(value = "/addAppUser", method = RequestMethod.POST)
    public String addAppUser(@Valid @ModelAttribute("appUser") AppUser appUser, BindingResult result, Model model) {
        System.out.println("First Name: " + appUser.getFirstName() +
                " Last Name: " + appUser.getLastName() +
                " Tel.: " + appUser.getTelephone() +
                " Email: " + appUser.getEmail());

        appUserValidator.validate(appUser, result);

        if (result.getErrorCount() == 0) {
            if (appUser.getId() == 0)
                appUserService.addAppUser(appUser);
            else
                appUserService.editAppUser(appUser);

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
