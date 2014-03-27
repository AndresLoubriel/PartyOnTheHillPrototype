package POHApplicationPrototype;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FriendRequestWindow extends JPanel {
	private static final long serialVersionUID = 8718099575313122751L;

	/**
	 * Create the panel.
	 */
	public FriendRequestWindow(final POHApplication theApp,final User sender,final MainFrame theMainFrame) {
		setBackground(Color.GRAY);
		setLayout(null);
		setBounds(50,50,450,300);
		JLabel requestSenderLbl = new JLabel("Accept"+" "+sender.getFirstName()+" "+sender.getLastName()
											+"'s friend request?");
		requestSenderLbl.setBounds(72, 48, 336, 39);
		add(requestSenderLbl);
		
		final JLabel confirmationLbl = new JLabel("");
		confirmationLbl.setBounds(132, 110, 229, 16);
		add(confirmationLbl);
		
		final JButton noButton = new JButton("No");
		noButton.setBounds(230, 169, 117, 29);
		add(noButton);
		
		final JButton yesButton = new JButton("Yes");
		yesButton.setBounds(88, 169, 117, 29);
		add(yesButton);
		
		yesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theApp.getLoggedinUser().AddFriend(sender);
				confirmationLbl.setForeground(Color.GREEN);
				confirmationLbl.setText("Friend Request Accepted!");
				theApp.getLoggedinUser().getFriendRequests().remove(sender);
				noButton.setEnabled(false);
				yesButton.setEnabled(false);
				LoggedinPage theLoggedinPage = new LoggedinPage(theApp,theMainFrame);
				theMainFrame.getContentPane().setVisible(false);
				theMainFrame.setContentPane(theLoggedinPage);
				theLoggedinPage.setVisible(true);
			}
		});
		
		
		noButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmationLbl.setForeground(Color.RED);
				confirmationLbl.setText("Friend Request Denied");
				theApp.getLoggedinUser().getFriendRequests().remove(sender);
				noButton.setEnabled(false);
				yesButton.setEnabled(false);
				LoggedinPage theLoggedinPage = new LoggedinPage(theApp,theMainFrame);
				theMainFrame.getContentPane().setVisible(false);
				theMainFrame.setContentPane(theLoggedinPage);
				theLoggedinPage.setVisible(true);
			}
		});
		
		
		

	}
}
