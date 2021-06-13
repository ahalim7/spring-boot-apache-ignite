package org.halim.ignite.controller;

import lombok.extern.slf4j.Slf4j;
import org.halim.ignite.model.Contact;
import org.halim.ignite.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("managed-contact")
public class ContactController {

    @Autowired
    ContactRepository contactRepository;

    @GetMapping
    public List<Contact> fetch() {
        List<Contact> results = new ArrayList<>();
        contactRepository.findAll().forEach(results::add);
        return results;
    }

    @PostMapping
    public Contact add(@RequestBody Contact contact) {
        contact.init();
        return null; //contactRepository.save(contact.getId(), contact);
    }

    @GetMapping("/{id}")
    public Contact findById(@PathVariable("id") Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    @GetMapping("/location/{location}")
    public List<Contact> findById(@PathVariable("location") String location) {
        return contactRepository.findByLocation(location);
    }

}
