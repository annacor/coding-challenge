package com.gumtree;

import com.gumtree.uk.entity.Gender;
import com.gumtree.uk.entity.Person;
import com.gumtree.uk.service.LookupAddressBookService;
import com.gumtree.uk.service.LookupAddressBookServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for the class LookupAddressBookServiceTest
 */
public class LookupAddressBookServiceTest
{
    private static List<Person> addressBook;

    private static LookupAddressBookService service;

    @BeforeClass
    public static void initialiseAddressBook()
    {
        addressBook = new ArrayList<>();

        addressBook.add( new Person( "Bill", "McKnight", Gender.MALE, LocalDate.of( 77, 3, 16 ) ) );
        addressBook.add( new Person( "Paul", "Robinson", Gender.MALE, LocalDate.of( 85, 1, 15 ) ) );
        addressBook.add( new Person( "Gemma", "Lane", Gender.FEMALE, LocalDate.of( 80, 9, 20 ) ) );
        addressBook.add( new Person( "Sarah", "Stone", Gender.FEMALE, LocalDate.of( 80, 9, 20 ) ) );
        addressBook.add( new Person( "Pauline", "Jackson", Gender.MALE, LocalDate.of( 74, 8, 14 ) ) );
        addressBook.add( new Person( "Claudio", "Anderson", Gender.MALE, LocalDate.of( 77, 3, 18 ) ) );
    }

    @Before
    public void setUp() throws Exception
    {
        service = new LookupAddressBookServiceImpl();
    }

    /**
     * Test that the number of females read from the address book is correct
     */
    @Test
    public void countElementsByGender_Female_Test()
    {
        int females = service.countElementsByGender( addressBook, Gender.FEMALE );
        Assert.assertEquals( "number of females", 2, females );
    }

    /**
     * Test that the number of males read from the address book is correct
     */
    @Test
    public void countElementsByGender_Male_Test()
    {
        int males = service.countElementsByGender( addressBook, Gender.MALE );
        Assert.assertEquals( "number of males", 4, males );
    }

    /**
     * Test that the if no result for that gender found the code will return 0 and do not break
     */
    @Test
    public void countElementsByGender_NoMale_Test()
    {
        List<Person> onlyFemale = new ArrayList<>();
        onlyFemale.add( new Person( "Gemma", "Lane", Gender.FEMALE, LocalDate.of( 91, 11, 20 ) ) );
        onlyFemale.add( new Person( "Sarah", "Stone", Gender.FEMALE, LocalDate.of( 80, 9, 20 ) ) );

        int males = service.countElementsByGender( onlyFemale, Gender.MALE );
        Assert.assertEquals( "No male found", 0, males );
    }

    /**
     * Test that there is a person in the address book with the specified name
     */
    @Test
    public void findPersonByName_FoundSingle_Test()
    {
        Person person = service.findPersonByName( addressBook, "Paul" );
        Assert.assertEquals( "Name", person.getName(), "Paul" );
        Assert.assertEquals( "Surname", person.getSurname(), "Robinson" );
    }

    /**
     * Test that an exception is thrown if there is no element in the address book with the specified name
     */
    @Test
    public void findPersonByName_PersonNotFound_Test()
    {
        try
        {
            service.findPersonByName( addressBook, "Linda" );
            Assert.fail( "Expected a Runtime Exception" );
        }
        catch ( RuntimeException e )
        {
            Assert.assertEquals( e.getMessage(), "Person with name Linda not found in the address book" );
        }
    }

    /**
     * Test that the distance in term of days between two people date of birth is calculate correctly
     */
    @Test
    public void calculateDayDifferenceBetweenDateOfBirth_differentAge_Test()
    {
        int dayDiff = service.calculateDayDifferenceBetweenDateOfBirth( addressBook, "Bill", "Paul" );
        int dayDiffOpposite = service.calculateDayDifferenceBetweenDateOfBirth( addressBook, "Paul", "Bill" );

        Assert.assertEquals( "Day difference", 2862, dayDiff );
        Assert.assertEquals( "Same people same difference", dayDiff, dayDiffOpposite );

        int dayDiff2 = service.calculateDayDifferenceBetweenDateOfBirth( addressBook, "Bill", "Claudio" );
        Assert.assertEquals( "Day difference", 2, dayDiff2 );
    }

    /**
     * Test two people with the same age
     */
    @Test
    public void calculateDayDifferenceBetweenDateOfBirth_sameAge_Test()
    {
        int dayDiff = service.calculateDayDifferenceBetweenDateOfBirth( addressBook, "Gemma", "Sarah" );

        Assert.assertEquals( "Day difference", 0, dayDiff );
    }

    /**
     * Test that the code return NUll if the address book is empty
     */
    @Test
    public void findOldestPerson_emptyList_Test()
    {
        List<Person> list = new ArrayList<>(  );
        Person person = service.findOldestPerson( list );
        Assert.assertNull( person );
    }

    /**
     * Test that the code return NUll if the address book is equals to null
     */
    @Test
    public void findOldestPerson_NullList_Test()
    {
        List<Person> list = null;
        Person person = service.findOldestPerson( list );
        Assert.assertNull( person );
    }

    /**
     * Test return the oldest person in the address book comparing the date of birth
     */
    @Test
    public void findOldestPerson_OldestPerson_Test()
    {
        Person person = service.findOldestPerson( addressBook );
        Assert.assertNotNull( person );
        Assert.assertEquals("name", "Pauline", person.getName() );
        Assert.assertEquals( "surname","Jackson", person.getSurname() );

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dd/MM/yy" );
        Assert.assertEquals( "Date of Birth", "14/08/74", person.getDateOfBirth().format( formatter ) );

        Assert.assertEquals( "Gender","MALE", person.getGender().toString() );

    }

}
