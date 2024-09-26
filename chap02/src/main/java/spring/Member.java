package spring;

import java.time.LocalDate;

public class Member {
	private Long id;
	private String email;
	private String password;
	private String name;
	private LocalDate registerDateTime;
	
	public Member(Long id, String email, String password, String name, LocalDate registerDateTime) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.registerDateTime = registerDateTime;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public LocalDate getRegisterDateTime() {
		return registerDateTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void changePassword(String oldPassword, String newPassword) {
		if(!password.equals(oldPassword))
			throw new WrongIdPasswordException();
		this.password = newPassword;
	}
	
}
