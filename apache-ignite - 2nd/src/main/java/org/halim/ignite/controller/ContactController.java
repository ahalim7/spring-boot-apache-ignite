package org.halim.ignite.controller;

import lombok.extern.slf4j.Slf4j;
import org.halim.ignite.model.Contact;
import org.halim.ignite.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("managed-contact")
public class ContactController {

    @Autowired
    ContactRepository repository;

    @PostMapping
    public Contact add(@RequestBody Contact contact) {
        contact.init();
        return repository.save(contact.getId(), contact);
    }

    @GetMapping("/{id}")
    public Contact findById(@PathVariable("id") Long id) {
        return repository.findOne(id);
    }

    @GetMapping("/location/{location}")
    public List<Contact> findById(@PathVariable("location") String location) {
        return repository.findByLocation(location);
    }

}
