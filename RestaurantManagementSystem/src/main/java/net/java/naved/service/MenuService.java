package net.java.naved.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.java.naved.model.Menu;
import net.java.naved.repository.Repository;

@Service
public class MenuService {
    private final  Repository repository;

    @Autowired
    public MenuService(Repository repository) {
        this.repository = repository;
    }

    public List<Menu> getAllEntities() {
        return (List<Menu>) repository.findAll();
    }
    
    public long getPriceById(String itemId) {
        Menu menu = repository.findById(itemId).orElse(null);
        if (menu != null) {
            return menu.getPrice();
        }
        else {
        }
            return 0; // Or some default value if the item is not found
        }
    public String getNameById(String itemId) {
    	Menu menu = repository.findById(itemId).orElse(null);
    	if (menu!=null) {
    		return menu.getItem_name();
    	}
    	else {
    		return null;
    	}
    }
   }
