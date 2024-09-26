package domain.entity;

import domain.value.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Delivery {
	private Long id;
	private Order order;
	private Address address;

}
