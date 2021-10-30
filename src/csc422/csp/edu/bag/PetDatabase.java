/* Devin Brueberg
 * CSC 422 Assignment 1 Part 2
 * PetDatabase.java
 * October 25, 2021
 * Updated(Initials, Date, Changes):
 *  (DAB, 10/30/2021, Added features to view the Pet objects in a table)
 *  (DAB, 10/30/2021, Added features to add Pets to the database)
 *  (DAB, 10/30/2021, Added a menu for user navigation)
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
//        petDatabase.add(new Pet("Harry", 4));
//        petDatabase.add(new Pet("Rob", 2));

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
     * The displayPetTable() method will print out a Pet Table according to
     * the parameters described by the client. It will display every Pet
     * in the database as well as display the number of Pet objects in
     * the set.
     *
     */
    public static void displayPetTable(ArrayList<Pet> petDatabase) {
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

        // Iterating through the items in the petDatabase and printing
        // them out to the screen in the specified rowFormat
        for (int id = 0; id < petDatabase.size(); id++) {
            // Initializing the current Pet to tempPet for readability
            Pet tempPet = petDatabase.get(id);

            // Printing out the Pet row in the specified format using rowFormat()
            System.out.println(rowFormat(id, tempPet.getName(), tempPet.getAge()));
        }

        // Ending the table with a breakLine and the number of rows in this set
        System.out.println(breakLine);
        System.out.println(petDatabase.size() + " rows in a set.\n");
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
            System.out.println("7) Exit the program");
            System.out.print("\nYour choice: ");

            // Requesting the int value from the user and validating it with
            // the intValidator() method
            userChoice = intValidator("Choice must be a valid int: ");
            // Formatting line
            System.out.println();

            // View all Pets objects in table format
            if (userChoice == 1) {
                // Calling the displayPetTable() to display the Pet
                // objects currently in the database in a table
                displayPetTable(petDatabase);
            }
            // Add more Pet objects
            else if (userChoice == 2) {
                // Calling the addPets() method so the user can add Pet
                // objects to the parameter passed petDatabase
                addPets(petDatabase);
            }
        // The do/while loop will end when the user types the following value
        } while (userChoice != 7);
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
}