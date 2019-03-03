package com.gumtree;

import com.gumtree.uk.entity.Person;
import com.gumtree.uk.service.AddressBookScanner;
import com.gumtree.uk.service.AddressBookScannerImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Unit test for the class AddressBookScanner
 */
public class AddressBookScannerTest
{
    private static AddressBookScanner service;

    @Before
    public void setUp() throws Exception
    {
        service = new AddressBookScannerImpl();
    }

    /**
     * Test that the scanner create a list with a single element inside that corresponde to the person
     * Bill McKnight, Male, 16/03/77
     */
    @Test
    public void scanAddressBookFile_personCreation_Test() throws IOException
    {

        List<Person> addressBook = service.scanAddressBookFile( "singleLineTest.txt" );

        Assert.assertNotNull( "Null address book", addressBook );
        Assert.assertTrue( "empty list", !addressBook.isEmpty() );
        Assert.assertEquals( "list size", 1, addressBook.size() );

        Person person = addressBook.get( 0 );
        Assert.assertEquals( "Name", "Bill", person.getName() );
        Assert.assertEquals( "Surname", "McKnight", person.getSurname() );
        Assert.assertEquals( "Gender", "MALE", person.getGender().toString() );

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dd/MM/yy" );
        Assert.assertEquals( "Date of Birth", "16/03/77", person.getDateOfBirth().format( formatter ) );

    }

    /**
     * Test the number of person read from the file is exactly the one expected
     */
    @Test
    public void scanAddressBookFile_multipleRowFile_Test() throws IOException
    {

        List<Person> addressBook = service.scanAddressBookFile( "addressBookTest.txt" );

        Assert.assertNotNull( "Null address book", addressBook );
        Assert.assertTrue( "empty list", !addressBook.isEmpty() );
        Assert.assertEquals( "list size", 5, addressBook.size() );
    }

    /**
     * Test that the code throws an FileNotFoundException if the file doesn't exist
     */
    @Test
    public void scanAddressBookFile_FileNotFound_Test() throws IOException
    {
        String fileName = "";
        try
        {
            fileName = "NotExistingFile.txt";
            service.scanAddressBookFile( fileName );
            Assert.fail( "Expected a FileNotFoundException" );
        }
        catch ( FileNotFoundException e )
        {
            Assert.assertEquals( e.getMessage(), "File with name " + fileName + " not found" );
        }
    }
}
