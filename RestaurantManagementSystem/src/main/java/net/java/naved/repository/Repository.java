package net.java.naved.repository;

import org.springframework.data.repository.CrudRepository;

import net.java.naved.model.Menu;

public interface Repository extends CrudRepository<Menu, String> {
    // You can define custom query methods here if needed	
}