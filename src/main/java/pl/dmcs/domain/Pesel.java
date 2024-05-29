package pl.dmcs.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Pesel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	String PESEL;

	@JsonBackReference
	@OneToOne(mappedBy="pesel")
	private AppUser appUser;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPESEL() {
		return PESEL;
	}

	public void setPESEL(String PESEL) {
		this.PESEL = PESEL;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
}

