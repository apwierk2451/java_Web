package sugang.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sugang.bean.ManagerDAO;


public class _10_SubjectDeletePro implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		int subject_id = Integer.parseInt(request.getParameter("subject_id"));
		String subject_kind = request.getParameter("subject_kind");
		
		//DB연동 - subject_id에 해당하는 상품을 삭제
		ManagerDAO subjectProcess = ManagerDAO.getInstance();
		subjectProcess.deleteSubject(subject_id); 
		
		request.setAttribute("subject_kind", subject_kind);
		return "/10_subjectDeletePro.jsp";
	}
}