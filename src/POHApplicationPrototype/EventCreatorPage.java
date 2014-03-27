package POHApplicationPrototype;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JRadioButton;

public class EventCreatorPage extends JPanel {
	
	private static final long serialVersionUID = -9218393036120273552L;
	private JTextField eventNameInput;
	private JTextField eventLocationInput;
	private JTextField bannerMessageInput;
	
	private String newEventName;
	private String newEventLocation;
	private int newAgeMin;
	private int newAgeMax;
	private String newEventDescription;
	private String newBannerMessage;
	private boolean newAreMalesAllowed;
	private boolean newAreFemalesAllowed;
	private String newCreatorfirst;
	private String newCreatorlast;
	
	private boolean continueEventCreation1;
	private boolean continueEventCreation2;

	/**
	 * Create the panel.
	 */
	public EventCreatorPage(final POHApplication theApp, final MainFrame theMainFrame) {
		setBackground(new Color(153, 153, 153));
		setLayout(null);
		
		eventNameInput = new JTextField();
		eventNameInput.setText("Event Name");
		eventNameInput.setBounds(6, 24, 134, 28);
		add(eventNameInput);
		eventNameInput.setColumns(10);
		
		eventLocationInput = new JTextField();
		eventLocationInput.setText("Event Location");
		eventLocationInput.setBounds(152, 24, 134, 28);
		add(eventLocationInput);
		eventLocationInput.setColumns(10);
		
		bannerMessageInput = new JTextField();
		bannerMessageInput.setText("Banner Message");
		bannerMessageInput.setBounds(298, 24, 134, 28);
		add(bannerMessageInput);
		bannerMessageInput.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 64, 426, 12);
		add(separator);
		
		final JSpinner ageMinInput = new JSpinner();
		ageMinInput.setBounds(26, 94, 67, 28);
		add(ageMinInput);
		
		final JSpinner ageMaxInput = new JSpinner();
		ageMaxInput.setBounds(125, 94, 67, 28);
		add(ageMaxInput);
		
		JLabel ageMinLbl = new JLabel("Age Minimum");
		ageMinLbl.setBounds(16, 77, 87, 16);
		add(ageMinLbl);
		
		JLabel ageMaxLbl = new JLabel("Age Maximum");
		ageMaxLbl.setBounds(115, 77, 105, 16);
		add(ageMaxLbl);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 124, 426, 12);
		add(separator_1);
		
		JLabel femalesAllowedLbl = new JLabel("Females Allowed?");
		femalesAllowedLbl.setBounds(241, 168, 110, 16);
		add(femalesAllowedLbl);
		
		final JComboBox femalesAllowedInput = new JComboBox();
		femalesAllowedInput.setBounds(363, 164, 87, 27);
		femalesAllowedInput.addItem("Yes");
		femalesAllowedInput.addItem("No");
		add(femalesAllowedInput);
		
		JLabel malesAllowedLbl = new JLabel("Males Allowed?");
		malesAllowedLbl.setBounds(241, 196, 97, 16);
		add(malesAllowedLbl);
		
		final JComboBox malesAllowedInput = new JComboBox();
		malesAllowedInput.setBounds(363, 192, 87, 27);
		malesAllowedInput.addItem("Yes");
		malesAllowedInput.addItem("No");
		add(malesAllowedInput);
		
		final JTextPane eventDescInput = new JTextPane();
		eventDescInput.setText("Event Description");
		eventDescInput.setBounds(26, 146, 214, 137);
		add(eventDescInput);
		
		final JLabel errorLbl = new JLabel("");
		errorLbl.setForeground(Color.RED);
		errorLbl.setBackground(Color.RED);
		errorLbl.setBounds(142, 0, 161, 21);
		add(errorLbl);
		
		final JRadioButton noAgeRestrictionRadioButton = new JRadioButton("No Age Restriction");
		noAgeRestrictionRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(noAgeRestrictionRadioButton.isSelected()){
					ageMaxInput.setEnabled(false);
					ageMinInput.setEnabled(false);
				}
				else{
					ageMaxInput.setEnabled(true);
					ageMinInput.setEnabled(true);
				
				}
			}
		});
		noAgeRestrictionRadioButton.setBounds(241, 88, 170, 23);
		add(noAgeRestrictionRadioButton);
		
		
		JButton createEventButton = new JButton("Create Event");
		createEventButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newEventName = eventNameInput.getText();
				newEventLocation = eventLocationInput.getText();
				newBannerMessage = bannerMessageInput.getText();
				newEventDescription = eventDescInput.getText();
				newCreatorfirst = theApp.getLoggedinUser().getFirstName();
				newCreatorlast = theApp.getLoggedinUser().getLastName();
				
				if(malesAllowedInput.getSelectedItem().equals("Yes")){
					newAreMalesAllowed = true;
				}
				else{
					newAreMalesAllowed = false;
				}
				if(femalesAllowedInput.getSelectedItem().equals("Yes")){
					newAreFemalesAllowed = true;
				}
				else{
					newAreFemalesAllowed = false;
				}
				if(noAgeRestrictionRadioButton.isSelected()){
					continueEventCreation1 = true;
					continueEventCreation2 = true;
				}
				else{
				if(((Integer) ageMinInput.getValue()==0)&&((Integer)ageMaxInput.getValue()==0)){
					errorLbl.setText("Ages must be numbers");
					continueEventCreation1 = false;
					
				}
				else{
					continueEventCreation1 = true;
				}
				
				if(((Integer) ageMinInput.getValue())>((Integer)ageMaxInput.getValue())){
					errorLbl.setText("Min > Max");
					continueEventCreation2 = false;
					
				}
				else{
					continueEventCreation2 = true;
				 }
				}
				
				if((continueEventCreation1)&&(continueEventCreation2)){
				newAgeMin = (Integer) ageMinInput.getValue();
				newAgeMax = (Integer) ageMaxInput.getValue();
				Event createdEvent = CreateEvent();
				if(noAgeRestrictionRadioButton.isSelected()){
			    	 createdEvent.setNoAgeRestriction(true);
			     }
				if(theApp.getLoggedinUser().getMyEvent() != null){
					theApp.getLoggedinUser().getMyEvent().setShouldDelete(true);
					theApp.Refresh();
				}
				theApp.getLoggedinUser().setMyEvent(createdEvent);
				theApp.getLoggedinUser().getJoinedEvents().add(createdEvent);
				theApp.getLoggedinUser().getMyEvent().getJoinedUsers().add(theApp.getLoggedinUser());
				theApp.getLoggedinUser().getMyEvent().setEventLooker(theApp.getLoggedinUser());
				for(User blackList: theApp.getLoggedinUser().getBlackList()){
					createdEvent.getKickedUsers().add(blackList);
				}
				theApp.getEvents().add(createdEvent);
				
	
				LoggedinPage theLoggedinPage = new LoggedinPage(theApp,theMainFrame);
				 theMainFrame.setBounds(theMainFrame.bigX, theMainFrame.bigY, 
						 theMainFrame.bigWidth, theMainFrame.bigHeight);
				setVisible(false);
			    theLoggedinPage.setVisible(true);
			    theMainFrame.setContentPane(theLoggedinPage);
				}
			}
		});
		createEventButton.setBounds(333, 237, 117, 29);
		add(createEventButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoggedinPage theLoggedinPage = new LoggedinPage(theApp,theMainFrame);
				 theMainFrame.setBounds(theMainFrame.bigX, theMainFrame.bigY, 
						 theMainFrame.bigWidth, theMainFrame.bigHeight);
				setVisible(false);
			    theLoggedinPage.setVisible(true);
			    theMainFrame.setContentPane(theLoggedinPage);
			   
			}
		});
		cancelButton.setBounds(333, 265, 117, 29);
		add(cancelButton);
		
		
		

	}
	
	public Event CreateEvent(){
		Event newEvent = new Event(newCreatorfirst,newCreatorlast,newEventName,newEventLocation,newAgeMin
				,newAgeMax,newEventDescription,newBannerMessage,newAreMalesAllowed,newAreFemalesAllowed);
		return newEvent;
	}
}
