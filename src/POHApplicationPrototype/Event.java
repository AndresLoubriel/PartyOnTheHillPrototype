/**
 * 
 * @author ANDRES LOUBRIEL
 *
 *
 */
package POHApplicationPrototype;

import java.util.ArrayList;

public class Event {
	private String EventName;
	private String EventLocation;
	private int AgeMin;
	private int AgeMax;
	private String EventDescription;
	private String BannerMessage;
	private boolean areMalesAllowed;
	private boolean areFemalesAllowed;
	private String Creatorfirst;
	private String Creatorlast;
	private ArrayList<User> joinedUsers = new ArrayList<User>(); //TO DO: CREATE JOINED USER FUNCTIONS
	private ArrayList<User> kickedUsers = new ArrayList<User>();
	private User eventLooker;
	private boolean noAgeRestriction = false;
	private boolean shouldDelete = false;
	
	
	


	public Event(String Username, String Last ,String aName, String aLocation,int aMin, 
			int aMax, String aDesc, String aMess, boolean male, boolean female){
		EventName = aName;
		EventLocation = aLocation;
		AgeMin = aMin;
		AgeMax = aMax;
		EventDescription = aDesc;
		BannerMessage = aMess;
		areMalesAllowed = male;
		areFemalesAllowed = female;
		Creatorfirst = Username;
		Creatorlast = Last;
	}

	
	@Override
	public String toString(){
		return toStringAux(eventLooker);
	}
	
	public String toStringAux(User theUser) {
		/*Since I want to first check if the User is the creator I'm checking the
		 * User's Event to see if it's null so I can avoid The Null Pointer Exception Below
		 */
	//String noAgeRestriction;
	String malesAllowed = "No";
	String femalesAllowed = "No";
	if(this.AreFemalesAllowed()){
		femalesAllowed = "Yes";
	}
	if(this.AreMalesAllowed()){
		malesAllowed = "Yes";
	}
	
	//for(User item : this.getJoinedUsers()){
		if(theUser.getMyEvent()==null){
			if(this.getJoinedUsers().contains(theUser)){
				if(this.noAgeRestriction==false){
					//Joined User with Null Event, w/ Age Restriction
				return     BannerMessage +", " +"\n" +  
						   EventName +", "+"\n" +
						   "Created by: " + Creatorfirst+" " +"\n"+
						   Creatorlast+", " +"\n"+
						   "Event Location: "+ EventLocation +", " +"\n" +
						   "Minumum Age: " +  AgeMin +", " +"\n" +
					       "Maximum Age: " + AgeMax+", " +"\n" + 
						   EventDescription +", " +"\n" +
					       "Males Allowed? " + malesAllowed +", "+ "\n" + 
						   "Females Allowed? " + femalesAllowed ;
				}
				else{
					//Joined User with Null Event, without Age Restriction
					return    BannerMessage +", " +"\n" +  
		   			 		  EventName +", "+"\n" +
		   			 		  "Created by: " + Creatorfirst+" " +"\n"+
		   			 		  Creatorlast+", " +"\n"+
		   			 		  "Event Location: "+ EventLocation +", " +"\n" +
		   			 		  "No Age Restriction"+", " + "\n" +
		   			 		  EventDescription +", " +"\n" +
		   			 		  "Males Allowed? " + malesAllowed +", "+ "\n" + 
		   			 		  "Females Allowed? " + femalesAllowed ;
				}
			}
			else{
				//Potential Attendee with Null Event, w/ Age Restriction
				if(this.noAgeRestriction==false){
				return     EventName +", "+"\n" +
						   "Created by: " + Creatorfirst+" " +"\n"+
						   Creatorlast+", " +"\n"+
						   "Minumum Age: " +  AgeMin +", " +"\n" +
					       "Maximum Age: " + AgeMax+", " +"\n" + 
						   EventDescription +", " +"\n" +
					       "Males Allowed? " + malesAllowed +", "+ "\n" + 
						   "Females Allowed? " + femalesAllowed ;				}
				else{
				//Potential Attendee with Null Event, without Age Restriction
				return EventName +", "+"\n" +
			 		  "Created by: " + Creatorfirst+" " +"\n"+
			 		  Creatorlast+", " +"\n"+
			 		  "No Age Restriction" +", "+ "\n" +
			 		  EventDescription +", " +"\n" +
			 		  "Males Allowed? " + malesAllowed +", "+ "\n" + 
			 		  "Females Allowed? " + femalesAllowed ;
				}
			}
			
		}
		
		
		if(theUser.getMyEvent().equals(this)){
			if(noAgeRestriction==false){
			//User is Event Creator, w/ Age Restriction
			return  	 BannerMessage +", " +"\n" +  
			   			 EventName +", "+"\n" +
			   			"Created by: " + Creatorfirst+" " +"\n"+
			   			Creatorlast+", " +"\n"+
			   			"Event Location: "+ EventLocation +", " +"\n" +
			   			"Minumum Age: " +  AgeMin +", " +"\n" +
			   			"Maximum Age: " + AgeMax+", " +"\n" + 
			   			EventDescription +", " +"\n" +
			   			"Males Allowed? " + malesAllowed +", "+ "\n" + 
			   			"Females Allowed? " + femalesAllowed ;
			}
			else{
				//User is Event Creator, without Age Restriction
				return  BannerMessage +", " +"\n" +  
			 		    EventName +", "+"\n" +
			 		    "Created by: " + Creatorfirst+" " +"\n"+
			 		    Creatorlast+", " +"\n"+
			 		    "Event Location: "+ EventLocation +", " +"\n" +
			 		    "No Age Restriction" +", "+ "\n" +
			 		     EventDescription +", " +"\n" +
			 		    "Males Allowed? " + malesAllowed +", "+ "\n" + 
			 		    "Females Allowed? " + femalesAllowed ;
				
			}
	     } 
		
	
	   
		if(this.getJoinedUsers().contains(theUser)){
			if(noAgeRestriction == false){
				//User is an attendee, w/ Age Restriction
				return  BannerMessage +", " +"\n" +  
						EventName +", "+"\n" +
						"Created by: " + Creatorfirst+" " +"\n"+
						Creatorlast+", " +"\n"+
						"Event Location: "+ EventLocation +", " +"\n" +
						"Minumum Age: " +  AgeMin +", " +"\n" +
						"Maximum Age: " + AgeMax+", " +"\n" + 
						EventDescription +", " +"\n" +
						"Males Allowed? " + malesAllowed +", "+ "\n" + 
						"Females Allowed? " + femalesAllowed ;
			}
			else{
				  //User is an attendee, without Age Restriction
				  return BannerMessage +", " +"\n" +  
				  		 EventName +", "+"\n" +
				  		 "Created by: " + Creatorfirst+" " +"\n"+
				  		 Creatorlast+", " +"\n"+
				  		 "Event Location: "+ EventLocation +", " +"\n" +
				  		 "No Age Restriction" +", "+ "\n" +
				  		 EventDescription +", " +"\n" +
				  		 "Males Allowed? " + malesAllowed +", "+ "\n" + 
				  		 "Females Allowed? " + femalesAllowed ;
			}
		}
		else{
			if(noAgeRestriction == false){
			//User is a potential attendee, w/ Age Restriction 
		    return     EventName +", "+"\n" +
			   		   "Created by: " + Creatorfirst+" " +"\n"+
			   		   Creatorlast+", " +"\n"+
			   		   "Minumum Age: " +  AgeMin +", " +"\n" +
			   		   "Maximum Age: " + AgeMax+", " +"\n" + 
			   		   EventDescription +", " +"\n" +
			   		   "Males Allowed? " + malesAllowed +", "+ "\n" + 
			   		   "Females Allowed? " + femalesAllowed ;			
			}
			else{
				//User is a potential attendee, without Age Restriction 
				return     BannerMessage +", " +"\n" +  
	 		    		   EventName +", "+"\n" +
	 		    		   "Created by: " + Creatorfirst+" " +"\n"+
	 		    		   Creatorlast+", " +"\n"+
	 		    		   "Event Location: "+ EventLocation +", " +"\n" +
	 		    		   "No Age Restriction"+ ", " + "\n" +
	 		    		   EventDescription +", " +"\n" +
	 		    		   "Males Allowed? " + malesAllowed +", "+ "\n" + 
	 		    		   "Females Allowed? " + femalesAllowed ;
				
			}
		}
	}


	public boolean isNoAgeRestriction() {
		return noAgeRestriction;
	}


	public void setNoAgeRestriction(boolean noAgeRestriction) {
		this.noAgeRestriction = noAgeRestriction;
	}
	
	public String getEventName() {
		return EventName;
	}

	public void setEventName(String eventName) {
		EventName = eventName;
	}

	public String getEventLocation() {
		return EventLocation;
	}

	public void setEventLocation(String eventLocation) {
		EventLocation = eventLocation;
	}

	public int getAgeMin() {
		return AgeMin;
	}

	public void setAgeMin(int ageMin) {
		AgeMin = ageMin;
	}

	public int getAgeMax() {
		return AgeMax;
	}

	public void setAgeMax(int ageMax) {
		AgeMax = ageMax;
	}

	public String getEventDescription() {
		return EventDescription;
	}

	public void setEventDescription(String eventDescription) {
		EventDescription = eventDescription;
	}

	public String getBannerMessage() {
		return BannerMessage;
	}

	public void setBannerMessage(String bannerMessage) {
		BannerMessage = bannerMessage;
	}

	public boolean AreMalesAllowed() {
		return areMalesAllowed;
	}

	public void setAreMalesAllowed(boolean areMalesAllowed) {
		this.areMalesAllowed = areMalesAllowed;
	}

	public boolean AreFemalesAllowed() {
		return areFemalesAllowed;
	}

	public void setAreFemalesAllowed(boolean areFemalesAllowed) {
		this.areFemalesAllowed = areFemalesAllowed;
	}


	public ArrayList<User> getJoinedUsers() {
		return joinedUsers;
	}


	public User getEventLooker() {
		return eventLooker;
	}

	public void setEventLooker(User eventLooker) {
		this.eventLooker = eventLooker;
	}

	public void setJoinedUsers(ArrayList<User> joinedUsers) {
		this.joinedUsers = joinedUsers;
	}


	public boolean isShouldDelete() {
		return shouldDelete;
	}


	public void setShouldDelete(boolean shouldDelete) {
		this.shouldDelete = shouldDelete;
	}


	public ArrayList<User> getKickedUsers() {
		return kickedUsers;
	}


	public void setKickedUsers(ArrayList<User> kickedUsers) {
		this.kickedUsers = kickedUsers;
	}


}
