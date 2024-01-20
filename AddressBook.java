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

    public void addPerson()
    {
    }

    public void deletePerson()
    {
    }

    public void findPerson()
    {
    }

    private boolean remove(final String name)
    {
        return (database.removeByName(name) != null);
    }

    private Person search(final String name)
    {
        return (database.findByName(name));
    }

    public void displayAll()
    {
    }

    private void display(final Person person)
    {
    }
}
