import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BookRent extends JPanel{

	JComboBox cb = null;
	JTable table = null;
	DefaultTableModel model = null;
	String query = null;
//	Statement stmt = null;
//	Connection conn = null;

	public BookRent() {
		
//		ResultSet rs = null; // select한 결과를 저장하는 객체
//
//		String url = null; // 서버 url
//		/* oracle */
//		String uid = "ora_user"; // ID
//		String pw = "hong"; // PW
//		url = "jdbc:oracle:thin:@localhost:1521:orcl";
//
//		// 전체. 기본쿼리
		query = "select s.id, s.name, b.title, br.rDate" + " from student s, books b, bookRent br" + " where br.id=s.id"
				+ " and br.bookNo=b.no order by br.no";

//		// String uid = "root";// ID
//		// String pw = "1234";//PW
//		// url="jdbc:mysql://localhost:3306/sampledb?useSSL=false";
//
//		// DB Connection
//		try {
//			/* oracle */
//			Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
//
//			// Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(url, uid, pw);// 연결
//			stmt = conn.createStatement();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		// JComboBox
		String[] dept = { "전체", "컴퓨터시스템", "멀티미디어", "컴퓨터공학" };
		cb = new JComboBox(dept);
		cb.setBounds(45, 10, 100, 20);
		add(cb);

		cb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				int si = cb.getSelectedIndex();

				// 동적쿼리(dynamic). 실행시에 쿼리가 병경됨
				query = "select s.id, s.name, b.title, br.rDate" + " from student s, books b, bookRent br"
						+ " where br.id=s.id" + " and br.bookNo=b.no";

				if (si == 0) {
					// 전체
					query += " order by b.no";
				} else if (si == 1) {
					// 컴퓨터 시스템
					query += " and s.dept='컴퓨터시스템'" + " order by br.no";
				} else if (si == 2) {
					// 멀티미디어
					query += " and s.dept='멀티미디어'" + " order by br.no";
				} else if (si == 3) {
					// 컴퓨터공학
					query += " and s.dept='컴퓨터공학'" + " order by br.no";
				}
				//list();
			}
		});

		String colName[] = { "학번", "이름", "도서명", "대출일" };
		model = new DefaultTableModel(colName, 0);

		table = new JTable(model);

		table.setPreferredScrollableViewportSize(new Dimension(470, 200));
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 40, 460, 250);
		add(sp);

//	    addWindowListener(new WindowAdapter(){
//	        public void windowClosing(WindowEvent e){
//	         try {
//	          if(conn!=null){
//	           conn.close(); // 연결종료.
//	          }
//	         } catch (SQLException e1) {
//	          e1.printStackTrace();
//	         }
//	        }
//	       });
		// 전체 목록 출력
		//list();
		System.out.println(query);
		//setTable(JdbcConnect.executeQuery(query));

		this.setLayout(null);
		this.setSize(500, 500);
		this.setVisible(true);
	}
	
	public void setTable(ResultSet rs) {
		try {
			// JTable 초기화
			model.setNumRows(0); // model에서 행의 수를 0으로 설정
			while (rs.next()) {
				String[] row = new String[4];// 컬럼의 갯수가 3
				row[0] = rs.getString("id");	//id는 학번
				row[1] = rs.getString("name"); 
				row[2] = rs.getString("title");
				row[3] = rs.getString("rdate");
				model.addRow(row);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}	
}