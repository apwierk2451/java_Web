package sugang.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sugang.bean.ManagerBean;
import sugang.bean.ManagerDAO;


public class _22_ShopList implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		List<ManagerBean> subjectList = null;
		int count = 0;
		String subject_kind = request.getParameter("subject_kind");
		
		System.out.println("subject_kind :  " + subject_kind);
		
		ManagerDAO subjectProcess = ManagerDAO.getInstance();
		
		//kind값이 all이면 전체 상품의 수를 얻어냄
		if(subject_kind.equals("all"))
            count = subjectProcess.getSubjectCount(); 
		else//all이 아니면 해당 카테고리의 상품수를 얻어냄
			count = subjectProcess.getSubjectCount(subject_kind);
		
        if (count >= 0){//상품이 있으면 수행
        	//상품목록을 얻어냄
        	subjectList = subjectProcess.getSubjects(subject_kind);
        	request.setAttribute("subjectList", subjectList);
        }
        
        //해당 뷰에서 사용할 속성
        request.setAttribute("count", new Integer(count));
        request.setAttribute("subject_kind", subject_kind);
        request.setAttribute("type", new Integer(1));
		return "/22_shopList.jsp";
	}

}
