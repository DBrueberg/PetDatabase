/* Devin Brueberg
 * CSC 422 Assignment 1 Part 2
 * Pet.java
 * October 25, 2021
 * Updated(Initials, Date, Changes):
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
}