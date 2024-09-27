package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import exception.CantParseStringToNumberException;
import exception.ColumnNotFoundException;
import service.ItemService;
import service.MemberService;
import service.OrderService;

public class ServiceTest {
	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

		OrderService os = ctx.getBean(OrderService.class);
		MemberService ms = ctx.getBean(MemberService.class);
		ItemService is = ctx.getBean(ItemService.class);

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("명령어를 입력하세요:");
			String command = reader.readLine();
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			try {
				if (command.startsWith("info ")) { // 멤버번호 상품번호 갯수
					os.getOIViewByMember(command.split(" "));
					continue;
				} else if (command.startsWith("order ")) { // 상품 한 번에 주문
					os.orders(command.split(" "));
					continue;
				} else if (command.startsWith("newOrder ")) {
					os.addOrders(command.split(" "));
					continue;
				} else if (command.startsWith("newOrderItem ")) { // 멤버번호 상품번호 갯수
					os.addOrderItem(command.split(" "));
					continue;
				} else if (command.startsWith("newMember ")) {
					ms.addMember(command.split(" "));
					continue;
				} else if (command.startsWith("newItem ")) {
					is.addItem(command.split(" "));
					continue;
				} else if (command.startsWith("cancleOrder ")) { // 멤버번호 상품번호 갯수
					os.delOrders(command.split(" "));
					continue;
				} else if (command.startsWith("cancleOrderItem ")) { // 멤버번호 상품번호 갯수
					os.delOrderItem(command.split(" "));
					continue;
				} else if (command.startsWith("memberList")) {
					ms.printAllMember();
					continue;
				} else if (command.startsWith("itemList")) {
					is.printAllItem();
					continue;
				} else if (command.startsWith("oivList")) {
					os.printAllOiv();
					continue;
				} else if (command.startsWith("oiList")) {
					os.printAllOrderItem();
					continue;
				} else if (command.startsWith("orderList")) {
					os.printAllOrder();
					continue;
				}
				printHelp();
			}catch(CantParseStringToNumberException e) {
				System.out.println("숫자가 아닙니다");
			}catch(ColumnNotFoundException e) {
				System.out.println("입력한 아이디의 해당하는 컬럼이 존재하지 않습니다.");
			}
		}
		ctx.close();
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
		System.out.println("cancleOrder 주문번호");
		System.out.println("cancleOrderItem 주문아이템번호");
		System.out.println("memberList");
		System.out.println("itemList");
		System.out.println("oivList");
		System.out.println("oiList");
		System.out.println("orderList");
		System.out.println();
	}
}
