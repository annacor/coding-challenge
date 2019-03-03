package com.gumtree.uk.service;

import com.gumtree.uk.entity.Person;

import java.io.IOException;
import java.util.List;

/**
 * This class contains all the operation to retrieve an address book
 */
public interface AddressBookScanner
{
    /**
     * Scan a file from the resource folder with the specified filename and build the address book as list of single person
     * @param fileName neme of the file to scan
     *
     * @throws  IOException
     *          if an I/O error occurs opening the file
     * @return a list of Person that represents all the elements in the ddress book file
     */
    List<Person> scanAddressBookFile( String fileName ) throws IOException;
}
