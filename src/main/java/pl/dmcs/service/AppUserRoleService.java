package pl.dmcs.service;

import pl.dmcs.domain.AppUserRole;
import java.util.List;

public interface AppUserRoleService {

    void addAppUserRole(AppUserRole appUserRole);
    List<AppUserRole> listAppUserRole();
    AppUserRole getAppUserRole(long id);

}

