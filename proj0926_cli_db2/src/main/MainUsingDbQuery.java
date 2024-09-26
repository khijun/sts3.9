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

public class MainUsingDbQuery {

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
		
		int memberId = Integer.parseInt(arg[1]);
		int itemId = Integer.parseInt(arg[2]);
		int count = Integer.parseInt(arg[3]);
		
		Member member = memberDao.getById(memberId);
		Item item = itemDao.getById(itemId);
		
		long orderId = ordersDao.addOrders(new Orders(0, memberId, member.getCity(), member.getStreet(), member.getZipcode(), null));
		orderItemDao.addOrderItem(new OrderItem(0, itemId, orderId, item.getPrice() * count, count));
		System.out.println("상품 주문 완료!");
	}
	
	public static void processGetOIViewByMember(String[] arg) { // 주문목록 얻기
		oiViewDao.getOIViewById(Integer.parseInt(arg[1])).forEach(o->System.out.println(o));
	}
	
	public boolean processSetOrders() { // 주문 수정
		return false;
	}
	
	public boolean processDelOrders() { // 주문 취소(삭제)
		return false;
	}
	
	public static void processAddItem(String[] arg) {	// 상품 추가
		itemDao.addItem(new Item(0, arg[1], Integer.parseInt(arg[2]), Integer.parseInt(arg[3])));
		System.out.println("상품 추가 완료!");
	}
	
	public static void processPrintAllItem() {
		itemDao.getAllItem().forEach(i->System.out.println(i));
		System.out.println("상품 조회 완료");
	}
	
	public static void processAddMember(String[] arg) {	// 멤버 등록		
		memberDao.addMember(new Member(0, arg[1], arg[2], arg[3], arg[4]));
		System.out.println("멤버 등록 완료!");
	}
	
	public static void processPrintAllMember() {
		memberDao.getAllMember().forEach(m->System.out.println(m));
		System.out.println("멤버 조회 완료");
	}
	
	public static void processPrintAllOi() {
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
