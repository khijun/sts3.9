package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangePasswordService {
	@Autowired
	private MemberDao dao;
	
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = dao.selectByEmail(email);
		if(member==null)
			throw new MemberNotFoundException();
		member.changePassword(oldPwd, newPwd);
		dao.update(member);
	}
	
}
