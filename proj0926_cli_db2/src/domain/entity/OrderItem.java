package domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class OrderItem {
	private long id;
	private long itemId;
	private long orderId;
	private int orderprice;
	private int count;
}
