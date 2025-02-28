package domain.entity;

import java.util.ArrayList;
import java.util.List;

import domain.value.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Member {
	private Long id;
	private String name;
	private int address;
}
