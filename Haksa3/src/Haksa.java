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
		this.setTitle("�л���� ���α׷�");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar mb = new JMenuBar(); // MenuBar

		JMenu menu1 = new JMenu("�л�����"); // Menu
		mb.add(menu1);

		JMenuItem mi_list = new JMenuItem("�л�����");
		menu1.add(mi_list);

		mi_list.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.removeAll(); // ���������Ʈ ����
				panel.revalidate(); // �ٽ� Ȱ��ȭ
				panel.repaint(); // �ٽ� �׸���
				panel.add(new Student()); // ȭ�� ����.
				panel.setLayout(null);// ���̾ƿ��������
			}
		});

		JMenu menu2 = new JMenu("��������");
		mb.add(menu2);

		JMenuItem book_rent_item = new JMenuItem("������");
		menu2.add(book_rent_item);

		book_rent_item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.removeAll(); // ���������Ʈ ����
				panel.revalidate(); // �ٽ� Ȱ��ȭ
				panel.repaint(); // �ٽ� �׸���
				panel.add(new BookRent()); // ȭ�� ����.
				panel.setLayout(null);// ���̾ƿ��������
			}
		});

		JMenuItem Loan_status = new JMenuItem("���� ��Ȳ");
		menu2.add(Loan_status);

		this.setJMenuBar(mb);

		panel = new JPanel();
		panel.setLayout(null);// ���̾ƿ��������
		panel.add(new Student()); // Default ȭ���� Studentȭ���̴�.
		this.add(panel); 

		this.setSize(500, 500);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Haksa();
	}
}
