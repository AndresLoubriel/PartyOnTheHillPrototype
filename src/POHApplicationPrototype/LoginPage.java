package POHApplicationPrototype;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class LoginPage extends JPanel {
	private static final long serialVersionUID = 6076890693419812680L;
	private JTextField firstnameInput;
	private JTextField lastnameInput;
	private JPasswordField passwordInput;

	/**
	 * Create the panel.
	 */
	public LoginPage(final POHApplication theApp,final MainFrame theMainFrame) {
		setBackground(new Color(153, 153, 153));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		
		
		final JLabel welcomeBanner = new JLabel("Welcome to Party on the Hill!");
		welcomeBanner.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		welcomeBanner.setBounds(6, 0, 209, 37);
		add(welcomeBanner);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(177, 158, -21, -63);
		add(separator);
		
		final JButton signUpButton = new JButton("Signup");
		signUpButton.setBounds(6, 239, 117, 29);
		add(signUpButton);
		
		final JLabel loginPrompt = new JLabel("Login:");
		loginPrompt.setBounds(193, 79, 61, 16);
		add(loginPrompt);
		
		firstnameInput = new JTextField();
		firstnameInput.setFont(new Font("Yuppy TC", Font.PLAIN, 12));
		firstnameInput.setText("First Name");
		firstnameInput.setBounds(147, 106, 134, 28);
		firstnameInput.setColumns(10);
		add(firstnameInput);
		
		lastnameInput = new JTextField();
		lastnameInput.setFont(new Font("Yuppy SC", Font.PLAIN, 12));
		lastnameInput.setText("Last Name");
		lastnameInput.setBounds(147, 136, 134, 28);
		lastnameInput.setColumns(10);
		add(lastnameInput);
		
		passwordInput = new JPasswordField();
		passwordInput.setToolTipText("");
		passwordInput.setFont(new Font("Yuppy TC", Font.PLAIN, 12));
		passwordInput.setBounds(147, 168, 134, 28);
		add(passwordInput);
		
		final JButton loginButton = new JButton("Login");
		loginButton.setBounds(157, 198, 117, 29);
		add(loginButton);
		
		final JLabel errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		errorLabel.setBackground(Color.BLACK);
		errorLabel.setBounds(114, 51, 220, 16);
		add(errorLabel);
		
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpPage thesignUpPage = new SignUpPage(theApp,theMainFrame);
				theMainFrame.setContentPane(thesignUpPage);
				thesignUpPage.setVisible(true);
				
			}
		});
		loginButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				theApp.loginFirstName = firstnameInput.getText();
				theApp.loginLastName = lastnameInput.getText();
				theApp.loginPassWord = passwordInput.getText();
				 if(theApp.Login2()==0){
					 errorLabel.setText("Login Successful");
					 theMainFrame.setBounds(theMainFrame.bigX, theMainFrame.bigY, 
							 theMainFrame.bigWidth, theMainFrame.bigHeight);
					 LoggedinPage theloggedinPage = new LoggedinPage(theApp,theMainFrame);
					 theMainFrame.setContentPane(theloggedinPage);
					 theloggedinPage.setVisible(true);
					 setVisible(false);
				 }
				 else{
					 errorLabel.setText("Username or Password incorrect");
					 firstnameInput.setText("First Name");
					 lastnameInput.setText("Last Name");
					 passwordInput.setText("");
					 
				 }
			}
		});

	}
	

}
