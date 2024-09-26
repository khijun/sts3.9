package domain.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.entity.Delivery;
import domain.entity.Item;
import domain.entity.Member;
import domain.entity.Order;
import domain.entity.OrderItem;

public class OrderRepository {
	private static Long nextId = 0L;
	private Map<Long, Order> orders = new HashMap<Long, Order>();
	private OrderItem orderItem;

	public Order orderItem(Member member, Delivery delivery, Item item) {
		Order order = new Order(++nextId, member, delivery, new Date());
//		for(int i = 1;i<items.size()+1;i++) {
//			orderItem = new OrderItem(nextId, items.get(i), order, items.get(i).getPrice() * counts.get(i), counts.get(i));
//			order.addOrderItem(orderItem);
//		}
		orderItem = new OrderItem(nextId, item, order, item.getPrice() * 1, 1);
		orders.put(nextId, order);
		return order;
	}

}
