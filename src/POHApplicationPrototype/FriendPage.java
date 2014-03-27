package POHApplicationPrototype;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FriendPage extends JPanel {

	private static final long serialVersionUID = 4708164732338704088L;
	
	private DefaultListModel FriendJoinedEventsListContents;
	private DefaultListModel FriendEventListContents;

	/**
	 * Create the panel.
	 */
	public FriendPage(final POHApplication theApp, User Friend,final MainFrame theMainFrame) {
		setLayout(null);
		setBackground(new Color(153, 153, 153));
		
		FriendJoinedEventsListContents = new DefaultListModel();
		FriendEventListContents = new DefaultListModel();
		
		try{
			//System.out.println(theApp.getLoggedinUser().getMyEvent().CreatorEventString());
			FriendEventListContents.clear();
			if(theApp.getLoggedinUser().CheckEvent(Friend.getMyEvent())){
				FriendEventListContents.addElement(Friend.getMyEvent());
			}
		}catch(NullPointerException e){
			FriendEventListContents.addElement("No event created");
		}
		
		try{
			FriendJoinedEventsListContents.clear();
		  for(Event joinedEvents : Friend.getJoinedEvents()){
			 if(theApp.getLoggedinUser().CheckEvent(joinedEvents)){
				 FriendJoinedEventsListContents.addElement(joinedEvents);
			 }
		  }
		}catch(NullPointerException e){
			FriendJoinedEventsListContents.addElement("No events joined");
		}
		
		JLabel friendName = new JLabel(Friend.getFirstName()+" "+Friend.getLastName());
		friendName.setBounds(6, 6, 240, 28);
		add(friendName);
		
		JLabel friendAge = new JLabel(Friend.getUserAge()+"");
		friendAge.setBounds(6, 37, 97, 28);
		add(friendAge);
		
		final JList friendJoinedEventsList = new JList(FriendJoinedEventsListContents);
		friendJoinedEventsList.setBackground(Color.GREEN);
		friendJoinedEventsList.setBounds(203, 200, 226, 80);
		
		JScrollPane friendJoinedEventsListPane = new JScrollPane(friendJoinedEventsList);
		friendJoinedEventsListPane.setBounds(friendJoinedEventsList.getBounds());
		add(friendJoinedEventsListPane);
		
		final JList friendEventList = new JList(FriendEventListContents);
		friendEventList.setBackground(new Color(30, 144, 255));
		friendEventList.setBounds(203, 103, 226, 48);
		
		JScrollPane friendEventListPane = new JScrollPane(friendEventList);
		friendEventListPane.setBounds(friendEventList.getBounds());
		add(friendEventListPane);
		
		JLabel genderLabel = new JLabel();
		genderLabel.setBounds(6, 71, 61, 16);
		add(genderLabel);
		
		JLabel joinedEventsLabel = new JLabel(Friend.getFirstName()+"'s"+" "+"joined Events");
		joinedEventsLabel.setBounds(202, 182, 193, 16);
		add(joinedEventsLabel);
		
		if(Friend.getUserGender()==1){
			genderLabel.setText("Male");
		}
		else{
			genderLabel.setText("Female");
		}
		
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoggedinPage theLoggedinPage = new LoggedinPage(theApp,theMainFrame);
				 theMainFrame.setBounds(theMainFrame.bigX, theMainFrame.bigY, 
						 theMainFrame.bigWidth, theMainFrame.bigHeight);
				setVisible(false);
			    theLoggedinPage.setVisible(true);
			    theMainFrame.setContentPane(theLoggedinPage);
			}
		});
		backButton.setBounds(312, 7, 117, 29);
		add(backButton);
		
		JLabel friendCreatedEventLbl = new JLabel(Friend.getFirstName()+"'s"+" "+ "Event");
		friendCreatedEventLbl.setBounds(204, 85, 225, 16);
		add(friendCreatedEventLbl);
		
		final JLabel errorLbl = new JLabel("");
		errorLbl.setBounds(203, 46, 226, 16);
		add(errorLbl);
		
		JButton joinButton1 = new JButton("Join");
		joinButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(friendEventList.isSelectionEmpty()){
					errorLbl.setForeground(Color.RED);
					errorLbl.setText("Please Select an Event to Join");
				}
				else{
					Event abouttoJoinEvent = (Event) friendEventList.getSelectedValue();
					if(theApp.getLoggedinUser().getJoinedEvents().contains(abouttoJoinEvent)){
						errorLbl.setForeground(Color.RED);
						errorLbl.setText("You are already attending this event");
					}
					else{
						theApp.getLoggedinUser().getJoinedEvents().add(abouttoJoinEvent);
						errorLbl.setForeground(Color.GREEN);
						errorLbl.setText("Event Joined");
					}

				}
			}
		});
		joinButton1.setBounds(203, 154, 92, 16);
		add(joinButton1);
		
		JButton joinButton2 = new JButton("Join");
		joinButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(friendJoinedEventsList.isSelectionEmpty()){
					errorLbl.setForeground(Color.RED);
					errorLbl.setText("Please Select an Event to Join");
				}
				else{
					Event abouttoJoinEvent = (Event) friendJoinedEventsList.getSelectedValue();
					if(theApp.getLoggedinUser().getJoinedEvents().contains(abouttoJoinEvent)){
						errorLbl.setForeground(Color.RED);
						errorLbl.setText("You are already attending this event");
					}
					else{
						theApp.getLoggedinUser().getJoinedEvents().add(abouttoJoinEvent);
						errorLbl.setForeground(Color.GREEN);
						errorLbl.setText("Event Joined");
					}

				}
			
			}
		});
		joinButton2.setBounds(203, 284, 92, 16);
		add(joinButton2);
		
		
		
		
		

	}
}
