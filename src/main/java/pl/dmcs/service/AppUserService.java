package pl.dmcs.service;

import pl.dmcs.domain.AppUser;

import java.util.List;

public interface AppUserService {

    public void addAppUser(AppUser appUser);
    public void editAppUser(AppUser appUser);
    public List<AppUser> listAppUser();
    public void removeAppUser(long id);
    public AppUser getAppUser(long id);
    AppUser findByLogin(String login);
}
