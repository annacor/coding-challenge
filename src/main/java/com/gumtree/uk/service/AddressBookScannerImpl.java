package com.gumtree.uk.service;

import com.gumtree.uk.entity.Gender;
import com.gumtree.uk.entity.Person;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * This class is used to scan the address book and build a list of Elements, one for each row.
 *
 * @See AddressBookScanner
 * @see Person
 */
public class AddressBookScannerImpl implements AddressBookScanner
{
    public List<Person> scanAddressBookFile( String fileName ) throws IOException
    {
        List<Person> addressBook = new ArrayList<>();

        BufferedReader reader;

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource( fileName );
        if ( url == null )
            throw new FileNotFoundException( "File with name " + fileName + " not found" );

        Path path;
        try
        {
            path = Paths.get( url.toURI() );
        }
        catch ( URISyntaxException e )
        {
            throw new RuntimeException( "Malformed Url. Cannot build the path for the file " + fileName );
        }
        reader = Files.newBufferedReader( path, StandardCharsets.UTF_8 );

        Stream<String> entries = reader.lines();
        entries.forEach( entry -> {
            Person person = populatePerson( entry );
            addressBook.add( person );
        } );

        return addressBook;
    }

    public LocalDate convertStringToLocalDate( String localDate )
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dd/MM/yy" );
        return LocalDate.parse( localDate.trim(), formatter );
    }

    public Person populatePerson( String entry )
    {
        String[] parts = entry.trim().split( "," );
        String[] fullName = parts[ 0 ].split( " " );
        String name = fullName[ 0 ].trim();
        String surname = fullName[ 1 ].trim();
        String gender = parts[ 1 ];
        String dob = parts[ 2 ];

        Person person = new Person();
        person.setName( name );
        person.setSurname( surname );
        person.setGender( Gender.mapStringToGender( gender ) );
        person.setDateOfBirth( convertStringToLocalDate( dob ) );

        return person;
    }

}
