package eunchan;

import java.util.*;

public class EnglishDic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Map<String, String> st = new HashMap<String, String>();
		
		st.put("map", "지도");
		st.put("java", "자바");
		st.put("school", "학교");
		
		Scanner sc = new Scanner(System.in);
		do {
			System.out.print("영어 단어를 입력하시오:");
			String key = sc.next();
			if(key.contentEquals("quit")) break;
			System.out.println("단어의 의미는 " + st.get(key));
			
		} while(true);
		
	}

}
