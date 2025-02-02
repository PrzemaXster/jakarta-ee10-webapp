package org.pgrabarek.jakartawebproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="example")
@Entity
public class ExamplePojo {
    @Id
    @GeneratedValue
    private Long id;
    private String name;


    public ExamplePojo(String name) {
        this.name = name;
    }

    public ExamplePojo() {

    }

    @Override
    public String toString() {
        return "ExamplePojo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
