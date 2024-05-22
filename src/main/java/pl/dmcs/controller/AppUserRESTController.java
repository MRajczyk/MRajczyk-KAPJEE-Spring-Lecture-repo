package pl.dmcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.domain.AppUser;
import pl.dmcs.repository.AppUserRepository;

import java.util.List;

@RestController
@RequestMapping("appUserRest")
public class AppUserRESTController {

    private AppUserRepository appUserRepository;

    @Autowired
    public AppUserRESTController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }
	
    @RequestMapping(value = "/{login}", method = RequestMethod.GET, produces = "application/json")
    public AppUser getAppUserInJSON(@PathVariable String login) {
   	 return appUserRepository.findByLogin(login);
    }
 
    @RequestMapping(value = "/{login}.xml", method = RequestMethod.GET, produces = "application/xml")
    public AppUser getAppUserInXML(@PathVariable String login) {
 
   	 return appUserRepository.findByLogin(login);
    }

    @GetMapping
    public ResponseEntity<List<AppUser>> getAll() {
        return new ResponseEntity<>(appUserRepository.findAll(), HttpStatus.OK);
    }
}

