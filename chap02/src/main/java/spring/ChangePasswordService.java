package spring;

public class ChangePasswordService {
	private MemberDao dao;
	
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = dao.selectByEmail(email);
		if(member==null)
			throw new MemberNotFoundException();
		member.changePassword(oldPwd, newPwd);
		dao.update(member);
	}
	
	public void setMemberDao(MemberDao dao) {
		this.dao = dao;
	}
}
