package POHApplicationPrototype;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class JTable extends JFrame {

	private JPanel contentPane;
	private javax.swing.JTable table;
	private Event event1 = new Event("1","1","1", "1", 1, 24, "PEWPEW", "Woot", true, true);
	private Event event2 = new Event("party","party","MEOW", "Snooch", 1, 24, "PEWPEW", "Woot", true, true);
	private Event event3 = new Event("Yes","No","How", "Go", 1, 24, "PEWPEW", "Woot", true, true);
	private Event event4 = new Event("FFF","FFF","FFF", "FFF", 1, 24, "PEWPEW", "Woot", true, true);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JTable frame = new JTable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JTable() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultTableModel tableContents = new DefaultTableModel();
		tableContents.addColumn(event1);
		
		
		table = new javax.swing.JTable(tableContents);
		table.setBounds(78, 57, 243, 173);
		//contentPane.add(table);
		
		
		
		JScrollPane thepane = new JScrollPane(table);
		thepane.setBounds(table.getBounds());
		contentPane.add(thepane);
	}

}
