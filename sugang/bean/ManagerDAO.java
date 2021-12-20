package sugang.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ManagerDAO {

	private static ManagerDAO instance = new ManagerDAO();

	// MngrDBBean객체를 리턴하는 메소드
	public static ManagerDAO getInstance() {
		return instance;
	}

	private ManagerDAO() {
	}
	// 커넥션 풀에서 커넥션 객체를 얻어내는 메소드

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/pool");
		conn = ds.getConnection();
		return conn;
	}

	// 관리자 인증 메서드
	public int userCheck(String id, String passwd) {
		int check = -1;
		try {
			conn = getConnection();

			String sql = "SELECT managerPw FROM manager WHERE managerId=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			// 해당 아이디가 있으면 수행
			if (rs.next()) {
				String dbpw = rs.getString("managerPw");
				if (passwd.equals(dbpw)) {
					check = 1;

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		System.out.println("[check]" + check);
		return check;
	}
	// 해당 분류의 책의 수를 얻어내는 메소드
		public int getSubjectCount(String subject_kind)
		throws Exception {
		    Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;

		    int x=0;
		    int kind  = Integer.parseInt(subject_kind);

		    try {
		        conn = getConnection();
		        String query = "select count(*) from subject01 where subject_kind=" + kind;
		        pstmt = conn.prepareStatement(query);
		        rs = pstmt.executeQuery();

		        if (rs.next()) 
		            x= rs.getInt(1);
		    } catch(Exception ex) {
		        ex.printStackTrace();
		    } finally {
		        if (rs != null) 
		           try { rs.close(); } catch(SQLException ex) {}
		        if (pstmt != null) 
		           try { pstmt.close(); } catch(SQLException ex) {}
		        if (conn != null) 
		           try { conn.close(); } catch(SQLException ex) {}
		    }
			return x;
		}
		//책의 제목을 얻어냄
		public String getSubjectTitle(int subject_id){
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        String x="";

	        try {
	            conn = getConnection();
	            
	            pstmt = conn.prepareStatement("select subject_title from subject where subject_title = "+subject_id);
	            rs = pstmt.executeQuery();

	            if (rs.next()) 
	               x= rs.getString(1);
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
	            if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
	            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
	        }
			return x;
	    }
	// 전체 등록된 책의 수를 얻어내는 메서드
	public int getSubjectCount() {

		int count = 0;

		try {
			conn = getConnection();

			String sql = "SELECT COUNT(*) FROM subject01";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return count;
	}

	public List<ManagerBean> getSubjects(String subject_kind) {

		List<ManagerBean> subjectList = null;

		try {
			
			System.out.println("dao : " + subject_kind);
			
			
			conn = getConnection();

			String sql1 = "SELECT * FROM subject01";
			String sql2 = "SELECT * FROM subject01 ";
			sql2 += "WHERE subject_kind=? ORDER BY subject_kind DESC";
			
			if (subject_kind.equals("all")) {
				pstmt = conn.prepareStatement(sql1);
			} else {
				System.out.println("!!");
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, subject_kind.charAt(0) + "");
			}
			rs = pstmt.executeQuery();

			if (rs.next()) {
				subjectList = new ArrayList<ManagerBean>();
				do {
					ManagerBean subject = new ManagerBean();

					subject.setSubject_id(rs.getInt("subject_id"));
					subject.setSubject_kind(rs.getString("subject_kind"));
					subject.setSubject_title(rs.getString("subject_title"));
					subject.setProfessor(rs.getString("professor"));
					subject.setRoom(rs.getString("room"));
					subject.setSubject_count(rs.getInt("subject_count"));

					subjectList.add(subject);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return subjectList;
	}

	// 이미 등록된 책을 검증
	public int registedSubjectconfirm(String subject_kind, String subject_title, String professor) {

		int check = 1; // 해당 책이 등록되어 있지 않음

		try {

			conn = getConnection();

			String sql = "SELECT subject_title FROM subject01 ";
			sql += " WHERE subject_kind=? AND subject_title=? AND professor=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, subject_kind);
			pstmt.setString(2, subject_title);
			pstmt.setString(3, professor);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				check = -1; // 해당 책이 이미 등록되어 있음
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return check;
	}

	// 책 등록 메서드
	public void insertSubject(ManagerBean subject) {

		int num = 0;

		try {
			conn = getConnection();

			// num의 최대값을 꺼내와 1증가 시키기
			String sql = "SELECT COUNT(subject_id) FROM subject01";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}

			sql = "INSERT INTO subject01(subject_id,subject_kind,subject_title,";
			sql += "professor,room,subject_content,";
			sql += "subject_count) VALUES (?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setString(2, subject.getSubject_kind());
			pstmt.setString(3, subject.getSubject_title());
			pstmt.setString(4, subject.getProfessor());
			pstmt.setString(5, subject.getRoom());
			pstmt.setString(6, subject.getSubject_content());
			pstmt.setInt(7, subject.getSubject_count());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}

	// subjectId에 해당하는 책의 정보를 얻어내는 메소드로
	// 등록된 책을 수정하기 위해 수정폼으로 읽어들기이기 위한 메서드
	public ManagerBean getSubject(int subjectId) {

		ManagerBean subject = null;

		try {
			conn = getConnection();

			String sql = "SELECT * FROM subject01 WHERE subject_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, subjectId);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				subject = new ManagerBean();

				subject.setSubject_kind(rs.getString("subject_kind"));
				subject.setSubject_title(rs.getString("subject_title"));
				subject.setSubject_count(rs.getInt("subject_count"));
				subject.setProfessor(rs.getString("professor"));
				subject.setRoom(rs.getString("room"));
				subject.setSubject_content(rs.getString("subject_content"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return subject;
	}
	// 등록된 책의 정보를 수정시 사용하는 메소드
    public void updateSubject(ManagerBean subject, String subject_id) {
        
    	try {
            conn = getConnection();
            
            String sql = "UPDATE subject01 SET subject_kind=?,subject_title=?,professor=?";
            sql += ",room=?,subject_content=?,subject_count=?";
            sql += " where subject_id=?";
            
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, subject.getSubject_kind());
            pstmt.setString(2, subject.getSubject_title());
            pstmt.setString(3, subject.getProfessor());
            pstmt.setString(4, subject.getRoom());
			pstmt.setString(5, subject.getSubject_content());
			pstmt.setInt(6, subject.getSubject_count());
			pstmt.setString(7, subject_id);
            
            pstmt.executeUpdate();
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
        	if (pstmt != null) try{ pstmt.close(); }catch(SQLException e) {}
        	if (conn != null) try{ conn.close(); }catch(SQLException e) {}
        }
    }
    
    // subjectId에 해당하는 책의 정보를 삭제시 사용하는 메소드
    public void deleteSubject(int subjectId) {
        
        try {
        	
			conn = getConnection();
			
			String sql = "DELETE FROM subject01 WHERE subject_id=?"; 
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, subjectId);
            
            pstmt.executeUpdate();
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
        	if (rs != null) try{ rs.close(); }catch(SQLException e) {}
        	if (pstmt != null) try{ pstmt.close(); }catch(SQLException e) {}
        	if (conn != null) try{ conn.close(); }catch(SQLException e) {}
        }
    }
 // 쇼핑몰 메인에 표시하기 위해서 사용하는 분류별 신간책목록을 얻어내는 메소드
 	public ManagerBean[] getSubjects(String subject_kind, int count) {

 		ManagerBean subjectList[]=null;
         
 		int i = 0;
         
         try {
             conn = getConnection();
             
             String sql = "SELECT * FROM subject01 WHERE subject_kind=? ";
             sql += "ORDER  BY email DESC LIMIT ?,?";
             
             pstmt = conn.prepareStatement(sql);
             pstmt.setString(1, subject_kind);
             pstmt.setInt(2, 0);
             pstmt.setInt(3, count);
             
         	rs = pstmt.executeQuery();

             if (rs.next()) {
             
             	subjectList = new ManagerBean[count];
                 
             	do{
                 	ManagerBean subject= new ManagerBean();
                 	
                     subject.setSubject_id(rs.getInt("subject_id"));
                     subject.setSubject_kind(rs.getString("subject_kind"));
                     subject.setSubject_title(rs.getString("subject_title"));
                     subject.setSubject_count(rs.getInt("subject_count"));
                     subject.setProfessor(rs.getString("professor"));
                     subject.setRoom(rs.getString("room"));
                     subjectList[i]=subject;
                      
                     i++;
                     
 			    }while(rs.next());
 			}
         } catch(Exception e) {
             e.printStackTrace();
         } finally {
         	if (rs != null) try{ rs.close(); }catch(SQLException e) {}
         	if (pstmt != null) try{ pstmt.close(); }catch(SQLException e) {}
         	if (conn != null) try{ conn.close(); }catch(SQLException e) {}
         }
 		return subjectList;
     }
}
