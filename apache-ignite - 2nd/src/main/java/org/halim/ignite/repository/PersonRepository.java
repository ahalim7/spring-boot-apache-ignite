package org.halim.ignite.repository;

import org.apache.ignite.springdata.repository.IgniteRepository;
import org.apache.ignite.springdata.repository.config.Query;
import org.apache.ignite.springdata.repository.config.RepositoryConfig;
import org.halim.ignite.model.Contact;
import org.halim.ignite.model.Person;

import java.util.List;

@RepositoryConfig(cacheName = "PersonCache")
public interface PersonRepository extends IgniteRepository<Person, Long> {

    List<Person> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT c.* " +
            "FROM Person p " +
            "JOIN \"ContactCache\".Contact c " +
            "ON p.id=c.personId " +
            "WHERE p.firstName=? " +
            "and p.lastName=?")
    List<Contact> selectContacts(String firstName, String lastName);

    @Query("SELECT p.id, p.firstName, p.lastName, c.id, c.type, c.location " +
            "FROM Person p " +
            "JOIN \"ContactCache\".Contact c " +
            "ON p.id=c.personId " +
            "WHERE p.firstName=? and p.lastName=?")
    List<List<?>> selectContacts2(String firstName, String lastName);

}
