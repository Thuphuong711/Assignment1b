/*
 * AddressBook.java

 */


public class AddressBook
{
    private final Database database;
    private final UI ui;

    public AddressBook(final UI u)
    {
        ui       = u;
        database = new Database();
    }

    /**
     * reads a Person from the user interface 
     * and adds them to the database
     * 
     */
    public void addPerson()
    {
    	Person person = ui.readPerson();
    	database.add(person);
    }

    /**
     * reads a Person’s name from the user interface 
     * and tries to delete them 
     */
    public void deletePerson()
    {
    	String name = ui.readName();
    	if (this.remove(name)) {
    		ui.displayMsg(name + " was deleted successfully");
    	} else {
    		ui.displayErrorMsg("Could not delete " + name );
    	}
    }

    /**
     * reads a Person’s name from the user interface 
     * and tries to find them in the database. 
     * If not found the error message “No such person” is displayed.
     */
    public void findPerson()
    {
    	String name = ui.readName();
    	Person foundPerson = this.search(name);
    	if (foundPerson == null) {
    		ui.displayErrorMsg("There is no such person in the database");
    	} else {
    		this.display(foundPerson);
    	}

    }

    /**
     * to check whether can remove or not, then remove
     * @param name
     * @return
     */
    private boolean remove(final String name)
    {
        return (database.removeByName(name) != null);
    }

    /**
     * 
     * @param name
     * @return
     */
    private Person search(final String name)
    {
        return (database.findByName(name));
    }

    
    /**
     * displays all people in the database on the user interface
     */
    public void displayAll()
    {
    	Person[] storage = database.getAll();
    	int len = storage.length;
    	if (len == 0) {
    		ui.displayErrorMsg("There is no record in the database");
    	}
    	else {
    		System.out.printf("%-20s%-15s%n","Name","PhoneNumber");
	    	for (int i = 0; i < len; i++) {
	    		System.out.printf("%-20s%-15s%n", storage[i].getName(), storage[i].getPhoneNumber());
	    		
	    	}
    	}
    }


    /*I asked you about why this display() method has private access modifier.
      I see the display() method in ConsoleUI class, but because this display() method in AddressBook class
      is private, so I cannot call this method in ConsoleUI class */

     /* Moreover, I don't really understand why we still need display() method in ConsoleUI class, for what purpose?
     If we want to search the name of a person, we just need to call findPerson class via AddressBook object(named book),
     I wonder why we still need display() and displayAll() method in ConsoleUI class as we can call these methods from AddressBook class,
     I feel it like redundancy?/
     
    
    /**
     * displays the requested person on the user interface
     * @param person
     */
    private void display(final Person person)
    {
    	System.out.printf("%-20s%-15s%n","Name","PhoneNumber");
    	Person found = database.findByName(person.getName());
    	String foundName = found.getName();
    	String foundNumber = found.getPhoneNumber();
    	System.out.printf("%-20s%-15s%n",foundName,foundNumber);
    }
}
