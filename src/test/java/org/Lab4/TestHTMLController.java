package org.Lab4;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestHTMLController {

    @Autowired
    private HTMLController controller;

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void contextLoads() throws Exception{
        assertThat(controller).isNotNull();
    }
    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("Hello, World");
    }
    @Test
    /**
     * In this test, the full Spring application context is started but without the server.
     * We can narrow the tests to only the web layer by using @WebMvcTest
     */
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));
    }

    @Test
    public void createAddressBook() throws Exception {
        this.addressBookRepository.deleteAll();
        this.mockMvc.perform(get("/createaddressbook")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("All Address Books")));
    }
    @Test
    public void addBuddyToAddress() throws Exception{
        this.addressBookRepository.deleteAll();
        this.addressBookRepository.save(new AddressBook());
        this.mockMvc.perform(post("/addbuddy")
                .param("id", "1").param("name", "andre").param("phone","613"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("andre")));
    }
    @Test
    public void getAllBooksTest() throws Exception{
        this.addressBookRepository.deleteAll();
        this.addressBookRepository.save(new AddressBook());
        this.addressBookRepository.save(new AddressBook());
        this.addressBookRepository.save(new AddressBook());
        MvcResult r = this.mockMvc.perform(get("/getAllBooks")).andExpect(status().isOk())
                .andReturn();
        String s = Objects.requireNonNull(r.getModelAndView()).getModel().get("Addresses").toString();
        long count = StringUtils.countOccurrencesOf(s, "id");
        Assert.assertEquals(3,count);
    }
    @Test
    public void getAddressBookTest() throws Exception{
        this.addressBookRepository.deleteAll();
        this.addressBookRepository.save(new AddressBook());
        this.addressBookRepository.save(new AddressBook());
        this.addressBookRepository.save(new AddressBook());
        this.mockMvc.perform(get("/getaddressbook/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Address Book with Id: 1")));
    }

}
