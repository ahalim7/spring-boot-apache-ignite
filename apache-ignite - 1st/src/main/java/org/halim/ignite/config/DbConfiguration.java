package org.halim.ignite.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.store.CacheStore;
import org.apache.ignite.cache.store.jdbc.CacheJdbcBlobStore;
import org.apache.ignite.cache.store.jdbc.CacheJdbcPojoStoreFactory;
import org.apache.ignite.cache.store.jdbc.JdbcType;
import org.apache.ignite.cache.store.jdbc.JdbcTypeField;
import org.apache.ignite.cache.store.jdbc.dialect.BasicJdbcDialect;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.halim.ignite.model.Contact;
import org.halim.ignite.model.ContactType;
import org.halim.ignite.model.Gender;
import org.halim.ignite.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.Types;

@Configuration
public class DbConfiguration {

    @Bean
    public Ignite igniteInstance() {
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setIgniteInstanceName("my-ignite");
        return Ignition.start(cfg);
    }

   /* @Scheduled(fixedDelay = 60000L)
    public void populateCaches() {

        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setIgniteInstanceName("ignite-1");
        cfg.setPeerClassLoadingEnabled(true);

        CacheConfiguration<Long, Contact> ccfg2 = new CacheConfiguration<>("ContactCache"); // (1)
        ccfg2.setIndexedTypes(Long.class, Contact.class); // (2)
        ccfg2.setWriteBehindEnabled(true);
        ccfg2.setWriteThrough(true); // (3)
        ccfg2.setReadThrough(true); // (4)
        CacheJdbcPojoStoreFactory<Long, Contact> f2 = new CacheJdbcPojoStoreFactory<>(); // (5)
        f2.setDataSource(dataSource); // (6)
        f2.setDialect(new BasicJdbcDialect());// (7)
        JdbcType jdbcContactType = new JdbcType(); // (8)
        jdbcContactType.setCacheName("ContactCache");
        jdbcContactType.setKeyType(Long.class);
        jdbcContactType.setValueType(Contact.class);
        jdbcContactType.setDatabaseTable("contact");
        jdbcContactType.setDatabaseSchema("locals");
        jdbcContactType.setKeyFields(new JdbcTypeField(Types.INTEGER, "id", Long.class, "id"));
        jdbcContactType.setValueFields(
                new JdbcTypeField(Types.VARCHAR, "contact_type", ContactType.class, "type"),
                new JdbcTypeField(Types.VARCHAR, "location", String.class, "location"),
                new JdbcTypeField(Types.INTEGER, "person_id", Long.class, "personId"));
        f2.setTypes(jdbcContactType);

        ccfg2.setCacheStoreFactory(f2);

        CacheConfiguration<Long, Person> ccfg = new CacheConfiguration<>("PersonCache");
        ccfg.setIndexedTypes(Long.class, Person.class);
        ccfg.setWriteBehindEnabled(true);
        ccfg.setReadThrough(true);
        ccfg.setWriteThrough(true);
        CacheJdbcPojoStoreFactory<Long, Person> f = new CacheJdbcPojoStoreFactory<>();
        f.setDataSource(dataSource);
        f.setDialect(new BasicJdbcDialect());
        JdbcType jdbcType = new JdbcType();
        jdbcType.setCacheName("PersonCache");
        jdbcType.setKeyType(Long.class);
        jdbcType.setValueType(Person.class);
        jdbcType.setDatabaseTable("person");
        jdbcType.setDatabaseSchema("locals");
        jdbcType.setKeyFields(new JdbcTypeField(Types.INTEGER, "id", Long.class, "id"));
        jdbcType.setValueFields(
                new JdbcTypeField(Types.VARCHAR, "first_name", String.class, "firstName"),
                new JdbcTypeField(Types.VARCHAR, "last_name", String.class, "lastName"),
                new JdbcTypeField(Types.VARCHAR, "gender", Gender.class, "gender"),
                new JdbcTypeField(Types.VARCHAR, "country", String.class, "country"),
                new JdbcTypeField(Types.VARCHAR, "city", String.class, "city"),
                new JdbcTypeField(Types.VARCHAR, "address", String.class, "address"),
                new JdbcTypeField(Types.DATE, "birth_date", Date.class, "birthDate"));
        f.setTypes(jdbcType);
        ccfg.setCacheStoreFactory(f);

        cfg.setCacheConfiguration(ccfg, ccfg2);
        Ignition.start(cfg);
    }*/

}
