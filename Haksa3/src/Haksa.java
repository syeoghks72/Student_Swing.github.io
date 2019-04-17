import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Haksa extends JFrame {
	JPanel panel = null;

	public Haksa() {
		this.setTitle("학사관리 프로그램");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar mb = new JMenuBar(); // MenuBar

		JMenu menu1 = new JMenu("학생관리"); // Menu
		mb.add(menu1);

		JMenuItem mi_list = new JMenuItem("학생정보");
		menu1.add(mi_list);

		mi_list.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.removeAll(); // 모든컴포넌트 삭제
				panel.revalidate(); // 다시 활성화
				panel.repaint(); // 다시 그리기
				panel.add(new Student()); // 화면 생성.
				panel.setLayout(null);// 레이아웃적용안함
			}
		});

		JMenu menu2 = new JMenu("도서대출");
		mb.add(menu2);

		JMenuItem book_rent_item = new JMenuItem("대출목록");
		menu2.add(book_rent_item);

		book_rent_item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.removeAll(); // 모든컴포넌트 삭제
				panel.revalidate(); // 다시 활성화
				panel.repaint(); // 다시 그리기
				panel.add(new BookRent()); // 화면 생성.
				panel.setLayout(null);// 레이아웃적용안함
			}
		});

		JMenuItem Loan_status = new JMenuItem("대출 현황");
		menu2.add(Loan_status);

		this.setJMenuBar(mb);

		panel = new JPanel();
		panel.setLayout(null);// 레이아웃적용안함
		panel.add(new Student()); // Default 화면은 Student화면이다.
		this.add(panel); 

		this.setSize(500, 500);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Haksa();
	}
}
