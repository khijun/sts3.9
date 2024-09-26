package domain.entity;

import lombok.Getter;

@Getter
public class OrderItem {
	private Long id;
	private Item item;
	private Order order;
	private int orderPrice;
	private int count;
	
	public OrderItem(Long id, Item item, Order order, int orderPrice, int count) {
		super();
		this.id = id;
		this.item = item;
		this.order = order;
		this.orderPrice = orderPrice;
		this.count = count;
	}
	
}
