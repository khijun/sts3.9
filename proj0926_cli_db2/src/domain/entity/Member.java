package domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Member {
	private long id;
	private String name;
	private String city;
	private String street;
	private String zipcode;
}
