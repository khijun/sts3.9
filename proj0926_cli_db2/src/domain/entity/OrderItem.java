package domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderItem {
	private long id;
	private long itemId;
	private long orderId;
	private int orderprice;
	private int count;
}
