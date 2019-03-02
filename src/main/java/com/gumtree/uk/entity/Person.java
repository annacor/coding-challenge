package com.gumtree.uk.entity;

import java.time.LocalDate;

/**
 * Object Person that represents all the information of a single item in the address book
 */
public class Person
{

    private String fullName;
    private Gender gender;
    private LocalDate dateOfBirth;

    public Person()
    {
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName( String fullName )
    {
        this.fullName = fullName;
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
