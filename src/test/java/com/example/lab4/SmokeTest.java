package com.example.lab4;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.lab4.addressbook.AddressBookController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private AddressBookController addyBookController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(addyBookController).isNotNull();
    }
}