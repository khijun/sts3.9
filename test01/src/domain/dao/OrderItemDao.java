package domain.dao;

import lombok.Getter;

@Getter
public class OrderItemDao {
	private Long id;
	private ItemDao item;
	private OrderDao order;
	private int orderPrice;
	private int count;
	
	public OrderItemDao(Long id, ItemDao item, OrderDao order, int orderPrice, int count) {
		super();
		this.id = id;
		this.item = item;
		this.order = order;
		this.orderPrice = orderPrice;
		this.count = count;
	}
	
}
