package pl.dmcs.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	private String city;
	private String street;
	private String number;
	private String postalCode;
	
//	@OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
//    @JoinTable(name="appuser_address",joinColumns = @JoinColumn(name="appuser_id"),inverseJoinColumns = @JoinColumn(name="address_id"))
	@OneToMany(mappedBy = "address")
	private List<AppUser> appUserList;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public List<AppUser> getAppUserList() {
		return appUserList;
	}
	public void setAppUserList(List<AppUser> appUserList) {
		this.appUserList = appUserList;
	}
}
