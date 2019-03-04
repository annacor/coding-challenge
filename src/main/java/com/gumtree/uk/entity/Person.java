package com.gumtree.uk.entity;

import java.time.LocalDate;

/**
 * Object Person that represents the information of a single item in the address book
 */
public class Person
{

    private String name;
    private String surname;
    private Gender gender;
    private LocalDate dateOfBirth;

    public Person()
    {
    }

    public Person( String name, String surname, Gender gender, LocalDate dateOfBirth )
    {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname( String surname )
    {
        this.surname = surname;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender( Gender gender )
    {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth( LocalDate dateOfBirth )
    {
        this.dateOfBirth = dateOfBirth;
    }
}
