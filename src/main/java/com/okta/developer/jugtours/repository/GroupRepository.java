package com.okta.developer.jugtours.repository;

import com.okta.developer.jugtours.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

// Note that H2 Database is being used, not PostgreSQL or MySQL
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByName(String name);
}
