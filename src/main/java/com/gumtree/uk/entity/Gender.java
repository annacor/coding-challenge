package com.gumtree.uk.entity;

/**
 * Enumeration class that represent the typology of gender of a person
 */
public enum Gender
{
    MALE, FEMALE;

    public static Gender mapStringToGender( String gender )
    {
        gender = gender.trim().toUpperCase();
        if ( MALE.toString().equals( gender ) )
            return MALE;
        if ( FEMALE.toString().equals( gender ) )
            return FEMALE;
        else
            throw new IllegalArgumentException( "The input " + gender + " is not a recognised type." );
    }
}
