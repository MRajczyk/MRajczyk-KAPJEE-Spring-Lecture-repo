package pl.dmcs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.domain.Address;
import pl.dmcs.repository.AddressRepository;

import java.util.List;

@Service("addressService")
@Transactional
public class AddressServiceImpl implements AddressService{

	private AddressRepository addressRepository;

	@Autowired
	public AddressServiceImpl(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	
	@Transactional
	public void addAddress(Address address) {
		addressRepository.save(address);
	}
	
	@Transactional
	public void editAddress(Address address) {
		addressRepository.save(address);
	}

	@Transactional
	public List<Address> listAddress() {
		return addressRepository.findAll();
	}

	@Transactional
	public void removeAddress(long id) {
		addressRepository.deleteById(id);
	}
	
	@Transactional
	public Address getAddress(long id) {
		return addressRepository.findById(id);
	}
}

