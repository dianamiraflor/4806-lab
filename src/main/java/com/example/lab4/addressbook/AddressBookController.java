package com.example.lab4.addressbook;

import com.example.lab4.buddyinfo.BuddyInfo;
import com.example.lab4.buddyinfo.BuddyInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@RestController
public class AddressBookController {
    private static final Logger log = LoggerFactory.getLogger(AddressBookController.class);
    @Autowired
    AddressBookRepository addressBookRepository;
    @Autowired
    BuddyInfoRepository buddyInfoRepository;

    @RequestMapping(value = "/")
    public String home() {
        System.out.println("AddressBook: Passing through...");
        return "Hello";
    }

    @RequestMapping(value = "/new", method= {RequestMethod.POST, RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public AddressBook newAddressBook() {
        AddressBook addyBook = new AddressBook();

        addressBookRepository.save(addyBook);

        return addyBook;
    }

    @RequestMapping(value = "/add", method= {RequestMethod.POST, RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public AddressBook addBuddyInfo(@RequestParam("addyId") String addyId,
                               @RequestParam("name") String name,
                               @RequestParam("phoneNumber") String phoneNumber,
                               @RequestParam("address") String address) {
        Long addyIdLong = Long.parseLong(addyId);

        BuddyInfo buddy = new BuddyInfo();
        buddy.setName(name);
        buddy.setAddress(address);
        buddy.setPhoneNumber(phoneNumber);

        Optional<AddressBook> addyBookOpt = addressBookRepository.findById(addyIdLong);
        AddressBook addyBook = addyBookOpt.get();

        addyBook.addBuddy(buddy);

        addressBookRepository.save(addyBook); // Save again

        return addyBook;
    }

    @RequestMapping(value="/addressBook", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AddressBook getAddressBook(@RequestParam("id") Long id) {
        Optional<AddressBook> addyBookOpt = addressBookRepository.findById(id);
        AddressBook addyBook = addyBookOpt.get();

        // Return JSON
        return addyBook;
    }


    @RequestMapping(value = "/remove", method= {RequestMethod.DELETE, RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public AddressBook removeBuddy(@RequestParam("id") Long id,
                              @RequestParam("addyId") Long addyId) {

        Optional<AddressBook> addyBookOpt = addressBookRepository.findById(addyId);
        AddressBook addyBook = addyBookOpt.get();
        Optional<BuddyInfo> buddyOpt = buddyInfoRepository.findById(id);
        BuddyInfo buddy = buddyOpt.get();

        addyBook.removeBuddy(buddy);

        addressBookRepository.save(addyBook);

        return addyBook;
    }

}
