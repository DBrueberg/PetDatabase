/* Devin Brueberg
 * CSC 422 Assignment 1 Part 2
 * PetDatabase.java
 * October 25, 2021
 * Updated(Initials, Date, Changes):
 * *****VERSION 1******
 *  (DAB, 10/30/2021, Added features to view the Pet objects in a table)
 *  (DAB, 10/30/2021, Added features to add Pets to the database)
 *  (DAB, 10/30/2021, Added a menu for user navigation)
 *
 * ****VERSION 2******
 *  (DAB, 10/30/2021, Added in features to search by pet name or age:
 *  results will be printed in a table)
 *  (DAB, 10/30/2021, Created overloaded methods for displayTable() that
 *  allow for the printing of tables based of a name or age search)
 *
 * ****VERSION 3******
 *  (DAB, 10/30/2021, Added in features to remove and update Pet objects
 *  in the database)
 *  (DAB, 10/30/2021, Changed the menu if/elseif to a cleaner case statement
 *  and added final variables for better readability)
 *  (DAB, 10/30/2021, Moved the Pet search features into their own methods
 *  for better readability)
 *
 * PetDatabase.java, Pet.java run together for Assignment 1 Part 2
 *
 */

// Package of this program
package csc422.csp.edu.bag;

// Importing needed packages for the program
import csc422.csp.edu.impl.Pet;

// Importing needed java packages for this program
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The PetDatabase program will allow the user to create and manage a
 * database consisting of Pet objects. The Pet object will hold both the
 * String name and int age of the Pet. The user will be given a menu that
 * will allow them to view all pets, add more pets, update a pet, remove a
 * pet, and search for a pet by name or age.
 *
 * Note: This program holds the pet objects in the virtual memory so as of
 * 10/25/2021 the data will not be saved upon closing the program.
 */
public class PetDatabase {
    // Initializing Scanner object for the program
    public static Scanner input = new Scanner(System.in);

    // Declaring final int variables to handle the menu selection
    public static final int VIEW_ALL_PETS = 1;
    public static final int ADD_PETS = 2;
    public static final int UPDATE_PET = 3;
    public static final int REMOVE_PET = 4;
    public static final int PET_BY_NAME = 5;
    public static final int PET_BY_AGE = 6;


    /**
     * The main method of PetDatabase. This method will control the flow of
     * the program by executing the desired methods as needed.
     *
     * @param args
     */
    public static void main(String[] args) {
        // The petDatabase will be an ArrayList<Pet>
        ArrayList<Pet> petDatabase = new ArrayList<>();
        // Test objects
        petDatabase.add(new Pet("Harry", 4));
        petDatabase.add(new Pet("Rob", 2));

        // Printing out the Program Title
        System.out.println("Pet Database Program.\n");

        // Running the menu and passing in the petDatabase
        mainMenu(petDatabase);
    }


    /**
     * The addPets() method will accept a petDatabase to reference.
     * It will then add Pet objects to this database until the user
     * types "done". It will then let the user know the pets have been
     * added and return to the caller.
     *
     * @param petDatabase - The petDatabase to be referenced for
     *                    adding in new Pet objects.
     */
    public static void addPets(ArrayList<Pet> petDatabase) {
        // Initializing the variables that will hold the name and age of
        // the Pet
        String name = "";
        int age = 0;

        // The Pet data will be input validated and will include a name and
        // age in the (name age) format
        do {
            // The user is asked to add a pet
            System.out.print("add pet (name age): ");
            // Whitespace is taken off the first part of input and it is
            // saved to the name
            name = input.next().strip();

            // If the name was not "done", an age will be accepted and the
            // Pet will be added to the database after input validation
            if (!name.equals("done")) {
                // Calling the intValidator() method to validate that the
                // user input is an int value for the age
                age = intValidator("Second input must be an age: ");

                // The name and age are used to construct and add a new Pet to
                // the database
                petDatabase.add(new Pet(name, age));
            }
        // While the name does not equal "done" the user will still be allowed
        // to enter more Pet objects
        } while (!name.equals("done"));

        // Some format text to show that the pets are added
        System.out.println("Pets added.\n");
    }


    /**
     * The overloaded displayPetTable() will accept a petDatabase as a
     * parameter. It will then make a recursive call to displayPetTable()
     * and print out every Pet in the database as well as display the number
     * of Pet objects in the set.
     *
     * @param petDatabase - ArrayList<Pet> database to print in a table.
     */
    public static void displayPetTable(ArrayList<Pet> petDatabase) {
        // Recursive call to displayPetTable and passing null as the second
        // parameter to print the full table
        displayPetTable(petDatabase, (ArrayList<Integer>)null);
    }


    /**
     * The overloaded displayPetTable() will accept a petDatabase and an
     * int age as parameters. It will then make a recursive call to
     * displayPetTable() after searching the Pet database for Pets that match
     * the age. It will then print out every Pet in the database that match
     * that age as well as display the total number of Pet objects found.
     *
     * @param petDatabase - ArrayList<Pet> database to print in a table.
     * @param age - int age of the pets to print.
     */
    public static void displayPetTable(ArrayList<Pet> petDatabase, int age) {
        // Initializing an ArrayList to hold the indices of the Pet objects where
        // the age matches
        ArrayList<Integer> indices = new ArrayList<>();

        // Iterating through the petDatabase and comparing the age. If they
        // match the index is added to the indices List
        for (int id = 0; id < petDatabase.size(); id++) {
            // Creating a temp variable to hold the current Pet for readability
            Pet tempPet = petDatabase.get(id);

            // If the age matches the parameter age, the index is added to indices
            if (tempPet.getAge() == age) {
                indices.add(id);
            }
        }

        // A recursive call is made to displayPetTable() with the new indices
        // List and the Pets matching the indices list are printed to the screen
        displayPetTable(petDatabase, indices);
    }


    /**
     * The overloaded displayPetTable() is the method that will actually print the
     * table to the screen. It will accept an ArrayList<Pet> and an ArrayList<Integer>
     * indices and print the Pet at the specified index locations. If null is passed
     * for the indices ArrayList, all the Pet objects in the petDatabase will be
     * printed to the screen. Last the total number of Pet objects in the set will
     * be printed to the screen.
     *
     * @param petDatabase - ArrayList<Pet> database to print in a table.
     * @param indices - ArrayList<Integer> indices that will reference the indices in the
     *                petDatabase. If this value is null, the full database is printed to the
     *                screen.
     */
    public static void displayPetTable(ArrayList<Pet> petDatabase, ArrayList<Integer> indices) {
        // Initializing breakLine that will be the breakLine for the table
        String breakLine = "+";
        // Initializing the width of the table by referencing the length of
        // the rows. Subtracting 2 for the "+" operators on the ends
        int breakWidth = rowFormat("", "", "").length() - 2;
        // Adding in "-" to fill the rows
        for (int i = 0; i < breakWidth; i++) {
            breakLine += "-";
        }
        // Capping the breakLine with a final "+"
        breakLine += "+";

        // Printing out the header with breakLines and using rowFormat()
        // to populate the header with the column names
        System.out.println(breakLine);
        System.out.println(rowFormat("ID", "NAME", "AGE"));
        System.out.println(breakLine);

        // If the indices parameter is null, the full database will be printed
        if (indices == null) {
            // Iterating through the items in the petDatabase and printing
            // them out to the screen in the specified rowFormat
            for (int id = 0; id < petDatabase.size(); id++) {
                // Initializing the current Pet to tempPet for readability
                Pet tempPet = petDatabase.get(id);

                // Printing out the Pet row in the specified format using rowFormat()
                System.out.println(rowFormat(id, tempPet.getName(), tempPet.getAge()));
            }
        }
        // Else the table will be generated based off the parameter set of indices
        else {
            // If indices has items they will be printed to the screen
            if (indices.size() > 0) {
                // Using a forEach iterator to travers the indices ArrayList and print
                // the correct Pet database Pet's to the screen
                indices.forEach(e -> {
                    // Temporary pet at the current index
                    Pet tempPet = petDatabase.get(e);
                    // Using rowFormat() method to print out the Pet row
                    System.out.println(rowFormat(e, tempPet.getName(), tempPet.getAge()));
                });
            }
        }

        // Ending the table with a breakLine
        System.out.println(breakLine);

        // If the table was generated by the database set it will display the full
        // set size
        if (indices == null) {
            System.out.println(petDatabase.size() + " row/s in a set.\n");
        }
        // Else the table was generated off a prescribed number of indices so the
        // indices set size is displayed
        else {
            System.out.println(indices.size() + " row/s in a set.\n");
        }
    }


    /**
     * The overloaded displayPetTable() will accept a petDatabase and an
     * String name as parameters. It will then make a recursive call to
     * displayPetTable() after searching the Pet database for Pets that match
     * the name. It will then print out every Pet in the database that match
     * that name as well as display the total number of Pet objects found.
     *
     * @param petDatabase - ArrayList<Pet> database to print in a table.
     * @param name - String name of the pets to print.
     */
    public static void displayPetTable(ArrayList<Pet> petDatabase, String name) {
        // Initializing an ArrayList to hold the indices of the Pet objects where
        // the name matches
        ArrayList<Integer> indices = new ArrayList<>();

        // Iterating through the petDatabase and comparing the name. If they
        // match the index is added to the indices List
        for (int id = 0; id < petDatabase.size(); id++) {
            // Creating a temp variable to hold the current Pet for readability
            Pet tempPet = petDatabase.get(id);

            // If the name matches the parameter name, the index is added to indices.
            // The comparison ignores case as described in the client requirments
            if (tempPet.getName().equalsIgnoreCase(name)) {
                indices.add(id);
            }
        }

        // A recursive call is made to displayPetTable() with the new indices
        // List and the Pets matching the indices list are printed to the screen
        displayPetTable(petDatabase, indices);
    }


    /**
     * The intValidator() method will accept an error message to display to the
     * user if the value entered is not an int. When the value is determined to
     * be of int value it is returned to the caller.
     *
     * @param message - Error message that will re-request the int value.
     * @return - The validated int value.
     */
    public static int intValidator(String message) {
        // The isInt sentinel value is set to false since there is no int value
        // entered by the user. TempInt is initialized to hold the entered int
        // value
        boolean isInt = false;
        int tempInt = 0;

        // While the value is not an int, the loop will continue to request the
        // user for an int value
        while (!isInt) {
            // Using a try/catch block to catch exceptions
            try {
                // Attempting to grab the int value from the user input
                tempInt = input.nextInt();
                // If it is a valid int, isInt will be set to true
                isInt = true;
            }
            // If there is an exception thrown
            catch (InputMismatchException e) {
                // The user will be notified via the parameter message and the
                // bad input will be cleared and requested again in the loop
                System.out.print("\n" + message);
                input.nextLine();
            }
        }

        // Clearing any leftover data in the Scanner object
        input.nextLine();

        // Returning the valid int
        return tempInt;
    }


    /**
     * The mainMenu() method will accept the petDatabase used as a
     * parameter. It will then allow the user to select from a number
     * of choices via entering an int value. The value) choice list
     * will be displayed and selected from until the user chooses to
     * exit the menu by selecting the appropriate exit value.
     *
     * @param petDatabase - The ArrayList<Pet> petDatabase to be used
     *                    with the menu.
     */
    public static void mainMenu(ArrayList<Pet> petDatabase) {
        // Priming the userChoice value with 0
        int userChoice = 0;

        // This do/while loop will repeat until the user selects the
        // int exit value
        do {
            // Printing out the menu for the user to choose from
            System.out.println("What would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            System.out.println("3) Update an existing pet");
            System.out.println("4) Remove an existing pet");
            System.out.println("5) Search pets by name");
            System.out.println("6) Search pets by age");
            System.out.println("7) Exit the program");
            System.out.print("\nYour choice: ");

            // Requesting the int value from the user and validating it with
            // the intValidator() method
            userChoice = intValidator("Choice must be a valid int: ");
            // Formatting line
            System.out.println();

            // The switch statement will control the selected menu userChoice
            switch (userChoice) {
                case VIEW_ALL_PETS:
                    // Calling the displayPetTable() to display the Pet
                    // objects currently in the database in a table
                    displayPetTable(petDatabase);
                    break;
                case ADD_PETS:
                    // Calling the addPets() method so the user can add Pet
                    // objects to the parameter passed petDatabase
                    addPets(petDatabase);
                    break;
                case UPDATE_PET:
                    // Calling the updatePet() method so the user can update
                    // a Pet in the database by referencing the Pet id
                    updatePet(petDatabase);
                    break;
                case REMOVE_PET:
                    // Calling the removePet() method so the user can remove
                    // a Pet in the database by referencing the Pet id
                    removePet(petDatabase);
                    break;
                case PET_BY_NAME:
                    // Calling the petNameSearch() method so the user can search
                    // the database using the Pet name
                    petNameSearch(petDatabase);
                    break;
                case PET_BY_AGE:
                    // Calling the petAgeSearch() method so the user can search
                    // the database using the Pet age
                    petAgeSearch(petDatabase);
                    break;
                default:
                    break;
            }

        // The do/while loop will end when the user types the following value
        } while (userChoice != 7);
    }


    /**
     * The petAgeSearch() method will ask the user for a Pet age then
     * search and return the results in a table format.
     *
     * @param petDatabase - The ArrayList<Pet> petDatabase to be used
     *                    with the menu.
     */
    public static void petAgeSearch(ArrayList<Pet> petDatabase) {
        // Initializing the name variable to hold the user entered int age
        int age = 0;

        // Asking the user to enter the age
        System.out.print("Enter age to search: ");

        // Validating and saving the int age to age
        age = intValidator("Please enter a valid int age: ");

        // newLine for formatting
        System.out.println();

        // Calling the displayPetTable() to display the pets in the
        // table with the parameter age
        displayPetTable(petDatabase, age);
    }


    /**
     * The petNameSearch() method will ask the user for a Pet name then
     * search and return the results in a table format.
     *
     * @param petDatabase - The ArrayList<Pet> petDatabase to be used
     *                    with the menu.
     */
    public static void petNameSearch(ArrayList<Pet> petDatabase) {
        // Initializing the name variable to hold the user entered String name
        String name = "";

        // Asking the user to enter the name
        System.out.print("Enter a name to search: ");
        // Saving the name and stripping off white space
        name = input.next().strip();

        // Clearing the Scanner and adding a newLine for formatting
        input.nextLine();
        System.out.println();

        // Calling the displayPetTable() to display the pets in the
        // table with the parameter name
        displayPetTable(petDatabase, name);
    }


    /**
     * The rowFormat() method will return a String consisting of the Pet objects
     * id, name, and age in a row formatted to match the petTable() specifications.
     *
     * @param id - String id of the Pet.
     * @param name - String name of the Pet.
     * @param age - String age of the Pet.
     * @return - A formatted String, according to Pet Table specs, with the id,
     * name, and age of the pet.
     *
     */
    public static String rowFormat(String id, String name, String age) {
        // Formatting the string to fit the Pet Table specifications and returning
        return String.format("| %3s | %-10s | %4s |", id, name, age);
    }


    /**
     * The overloaded rowFormat() method will return a String consisting of the
     * Pet objects id, name, and age in a row formatted to match the
     * petTable() specifications.
     *
     * @param id - int id of the Pet.
     * @param name - String name of the Pet.
     * @param age - int age of the Pet.
     * @return - A formatted String, according to Pet Table specs, with the id,
     * name, and age of the pet.
     *
     */
    public static String rowFormat(int id, String name, int age) {
        // Formatting the string to fit the Pet Table specifications and returning
        return String.format("| %3s | %-10s | %4s |", id, name, age);
    }


    /**
     * The removePet() method will first display a table of all the
     * Pet objects. It will then ask the user to pick a
     * Pet id in order to remove the referenced Pet from the database.
     *
     * @param petDatabase - The ArrayList<Pet> petDatabase to be used
     *                    with the menu.
     */
    public static void removePet(ArrayList<Pet> petDatabase) {
        // Displaying the petDatabase table
        displayPetTable(petDatabase);

        // If there are no Pet objects in the database the user is notified
        if (petDatabase.size() == 0) {
            System.out.println("Sorry there are no pets in the database\n");
        }
        // Else the user is prompted for a Pet id which is then removed from the
        // database
        else {
            // Initializing the id for user input
            int id = 0;

            // Asking the user to choose the Pet id they would like to delete
            System.out.print("Enter the id of the pet you wish to delete: ");

            // The user input id is validated
            id = intValidator("Enter a valid id: ");

            // If the user entered id is in the database the Pet is removed
            if (id >= 0 && id < petDatabase.size()) {
                // Creating a currentPet object for readability
                Pet currentPet = petDatabase.get(id);

                // Removing the Pet from the database
                petDatabase.remove(id);

                // Letting the user know the Pet was removed
                System.out.println(currentPet.getName() + " " + currentPet.getAge() + " is removed.\n");
            }
            // Else the user is notified the Pet does not exist
            else {
                System.out.println("The pet id " + id + " does not exist.\n");
            }
        }
    }


    /**
     * The updatePet() method will first display a table of all the
     * Pet objects. It will then ask the user to pick a
     * Pet id in order to update the referenced Pet from the database.
     * The user will then enter the new Pet name and age. The existing Pet
     * will then be updated in the database.
     *
     * @param petDatabase - The ArrayList<Pet> petDatabase to be used
     *                    with the menu.
     */
    public static void updatePet(ArrayList<Pet> petDatabase) {
        // Displaying the petDatabase table
        displayPetTable(petDatabase);

        // If there are no Pet objects in the database the user is notified
        if (petDatabase.size() == 0) {
            System.out.println("Sorry there are no pets in the database\n");
        }
        // Else the user is prompted for the Pet id to be updated in the database
        else {
            // Initializing the id for user input
            int id = 0;

            // Asking the user to select an int id
            System.out.print("Enter the id of the pet you wish to update: ");

            // The entered int id is validated and saved to id
            id = intValidator("Enter a valid id: ");

            // If the Pet id is in the database the Pet information is requested
            // and the Pet is updated
            if (id >= 0 && id < petDatabase.size()) {
                // Saving the chosen pet to currentPet for readability
                Pet currentPet = petDatabase.get(id);
                // Initializing the variables that will hold the new name/age and
                // ones that hold the old name/age
                String newName = "";
                int newAge = 0;
                String oldName = currentPet.getName();
                int oldAge = currentPet.getAge();

                // Asking the user for the name and age to update the Pet
                System.out.print("Enter new name and new age (name age): ");
                newName = input.next();
                newAge = intValidator("Please enter a valid int age: ");

                // Updating the database with the new Pet data
                petDatabase.set(id, new Pet(newName, newAge));

                // Letting the user know the old pet data and the new data it was updated to
                System.out.println(oldName + " " + oldAge + " changed to " + newName + " " + newAge + ".\n");
            }
            // Else the Pet does not exist in the database and the user is notified
            else {
                System.out.println("The pet id " + id + " does not exist.\n");
            }
        }
    }
}