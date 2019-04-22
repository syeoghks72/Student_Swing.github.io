import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
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

	public BookRent() {
		query = "select s.id, s.name, b.title, br.rDate" + " from student s, books b, bookRent br" + " where br.id=s.id"
				+ " and br.bookNo=b.no order by br.no";
		
		cb = new JComboBox(getDeptArry(JdbcConnect.executeQuery("select distinct dept from student")));		
		cb.setBounds(45, 10, 120, 20);
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
		
		setTable(JdbcConnect.executeQuery(query));

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
	
	//Dept를 정보를 매개변수로 받아 Dept정보를 String 배열로 리턴한다.
	//dept(학과종류)를 배열로 리턴하는 함수..
	public String[] getDeptArry(ResultSet dept_rs) {
		String[] dept_arry = null;	//지역변수는 null로 초기화
		ArrayList<String> dept_arryList = new ArrayList<String>();
		int arr_index = 0;	//dept배열의 인덱스 변수
		try {
			dept_arry = new String[10];	//패치 사이즈 만큼 배열 크기 할당
			System.out.println(dept_rs.getFetchSize());
			while (dept_rs.next()) {
				arr_index++;
				dept_arryList.add(dept_rs.getString("dept"));
			}
			dept_arry = new String[arr_index];
			arr_index = 0;
			for(String s : dept_arryList) {
				dept_arry[arr_index++] = s;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return dept_arry;
	}
}