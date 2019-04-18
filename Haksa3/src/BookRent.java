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
		
//		ResultSet rs = null; // select�� ����� �����ϴ� ��ü
//
//		String url = null; // ���� url
//		/* oracle */
//		String uid = "ora_user"; // ID
//		String pw = "hong"; // PW
//		url = "jdbc:oracle:thin:@localhost:1521:orcl";
//
//		// ��ü. �⺻����
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
//			conn = DriverManager.getConnection(url, uid, pw);// ����
//			stmt = conn.createStatement();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		// JComboBox
		String[] dept = { "��ü", "��ǻ�ͽý���", "��Ƽ�̵��", "��ǻ�Ͱ���" };
		cb = new JComboBox(dept);
		cb.setBounds(45, 10, 100, 20);
		add(cb);

		cb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				int si = cb.getSelectedIndex();

				// ��������(dynamic). ����ÿ� ������ �����
				query = "select s.id, s.name, b.title, br.rDate" + " from student s, books b, bookRent br"
						+ " where br.id=s.id" + " and br.bookNo=b.no";

				if (si == 0) {
					// ��ü
					query += " order by b.no";
				} else if (si == 1) {
					// ��ǻ�� �ý���
					query += " and s.dept='��ǻ�ͽý���'" + " order by br.no";
				} else if (si == 2) {
					// ��Ƽ�̵��
					query += " and s.dept='��Ƽ�̵��'" + " order by br.no";
				} else if (si == 3) {
					// ��ǻ�Ͱ���
					query += " and s.dept='��ǻ�Ͱ���'" + " order by br.no";
				}
				//list();
			}
		});

		String colName[] = { "�й�", "�̸�", "������", "������" };
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
//	           conn.close(); // ��������.
//	          }
//	         } catch (SQLException e1) {
//	          e1.printStackTrace();
//	         }
//	        }
//	       });
		// ��ü ��� ���
		//list();
		System.out.println(query);
		//setTable(JdbcConnect.executeQuery(query));

		this.setLayout(null);
		this.setSize(500, 500);
		this.setVisible(true);
	}
	
	public void setTable(ResultSet rs) {
		try {
			// JTable �ʱ�ȭ
			model.setNumRows(0); // model���� ���� ���� 0���� ����
			while (rs.next()) {
				String[] row = new String[4];// �÷��� ������ 3
				row[0] = rs.getString("id");	//id�� �й�
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