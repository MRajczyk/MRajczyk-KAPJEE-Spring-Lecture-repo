package pl.dmcs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.domain.AppUser;
import pl.dmcs.repository.AppUserRepository;
import pl.dmcs.repository.AppUserRoleRepository;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository appUserRepository;
    private AppUserRoleRepository appUserRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, AppUserRoleRepository appUserRoleRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appUserRoleRepository = appUserRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void addAppUser(AppUser appUser) {
        appUser.getAppUserRole().add(appUserRoleRepository.findByRole("ROLE_USER"));
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);
    }

    @Transactional
    public void editAppUser(AppUser appUser) {
        appUser.getAppUserRole().add(appUserRoleRepository.findByRole("ROLE_USER"));
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);
    }

//	private String hashPassword(String password)
//	{
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		return passwordEncoder.encode(password);
//	}

    @Transactional
    public List<AppUser> listAppUser() {
        return appUserRepository.findAll();
    }

    @Transactional
    public void removeAppUser(long id) {
        appUserRepository.deleteById(id);
    }

    @Transactional
    public AppUser getAppUser(long id) {
        return appUserRepository.findById(id);
    }

    @Transactional
    public AppUser findByLogin(String login) {
        return appUserRepository.findByLogin(login);
    }

    @Scheduled(fixedRate = 20 * 1000)
    //@Scheduled(cron = "0 15 10 15 * ?")
    public void activateInactiveAppUsers() {
        String processName = "activating_inactive_users";
        System.out.println("activating_inactive_users: ");
        List<AppUser> appUsers = appUserRepository.findAllByEnabledIsFalse();
        appUsers.forEach(appUser -> {
            appUser.setEnabled(true);
            appUserRepository.saveAndFlush(appUser);
            System.out.println(appUser.getLogin());
        });
    }
}