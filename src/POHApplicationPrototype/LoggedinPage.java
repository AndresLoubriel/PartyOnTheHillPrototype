package POHApplicationPrototype;

import javax.swing.DefaultListModel;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JList;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ListSelectionModel;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.swing.JMenuBar;


public class LoggedinPage extends JPanel {

	private static final long serialVersionUID = 2896302943239323159L;
	private DefaultListModel availableEventsListContents;
	private DefaultListModel joinedEventsListContents;
	private DefaultListModel myEventListContents;
	private DefaultListModel eventCreatorJoinedUsersContents;
	private DefaultListModel eventAttendeeJoinedUsersContents;
	
	private String firstName;
	private String lastName;
	
	private int availableEventsX = 6;
	private int availableEventsY = 300;
	private int availableEventsWidth = 700;
	private int availableEventsHeight = 100;
	
	private int joinedEventsX = 6;
	private int joinedEventsY = 500;
	private int joinedEventsWidth = 700;
	private int joinedEventsHeight = 100;
	
	private int myEventsX = 6;
	private int myEventsY = 100;
	private int myEventsWidth = 700;
	private int myEventsHeight = 60;
	
	

	/**
	 * Create the panel.
	 */
	public LoggedinPage(final POHApplication theApp, final MainFrame theMainFrame) {
		setBackground(new Color(153, 153, 153));
		
		setLayout(null);
		setBounds(theMainFrame.getBounds());
		firstName = theApp.getLoggedinUser().getFirstName();
		lastName = theApp.getLoggedinUser().getLastName();
		
		
		JLabel greetingLbl = new JLabel("Welcome: " + firstName + " " + lastName);
		greetingLbl.setFont(new Font("Yuppy TC", Font.PLAIN, 13));
		greetingLbl.setBackground(new Color(255, 102, 102));
		greetingLbl.setBounds(19, 6, 389, 16);
		add(greetingLbl);
		
		final JLabel errorLbl = new JLabel("");
		errorLbl.setForeground(new Color(255, 51, 51));
		errorLbl.setBounds(156, 224, 288, 16);
		add(errorLbl);
	
		availableEventsListContents = new DefaultListModel();
		eventCreatorJoinedUsersContents = new DefaultListModel();
		eventAttendeeJoinedUsersContents = new DefaultListModel();
	    joinedEventsListContents = new DefaultListModel();
		myEventListContents = new DefaultListModel();
		eventCreatorJoinedUsersContents = new DefaultListModel();
		
		JMenu friendsList = new JMenu("Friends");
		JMenuBar friendsMenu = new JMenuBar();
		friendsMenu.add(friendsList);
		friendsMenu.setBounds(276, 6, 132, 22);
		add(friendsMenu);
		
		JMenu friendRequests = new JMenu("Friend Requests");
		JMenuBar friendRequestsMenu = new JMenuBar();
		friendRequestsMenu.add(friendRequests);
		friendRequestsMenu.setBounds(420, 6, 132, 22);
		add(friendRequestsMenu);
		
		JMenu blackList = new JMenu("Blacklist");
		JMenuBar blackListMenu = new JMenuBar();
		blackListMenu.add(blackList);
		blackListMenu.setBounds(566, 6, 132, 22);
		add(blackListMenu);
		
		final ArrayList<JMenuItem> friendItems = new ArrayList<JMenuItem>();
		final ArrayList<JMenuItem> friendRequestItems = new ArrayList<JMenuItem>();
		final ArrayList<JMenuItem> blackListItems = new ArrayList<JMenuItem>();
		
		try{
			blackList.removeAll();
			//FriendsItems.clear();
			for(User blackListers: theApp.getLoggedinUser().getBlackList()){
				JMenuItem douche = new JMenuItem(blackListers.getFirstName()+" "+blackListers.getLastName());
				blackListItems.add(douche);
				
			}
			for(JMenuItem blackListers: blackListItems){
				blackList.add(blackListers);
			}
		
		}catch(NullPointerException n){
			//friendsList.add("No friends")
		}


		try{
			friendRequests.removeAll();
			//FriendsItems.clear();
			for(User friendrequests: theApp.getLoggedinUser().getFriendRequests()){
				JMenuItem request = new JMenuItem(friendrequests.getFirstName()+" "+friendrequests.getLastName());
				friendRequestItems.add(request);
				
			}
			for(JMenuItem friendrequest: friendRequestItems){
				friendRequests.add(friendrequest);
			}
		
		}catch(NullPointerException n){
			//friendsList.add("No friends")
		}
		 		
		try{
			friendsList.removeAll();
			//FriendsItems.clear();
			for(User friends: theApp.getLoggedinUser().getFriends()){
				JMenuItem friend = new JMenuItem(friends.getFirstName()+" "+friends.getLastName());
				friendItems.add(friend);
				
			}
			for(JMenuItem friends: friendItems){
				friendsList.add(friends);
			}
		
		}catch(NullPointerException n){
			//friendsList.add("No friends")
		}
		
		
		try{
			theApp.Refresh();
		}catch(ConcurrentModificationException cme){
			theApp.Refresh();
		}
		
		try{
			//System.out.println(theApp.getLoggedinUser().getMyEvent().CreatorEventString());
			myEventListContents.clear();
			myEventListContents.addElement(theApp.getLoggedinUser().getMyEvent());
		}catch(NullPointerException e){
			myEventListContents.addElement("No event created");
		}
		
		
		
		try{
			joinedEventsListContents.clear();
		  for(Event joinedEvents : theApp.getLoggedinUser().getJoinedEvents()){
			joinedEventsListContents.addElement(joinedEvents);
		  }
		}catch(NullPointerException e){
			myEventListContents.addElement("No events joined");
		}
		
		for(final JMenuItem blackListers: blackListItems){
			blackListers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0){
					User blackLister = theApp.getLoggedinUser().getBlackList().get(blackListItems.indexOf(blackListers));
					JFrame windowFrame = new JFrame();
					BlacklistPage theBlackListPage = new BlacklistPage(theApp,blackLister,theMainFrame,windowFrame);
					theBlackListPage.setVisible(true);
					windowFrame.setBounds(theBlackListPage.getBounds());
					windowFrame.setContentPane(theBlackListPage);
					windowFrame.setVisible(true);
				}
			});
		}
		
		for(final JMenuItem friendRequest: friendRequestItems){
			friendRequest.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0){
					User friendRequestsender = theApp.getLoggedinUser().getFriendRequests().get(friendRequestItems.indexOf(friendRequest));
					FriendRequestWindow theFriendRequestWindow = new FriendRequestWindow(theApp,friendRequestsender,theMainFrame);
					theFriendRequestWindow.setVisible(true);
					JFrame windowFrame = new JFrame();
					windowFrame.setBounds(theFriendRequestWindow.getBounds());
					windowFrame.setContentPane(theFriendRequestWindow);
					windowFrame.setVisible(true);
				}
			});
		}
		
		for(final JMenuItem friends: friendItems){
			friends.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0){
					FriendPage theFriendPage = new FriendPage(theApp,theApp.getLoggedinUser().getFriends().get(friendItems.indexOf(friends)),
							theMainFrame);
					theMainFrame.setBounds(theMainFrame.smallX, theMainFrame.smallY, 
							 theMainFrame.smallWidth, theMainFrame.smallHeight);
					theMainFrame.setContentPane(theFriendPage);
					setVisible(false);
					theFriendPage.setVisible(true);
					
					
				}
				
			});
		}
			
		
		JButton createEvenBtn = new JButton("Create Event");
		createEvenBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventCreatorPage theEventCreator = new EventCreatorPage(theApp,theMainFrame);
				 theMainFrame.setBounds(theMainFrame.smallX, theMainFrame.smallY, 
						 theMainFrame.smallWidth, theMainFrame.smallHeight);
				theMainFrame.setContentPane(theEventCreator);
			    theEventCreator.setVisible(true);
			    setVisible(false);
			}
		});
		createEvenBtn.setBounds(6, 173, 117, 29);
		add(createEvenBtn);
		
		JButton signOutButton = new JButton("Sign Out");
		signOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage loginPage = new LoginPage(theApp,theMainFrame);
				theMainFrame.setBounds(theMainFrame.smallX, theMainFrame.smallY, 
						 theMainFrame.smallWidth, theMainFrame.smallHeight);
				loginPage.setVisible(true);
				setVisible(false);
				theMainFrame.setContentPane(loginPage);
			}
		});
		signOutButton.setBounds(800, 1, 117, 29);
		add(signOutButton);
		
		final JLabel requestConfirmation1 = new JLabel("");
		requestConfirmation1.setForeground(Color.GREEN);
		requestConfirmation1.setBounds(789, 55, 200, 29);
		add(requestConfirmation1);
		
		final JLabel requestConfirmation2 = new JLabel("");
		requestConfirmation2.setForeground(Color.GREEN);
		requestConfirmation2.setBounds(789, 470, 200, 29);
		add(requestConfirmation2);
		
		JButton searchEventsButton = new JButton("Search Events");
		searchEventsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				theApp.getLoggedinUser().setAvaliableEvents(new ArrayList<Event>());
				availableEventsListContents.clear();
				if(theApp.getEvents().isEmpty()){
					errorLbl.setText("No Events Happening..bummer");
				}
				else{
					for(Event potentialEvents : theApp.getEvents()){
						theApp.getLoggedinUser().SearchEvent(potentialEvents);
					}
					if(!theApp.getLoggedinUser().getAvaliableEvents().contains(theApp.getLoggedinUser().getMyEvent())&&
							theApp.getLoggedinUser().getMyEvent()!=null){
						    theApp.getLoggedinUser().getAvaliableEvents().add(theApp.getLoggedinUser().getMyEvent());
					}
					if(theApp.getLoggedinUser().getAvaliableEvents().isEmpty()){
						errorLbl.setText("No Events Happening...bummer");
					}
					else{
						for(Event agreeableEvents : theApp.getLoggedinUser().getAvaliableEvents()){
							agreeableEvents.setEventLooker(theApp.getLoggedinUser());
							availableEventsListContents.addElement(agreeableEvents);
						}
					}
				}
			}
		});
		
		searchEventsButton.setBounds(6, 405 , 117, 29);
		add(searchEventsButton);
		
		final JList eventCreatorJoinedUsersList = new JList(eventCreatorJoinedUsersContents);
		eventCreatorJoinedUsersList.setBounds(myEventsX+750, myEventsY-20, 200, 100);
		JScrollPane eventCreatorJoinedUsersPane = new JScrollPane(eventCreatorJoinedUsersList);
		eventCreatorJoinedUsersPane.setBounds(eventCreatorJoinedUsersList.getBounds());
		add(eventCreatorJoinedUsersPane);
		
		final JList attendeeJoinedUsersList = new JList(eventAttendeeJoinedUsersContents);
		attendeeJoinedUsersList.setBounds(myEventsX+750, myEventsY+400, 200, 100);
		JScrollPane attendeeJoinedUsersPane = new JScrollPane(attendeeJoinedUsersList);
		attendeeJoinedUsersPane.setBounds(attendeeJoinedUsersList.getBounds());
		add(attendeeJoinedUsersPane);
		
		final JList joinedEventsList = new JList(joinedEventsListContents);
		joinedEventsList.setBackground(new Color(0, 204, 0));
		joinedEventsList.setBounds(this.joinedEventsX, this.joinedEventsY, 
				this.joinedEventsWidth, this.joinedEventsHeight);
		
		final JList avaliableEventsList = new JList(availableEventsListContents);
		avaliableEventsList.setForeground(Color.MAGENTA);
		avaliableEventsList.setBackground(Color.BLACK);
		avaliableEventsList.setBounds(this.availableEventsX, this.availableEventsY, 
				this.availableEventsWidth, this.availableEventsHeight);
		
		final JList myEventList = new JList(myEventListContents);
		myEventList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		myEventList.setBackground(new Color(0, 153, 255));
		myEventList.setBounds(this.myEventsX, this.myEventsY, 
				this.myEventsWidth, this.myEventsHeight);
		
		
		JScrollPane avaliableEventspane = new JScrollPane(avaliableEventsList);
		avaliableEventspane.setBounds(avaliableEventsList.getBounds());
		add(avaliableEventspane);
		
		JScrollPane joinedEventspane = new JScrollPane(joinedEventsList);
		joinedEventspane.setBounds(joinedEventsList.getBounds());
		add(joinedEventspane);
		
		JScrollPane myEventPane = new JScrollPane(myEventList);
		myEventPane.setBounds(myEventList.getBounds());
		add(myEventPane);
		
		JButton addEventButton = new JButton("Join Event");
		addEventButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(avaliableEventsList.isSelectionEmpty()){
					errorLbl.setText("Please select and event to join");
				}
				else{
				Event aboutToJoinEvent = (Event) avaliableEventsList.getSelectedValue();
				if(theApp.getLoggedinUser().getJoinedEvents().isEmpty()){
					//System.out.println("1");
					theApp.getLoggedinUser().getJoinedEvents().add(aboutToJoinEvent);
					aboutToJoinEvent.getJoinedUsers().add(theApp.getLoggedinUser());
					joinedEventsListContents.addElement(avaliableEventsList.getSelectedValue());
					
				}
				else{
						if(theApp.getLoggedinUser().getJoinedEvents().contains(aboutToJoinEvent)){
							errorLbl.setText("You have already joined this event");	
						}
					
					    else{
					    	theApp.getLoggedinUser().getJoinedEvents().add(aboutToJoinEvent);
					    	aboutToJoinEvent.getJoinedUsers().add(theApp.getLoggedinUser());
					    	joinedEventsListContents.addElement(aboutToJoinEvent);

					}
				}
			}
		}
				
	});
		
		addEventButton.setBounds(400, 405, 117, 29);
		add(addEventButton);
		
		JButton showMyEventAttendees = new JButton("Show Attendees");
		showMyEventAttendees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(theApp.getLoggedinUser().getMyEvent()==null){
					errorLbl.setText("Create an event first");
				}
				else{
					eventCreatorJoinedUsersContents.clear();
					if(myEventList.isSelectionEmpty()){
						errorLbl.setText("Please select the event");
					}
					else{
						Event myEvent = ((Event) myEventList.getSelectedValue());
							if(myEvent.getJoinedUsers().isEmpty()){
								eventCreatorJoinedUsersContents.addElement("Er..uhm..awkward");
							}
							else{
								for(User joinedUsers : myEvent.getJoinedUsers()){
								eventCreatorJoinedUsersContents.addElement(joinedUsers);
					            }
					}		
				}
			  }
				
			}
		});
		showMyEventAttendees.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		showMyEventAttendees.setBounds(400, 173, 117, 29);
		add(showMyEventAttendees);
		
		final JButton kickButton = new JButton("Kick");
		kickButton.setBounds(800, 190, 117, 29);
		add(kickButton);
		
		JButton sendRequestButton1 = new JButton("Send Friend Request");
		sendRequestButton1.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		sendRequestButton1.setBounds(780, 220, 156, 29);
		add(sendRequestButton1);
		
		JButton sendRequestButton2 = new JButton("Send Friend Request");
		sendRequestButton2.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		sendRequestButton2.setBounds(780, 605, 156, 29);
		add(sendRequestButton2);
		
		sendRequestButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(attendeeJoinedUsersList.isSelectionEmpty()){
					errorLbl.setText("Please select someone.");
				}
				else{
					User potentialNewFriend = (User) attendeeJoinedUsersList.getSelectedValue();
					if(potentialNewFriend.equals(theApp.getLoggedinUser())){
						errorLbl.setText("Narcissist?");
					}
					else{
						theApp.getLoggedinUser().SendFriendRequest(potentialNewFriend);
						requestConfirmation2.setForeground(Color.GREEN);
						requestConfirmation2.setText("Friend Request Sent!");
					}
				}
			}
		});

		
		sendRequestButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(eventCreatorJoinedUsersList.isSelectionEmpty()){
					errorLbl.setText("Please select someone.");
				}
				else{
					User potentialNewFriend = (User) eventCreatorJoinedUsersList.getSelectedValue();
					if(potentialNewFriend.equals(theApp.getLoggedinUser())){
						errorLbl.setText("Narcissist?");
					}
					else{
						theApp.getLoggedinUser().SendFriendRequest(potentialNewFriend);
						requestConfirmation1.setForeground(Color.GREEN);
						requestConfirmation1.setText("Friend Request Sent!");
					}
				}
			}
		});
		
		kickButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(eventCreatorJoinedUsersList.isSelectionEmpty()){
					errorLbl.setText("Please select someone to kick");
				}
				else{
					User kickedUser = (User) eventCreatorJoinedUsersList.getSelectedValue();
					if(kickedUser.equals(theApp.getLoggedinUser())){
						errorLbl.setText("Why are you trying to kick yourself?");
					}
					else{
					eventCreatorJoinedUsersContents.removeElement(kickedUser);
					theApp.getLoggedinUser().getMyEvent().getJoinedUsers().remove(kickedUser);
					kickedUser.getJoinedEvents().remove(theApp.getLoggedinUser().getMyEvent());
					theApp.getLoggedinUser().getMyEvent().getKickedUsers().add(kickedUser);
					}
				}
			}
		});
		
		JButton deleteEventButton = new JButton("Delete Event");
		deleteEventButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(theApp.getLoggedinUser().getMyEvent()==null){
					errorLbl.setText("Create an event first");
				}
				else{
					if(myEventList.isSelectionEmpty()){
						errorLbl.setText("Please select an event to delete");
					}
					else{
						myEventListContents.removeElement(theApp.getLoggedinUser().getMyEvent());
						joinedEventsListContents.removeElement(theApp.getLoggedinUser().getMyEvent());
						theApp.getLoggedinUser().getMyEvent().setShouldDelete(true);
						theApp.Refresh();
					}
				}
			}
		});
		deleteEventButton.setBounds(164, 173, 117, 29);
		add(deleteEventButton);
		
		JButton bailButton = new JButton("Bail");
		bailButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(theApp.getLoggedinUser().getJoinedEvents().isEmpty()){
					errorLbl.setText("You haven't joined an Event to bail on");
				}
				else{
					if(joinedEventsList.isSelectionEmpty()){
						errorLbl.setText("Please select an Event to bail on");
					}
					else{
						Event bailedEvent = (Event) joinedEventsList.getSelectedValue();
							if(bailedEvent.equals(theApp.getLoggedinUser().getMyEvent())){
								errorLbl.setText("You can't bail on your own event pussy");
							}
							else{
								theApp.getLoggedinUser().getJoinedEvents().remove(bailedEvent);
								joinedEventsListContents.removeElement(bailedEvent);
							}
					}
				}
			}
		});
		bailButton.setBounds(400, 605, 117, 29);
		add(bailButton);
		
		JButton showAttendees = new JButton("Show Attendees");
		showAttendees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(theApp.getLoggedinUser().getJoinedEvents().isEmpty()){
					errorLbl.setText("No Events joined");
				}
				else{
					eventAttendeeJoinedUsersContents.clear();
					if(joinedEventsList.isSelectionEmpty()){
						errorLbl.setText("Please select an event");
					}
					else{
						Event joinedEvent = ((Event) joinedEventsList.getSelectedValue());
							if(joinedEvent.getJoinedUsers().isEmpty()){
								errorLbl.setText("Er..uhm..awkward");
							}
							else{
								for(User joinedUsers : joinedEvent.getJoinedUsers()){
								eventAttendeeJoinedUsersContents.addElement(joinedUsers);
					            }
					}		
				}
			  }
				
			}
			
		});
		showAttendees.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		showAttendees.setBounds(6, 605, 117, 29);
		add(showAttendees);
		
		JButton blackListBtn1 = new JButton("Blacklist");
		blackListBtn1.setOpaque(true);
		blackListBtn1.setBackground(Color.BLACK);
		blackListBtn1.setForeground(Color.RED);
		blackListBtn1.setBounds(800, 250, 117, 29);
		add(blackListBtn1);
		
		blackListBtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(eventCreatorJoinedUsersList.isSelectionEmpty()){
					errorLbl.setText("Please select someone to Blacklist");
				}
				else{
					User douche = (User) eventCreatorJoinedUsersList.getSelectedValue();
					if(douche.equals(theApp.getLoggedinUser())){
						errorLbl.setText("Why are you trying to Blacklist yourself?");
					}
					else{
					errorLbl.setText("");
					eventCreatorJoinedUsersContents.removeElement(douche);
					theApp.getLoggedinUser().BlackList(douche);
					kickButton.doClick();
					//douche.getBlackList().add(theApp.getLoggedinUser());
					
					LoggedinPage theLoggedinPage = new LoggedinPage(theApp,theMainFrame);
					theMainFrame.getContentPane().setVisible(false);
					theMainFrame.setContentPane(theLoggedinPage);
					theLoggedinPage.setVisible(true);
					}
				}
				

			}
		});
		
		JButton blackListBtn2 = new JButton("Blacklist");
		blackListBtn2.setOpaque(true);
		blackListBtn2.setBackground(Color.BLACK);
		blackListBtn2.setForeground(Color.RED);
		blackListBtn2.setBounds(800, 635, 117, 29);
		add(blackListBtn2);
		
		blackListBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(attendeeJoinedUsersList.isSelectionEmpty()){
					errorLbl.setText("Please select someone to Blacklist");
				}
				else{
					User douche = (User) attendeeJoinedUsersList.getSelectedValue();
					if(douche.equals(theApp.getLoggedinUser())){
						errorLbl.setText("Why are you trying to Blacklist yourself?");
					}
					else{
					errorLbl.setText("");
					theApp.getLoggedinUser().BlackList(douche);
					
					LoggedinPage theLoggedinPage = new LoggedinPage(theApp,theMainFrame);
					theMainFrame.getContentPane().setVisible(false);
					theMainFrame.setContentPane(theLoggedinPage);
					theLoggedinPage.setVisible(true);
					}
				}
				

			}
		});
		if(!theApp.getLoggedinUser().getFriendRequests().isEmpty()){
			friendRequests.setBackground(Color.GREEN);
		}
	}
		
		
			
			
			
		
		
		
	

	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
}
