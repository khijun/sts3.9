package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("infoPrinter")
public class MemberInfoPrinter {

	private MemberDao memDao;	
	private MemberPrinter printer;
	
	public void printMemberInfo(String email) throws WrongIdPasswordException {
		Member member = memDao.selectByEmail(email);
		if(member == null) {
			throw new WrongIdPasswordException();
		}
		printer.print(member);
		System.out.println();
	}
	
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memDao = memberDao;
	}
	
	@Autowired
//	@Qualifier("summeryPrinter")
	@Qualifier("printer")
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
}
