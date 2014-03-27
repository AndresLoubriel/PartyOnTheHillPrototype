/**
 * 
 * @author ANDRES LOUBRIEL
 *
 *
 *ADD PRIVACY LEVELS TO EVENTS
 *
 */
package POHApplicationPrototype;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Scanner;


public class POHApplication {
	ArrayList<User> Users = new ArrayList<User>();
	User LoggedinUser;
	ArrayList<Event> Events = new ArrayList<Event>();
	int quit = 0;
	
	String loginFirstName;
	String loginLastName;
	String loginPassWord;
	
	
	
	public int Login2(){
		for(User item : this.getUsers()){
			//System.out.println(item);
			if(this.loginFirstName.equals(item.getFirstName())&&
					this.loginLastName.equals(item.getLastName())&&
					this.loginPassWord.equals(item.getUserPassword())){
				LoggedinUser = item;
				return 0;
				
			}
			
		}
		
		return 2;
	}
	 
	public void Refresh(){
		try{
		for(Event allEvents : Events){
			if(allEvents.isShouldDelete()){
				Events.remove(allEvents);
				for(User allUsers : Users){
					allUsers.getAvaliableEvents().remove(allEvents);
					allUsers.getJoinedEvents().remove(allEvents);
					if(allUsers.getMyEvent()!=null){
						if(allUsers.getMyEvent().equals(allEvents)){
							allUsers.setMyEvent(null);
						}
					}
				}
			}
			allEvents = null;
		 }
	  }catch(ConcurrentModificationException cme){
		  for(Event allEvents : Events){
				if(allEvents.isShouldDelete()){
					Events.remove(allEvents);
					for(User allUsers : Users){
						allUsers.getAvaliableEvents().remove(allEvents);
						allUsers.getJoinedEvents().remove(allEvents);
						if(allUsers.getMyEvent()!=null){
							if(allUsers.getMyEvent().equals(allEvents)){
								allUsers.setMyEvent(null);
							}
						}
					}
				}allEvents = null;
			 }
	     }
	}
	
	public int Login(){
		if(quit == 2){
			return 0;
		}
		Scanner usernameReader = new Scanner(System.in);
		System.out.println("Type in your Username");
	    String Username = usernameReader.nextLine();
	    //System.out.println(Username);
		for(User item : this.getUsers()){
			if(Username.equals(item.getFirstName())){
				User FirstLevel = item;
				System.out.println("Username Found! Enter Password:");
				Scanner passwordReader = new Scanner (System.in);
				String password = passwordReader.nextLine();
				if(password.equals(FirstLevel.getUserPassword())){
					LoggedinUser = FirstLevel;
					System.out.println("Signed in!");
					//System.out.println(LoggedinUser);
					//Greeting();
					Loggedin();
					System.out.println(Username);
					quit = 2;
					return 0;
					
				}
				else{
					System.out.println("Wrong Password");
					Login();
				}
			}		
		
	    }
		
		//System.out.println(Username);
		System.out.println("Username not found");
		System.out.println("Would you like to SignUp? Yes or No?");
		Scanner signupReader = new Scanner(System.in);
		String Response = signupReader.nextLine();
		if(Response.equals("Yes")){
			SignUp();
			return 0;
		}
	    Login();
	    return 0;
	}
	
	//public void Greeting(){
	//	System.out.println("Hello " + LoggedinUser.getFirstName());
	//}
	
	public void Loggedin(){
		System.out.println("Would you like to 'A' Search for an Event, or 'B' Create Event");
		System.out.println(" 'C' Show your Event, Or 'D' sign out?");
		Scanner LoggedinReader = new Scanner(System.in);
		//Scanner LoggedinReader2 = new Scanner(System.in);
		String Response = LoggedinReader.nextLine();
		if(Response.equals("A")){
			LoggedinUser.setAvaliableEvents(new ArrayList<Event>());
			
			if(getEvents().isEmpty()){
				System.out.println("No Events Happening..bummer");
				Loggedin();
			}
			int i = 0;
			while(i <= Events.size()-1){
				LoggedinUser.SearchEvent(Events.get(i));
				i++;
			}
			if(!LoggedinUser.getAvaliableEvents().contains(LoggedinUser.getMyEvent())&&
					LoggedinUser.getMyEvent()!=null){
				LoggedinUser.getAvaliableEvents().add(LoggedinUser.getMyEvent());
			}
			if(LoggedinUser.getAvaliableEvents().isEmpty()){
				System.out.println("No Events Happening..bummer");
				Loggedin();
			}
			for(Event events : LoggedinUser.getAvaliableEvents()){
				 
				  System.out.println(events);
			}
			Loggedin();
		}
		 if(Response.equals("B")){
			Event NewEvent = LoggedinUser.CreateEvent();
			Events.add(NewEvent);
			NewEvent.getJoinedUsers().add(LoggedinUser);
			
			System.out.println("Here's your Event " + NewEvent);
			Loggedin();
		}
		 if(Response.equals("C")){
			 if(LoggedinUser.getMyEvent()==null){
				 System.out.println("You have not created an event.");
				 System.out.println("Returning you to home..");
				 Loggedin(); 
			 }
			 else{
				 System.out.println(LoggedinUser.getMyEvent());
				 System.out.println("Returning you to home..");
				 Loggedin();
			 }
		 }
		 
		if(Response.equals("D")){
			System.out.println("Signing out..");
			LoggedinUser=null;
			Login();
		}
		else{
			System.out.println("Please choose A, B or C");
			Loggedin();
		}
		Loggedin();
	}
	
	public static void main(String[] args){
		//Event testEvent = new Event("Meow?","Meow!","Test1","CatHouse",10,56,"pew","pew",true,true);
		User Andres = new User("Andres","Loubriel","Andres",19,1);
		User Andres1 = new User("Andres1","Loubriel1","Andres1",19,1);
		User Andres2 = new User("Andres2","Loubriel2","Andres2",19,1);
		//testEvent.getJoinedUsers().add(Andres);
		//testEvent.getJoinedUsers().add(Andres1);
		POHApplication test = new POHApplication();
		test.getUsers().add(Andres);
		test.getUsers().add(Andres1);
		test.getUsers().add(Andres2);
		//test.getEvents().add(testEvent);
		test.Login();
	
	}
	

	public void SignUp(){
		String Username;
		String Password;
		String Lastname;
		int Age;
		int Gender;
		Scanner FirstnameReader = new Scanner(System.in);
		System.out.println("What is your first name?");
		Username = FirstnameReader.nextLine();
		
		System.out.println("What is your last name?");
		Scanner LastnameReader = new Scanner(System.in);
		Lastname = LastnameReader.nextLine();
		
		System.out.println("Make a Password");
		Scanner PasswordReader = new Scanner(System.in);
		Password = PasswordReader.nextLine();
		
		System.out.println("How old are you?");
		Scanner AgeReader = new Scanner(System.in);
		Age = AgeReader.nextInt();
		
		System.out.println("Are you Male or Female?");
		Scanner GenderReader = new Scanner(System.in);
		if(GenderReader.nextLine().equals("Male")){
			Gender = 1;
		}
		else{
			Gender = 2;
		}
		User NewUser = new User(Username,Lastname, Password, Age, Gender);
		Users.add(NewUser);
		Login();
	
	}
	
	
	public ArrayList<User> getUsers() {
		return Users;
	}

	public void setUsers(ArrayList<User> users) {
		Users = users;
	}

	public User getLoggedinUser() {
		return LoggedinUser;
	}

	public void setLoggedinUser(User loggedinUser) {
		LoggedinUser = loggedinUser;
	}

	public ArrayList<Event> getEvents() {
		return Events;
	}

	public void setEvents(ArrayList<Event> events) {
		Events = events;
	}
	
}
