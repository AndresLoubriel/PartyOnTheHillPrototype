package POHApplicationPrototype;

import java.awt.Color;

import javax.swing.JPanel;



import javax.swing.JFrame;

import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class BlacklistPage extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8931462334242060545L;

	/**
	 * Create the panel.
	 */
	public BlacklistPage(final POHApplication theApp, final User blackLister,final MainFrame theMainFrame,final JFrame windowFrame) {
		setBackground(new Color(153, 153, 153));
		setLayout(null);
		setBounds(50,50,450,300);
		
		JTextArea explanationTextArea = new JTextArea();
		explanationTextArea.setBackground(Color.BLACK);
		explanationTextArea.setForeground(Color.WHITE);
		explanationTextArea.setWrapStyleWord(true);
		explanationTextArea.setLineWrap(true);
		explanationTextArea.setEditable(false);
		explanationTextArea.setText("User, " +blackLister.getFirstName()+" "+blackLister.getLastName()+" " +
		"is Blacklisted and is unable to view or join any event you create, or be your friend."
		+ " If you would like to remove this"
		+ " person from your Blacklist, hit the button. Otherwise you may exit this window.");
		explanationTextArea.setBounds(6, 6, 438, 80);
		add(explanationTextArea);
		
		JButton removeBtn = new JButton("Remove from Blacklist");
		removeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theApp.getLoggedinUser().RemoveFromBlackList(blackLister);
				LoggedinPage theLoggedinPage = new LoggedinPage(theApp,theMainFrame);
				theMainFrame.getContentPane().setVisible(false);
				theMainFrame.setContentPane(theLoggedinPage);
				theLoggedinPage.setVisible(true);
				windowFrame.dispose();
			}
		});
		removeBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		removeBtn.setBounds(130, 159, 163, 29);
		add(removeBtn);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowFrame.dispose();
			}
		});
		cancelButton.setBounds(150, 207, 117, 29);
		add(cancelButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(113, 190, 199, 22);
		add(separator);

	}
}
