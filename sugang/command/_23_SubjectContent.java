package sugang.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sugang.bean.ManagerBean;
import sugang.bean.ManagerDAO;

public class _23_SubjectContent implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		
		String subject_kind = request.getParameter("subject_kind");
		int subject_id = Integer.parseInt(request.getParameter("subject_id"));
		
		//book_id에 해당하는 상품을 얻어냄
		ManagerDAO subjectProcess = ManagerDAO.getInstance();
		ManagerBean subject= subjectProcess.getSubject(subject_id);
		
		//book_id에 해당하는 상품의 QnA 수를 얻어냄
		System.out.println(subject_kind);
		request.setAttribute("subject", subject);
		request.setAttribute("subject_id", new Integer(subject_id));
		request.setAttribute("subject_kind", subject_kind);
		request.setAttribute("type", new Integer(1));
		return "/23_subjectContent.jsp";
	}
}