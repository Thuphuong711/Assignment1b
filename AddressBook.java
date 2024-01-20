/*
 * AddressBook.java

 */
public class AddressBook
{
    private final Database database;
    private final UI       ui;

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
        database.add(ui.readPerson());
    }

    /**
     * reads a Person’s name from the user interface 
     * and tries to delete them 
     */
    public void deletePerson()
    {
        String personName = ui.readName();
        if (this.remove(personName)) {
            ui.displayMsg(personName + " was deleted successfully");
        } else {
            ui.displayErrorMsg("Could not delete " + personName);
        }
    }

    /**
     * reads a Person’s name from the user interface 
     * and tries to find them in the database. 
     * If not found the error message “No such person” is displayed.
     */
    public Person findPerson()
    {
        String personName = ui.readName();
        Person person = this.search(personName);
        if (person == null) {
            ui.displayErrorMsg("There is no such person in the database");
            return null;
        }

        return person;
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
        Person[] people = database.getAll();
        if (people.length == 0) {
            ui.displayErrorMsg("There is no record in the database.");
            // return early
            return;
        }

        printPersonInfo("Name", "PhoneNumber");
        for (Person person : people) {
            printPersonInfo(person.getName(), person.getPhoneNumber());
        }
    }

    /**
    * displays the requested person on the user interface
    * @param person
    */
    public void display(final Person person)
    {
        printPersonInfo("Name", "PhoneNumber");
        printPersonInfo(person.getName(), person.getPhoneNumber());
    }

    private void printPersonInfo(String Name, String PhoneNumber) {
        System.out.printf("%-20s%-15s%n", Name, PhoneNumber);
    }
}