package org.halim.ignite.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact implements Serializable {

    private static final AtomicLong ID_GEN = new AtomicLong();

    @QuerySqlField(index = true)
    private Long id;

    @QuerySqlField
    private ContactType type;

    @QuerySqlField(index = true)
    private String location;

    @QuerySqlField(index = true)
    private Long personId;

    public void init() {
        this.id = ID_GEN.incrementAndGet();
    }

}
