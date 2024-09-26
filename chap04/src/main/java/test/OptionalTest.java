package test;

import java.util.Optional;

public class OptionalTest {
	public static void main(String[] args) {
		String value = "Hello Optional!";
		Optional<String> optionalValue = Optional.ofNullable(value);
		
		if(optionalValue.isPresent()) {
			System.out.println("Value is present: " + optionalValue.get());
		}else {
			System.out.println("Value is not present.");
		}
	}
}
