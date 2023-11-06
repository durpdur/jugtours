package com.okta.developer.jugtours;

import com.okta.developer.jugtours.entity.Group;
import com.okta.developer.jugtours.repository.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        Stream.of creates a stream from the list of group
         */
        Stream.of("Seattle JUG", "Denver JUG", "Dublin JUG", "London JUG").forEach(
                name ->
                        groupRepository.save(new Group(name))
        );
    }
}
