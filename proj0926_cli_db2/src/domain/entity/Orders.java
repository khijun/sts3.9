package domain.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Orders {
	private long id;
	private long memberId;
	private String city;
	private String street;
	private String zipcode;
	private LocalDateTime orderDate;
}
