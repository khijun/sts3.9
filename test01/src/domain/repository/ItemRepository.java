package domain.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.entity.Item;

public class ItemRepository {
	private static long nextId = 0;
	private Map<Long, Item> items = new HashMap<Long, Item>();
	
	public Map<Long, Item> addItem(String name, int price, int stockQuantity){
		Item item = new Item(++nextId, name, price, stockQuantity);
		items.put(nextId, item);
		return items;
	}
	
	public Item findById(Long id) {
		return items.get(id);
	}
	
	public Item stockUpdate(long id, int count) {
		Item item = items.get(id);
		item.setStockQuantity(item.getStockQuantity()+count);
		return item;
	}
	
	public void printItems() {
		for(long i=1;i<items.size()+1;i++) {
			System.out.println(items.get(i));
		}
	}
}
