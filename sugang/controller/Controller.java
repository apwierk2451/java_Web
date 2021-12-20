 package sugang.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sugang.command.CommandAction;
import sugang.command._01_ManagerMain;
import sugang.command._02_ManagerLogin;
import sugang.command._03_ManagerLoginPro;
import sugang.command._04_ManagerLogout;
import sugang.command._05_SubjectList;
import sugang.command._06_SubjectRegister;
import sugang.command._07_SubjectRegisterPro;
import sugang.command._08_SubjectUpdate;
import sugang.command._09_SubjectUpdatePro;
import sugang.command._10_SubjectDeletePro;
import sugang.command._11_ShopMain;
import sugang.command._12_Login;
import sugang.command._13_LoginPro;
import sugang.command._14_Logout;
import sugang.command._15_Modify;
import sugang.command._16_ModifyForm;
import sugang.command._17_ModifyPro;
//import sugang.command._18_Register;
//import sugang.command._19_RegisterPro;
//import sugang.command._20_Confirm;
import sugang.command._21_DeletePro;
import sugang.command._22_ShopList;
import sugang.command._23_SubjectContent;
import sugang.command._34_InsertCart;
import sugang.command._35_CartList;
import sugang.command._36_CartUpdateForm;
import sugang.command._37_CartUpdatePro;
import sugang.command._38_DeleteCart;
//import sugang.command._42_OrderList;

@WebServlet(urlPatterns = {"*.do"})	
public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Map<String, Object> commandMap = new HashMap<String, Object>();  
	
	public void init() throws ServletException {
		//==========================================================================
		commandMap.put("/mg/managerMain.do", new _01_ManagerMain());
		commandMap.put("/mg/managerLogin.do", new _02_ManagerLogin());
		commandMap.put("/mg/managerLoginPro.do", new _03_ManagerLoginPro());
		commandMap.put("/mg/managerLogout.do", new _04_ManagerLogout());
		commandMap.put("/mg/subjectList.do", new _05_SubjectList());
		commandMap.put("/mg/subjectRegister.do", new _06_SubjectRegister());
		commandMap.put("/mg/subjectRegisterPro.do", new _07_SubjectRegisterPro());
		commandMap.put("/mg/subjectUpdate.do", new _08_SubjectUpdate());
		commandMap.put("/mg/subjectUpdatePro.do", new _09_SubjectUpdatePro());
		commandMap.put("/mg/subjectDeletePro.do", new _10_SubjectDeletePro());
		//==========================================================================
		commandMap.put("/index.do", new _11_ShopMain());
		commandMap.put("/login.do", new _12_Login());
		commandMap.put("/loginPro.do", new _13_LoginPro());
		commandMap.put("/logout.do", new _14_Logout());
		commandMap.put("/modify.do", new _15_Modify());
		commandMap.put("/modifyForm.do", new _16_ModifyForm());
		commandMap.put("/modifyPro.do", new _17_ModifyPro());
//		commandMap.put("/register.do", new _18_Register());
//		commandMap.put("/registerPro.do", new _19_RegisterPro());
//		commandMap.put("/confirm.do", new _20_Confirm());
		commandMap.put("/deletePro.do", new _21_DeletePro());
		commandMap.put("/list.do", new _22_ShopList());
		commandMap.put("/subjectContent.do", new _23_SubjectContent());
		//==========================================================================
		commandMap.put("/insertCart.do", new _34_InsertCart());
		commandMap.put("/cartList.do", new _35_CartList());
		commandMap.put("/cartUpdateForm.do", new _36_CartUpdateForm());
		commandMap.put("/cartUpdatePro.do", new _37_CartUpdatePro());
		commandMap.put("/deleteCart.do", new _38_DeleteCart());
		//==========================================================================
//		commandMap.put("/mg/orderList.do", new _42_OrderList());
	}	
	protected void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String view = null;
		CommandAction com=null;
		try {
			String command = request.getRequestURI();
	        if(command.indexOf(request.getContextPath()) == 0) 
	           command = command.substring(request.getContextPath().length());
	        System.out.println(command);
	        com = (CommandAction)commandMap.get(command);  
	        System.out.println(com);
	        
	        view = com.requestPro(request, response);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		request.setAttribute("cont",view);
	    RequestDispatcher dispatcher = 
	       request.getRequestDispatcher("/00_index.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		requestPro(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		requestPro(request, response);
	}
}
