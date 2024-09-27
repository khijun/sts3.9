package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import dbquery.ItemDao;
import dbquery.MemberDao;
import dbquery.OrderItemDao;
import dbquery.OrderItemViewDao;
import dbquery.OrdersDao;
import domain.entity.Item;
import domain.entity.Member;
import domain.entity.OrderItem;
import domain.entity.Orders;

public class Main {

	private static AnnotationConfigApplicationContext ctx = null;
	private static MemberDao memberDao;
	private static ItemDao itemDao;
	private static OrderItemDao orderItemDao;
	private static OrdersDao ordersDao;
	private static OrderItemViewDao oiViewDao;
	
	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);

		itemDao = ctx.getBean(ItemDao.class);
		memberDao = ctx.getBean(MemberDao.class);
		orderItemDao = ctx.getBean(OrderItemDao.class);
		ordersDao = ctx.getBean(OrdersDao.class);
		oiViewDao = ctx.getBean(OrderItemViewDao.class);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.println("명령어를 입력하세요:");
			String command = reader.readLine();
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			
			if(command.startsWith("newOrder ")) {
				processAddOrders(command.split(" "));
				continue;
			}else if(command.startsWith("newOrderItem ")) {	// 멤버번호 상품번호 갯수
				processAddOrderItem(command.split(" "));
				continue;
			}else if(command.startsWith("cancleOrder " )) {	// 멤버번호 상품번호 갯수
				processDelOrders(command.split(" "));
				continue;
			}else if(command.startsWith("cancleOrderItem ")) {	// 멤버번호 상품번호 갯수
				processDelOrderItem(command.split(" "));
				continue;
			}else if(command.startsWith("info ")) {	// 멤버번호 상품번호 갯수
				processGetOIViewByMember(command.split(" "));
				continue;
			}else if(command.startsWith("newItem ")) {
				processAddItem(command.split(" "));
				continue;
			}else if(command.startsWith("newMember ")) {
				processAddMember(command.split(" "));
				continue;
			}else if(command.startsWith("itemList")) {
				processPrintAllItem();
				continue;
			}else if(command.startsWith("memberList")) {
				processPrintAllMember();
				continue;
			}else if(command.startsWith("oivList")) {
				processPrintAllOiv();
				continue;
			}else if(command.startsWith("orderList")) {
				processPrintAllOrder();
				continue;
			}else if(command.startsWith("order ")) {	// 상품 한 번에 주문
				processOrders(command.split(" "));
				continue;
			}
			printHelp();
		}
		
		ctx.close();
	}
	
	public static void processOrders(String[] arg) { // 주문 한 번에 추가
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
				int itemId = Integer.parseInt(input[0]);
				int count = Integer.parseInt(input[1]);
				orderItemDao.addOrderItem(new OrderItem(0, itemId, orderId, itemDao.getById(itemId).getPrice() * count, count));
				System.out.println("상품 추가 완료");
			}catch(Exception e) {
				System.out.println("숫자가 아닙니다. 종료를 원하시면 end을 입력하세요.");
			}
		}
		System.out.println("주문이 완료되었습니다!");
	}
	
	public static void processAddOrders(String[] arg) { // 주문추가 1. 멤버아이디 2. 도시, 3. 길, 4. 집코드, 5. 날짜(자동이라 널)
		ordersDao.addOrders(new Orders(0, Long.parseLong(arg[1]), arg[2], arg[3], arg[4], null));
		System.out.println("배송지 생성 완료");
	}
	
	public static void processAddOrderItem(String[] arg) { // 주문에 상품 추가 1. 상품번호, 2. 주문번호, 3. orderprice, 4. count
		
		long itemId = Long.parseLong(arg[1]);
		long orderId = Long.parseLong(arg[2]);
		int count = Integer.parseInt(arg[3]);	// orderprice 공백자리 채우기
		
		Item item = itemDao.getById(itemId);	// orderprice의 직접입력을 피하기위해 있음. 굳이 직접 넣어야하나

		orderItemDao.addOrderItem(new OrderItem(-1, itemId, orderId, item.getPrice() * count, count));
		System.out.println("상품 주문 완료!");
	}
	
	public static void processGetOIViewByMember(String[] arg) { // 주문목록 얻기
		oiViewDao.getOIViewById(Integer.parseInt(arg[1])).forEach(o->System.out.println(o));
	}
	
	public static void processDelOrders(String arg[]) { // 주문 취소(삭제)
		long id = Long.parseLong(arg[1]);	// 오더 아이디
		orderItemDao.delByOrderId(id);
		ordersDao.delOne(id);
		System.out.println("주문 취소!");
	}
	
	public static void processDelOrderItem(String arg[]) { // 주문한 상품 취소(삭제)
		orderItemDao.delOne(Long.parseLong(arg[1]));
		System.out.println("상품 삭제 완료!");
	}
	
	public static void processPrintAllOiv() {
		oiViewDao.getAllOIView().forEach(oiv -> System.out.println(oiv));
		System.out.println("주문 테이블 조회 완료");
	}
	
	public static void processPrintAllOrder() {
		ordersDao.getAll().forEach(o -> System.out.println(o));
		System.out.println("주문 테이블 조회 완료");
	}
	
	public static void processAddItem(String[] arg) {	// 상품 추가
		itemDao.addItem(new Item(-1, arg[1], Integer.parseInt(arg[2]), Integer.parseInt(arg[3])));
		System.out.println("상품 추가 완료!");
	}
	
	public static void processPrintAllItem() {
		itemDao.getAllItem().forEach(i->System.out.println(i));
		System.out.println("상품 조회 완료");
	}
	
	public static void processAddMember(String[] arg) {	// 멤버 등록		
		memberDao.addMember(new Member(-1, arg[1], arg[2], arg[3], arg[4]));
		System.out.println("멤버 등록 완료!");
	}
	
	public static void processPrintAllMember() {
		memberDao.getAllMember().forEach(m->System.out.println(m));
		System.out.println("멤버 조회 완료");
	}
	
	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법:");
		System.out.println("info 멤버아이디");
		System.out.println("order 멤버아이디 배송도시 배송길 배송집코드");		
		System.out.println("newOrder 멤버아이디 배송도시 배송길 배송집코드");
		System.out.println("newOrderItem 주문아이디 아이템아이디 구매개수");
		System.out.println("newMember 이름 도시 길 집코드");
		System.out.println("newItem 이름 가격 개수");
		System.out.println("memberList");
		System.out.println("itemList");
		System.out.println("oiList");
		System.out.println("orderList");
		System.out.println();
	}
	
}
