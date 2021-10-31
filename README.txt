# PetDatabase

The PetDatabase was created as an assignment for my Software Engineering class at Concordia University St-Paul.<br 
The required specifications are shown below. The version I created has full input validation. It is good to note 
that this is a dynamic database.

CSC 422 Assignment 1, Part 2
Topics: version control
Effort: 5+ hours
This assignment will give you practice of using version control such as git to manage changes of
your code and to give you experience of incremental development.
For the purpose of this assignment, you will create a basic database program for managing
information (name and age) about pets. The database allows the user to add pet information
to the database, remove pet information, updating pet information, and searching for pets by
name or by age. You can assume the user only input pet names consisting of a single word.
Error handling is optional.
You will build this program incrementally. For each increment, you will create a release that
the user can download and run. You will use git and GitHub to track the changes and to create
releases.
The Program
You will create a pet database program with the following operations incrementally as describe
in the Milestones section. You are not required to save the pet data into a file. You must use
appropriate design and make use of Object-Oriented Design. See milestones.
• Add pets o Let the user add as many pets as they want. A pet is entered as a single line
consisting of a name and an integer which represents the age of the pet. The user type
“done” to end reading. See sample run.
• Show pets
o Prints a table of all pets in the database. See below or the sample run for the
formatting of the table.
• Update pets o Shows the user a table of all the pets and reads the pet ID that the user
wants to update. ID is the array index of the object. Once the user has selected a pet by
typing and ID, it asks the user to enter the new name and new age. It then updates the
actual object.
• Search pets by name or age o Prompts the user for a name and then displays a table
showing all pets matching the name. The name is case insensitive. For example,
“boomer” will “Boomer”.
o Prompts the user for an age and shows a table consists of pets with that age.
• Remove a pet
o Prompts the user for the index of the Pet to delete.
Table Formatting: 
Your program will display the results for view all pets, search pet by name and search pet by
age using a table as shown below. You will need to use System.out.printf() to format the table.
Below is a sample table with annotations as to which part is the header, rows, and footer. A
row represents a single record about a pet. The ID is the index in the array the object is
located. A table consists of zero more rows. You may use 3 characters for ID, 10 characters for
NAME, and 4 characters for AGE.
+----------------------+
| ID | NAME | AGE | header
+----------------------+
| 0 | Kitty | 8 |
| 1 | Bruno | 7 |
| 2 | Boomer | 8 | rows
| 3 | Boomer | 3 |
| 4 | Fiesty | 3 |
+----------------------+
5 rows in set. footer
Milestones
You will create three releases with the functionality describe below.
• Release 1: Your program should allow adding and displaying of pets.
• Release 2: Your program should allow searching of pets.
• Release 3: Your program should allow for updating and removing pets. 
