package org.halim.ignite.controller;

import lombok.extern.slf4j.Slf4j;
import org.halim.ignite.model.Contact;
import org.halim.ignite.model.ContactType;
import org.halim.ignite.model.Person;
import org.halim.ignite.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("managed-person")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping
    public List<?> get() {
        List<Person> results = new ArrayList<>();
        personRepository.findAll().forEach(results::add);
        return results;
    }

    @PostMapping
    public Person add(@RequestBody Person person) {
        person.init();
        return personRepository.save(person.getId(), person);
    }

    @PutMapping
    public Person update(@RequestBody Person person) {
        return personRepository.save(person.getId(), person);
    }


    @GetMapping("/{firstName}/{lastName}")
    public List<Person> findByName(@PathVariable("firstName") String firstName,
                                   @PathVariable("lastName") String lastName) {
        return personRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping("/contacts/{firstName}/{lastName}")
    public List<Person> findByNameWithContacts(@PathVariable("firstName") String firstName,
                                               @PathVariable("lastName") String lastName) {
        List<Person> persons = personRepository.findByFirstNameAndLastName(firstName, lastName);
        List<Contact> contacts = personRepository.selectContacts(firstName, lastName);
        persons.forEach(person -> person.setContacts(
                contacts.stream()
                        .filter(contact -> contact.getPersonId().equals(person.getId())).collect(Collectors.toList())));
        log.info("PersonController.findByIdWithContacts: {}", contacts);
        return persons;
    }

    @GetMapping("/contacts2/{firstName}/{lastName}")
    public List<Person> findByNameWithContacts2(@PathVariable("firstName") String firstName,
                                                @PathVariable("lastName") String lastName) {
        List<List<?>> result = personRepository.selectContacts2(firstName, lastName);
        List<Person> persons = new ArrayList<>();
        for (List<?> l : result) {
            persons.add(mapPerson(l));
        }
        log.info("PersonController.findByIdWithContacts: {}", result);
        return persons;
    }

    private Person mapPerson(List<?> l) {
        Person p = new Person();
        Contact c = new Contact();
        p.setId((Long) l.get(0));
        p.setFirstName((String) l.get(1));
        p.setLastName((String) l.get(2));
        c.setId((Long) l.get(3));
        c.setType((ContactType) l.get(4));
        c.setLocation((String) l.get(4));
        p.setContacts(Collections.singletonList(c));
        return p;
    }
}
