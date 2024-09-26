package domain.repository;

import java.util.HashMap;
import java.util.Map;

import domain.value.Address;

public class AddressRepository {
	private static Long nextId = 0L;
	private Map<Long, Address> addresses = new HashMap<Long, Address>();
	
	public Address newAddress(String city, String street, String zipcode) {
		Address address = new Address(city, street, zipcode);
		return address;
	}
}
