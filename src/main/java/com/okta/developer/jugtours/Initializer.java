package com.okta.developer.jugtours;

import com.okta.developer.jugtours.entity.Event;
import com.okta.developer.jugtours.entity.Group;
import com.okta.developer.jugtours.repository.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
public class Initializer implements CommandLineRunner {
    private final GroupRepository groupRepository;

    public Initializer(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    // @Override basically just... overrides the implementation of a method in a superclass/interface
    /*
    In this case, CommandLineRunner is the interface, and it only has a singular method: run
    The point of implementing CommandLineRunner is to use the fact CLR runs before Spring Boot Application
    So the data generation implementation below will happen before the app runs, so we'll have some data
    to work with!
     */
    @Override
    public void run(String... args) {
        /*
        JUG means Java User Groups (The Youtuber attends these groups for events)

        Stream.of creates a stream, a sequence of elements
        (really just a list of elements which can be kind of treated like an array).

        forEach specifies what action to take for each element identified in Stream
         - forEach takes a function as an argument

        name is the parameter, -> is the lambda function,
        which basically tells a function(save() in this case) how to receive and process the parameters.
         */
        Stream.of("Seattle JUG", "Denver JUG", "Dublin JUG", "London JUG").forEach(
                name ->
                        groupRepository.save(new Group(name))
        );

        // Pulls an instance of JUG(Seattle JUG) out of the H2-Database
        Group exampleJug = groupRepository.findByName("Seattle JUG");

        // Using @Builder that was annotated on the Entity class, an immutable event instance is created
        // Note Instant.parse, since the date field was set to type Instant
        Event event = Event.builder().title("Micro Frontends for Java Developers")
                .description("JHipster now has micro-frontend support!")
                . date(Instant.parse("2022-09-13T17:00:00.000Z"))
                .build();

        // Collections.singleton saves a single immutable object into the "event" field
        exampleJug.setEvents(Collections.singleton(event));

        // The group instance is now saved
        groupRepository.save(exampleJug);

        // findAll() retrieves all entries, then forEach() prints them all out
        // System.out::println, the println is used as a method reference
        groupRepository.findAll().forEach(System.out::println);
    }
}
