package com.gumtree.uk.service;

import com.gumtree.uk.entity.Gender;
import com.gumtree.uk.entity.Person;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class tha represents the implementation of the <tt>LookupAddressBookService</tt> interface.
 *
 * @see LookupAddressBookService
 */
public class LookupAddressBookServiceImpl implements LookupAddressBookService
{
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
    public int calculateDayDifferenceBetweenDateOfBirth( List<Person> addressBook, String name, String name2 )
    {
        Person first = searchPersonByName( addressBook, name );
        Person second = searchPersonByName( addressBook, name2 );

        if ( first.getDateOfBirth().isBefore( second.getDateOfBirth() ) )
            return (int)ChronoUnit.DAYS.between( first.getDateOfBirth(), second.getDateOfBirth() );
        else if ( first.getDateOfBirth().isAfter( second.getDateOfBirth() ) )
            return (int)ChronoUnit.DAYS.between( second.getDateOfBirth(), first.getDateOfBirth() );
        else
            return 0;
    }

    @Override
    public Person searchPersonByName( List<Person> addressBook, String name )
    {
        Optional<Person> person = addressBook.stream().filter( p -> p.getName().equals( name ) ).findFirst();
        if ( person.isPresent() )
            return person.get();
        else
            throw new RuntimeException( "Person with name " + name + " not found in the address book" );
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
