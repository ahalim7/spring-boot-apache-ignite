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
        return null; //personRepository.save(person.getId(), person);
    }

    @PutMapping
    public Person update(@RequestBody Person person) {
        return null; //personRepository.save(person.getId(), person);
    }

    @DeleteMapping("/{id}")
    public void delete(Long id) {
        personRepository.delete(personRepository.findById(id).orElse(null));
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable("id") Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @GetMapping("/{firstName}/{lastName}")
    public List<Person> findByName(@PathVariable("firstName") String firstName,
                                   @PathVariable("lastName") String lastName) {
        return personRepository.findByFirstNameAndLastName(firstName, lastName);
    }

}
