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
		
		setTable(JdbcConnect.executeQuery(query));

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
	
	//Dept�� ������ �Ű������� �޾� Dept������ String �迭�� �����Ѵ�.
	//dept(�а�����)�� �迭�� �����ϴ� �Լ�..
	public String[] getDeptArry(ResultSet dept_rs) {
		String[] dept_arry = null;	//���������� null�� �ʱ�ȭ
		ArrayList<String> dept_arryList = new ArrayList<String>();
		int arr_index = 0;	//dept�迭�� �ε��� ����
		try {
			dept_arry = new String[10];	//��ġ ������ ��ŭ �迭 ũ�� �Ҵ�
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