package sugang.command;

import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import sugang.bean.ManagerBean;
import sugang.bean.ManagerDAO;


public class _09_SubjectUpdatePro implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
        request.setCharacterEncoding("utf-8");//한글 인코딩
		
		String filename ="";
		String realFolder = "";//웹 어플리케이션상의 절대 경로 저장
		String saveFolder = "/subjectImage"; //파일 업로드 폴더 지정
		String encType = "utf-8"; //인코딩타입
		int maxSize = 1*1024*1024;  //최대 업로될 파일크기 1Mb
		
		MultipartRequest imageUp = null;

		//웹 어플리케이션상의 절대 경로를 구함
		ServletContext context = request.getSession().getServletContext();
		realFolder = context.getRealPath(saveFolder);  
       
		try{
			//파일 업로드를 수행하는 MultipartRequest 객체 생성 
			imageUp = new MultipartRequest(request,realFolder,maxSize,
					            encType,new DefaultFileRenamePolicy());
			   
			//<input type="file">인 모든 파라미터를 얻어냄
			Enumeration<?> files = imageUp.getFileNames();
			  
		     while(files.hasMoreElements()){
		       String name = (String)files.nextElement();
		       filename = imageUp.getFilesystemName(name);
		     }
		}catch(Exception e){
		     e.printStackTrace();
		}
		
		ManagerBean subject = new ManagerBean();
		String subject_id = imageUp.getParameter("subject_id");
		String subject_kind = imageUp.getParameter("subject_kind");
		String subject_title = imageUp.getParameter("subject_title");
		String subject_count = imageUp.getParameter("subject_count");
		String professor = imageUp.getParameter("professor");
		String room = imageUp.getParameter("room");
		String subject_content = imageUp.getParameter("subject_content");

		int a = Integer.parseInt(subject_id);
		int b = 0;
		if(!subject_kind.equals("all")) {
		b = Integer.parseInt(subject_kind);
		b /= 100;
		}
		subject.setSubject_id(a);
		subject.setSubject_kind(b + "");
		subject.setSubject_title(subject_title);
		subject.setProfessor(professor);
		subject.setSubject_count(Short.parseShort(subject_count));
		subject.setRoom(room);
		subject.setSubject_content(subject_content);

		//DB연동해서 상품 수정 처리
		ManagerDAO subjectProcess = ManagerDAO.getInstance();
		subjectProcess.updateSubject(subject, subject_id);
		
		request.setAttribute("subject_kind", subject_kind);
		return "/09_subjectUpdatePro.jsp";
	}
}