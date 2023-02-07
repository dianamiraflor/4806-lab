package com.example.lab4.addressbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
public class AddressBookHtmlController {
    @Autowired
    AddressBookRepository addressBookRepository;

    @RequestMapping(value="/addressBookList", method = RequestMethod.GET)
    public String getAddressBook(@RequestParam("id") Long id, Model model) {
        Optional<AddressBook> addyBookOpt = addressBookRepository.findById(id);
        AddressBook addyBook = addyBookOpt.get();

        model.addAttribute("addressBook", addyBook.toString());

        // Return JSON
        return "addressBook";
    }

    @RequestMapping(value="/addressBooks", method = RequestMethod.GET)
    public String getAddressBook(Model model) {
        Iterable<AddressBook> addyBooks = addressBookRepository.findAll();

        model.addAttribute("addyBooks", addyBooks);
        // Return JSON
        return "addressBooks";
    }


}
