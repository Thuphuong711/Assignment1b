import java.util.Scanner;

public class ConsoleUI implements UI {
    private final Scanner input;
    private AddressBook addressBook;

    public ConsoleUI() {
        input = new Scanner(System.in);
    }

    //reads the users choice from a Scanner
    public int readChoice(Scanner input) {
        displayMsg("choice ?");
        int choice = 0;
        try {
            choice = input.nextInt();
            input.nextLine();
        } catch (Exception e) {
            displayErrorMsg("Invalid input. Please type in number 1-5");
        }
        if (choice < 1 || choice > 5) {
            displayErrorMsg("Your choice is incorrect, please try again");
        }

        return choice;
    }


    /**
     * reads in the Person data from a Scanner
     * and returns the new Person.
     * This method is used for addPerson method
     */

    @Override
    public Person readPerson() {
        final String name = readName();
        displayMsg("Enter person's phone");
        final String phoneNumber = input.nextLine();
        return new Person(name, phoneNumber);
    }


    /**
     * reads in the name of a Person from a Scanner
     * This is used for findPerson method
     */
    @Override
    public String readName() {
        displayMsg("Enter person's name");
        return input.nextLine();
    }

    @Override
    public void display(Person person) {
        if (person != null) {
            this.addressBook.display(person);
        }
    }

    //displays all people in the database
    @Override
    public void displayAll(Person[] people) {
        for (Person person: people) {
            this.addressBook.display(person);
        }
    }


    //displays the available menu
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
    public void displayErrorMsg(String errorMessage) {
        System.out.printf("Error: %s\n", errorMessage);
    }

    //displays the String message
    //passed on the user interface
    @Override
    public void displayMsg(String msg) {
        System.out.println(msg);
    }


    //performs the address book functions
    @Override
    public void run(AddressBook book) {
        addressBook = book;
        while (true) {
            displayMenu();
            final int choice = readChoice(input);
            switch (choice) {
                case 1:
                    addressBook.addPerson();
                    break;
                case 2:
                    addressBook.deletePerson();
                    break;
                case 3:
                    Person person = addressBook.findPerson();
                    display(person);
                    break;
                case 4:
                    addressBook.displayAll();
                    break;
                default:
                    return;
            }
        }
    }
}
