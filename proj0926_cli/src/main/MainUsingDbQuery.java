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
import domain.entity.Item;
import domain.entity.Member;
import domain.entity.OrderItem;

public class MainUsingDbQuery {

	private static AnnotationConfigApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
//		ordersDao.getOrdersByMember(1).forEach(o->System.out.println(o));
//		Orders orders = new Orders(0, 1, "city", "streett", "zipcodee", null);
//		ordersDao.addOrders(orders);
//		ordersDao.getOrdersByMember(1).forEach(o->System.out.println(o));

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
			}else if(command.startsWith("oiList")) {
				processPrintAllOi();
				continue;
			}
			printHelp();
		}
		
		ctx.close();
	}
	
	public static void processAddOrders(String[] arg) { // 주문추가
		OrderItemDao oiDao = ctx.getBean(OrderItemDao.class);
		oiDao.addOrderItem(new OrderItem(0, Long.parseLong(arg[1]), Long.parseLong(arg[2]), Integer.parseInt(arg[3]), null));
		// 날짜는 mysql에서 넣으므로 null
		// 멤버 아이디, 아이템 아이디, 갯수
		System.out.println("상품 주문 완료!");
	}
	
	public static void processGetOIViewByMember(String[] arg) { // 주문목록 얻기
		OrderItemViewDao oiViewDao = ctx.getBean(OrderItemViewDao.class);
		oiViewDao.getOIViewById(Integer.parseInt(arg[1])).forEach(o->System.out.println(o));
	}
	
	public boolean processSetOrders() { // 주문 수정
		return false;
	}
	
	public boolean processDelOrders() { // 주문 취소(삭제)
		return false;
	}
	
	public static void processAddItem(String[] arg) {	// 상품 추가
		ItemDao itemDao = ctx.getBean(ItemDao.class);
		itemDao.addItem(new Item(0, arg[1], Integer.parseInt(arg[2]), Integer.parseInt(arg[3])));
		System.out.println("상품 추가 완료!");
	}
	
	public static void processPrintAllItem() {
		ItemDao itemDao = ctx.getBean(ItemDao.class);
		itemDao.getAllItem().forEach(i->System.out.println(i));
		System.out.println("상품 조회 완료");
	}
	
	public static void processAddMember(String[] arg) {	// 멤버 등록		
		MemberDao memberDao = ctx.getBean(MemberDao.class);
		memberDao.addMember(new Member(0, arg[1], arg[2], arg[3], arg[4]));
		System.out.println("멤버 등록 완료!");
	}
	
	public static void processPrintAllMember() {
		MemberDao memberDao = ctx.getBean(MemberDao.class);
		memberDao.getAllMember().forEach(m->System.out.println(m));
		System.out.println("멤버 조회 완료");
	}
	
	public static void processPrintAllOi() {
		OrderItemViewDao oiViewDao = ctx.getBean(OrderItemViewDao.class);
		oiViewDao.getAllOIView().forEach(oiv -> System.out.println(oiv));
		System.out.println("주문 테이블 조회 완료");
	}
	
	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법:");
		System.out.println("info 멤버아이디");
		System.out.println("newOrder 멤버아이디 아이템아이디 구매개수");
		System.out.println("newMember 이름 도시 길 집코드");
		System.out.println("newItem 이름 가격 개수");
		System.out.println("memberList");
		System.out.println("itemList");
		System.out.println("oiList");
		System.out.println();
	}
	
}
