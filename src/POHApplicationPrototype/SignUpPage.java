/**
 * 
 * @author ANDRES LOUBRIEL
 *
 */
package POHApplicationPrototype;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.Color;

import javax.swing.JPasswordField;
@SuppressWarnings("unused")


public class SignUpPage extends JPanel {
	
	
	private static final long serialVersionUID = 9046354807647113707L;
	private int newUserGender;
	private int newUserAge;
	private String newUserFirstName;
	private String newUserLastName;
	private String newUserPassword;
	private JTextField newFirstNameInput;
	private JTextField newLastNameInput;
	private JTextField newAgeInput;
	private JPasswordField passwordField;
	private JPasswordField passwordField2;
	private JLabel retypePasswordLbl;
	private boolean continueAccountCreation;
	private JLabel errorLbl;
	private boolean continueAccountCreation2;
	boolean signUpSuccessful;
	
	
	/**
	 * Create the panel.
	 */
	public SignUpPage(final POHApplication theApp,final MainFrame theLoginPage) {
		setBackground(new Color(153, 153, 153));
		
		setLayout(null);
		setVisible(false);
		
		
		final JComboBox genderBox = new JComboBox();
		
		genderBox.setMaximumRowCount(2);
		genderBox.setBounds(172, 114, 112, 27);
		add(genderBox);
		genderBox.addItem("male");
		genderBox.addItem("female");
		
		JLabel genderLbl = new JLabel("Choose your Gender");
		genderLbl.setBounds(169, 99, 141, 16);
		add(genderLbl);
		
		newFirstNameInput = new JTextField();
		newFirstNameInput.setText("Enter first name");
		newFirstNameInput.setFont(new Font("Yuppy SC", Font.PLAIN, 13));
		newFirstNameInput.setBounds(6, 56, 134, 28);
		add(newFirstNameInput);
		newFirstNameInput.setColumns(10);
		
		newLastNameInput = new JTextField();
		newLastNameInput.setText("Enter last name");
		newLastNameInput.setFont(new Font("Yuppy SC", Font.PLAIN, 13));
		newLastNameInput.setBounds(6, 93, 134, 28);
		add(newLastNameInput);
		newLastNameInput.setColumns(10);
		
		newAgeInput = new JTextField();
		newAgeInput.setFont(new Font("Yuppy SC", Font.PLAIN, 13));
		newAgeInput.setText("How old are you?");
		newAgeInput.setBounds(159, 56, 134, 28);
		add(newAgeInput);
		newAgeInput.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(318, 56, 115, 27);
		add(passwordField);
		
		final JLabel createPasswordLbl = new JLabel("Create Password");
		createPasswordLbl.setBounds(318, 37, 115, 16);
		add(createPasswordLbl);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(318, 112, 115, 28);
		add(passwordField2);
		
		retypePasswordLbl = new JLabel("Retype Password");
		retypePasswordLbl.setBounds(318, 93, 115, 16);
		add(retypePasswordLbl);
		
		errorLbl = new JLabel("");
		errorLbl.setBounds(130, 6, 199, 16);
		add(errorLbl);
		
		
		JButton btnCreateAccount = new JButton("Create account");
		btnCreateAccount.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				newUserFirstName = newFirstNameInput.getText();
				newUserLastName = newLastNameInput.getText();
				try{
					newUserAge = Integer.parseInt(newAgeInput.getText());
					continueAccountCreation = true;
				}catch(NumberFormatException f){
					errorLbl.setText("Age must be a number");
					continueAccountCreation = false;
				}
				

				if(!passwordField.getText().equals(passwordField2.getText())){
					errorLbl.setText("Passwords Don't Match");
					passwordField.setText("");
					passwordField2.setText("");
					continueAccountCreation2 = false;
				}
				else{
					continueAccountCreation2 = true;
					newUserPassword = passwordField.getText();
				}
				if(genderBox.getSelectedItem().equals("male")){
					newUserGender = 1;
					
				}
				else{
				newUserGender = 2;
				
				}
				 if(continueAccountCreation&&continueAccountCreation2){
				  theApp.getUsers().add(CreateUser());
				  LoginPage theloginPage = new LoginPage(theApp,theLoginPage);
				  theloginPage.setVisible(true);
				  setVisible(false);
				  theLoginPage.setContentPane(theloginPage);
				}
			}
			
		});
		
		btnCreateAccount.setFont(new Font("Yuppy SC", Font.PLAIN, 13));
		btnCreateAccount.setBackground(new Color(238, 238, 238));
		btnCreateAccount.setBounds(152, 188, 134, 29);
		add(btnCreateAccount);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginPage theloginPage = new LoginPage(theApp,theLoginPage);
				theloginPage.setVisible(true);
				setVisible(false);
				theLoginPage.setContentPane(theloginPage);
				
			}
		});
		backButton.setBounds(6, 6, 101, 29);
		add(backButton);
		
		
		

	}
	public User CreateUser(){
		 User newUser = new User(newUserFirstName,newUserLastName,newUserPassword,newUserAge,newUserGender);
		 return newUser;
		
		
	}
}
