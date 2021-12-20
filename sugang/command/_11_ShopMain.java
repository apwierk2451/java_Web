package sugang.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sugang.bean.ManagerBean;
import sugang.bean.ManagerDAO;

public class _11_ShopMain implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		ManagerBean subjectList[] = null;
		List<ManagerBean[]> subjectLists = new ArrayList<ManagerBean[]>();
		
		ManagerDAO subjectProcess = ManagerDAO.getInstance();//DB연동
		
		//카테고리별 최신의 상품 3개씩 얻어내서 List에 저장
		for(int i=1; i<=3;i++){
			subjectList = subjectProcess.getSubjects(i+"00",3);
			subjectLists.add(subjectList);
		}
		
        //해당 페이지로 보낼 내용 설정
        request.setAttribute("subjectLists", subjectLists);
        //사용자 화면을 의미하는 값을 설정
		request.setAttribute("type", new Integer(1));
		return "/11_shopMain.jsp";
	}
}