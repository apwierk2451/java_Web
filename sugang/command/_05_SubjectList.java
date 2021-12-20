package sugang.command;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sugang.bean.ManagerBean;
import sugang.bean.ManagerDAO;

public class _05_SubjectList implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		List<ManagerBean> subjectList = null;
		String subject_kind = request.getParameter("subject_kind");
		int count = 0;
		
		//DB연동 - 전체 상품의 수를 얻어냄
		ManagerDAO subjectProcess = ManagerDAO.getInstance();
        count = subjectProcess.getSubjectCount(); 
        
        if (count > 0){//상품이 있으면 수행
        	//상품전체를 테이블에서 얻어내서 subjectList에 저장
        	subjectList = subjectProcess.getSubjects(subject_kind);
        	//subjectList를 뷰에서 사용할 수 있도록 request속성에 저장
        	request.setAttribute("subjectList", subjectList);
        }
       
        //뷰에서 사용할 속성
        request.setAttribute("count", new Integer(count));
        request.setAttribute("subject_kind", subject_kind);
        request.setAttribute("type", new Integer(0));
       
		return "/05_subjectList.jsp";
	}

}
