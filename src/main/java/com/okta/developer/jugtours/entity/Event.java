package com.okta.developer.jugtours.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
/*
@Builder
 - Generates a static(belonging to the instance) inner class for the annotated class
 - Basically is an alternate way for you to instantiate and set fields for an object
 - Several benefits though
    - Immutability: Once objects are created, they are basically gucci
    - Null Safety: Fields can't be null
    - Consistency: All fields are required to be set, so no instances are left incomplete/incorrect
 */
@Builder
@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    private Instant date;
    private String title;
    private String description;

    @ManyToMany
    private Set<User> attendees;
}
