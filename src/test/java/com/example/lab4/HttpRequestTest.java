package com.example.lab4;

import com.example.lab4.addressbook.AddressBook;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAddressBook() throws Exception {
        AddressBook addressBookTest = new AddressBook();
        HttpEntity<AddressBook> request = new HttpEntity<>(new AddressBook(1));
        AddressBook addyBook = restTemplate.postForObject("http://localhost:" + port + "/new",
                request, AddressBook.class);
        // Retrieving POJO instead of JSON

        // Returns HTTP status
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/addressBook?id=1", String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode name = root.path("name");
        Assertions.assertNotNull(name.asText());
    }
    // Test POST Buddy
    // Test GET Buddy
    // Test DELETE Buddy
}