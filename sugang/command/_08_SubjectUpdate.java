package sugang.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sugang.bean.ManagerBean;
import sugang.bean.ManagerDAO;


public class _08_SubjectUpdate implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		int subject_id = Integer.parseInt(request.getParameter("subject_id"));
		String subject_kind = request.getParameter("subject_kind");
		
		//DB연동 subject_id에 해당하는 상품을 얻내서 subject에 저장
		ManagerDAO subjectProcess = ManagerDAO.getInstance();
		ManagerBean subject =  subjectProcess.getSubject(subject_id);
		
		request.setAttribute("subject_id", subject_id);
		request.setAttribute("subject_kind", subject_kind);
        request.setAttribute("subject", subject);
		request.setAttribute("type", new Integer(0));
		return "/08_subjectUpdate.jsp";
	}
}