package domain.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class OrderItemView {
	private String memberName;
	private String city;
	private String street;
	private String zipcode;
	private String itemName;
	private int count;
	private int price;
	private int totalprice;
	private LocalDateTime orderDate;
}
