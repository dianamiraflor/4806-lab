package com.example.lab4.addressbook;

import com.example.lab4.addressbook.AddressBook;
import org.springframework.data.repository.CrudRepository;

public interface AddressBookRepository extends CrudRepository<AddressBook, Long> {

    // Changed long to Long
    AddressBook findById(long id);

}