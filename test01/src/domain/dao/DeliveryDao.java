package domain.dao;

import domain.value.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeliveryDao {
	private Long id;
	private OrderDao order;
	private Address address;

}
