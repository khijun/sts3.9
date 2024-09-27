package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dbquery.ItemDao;
import domain.entity.Item;
import exception.CantParseStringToNumberException;

@Component
public class ItemService {
	@Autowired
	private ItemDao itemDao;
	
	public void addItem(String[] arg) {	// 상품 추가
		int price = intParser(arg[2]);
		int stockquantity = intParser(arg[3]);
		itemDao.addItem(new Item(-1, arg[1], price, stockquantity));
		System.out.println("상품 추가 완료!");
	}
	
	public void printAllItem() {
		itemDao.getAllItem().forEach(i->System.out.println(i));
		System.out.println("상품 조회 완료");
	}
	
	private int intParser(String str) {
		try {
			return Integer.parseInt(str);
		}catch(Exception e) {
			throw new CantParseStringToNumberException();
		}
	}
////	private long longParser(String str) {
//		try {
//			return Long.parseLong(str);
//		}catch(Exception e) {
//			throw new CantParseStringToNumberException();
//		}
//	}
}
