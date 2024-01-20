package A1bWinter;

import java.util.Scanner;


public class ConsoleUI implements UI
{
    private final Scanner     input;
    private AddressBook addressBook;

    public ConsoleUI()
    {
        input       = new Scanner(System.in);
    }
    
    //reads the users choice from a Scanner
    public int readChoice(Scanner input)  {
    	
    	displayMsg("choice ?");
    	int choice = 0;
    	try {
    		choice = input.nextInt();
        	input.nextLine();
    	} 
    	
    	catch(Exception e) {
    		displayErrorMsg("Invalid input. Please type in number 1-5");
    	}
    	if (choice < 1 || choice > 5) {
    		displayErrorMsg("Your choice is incorrect, please try again");
    	}

    	return choice;
    	
    }

    
    /**reads in the Person data from a Scanner 
    and returns the new Person.
    This method is used for addPerson method*/
    
	@Override
	public Person readPerson() {
		String name = "";
		String phoneNumber = "";
		
		name = readName();
		displayMsg("Enter person's phone");
		phoneNumber = input.nextLine();
		
		Person person = new Person(name,phoneNumber);
		return person;
	}
	
	
	/**reads in the name of a Person from a Scanner
	This is used for findPerson method
	*/
	@Override
	public String readName() {
		String name = "";
		displayMsg("Enter person's name");
		name = input.nextLine();
		return name;
	}
	
	
    //displays a single Person’s data
	//display is private
	@Override
	public void display(Person person) {
		// TODO Auto-generated method stub
		addressBook.display(person);
	}
	
	
	//displays all people in the database
	// why add parameter to this method
	@Override
	public void displayAll(Person[] people) {
		// TODO Auto-generated method stub
		addressBook.displayAll();
	}
	

	//displays the available menu
	//this mean use the displayMsg() method?
	public void displayMenu() {	
		displayMsg("1) Add");
		displayMsg("2) Delete");
		displayMsg("3) Search");
		displayMsg("4) Display All");
		displayMsg("5) Exit");
	}
	
	//displays the String message 
	//passed on the user interface
	@Override
	public void displayErrorMsg(String msg) {
		// TODO Auto-generated method stub
		System.out.println(msg);
	}
	
	//displays the String message 
	//passed on the user interface
	@Override
	public void displayMsg(String msg) {
		// TODO Auto-generated method stub
		System.out.println(msg);
		
	}
	
	
	//performs the address book functions
	@Override
	public void run(AddressBook book) {
		// TODO Auto-generated method stub
		boolean check = true;
		
		int choice = 0;
		do {
			displayMenu();
			choice = readChoice(input);
			switch(choice) {
				case 1:
					book.addPerson();
					break;
				case 2:
					book.deletePerson();
					break;
				case 3:
					book.findPerson();
					break;
				case 4:
					book.displayAll();
					break;
				default :
					check = false;
			}
		} while (check);
		
	}
			
	

}

