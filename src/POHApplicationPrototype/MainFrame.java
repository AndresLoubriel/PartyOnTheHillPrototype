/*
 * @author ANDRES LOUBRIEL
 */
package POHApplicationPrototype;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.GridLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPasswordField;

import java.awt.Canvas;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.JLayeredPane;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;


@SuppressWarnings("unused")
public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = -1396125054714636487L;
	
	private String firstName;
	private String lastName;
	private String passWord;
	
	private POHApplication theApp = new POHApplication();
	private LoggedinPage theloggedinPage;
	private SignUpPage theSignUpPage;
	private LoginPage theActualLoginPage;
	
	int smallX = 100;
	int smallY = 100;
	int smallWidth = 452;
	int smallHeight = 356;
	
	int bigX = 100;
	int bigY = 100;
	int bigWidth = 1000;
	int bigHeight = 1000;
	
	int tinyX = 50;
	int tinyY = 50;
	int tinyWidth = 300;
	int tinyHeight = 300;
	
	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame(theApp);
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
	public MainFrame(POHApplication someApp) {
		theApp = someApp;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(smallX, smallY, smallWidth, smallHeight);
		theActualLoginPage = new LoginPage(theApp,this);
		setContentPane(theActualLoginPage);
		theActualLoginPage.setVisible(true);
		
	}
	
	

	public static void main (String[] args){
		POHApplication Test = new POHApplication();
		MainFrame TestApp = new MainFrame(Test);
		TestApp.start();
		
	}
	
	 
    /*
     * 
     * GETTERS AND SETTERS
     */
	public String getFirstName() {
		return firstName;
	}

    public void setLoggedin(POHApplication someApp){
    	theloggedinPage = new LoggedinPage(someApp,this);
    }
    
    public void setSignUp(POHApplication someApp){
    	theSignUpPage = new SignUpPage(someApp,this);
    }
    
    public void setActualLogin(POHApplication someApp){
    	theActualLoginPage = new LoginPage(someApp,this);
    }
    
	public LoggedinPage getTheloggedinPage() {
		return theloggedinPage;
	}



	public void setTheloggedinPage(LoggedinPage theloggedinPage) {
		this.theloggedinPage = theloggedinPage;
	}



	public POHApplication getTheApp() {
		return theApp;
	}



	public void setTheApp(POHApplication theApp) {
		this.theApp = theApp;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


	
	public SignUpPage getTheSignUpPage() {
		return theSignUpPage;
	}



	public void setTheSignUpPage(SignUpPage theSignUpPage) {
		this.theSignUpPage = theSignUpPage;
	}

}
