package domain.repository;

import java.util.ArrayList;
import java.util.List;

import domain.entity.Delivery;
import domain.entity.Order;
import domain.value.Address;

public class DeliveryRepository {
	private static Long nextId = 0L;
	private List<Delivery> deliverys = new ArrayList<Delivery>();
	
	public Delivery newDelivery(Order order, Address address) {
		Delivery delivery = new Delivery(++nextId, order, address);
		deliverys.add(delivery);
		return delivery;
	}
	
	public Delivery findById(Long id) {
		for(Delivery d : deliverys) {
			if(d.getId() == id)
				return d;
		}
		return null;
	}
}
