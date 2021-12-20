package sugang.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sugang.bean.CartBean;
import sugang.bean.CartDAO;

public class _34_InsertCart implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		System.out.println(request.getParameter("buy_count"));
		//장바구니에 추가할 정보를 파라미터에서 받아냄
		byte buy_count = Byte.parseByte(request.getParameter("buy_count"));
		int subject_id = Integer.parseInt(request.getParameter("subject_id"));
		String subject_title = request.getParameter("subject_title");
		String buyer = request.getParameter("buyer");
		
		//장바구니에 추가하기 위한 정보구성
		CartBean cart = new CartBean();
		cart.setSubject_id(subject_id);
		cart.setSubject_title(subject_title);
		cart.setBuy_count(buy_count);
		cart.setBuyer(buyer);
		
		//장바구니에 추가
		CartDAO subjectProcess = CartDAO.getInstance();
		subjectProcess.insertCart(cart);
		
		return "/34_insertCart.jsp";
	}
}