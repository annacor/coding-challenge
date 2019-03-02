package com.gumtree.uk.service;

import com.gumtree.uk.entity.Gender;
import com.gumtree.uk.entity.Person;

import java.util.List;
import java.util.Map;

/**
 * Specify all the operation that can be applied on a adress book.
 */
public interface LookupAddressBookService
{
    Integer calculateDayDifferenceBetweenDateOfBirth( List<Person> addressBook, String name , String name2 );

    /**
     * Create a map that associate the list of person in the address book with their gender type
     * @param addressBook List of elements defined in a address book
     * @return a map with people grouped by gender
     */
    Map<Gender, List<Person>> mapPersonByGender( List<Person> addressBook );

    /**
     * Return the number of person contained in the address book that corresponde to the specified gender
     * @param addressBook List of elements defined in a address book
     * @param gender gender to select
     * @return number of people for specified gender
     */
    Integer countElementsByGender( List<Person> addressBook, Gender gender );

    Person searchPersonByName( String name );

    Person findOldestPerson( List<Person> addressBook );

    Person findYoungestPerson( List<Person> addressBook );
}
