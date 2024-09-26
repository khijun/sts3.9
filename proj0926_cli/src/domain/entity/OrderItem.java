package domain.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderItem {
	private long id;
	private long memberId;
	private long itemId;
	private int count;
	private LocalDateTime orderDate;
}
