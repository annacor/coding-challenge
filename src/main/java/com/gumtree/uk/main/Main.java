package com.gumtree.uk.main;

import com.gumtree.uk.entity.Gender;
import com.gumtree.uk.entity.Person;
import com.gumtree.uk.service.AddressBookScannerImpl;
import com.gumtree.uk.service.LookupAddressBookService;
import com.gumtree.uk.service.LookupAddressBookServiceImpl;
import com.gumtree.uk.service.AddressBookScanner;

import java.io.IOException;
import java.util.List;

/**
 * This application reads an attached AddressBook file and answer the following questions:
 * 1. How many males are in the address book?
 * 2. Who is the oldest person in the address book?
 * 3. How many days older is Bill than Paul?
 */
public class Main
{
    public static void main( String[] args ) throws IOException
    {
        AddressBookScanner scanner = new AddressBookScannerImpl();

        String fileName = "addressbook.txt";
        List<Person> addressBook = scanner.scanAddressBookFile( fileName );

        LookupAddressBookService service = new LookupAddressBookServiceImpl();

        int numOfMales = service.countElementsByGender( addressBook, Gender.MALE );
        System.out.println( "How many males are in the address book?" );
        System.out.println( "Number of males: " + numOfMales );

        Person oldest = service.findOldestPerson( addressBook );
        System.out.println( "Who is the oldest person in the address book? " );
        System.out.println( "Oldest person: " + oldest.getName() + " " + oldest.getSurname() );

        int dayDiff = service.calculateDayDifferenceBetweenDateOfBirth( addressBook, "Bill", "Paul" );
        System.out.println( "How many days older is Bill than Paul?" );
        System.out.println( "Difference: " + dayDiff + " day" + ( dayDiff > 1 ? "s" : "" ) );
    }
}
