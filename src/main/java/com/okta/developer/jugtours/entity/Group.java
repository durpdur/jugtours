package com.okta.developer.jugtours.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Set;

/*
@Data creates the following
 - Getters & Setters
 - toString(): Commonly used as a debug tool. Shows the fields(and hence the state) of a class.
    ex:
    @Data
    public class Person {
        private String name;
        private int age;
    }

    public class Main {
        public static void main (String[] args) {
            Person person = new Person();
            person.setName("Chunga");
            person.setAge(25);

            System.out.println(person.toString());
        }
    }

    It will output:
    Person(name=Chunga, age=25)

 - equals(): Returns a boolean depending on if the two compared objects' fields are the same
 - hashCode(): Generates a hashcode depending on the values of the object's fields.
                If they are the same, the hashcode will be the same.
                Useful in HashSet/HashMap
 */
@Data
/*
@NoArgsConstructor
 - Creates a constructor that takes no arguments
@AllArgsConstructor
 - Creates a constructor that takes all fields as arguments
 */
@NoArgsConstructor
/*
@RequiredArgsConstructor
 - Generates a constructor w/ all fields marked with 'final'
 */
@RequiredArgsConstructor
@Entity
@Table(name="user_group")
public class Group {
    // @Id identifies which field should be used as the primary key
    @Id
    // @GeneratedValue defaults to (strategy=GenerationType.AUTO), which basically means depending on
    // - JPA provider
    // - database used
    // It will automatically choose the best one
    @GeneratedValue
    private Long id;

    // @NonNull specifies the field should never be null
    // If null, NullPointerException will be thrown.
    @NonNull
    private String name;
    private String address;
    private String city;
    private String stateOrProvince;
    private String country;
    private String postalCode;

    // @ManyToOne basically says there's many of this annotated field to the current Entity class
    // (cascade=CascadeType.PERSIST) basically just says if the annotated field is saved(persisted),
    // so will the related Entity.
    @ManyToOne(cascade=CascadeType.PERSIST)
    private User user;

    // (fetch=FetchType.Eager) means that when the owning entity(Group) is fetched,
    // the related entities are loaded eagerly (Event)
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    // Set<Event> means a set of Event, with no duplicates (As the Set data structure doesn't allow it)
    private Set<Event> events;
}

/*
Many Event to One Group, Many Groups to One User
Many User to Many Event
 */
