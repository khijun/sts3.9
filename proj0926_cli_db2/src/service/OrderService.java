package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dbquery.ItemDao;
import dbquery.MemberDao;
import dbquery.OrderItemDao;
import dbquery.OrderItemViewDao;
import dbquery.OrdersDao;
import domain.entity.Item;
import domain.entity.OrderItem;
import domain.entity.Orders;
import exception.CantParseStringToNumberException;

@Component
public class OrderService {
	
	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private OrderItemViewDao oiViewDao;
	@Autowired
	private MemberDao memberDao;
	
	public void orders(String[] arg) { // 주문 한 번에 추가
		System.out.println("상품 배송지 입력 완료.");
		
		long orderId = ordersDao.addOrders(new Orders(-1, Long.parseLong(arg[1]), arg[2], arg[3], arg[4], null));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.println("담을 상품의 아이디와 개수를 입력하세요. 종료를 원하시면 end을 입력하세요.");
			try {
				String command = reader.readLine();
				if(command.equalsIgnoreCase("end")) {
					System.out.println("상품 담기 완료");
					break;
				}
				String[] input = command.split(" ");
				
				long itemId = longParser(input[0]);
				int count = intParser(input[1]);
				
				itemDao.isExist(itemId);
				
				orderItemDao.addOrderItem(new OrderItem(0, itemId, orderId, itemDao.getById(itemId).getPrice() * count, count));
				System.out.println("상품 추가 완료");
			}catch(ClassCastException ce) {
				System.out.println("숫자가 아닙니다. 종료를 원하시면 end을 입력하세요.");
			}catch(IOException ie) {
				System.out.println("입력중 오류가 발생했습니다.");
			}
		}
		System.out.println("주문이 완료되었습니다!");
	}
	
	public void addOrders(String[] arg) { // 주문추가 1. 멤버아이디 2. 도시, 3. 길, 4. 집코드, 5. 날짜(자동이라 널)
		long memberId = longParser(arg[1]);
		memberDao.isExist(memberId);
		ordersDao.addOrders(new Orders(0, memberId, arg[2], arg[3], arg[4], null));
		System.out.println("배송지 생성 완료");
	}
	
	public void addOrderItem(String[] arg) { // 주문에 상품 추가 1. 상품번호, 2. 주문번호, 3. orderprice, 4. count
		long orderId = longParser(arg[1]);
		long itemId = longParser(arg[2]);
		int count = Integer.parseInt(arg[3]);	// orderprice 공백자리 채우기
		
		ordersDao.isExist(orderId);
		itemDao.isExist(itemId);
		
		Item item = itemDao.getById(itemId);	// orderprice의 직접입력을 피하기위해 있음.

		orderItemDao.addOrderItem(new OrderItem(-1, itemId, orderId, item.getPrice() * count, count));
		System.out.println("상품 주문 완료!");
	}
	
	public void getOIViewByMember(String[] arg) { // 주문목록 얻기
		int id = intParser(arg[1]);
		oiViewDao.getOIViewById(id).forEach(o->System.out.println(o));
	}
	
	public void delOrders(String arg[]) { // 주문 취소(삭제)
		long id = longParser(arg[1]);
		orderItemDao.delByOrderId(id);
		ordersDao.delOne(id);
		System.out.println("주문 취소!");
	}
	
	public void delOrderItem(String arg[]) { // 주문한 상품 취소(삭제)
		long id = longParser(arg[1]);
		orderItemDao.delOne(id);
		System.out.println("상품 삭제 완료!");
	}
	
	public void printAllOiv() {
		oiViewDao.getAllOIView().forEach(oiv -> System.out.println(oiv));
		System.out.println("주문 테이블 조회 완료");
	}
	
	public void printAllOrder() {
		ordersDao.getAll().forEach(o -> System.out.println(o));
		System.out.println("주문 테이블 조회 완료");
	}
	
	public void printAllOrderItem() {
		orderItemDao.getAll().forEach(oi -> System.out.println(oi));
		System.out.println("주문 조회 완료");
	}
	
	private int intParser(String str) {
		try {
			return Integer.parseInt(str);
		}catch(Exception e) {
			throw new CantParseStringToNumberException();
		}
	}
	private long longParser(String str) {
		try {
			return Long.parseLong(str);
		}catch(Exception e) {
			throw new CantParseStringToNumberException();
		}
	}
}
