package org.halim.ignite.repository;

import org.apache.ignite.springdata.repository.IgniteRepository;
import org.apache.ignite.springdata.repository.config.RepositoryConfig;
import org.halim.ignite.model.Contact;

import java.util.List;

@RepositoryConfig(cacheName = "ContactCache")
public interface ContactRepository extends IgniteRepository<Contact, Long> {

    List<Contact> findByLocation(String location);


}
