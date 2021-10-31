/* Devin Brueberg
 * CSC 422 Assignment 1 Part 2
 * Pet.java
 * October 25, 2021
 * Updated(Initials, Date, Changes):
 *  (DAB, 10/30/2021, Added overridden clone() and equals() methods)
 *
 * PetDatabase.java, Pet.java run together for Assignment 1 Part 2
 *
 */

// Package of this program
package csc422.csp.edu.impl;

/**
 * The Pet() class will allow the use of a Pet object that will hold
 * the name and age of the pet.
 *
 */
public class Pet {
    private String name;
    private int age;


    /**
     * Default no arg constructor. Pet will have no name and an
     * age of 0.
     *
     */
    public Pet() {
        this.name = "";
        this.age = 0;
    }


    /**
     * Overloaded Pet constructor that will construct a pet with
     * a desired String name and int age.
     *
     * @param name - String name of the Pet.
     * @param age - int age of the Pet.
     */
    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }


    /**
     * Accessor method for name.
     *
     * @return - value of name.
     */
    public String getName() {
        return name;
    }


    /**
     * Mutator method for name.
     *
     * @param - new value for name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Accessor method for age.
     *
     * @return - value of age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Mutator method for age.
     *
     * @param - new value for age.
     */
    public void setAge(int age) {
        this.age = age;
    }


    /**
     * Adding in and overriding clone() functionality.
     *
     * @return
     */
    @Override
    public Object clone() {
        // The new Pet object is constructed that is the same as the old one
        Pet newPet = new Pet(this.getName(), this.getAge());
        // returning the new Pet object
        return newPet;
    }


    /**
     * Overriding Object's equals method so Pet objects can be compared.
     * The equals method will be case sensitive.
     *
     * @param obj - Object to be tested.
     * @return - true if equals, false if not.
     */
    @Override
    public boolean equals(Object obj) {
        // If the object is null false is returned
        if (obj == null) {
            return false;
        }

        // If the objects are not the same class false is returned
        if (obj.getClass() != this.getClass()) {
            return false;
        }

        // Casting object into Pet since the object must be Pet
        Pet otherPet = (Pet) obj;

        // If the names are not equal then false is returned
        if (this.getName() == "" ? otherPet.getName() != null : !this.getName().equals(otherPet.getName())) {
            return false;
        }

        // If the age is not equal then false is returned
        if (this.getAge() == 0 ? otherPet.getAge() != 0 : !(this.getAge() == (otherPet.getAge()))) {
            return false;
        }

        // Otherwise, the objects are equal
        return true;
    }
}