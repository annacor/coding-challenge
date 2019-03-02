package com.gumtree.uk.service;

import com.gumtree.uk.entity.Gender;
import com.gumtree.uk.entity.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class tha represents the implementation of the <tt>LookupAddressBookService</tt> interface.
 * @see LookupAddressBookService
 */
public class LookupAddressBookServiceImpl implements LookupAddressBookService
{
    public LookupAddressBookServiceImpl()
    {
    }

    @Override
    public Integer calculateDayDifferenceBetweenDateOfBirth( List<Person> addressBook, String name , String name2 )
    {
        return null;
    }

    @Override
    public Map<Gender, List<Person>> mapPersonByGender( List<Person> addressBook )
    {
        Map<Gender, List<Person>> genderPersonMap = new HashMap<>();

        for ( Gender gender : Gender.values() )
        {
            List<Person> list = addressBook.stream().filter( p -> p.getGender().equals( gender ) ).collect( Collectors.toList() );
            genderPersonMap.put( gender, list );
        }
        return genderPersonMap;
    }

    @Override
    public Integer countElementsByGender( List<Person> addressBook, Gender gender )
    {
        Map<Gender, List<Person>> genderPersonMap = mapPersonByGender( addressBook );
        List<Person> list = genderPersonMap.get( gender );

        return list.size();
    }

    @Override
    public Person searchPersonByName( String name )
    {
        return null;
    }

    @Override
    public Person findOldestPerson( List<Person> addressBook )
    {
        return null;
    }

    @Override
    public Person findYoungestPerson( List<Person> addressBook )
    {
        return null;
    }
}
