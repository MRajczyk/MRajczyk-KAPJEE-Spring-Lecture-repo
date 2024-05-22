package pl.dmcs.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;

@Controller
public class ExampleController {

    @RequestMapping(value = "/exampleOne")
    public String exampleOne(Principal principal) {
        System.out.println(principal.getName());
        return "exampleOne";
    }

    @RequestMapping(value = "/exampleTwo")
    public String exampleTwo(Authentication authentication) {
        System.out.println(authentication.getName());
        return "exampleTwo";
    }

    @RequestMapping(value = "/exampleThree")
    public String exampleThree() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            System.out.println(authentication.getName());
        }
        return "exampleThree";
    }
}

