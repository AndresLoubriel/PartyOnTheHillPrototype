/**
 * 
 * @author ANDRES LOUBRIEL
 *
 */
package POHApplicationPrototype;
import java.util.ArrayList;
import java.util.Scanner;


public class User {
	private String firstName;
	private String lastName;
	private String UserPassword;
	private int userAge;
	private int userGender; //MALE = 1, FEMALE 2
	private Event myEvent;
	private ArrayList<Event> AvaliableEvents = new ArrayList <Event>();
	private ArrayList<Event> joinedEvents = new ArrayList <Event>();
	private ArrayList<User> Friends = new ArrayList <User>(); 
	private ArrayList<User> friendRequests = new ArrayList<User>();
	private ArrayList<User> blackList = new ArrayList<User>();
	
	
	public User(String aName,String alName, String Apass, int Anage, int Gen){
		firstName = aName;
		lastName = alName;
		UserPassword = Apass;
		userAge = Anage;
		userGender = Gen;
		myEvent = null;
		//AvaliableEvents = null;
	}
	
	Scanner keyboard = new Scanner(System.in);
	
	//User creates events
	public Event CreateEvent(){
		Event UserEvent=new Event(this.getFirstName(),this.getLastName(),this.CreateEventName(), this.CreateEventLocation(),this.CreateAgeMin(),
				this.CreateAgeMax(), this.CreateDesc(), this.CreateMessage(), this.CreateMaleAccess(),
				this.CreateFemaleAccess());		
		if(UserEvent.getAgeMax()<UserEvent.getAgeMin()){
			System.out.println("Your Age Maximum is smaller than your Age Minimum");
			System.out.println("You have been returned to Event Creation");
			return this.CreateEvent();
		}
		else{
			myEvent = UserEvent;
			return myEvent;
		}
	}	
	
	public void AddFriend(User friend){
		this.getFriends().add(friend);
		friend.getFriends().add(this);
		
	}
	
	public void SendFriendRequest(User potentialFriend){
		potentialFriend.getFriendRequests().add(this);
	}
	
	public String CreateEventName(){
		System.out.println("What would you like this Event to be called?");
		String aName = keyboard.nextLine();
		//String clearName = keyboard.nextLine();
		return aName;
		
	}
	public String CreateEventLocation(){
		System.out.println("Where would you like this Event to be held?");
		Scanner keyboard2 = new Scanner(System.in);
		String aLocation = keyboard2.nextLine();
		//String clearLoc = keyboard.nextLine();
		return aLocation;	
	}
	public int CreateAgeMin(){
		System.out.println("What would you like the age minimum to be?");
		Scanner keyboard3 = new Scanner(System.in);
		int aMin = keyboard3.nextInt();
	   // String clearmin = keyboard.nextLine();
		return aMin;
	}
	
	public int CreateAgeMax(){
		System.out.println("What would you like the age maximum to be?");
		Scanner keyboard3 = new Scanner(System.in);
		int aMax = keyboard3.nextInt();
		//String clearmax = keyboard.nextLine();
		return aMax;
	}
	
	public String CreateDesc(){
		Scanner keyboard3 = new Scanner(System.in);
		System.out.println("Would you like to provide a description of the Event? Yes or No?");
		if(keyboard3.nextLine().equals("Yes")){
			System.out.println("Please type it in!");{
				Scanner keyboard4 = new Scanner(System.in);
				String ADesc = keyboard4.nextLine();
				return ADesc;
			}
		}
		return null;	
	}
	
	public String CreateMessage(){
		Scanner keyboard4 = new Scanner(System.in);
		System.out.println("Would you like to provide a banner message to pop up immediatly"
				+ " when someone joins? Yes or No?");
		if(keyboard4.next().equals("Yes")){
			System.out.println("Please type it in!");{
				Scanner keyboard5 = new Scanner(System.in);
				String aMessage = keyboard5.nextLine();
				return aMessage;
			}
		}
		return null;	
	}
	
	public boolean CreateMaleAccess(){
		Scanner keyboard2 = new Scanner(System.in);
		System.out.println("Would you like males to be able to join? Yes or No?");
		if(keyboard2.nextLine().equals("Yes")){
			return true;
		}
		return false;
		
	}
	public boolean CreateFemaleAccess(){
		Scanner keyboard3 = new Scanner(System.in);
		System.out.println("Would you like females to be able to join? Yes or No?");
		if(keyboard3.nextLine().equals("Yes")){
			//String clearM = keyboard.nextLine();
			return true;
		}
		return false;
		
	}
	
	public void RemoveFromBlackList(User forgivenUser){
		this.getBlackList().remove(forgivenUser);
		if(!(this.getMyEvent()==null)){
			if(this.getMyEvent().getKickedUsers().contains(forgivenUser)){
				this.getMyEvent().getKickedUsers().remove(forgivenUser);
			}
		}
	}
	
	public void BlackList(User cuntrag){
		this.getBlackList().add(cuntrag);
		if(!(this.getMyEvent()==null)){
			this.getMyEvent().getKickedUsers().add(cuntrag);
			if(this.getMyEvent().getJoinedUsers().contains(cuntrag)){
				this.getMyEvent().getJoinedUsers().remove(cuntrag);
				cuntrag.getJoinedEvents().remove(this.getMyEvent());
			}
			else{
				
			}
		}
		else{
			
		}
	}
	
	public boolean CheckEvent(Event E){
		if((this.CheckAge(E)==true) && (this.CheckGender(E)==true)&&(!E.getKickedUsers().contains(this))){
			return true;

		}
		return false;			
	}
	
	
	public void SearchEvent(Event E){
		if(this.CheckEvent(E)){
			this.getAvaliableEvents().add(E);

		}
		
				
	}
	
	public boolean CheckAge(Event E){
		if(E.isNoAgeRestriction()){
			return true;
		}
		if(this.getUserAge() >= E.getAgeMin() && (this.getUserAge() <= E.getAgeMax())){
			return true;
		}
		return false;
	}
	
	public boolean CheckGender(Event E){
		if((this.getUserGender()==1) && (E.AreMalesAllowed()==true)){
			return true;
		}
		if((this.getUserGender()==2) && (E.AreFemalesAllowed()==true)){
			return true;
		}
		return false;
	}

	
	
	@Override
	public String toString() {
		String stringGender;
		if(userGender == 1){
			stringGender = "Male";
		}
		else{
			stringGender = "Female";
		}
		return  firstName +"\n" + " " + lastName
				+ "\n"  + " " + userAge+ "\n"
				+ " " + stringGender + "\n";
	}


	public String getFirstName() {
		return firstName;
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


	public void setUserName(String userName) {
		firstName = userName;
	}

	public ArrayList<Event> getAvaliableEvents() {
		return AvaliableEvents;
	}


	public void setAvaliableEvents(ArrayList<Event> avaliableEvents) {
		AvaliableEvents = avaliableEvents;
	}


	public String getUserPassword() {
		return UserPassword;
	}

	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int anAge) {
		userAge = anAge;
	}

	

	public int getUserGender() {
		return userGender;
	}


	public void setUserGender(int aGender) {
		userGender = aGender;
	}


	public Event getMyEvent() {
		return myEvent;
	}

	public void setMyEvent(Event myEvent) {
		this.myEvent = myEvent;
	}


	public ArrayList<User> getFriends() {
		return Friends;
	}


	public void setFriends(ArrayList<User> friends) {
		Friends = friends;
	}


	public ArrayList<Event> getJoinedEvents() {
		return joinedEvents;
	}


	public void setJoinedEvents(ArrayList<Event> joinedEvents) {
		this.joinedEvents = joinedEvents;
	}

	public ArrayList<User> getFriendRequests() {
		return friendRequests;
	}

	public void setFriendRequests(ArrayList<User> friendRequests) {
		this.friendRequests = friendRequests;
	}

	public ArrayList<User> getBlackList() {
		return blackList;
	}

	public void setBlackList(ArrayList<User> blackList) {
		this.blackList = blackList;
	}
	

}
