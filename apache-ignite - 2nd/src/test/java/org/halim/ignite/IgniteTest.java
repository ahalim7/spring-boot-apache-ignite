package org.halim.ignite;

import org.halim.ignite.model.Contact;
import org.halim.ignite.model.ContactType;
import org.halim.ignite.model.Gender;
import org.halim.ignite.model.Person;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class IgniteTest {

    DecimalFormat f = new DecimalFormat("000000000");

    @Test
    public void testAddPerson() throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int j = 0; j < 10; j++) {
            es.execute(() -> {
                TestRestTemplate restTemplateLocal = new TestRestTemplate();
                Random r = new Random();
                for (int i = 0; i < 10000; i++) {
                    Person p = restTemplateLocal.postForObject("http://localhost:8090/managed-person", createTestPerson(), Person.class);
                    int x = r.nextInt(6);
                    for (int k = 0; k < x; k++) {
                        restTemplateLocal.postForObject("http://localhost:8090/managed-contact", createTestContact(p.getId()), Contact.class);
                    }
                }
            });
        }
        es.shutdown();
        es.awaitTermination(10, TimeUnit.MINUTES);
    }

    @Test
    public void testFindById() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        for (int i = 0; i < 1000; i++) {
            restTemplate.getForObject("http://localhost:8090/managed-person/{id}", Person.class, i + 1);
        }
    }

    @Test
    public void testFindContactByLocation() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            int n = r.nextInt(100000);
            restTemplate.getForObject("http://localhost:8090/managed-contact/location/{location}", Contact.class, "location-" + n);
        }
    }

    @Test
    public void testFindByName() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            int n = r.nextInt(100000);
            restTemplate.getForObject("http://localhost:8090/managed-person/{firstName}/{lastName}", Person.class, "Test" + n, "Test" + n);
        }
    }

    @Test
    public void testFindByNameWithContacts() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            int n = r.nextInt(100000);
            restTemplate.getForObject("http://localhost:8090/managed-person/contacts/{firstName}/{lastName}", Person.class, "Test" + n, "Test" + n);
        }
    }

    private Person createTestPerson() {
        Random r = new Random();
        Person p = new Person();
        int n = r.nextInt(100000);
        p.setFirstName("Test" + n);
        p.setLastName("Test" + n);
        p.setGender(Gender.values()[r.nextInt(2)]);
        p.setCountry("PL");
        p.setCity("Test" + n);
        p.setBirthDate(Date.valueOf(LocalDate.now().toString()));
        p.setAddress("Address " + n);
        return p;
    }

    private Contact createTestContact(Long personId) {
        Random r = new Random();
        Contact c = new Contact();
        c.setPersonId(personId);
        c.setType(ContactType.values()[r.nextInt(4)]);
        c.setLocation("location-" + f.format(r.nextInt()));
        return c;
    }

}
