package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberInfoPrinter {

	@Autowired
	private MemberDao memDao;	
	@Autowired
	private MemberPrinter printer;
	
	public void printMemberInfo(String email) throws WrongIdPasswordException {
		Member member = memDao.selectByEmail(email);
		if(member == null) {
			throw new WrongIdPasswordException();
		}
		printer.print(member);
		System.out.println();
	}
	
	public void setMemberDao(MemberDao memberDao) {
		this.memDao = memberDao;
	}
	
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
}
