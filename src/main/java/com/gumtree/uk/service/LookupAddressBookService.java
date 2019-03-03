package com.gumtree.uk.service;

import com.gumtree.uk.entity.Gender;
import com.gumtree.uk.entity.Person;

import java.util.List;
import java.util.Map;

/**
 * Specify all the operation that can be applied on a address book.
 */
public interface LookupAddressBookService
{
    /**
     * Create a map that associate the list of person in the address book with their gender type
     *
     * @param addressBook List of elements defined in a address book
     * @return a map with people grouped by gender
     */
    Map<Gender, List<Person>> mapPersonByGender( List<Person> addressBook );

    /**
     * Return the number of person contained in the address book that corresponde to the specified gender
     *
     * @param addressBook List of elements defined in a address book
     * @param gender      gender to select
     * @return number of people for specified gender
     */
    Integer countElementsByGender( List<Person> addressBook, Gender gender );

    /**
     * Search a person in the address book with the name specified.
     *
     * @param addressBook List of elements defined in a address book
     * @param name        - name of the person to search
     * @return The person in the address book with the specified name
     * @throws RuntimeException Person not found for the specified name
     */
    Person searchPersonByName( List<Person> addressBook, String name );

    /**
     * Calculate the difference in term of days between two people in the address book.
     *
     * @param addressBook List of elements defined in a address book
     * @param person      first person to compare
     * @param person2     second person to compare
     * @return number of days  between two people date of birthday
     * @throws RuntimeException if the name of the two people to compare have no corrispondence in the address book
     */
    int calculateDayDifferenceBetweenDateOfBirth( List<Person> addressBook, String person, String person2 );

    Person findOldestPerson( List<Person> addressBook );

    Person findYoungestPerson( List<Person> addressBook );
}
