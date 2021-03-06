
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Student extends JPanel {
	
	Statement statement = null;
	JButton listBtn = null;
	JButton insertBtn = null;
	JButton modifiedBtn = null;
	JButton deleteBtn = null;//

	JTextField idTxt = null;
	JTextField nameTxt = null;
	JTextField deptTxt = null;
	JTextField addressTxt = null;

	JTable table = null;
	DefaultTableModel model = null;

	public Student() {
		setLayout(new FlowLayout());

		add(new JLabel("학번"));

		idTxt = new JTextField(14);
		add(idTxt);

		JButton searchBtn = new JButton("검색");
		searchBtn.setPreferredSize(new Dimension(60, 22));
		add(searchBtn);

		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String whereString="";
				
				if(!idTxt.getText().equals("")) {
					whereString += "id='" + idTxt.getText() + "' ";
				}
				
				if(!nameTxt.getText().equals("")) {
					if(!whereString.equals("")) whereString+="AND ";
					whereString += "name='" + nameTxt.getText() + "' ";
				}
				
				if(!deptTxt.getText().equals("")) {
					if(!whereString.equals("")) whereString+="AND ";
					whereString += "dept='" + deptTxt.getText() + "' ";
				}
				
				if(!addressTxt.getText().equals("")) {
					if(!whereString.equals("")) whereString+="AND ";
					whereString += "address='" + addressTxt.getText() + "' ";
				}
				
				if(!whereString.equals("")) {
					setTable(JdbcConnect.executeQuery("select * from student where " + whereString));
				}
				
				//주석 삭제 보류......
//				try {
//					Class.forName("oracle.jdbc.driver.OracleDriver");
//					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ora_user", "hong");
//					// statement객체 생성
//					stmt = conn.createStatement();
//					
//					// 쿼리 실행
//					if(!whereString.equals("")) {						
//						System.out.println("select * from student where " + whereString);
//						rs = stmt.executeQuery("select * from student where " + whereString);
//						setTable(rs);
//					}
//
//				} catch (Exception e1) {
//					System.out.println(e1);
//				} finally {
//					if (rs != null) {
//						try {
//							rs.close();
//						} catch (SQLException e2) {
//							e2.printStackTrace();
//						}
//
//					}
//					if (conn != null) {
//						try {
//							conn.close();
//						} catch (SQLException e3) {
//							e3.printStackTrace();
//						}
//					}
//				}
			}
		});

		add(new JLabel("이름"));
		nameTxt = new JTextField(20);
		add(nameTxt);

		add(new JLabel("학과"));
		deptTxt = new JTextField(20);
		add(deptTxt);
		
		add(new JLabel("주소"));
		addressTxt = new JTextField(20);
		add(addressTxt);

		String colName[] = { "학번", "이름", "학과", "주소" }; // 컬럼명
		model = new DefaultTableModel(colName, 0); // model 생성
		table = new JTable(model); // Jtable 생성
		// table의 사이즈
		table.setPreferredScrollableViewportSize(new Dimension(250, 130));
		add(new JScrollPane(table));

		listBtn = new JButton("목록");
		listBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTable(JdbcConnect.executeQuery("select * from student order by id"));
				
				//////////////////주석 삭제 보류
//				try {
//					Class.forName("oracle.jdbc.driver.OracleDriver");
//					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ora_user", "hong");
//
//					// statement객체 생성
//					stmt = conn.createStatement();
//					rs = stmt.executeQuery("select * from student order by id");
//					System.out.println("select * from student order by id");
//					setTable(rs);
//
//				} catch (Exception e1) {
//					System.out.println(e1);
//				} finally {
//					if (rs != null) {
//						try {
//							rs.close();
//						} catch (SQLException e2) {
//							e2.printStackTrace();
//						}
//
//					}
//					if (conn != null) {
//						try {
//							conn.close();
//						} catch (SQLException e3) {
//							e3.printStackTrace();
//						}
//					}
//				}
			}
		});
		
		add(listBtn);
		
		insertBtn = new JButton("등록");
		add(insertBtn);

		insertBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JdbcConnect.executeUpdate(
						"insert into student values('" + idTxt.getText() + "', '" + nameTxt.getText()
				+ "', '" + deptTxt.getText() +"', '"+ addressTxt.getText()+ "')");
				
				setTable(JdbcConnect.executeQuery("select * from student order by id"));
				
				
										/* 주석처리 보류*/
//				try {
//					Class.forName("oracle.jdbc.driver.OracleDriver");
//					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ora_user", "hong");
//
//					// statement객체 생성
//					stmt = conn.createStatement();
//
//					// insert
//					stmt.executeUpdate("insert into student values('" + idTxt.getText() + "', '" + nameTxt.getText()
//							+ "', '" + deptTxt.getText() +"', '"+ addressTxt.getText()+ "')");
//					
//					System.out.println("insert into student values('" + idTxt.getText() + "', '" + nameTxt.getText()
//							+ "', '" + deptTxt.getText() +  "', '" + addressTxt.getText() + "')");
//					// 쿼리 실행
//					rs = stmt.executeQuery("select * from student order by id");
//					System.out.println("select * from student order by id");
//					setTable(rs);
//				} catch (Exception e1) {
//					System.out.println(e1);
//				} finally {
//					if (rs != null) {
//						try {
//							rs.close();
//						} catch (SQLException e2) {
//							e2.printStackTrace();
//						}
//
//					}
//					if (conn != null) {
//						try {
//							conn.close();
//						} catch (SQLException e3) {
//							e3.printStackTrace();
//						}
//					}
//				}
			}
		});

		modifiedBtn = new JButton("수정");
		add(modifiedBtn);

		modifiedBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!nameTxt.getText().equals("") && !deptTxt.getText().equals("") && !deptTxt.getText().equals("") && !addressTxt.getText().equals("")) {
					JdbcConnect.executeUpdate("update student set name='" + nameTxt.getText() +"' , dept='" + deptTxt.getText() +"' , address='"+ addressTxt.getText() +"' where id='" + idTxt.getText() + "'");
					setTable(JdbcConnect.executeQuery("select * from student order by id"));
				}
				else{ //수정시 빈칸이 있으면 경고 알림 띄우기
					JOptionPane.showMessageDialog(null, "빈칸이 있습니다.", "수정할수 없습니다!", JOptionPane.ERROR_MESSAGE);
				}
				
				////////////////////주석삭제 보류////////////////////////////////
//				try {
//					Class.forName("oracle.jdbc.driver.OracleDriver");
//					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ora_user", "hong");
//
//					// statement객체 생성
//					stmt = conn.createStatement();
//					
//					// update.. 수정
//					stmt.executeUpdate("update student set name='" + nameTxt.getText() +"' , dept='" + deptTxt.getText() +"' , address='"+ addressTxt.getText() +"' where id='" + idTxt.getText() + "'");
//					System.out.println("update student set name='" + nameTxt.getText() +"' , dept='" + deptTxt.getText() +"' , address='"+ addressTxt.getText() +"' where id='" + idTxt.getText() + "'");
//					
//					rs = stmt.executeQuery("select * from student where id = '" + idTxt.getText() + "'");
//					System.out.println("select * from student where id = '" + idTxt.getText() + "'");
//
//					// 쿼리 실행
//					rs = stmt.executeQuery("select * from student order by id");
//					System.out.println("select * from student order by id");
//					setTable(rs);
//
//				} catch (Exception e1) {
//					System.out.println(e1);
//				} finally {
//					if (rs != null) {
//						try {
//							rs.close();
//						} catch (SQLException e2) {
//							e2.printStackTrace();
//						}
//
//					}
//					if (conn != null) {
//						try {
//							conn.close();
//						} catch (SQLException e3) {
//							e3.printStackTrace();
//						}
//					}
//				}

			}
		});

		deleteBtn = new JButton("삭제");
		add(deleteBtn);

		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.CLOSED_OPTION) {

				} else if (result == JOptionPane.YES_OPTION) {
					JdbcConnect.executeUpdate("delete from student where id='" + idTxt.getText() + "'");
					setTable(JdbcConnect.executeQuery("select * from student order by id"));
					
				} else {

				}
				////////////////////////////주석 삭제 보류///////////////////////////////
				
//				try {
//					int result = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
//					Class.forName("oracle.jdbc.driver.OracleDriver");
//					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ora_user", "hong");
//
//					// statement객체 생성
//					stmt = conn.createStatement();
//
//					if (result == JOptionPane.CLOSED_OPTION) {
//
//					} else if (result == JOptionPane.YES_OPTION) {
//						// delete
//						stmt.executeUpdate("delete from student where id='" + idTxt.getText() + "'");
//
//						// 쿼리 실행
//						rs = stmt.executeQuery("select * from student order by id");
//						System.out.println("select * from student order by id");
//						setTable(rs);
//					} else {
//
//					}
//				} catch (Exception e1) {
//					System.out.println(e1);
//				} finally {
//					if (rs != null) {
//						try {
//							rs.close();
//						} catch (SQLException e2) {
//							e2.printStackTrace();
//						}
//
//					}
//					if (conn != null) {
//						try {
//							conn.close();
//						} catch (SQLException e3) {
//							e3.printStackTrace();
//						}
//					}
//				}

			}
		});

		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table = (JTable) e.getComponent();// 클릭한 테이블 구하기
				model = (DefaultTableModel) table.getModel();// 테이블의 모델 구하기
				String id = (String) model.getValueAt(table.getSelectedRow(), 0);
				idTxt.setText(id);
				String name = (String) model.getValueAt(table.getSelectedRow(), 1);
				nameTxt.setText(name);
				String dept = (String) model.getValueAt(table.getSelectedRow(), 2);
				deptTxt.setText(dept);
				String address = (String) model.getValueAt(table.getSelectedRow(), 3);
				addressTxt.setText(address);
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}
		});

		this.setSize(280, 350);
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
				row[2] = rs.getString("dept");
				row[3] = rs.getString("address");
				model.addRow(row);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
