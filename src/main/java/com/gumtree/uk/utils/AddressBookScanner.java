package com.gumtree.uk.utils;

import com.gumtree.uk.entity.Gender;
import com.gumtree.uk.entity.Person;

import java.io.BufferedReader;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * This class is used to scan the address book and build a list of Elements, one for each row.
 * @see Person
 */
public class AddressBookScanner
{
    private static final Logger LOGGER = Logger.getLogger( AddressBookScanner.class.getName() );

    public static List<Person> scanAddressBookFile( String fileName )
    {
        List<Person> addressBook = new ArrayList<>();

        BufferedReader reader;
        try
        {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            URL url = classloader.getResource( fileName );
            Path path;
            try
            {
                path = Paths.get( url.toURI() );
            }
            catch ( URISyntaxException e )
            {
                LOGGER.log( Level.SEVERE, e.toString(), e );
                return addressBook;
            }
            reader = Files.newBufferedReader( path, StandardCharsets.UTF_8 );
        }
        catch ( IOException e )
        {
            LOGGER.log( Level.SEVERE, e.toString(), e );
            return addressBook;
        }
        Stream<String> entries = reader.lines();
        entries.forEach( entry -> {
            String[] parts = entry.trim().split( "," );
            String fullName = parts[ 0 ];
            String gender = parts[ 1 ];
            String dob = parts[ 2 ];

            Person person = new Person();
            person.setFullName( fullName );
            person.setGender( Gender.mapStringToGender( gender ) );
            person.setDateOfBirth( convertStringToLocalDate( dob ) );

            addressBook.add( person );
        } );

        return addressBook;
    }

    private static LocalDate convertStringToLocalDate( String localDate )
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dd/MM/yy" );
        return LocalDate.parse( localDate.trim(), formatter );
    }

}
