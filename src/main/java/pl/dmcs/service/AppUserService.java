package pl.dmcs.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import pl.dmcs.domain.AppUser;

import java.util.List;

public interface AppUserService {

    @Secured("ROLE_ADMIN")
    void addAppUser(AppUser appUser);

    @PreAuthorize("hasRole('ROLE_ADMIN') OR (#appUser.login == principal.username)")
    void editAppUser(AppUser appUser);

    List<AppUser> listAppUser();

    @Secured("ROLE_ADMIN")
    void removeAppUser (long id);

    AppUser getAppUser(long id);

    AppUser findByLogin(String login);

    void activateInactiveAppUsers();
}
