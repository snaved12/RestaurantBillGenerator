package net.java.naved.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.java.naved.model.Menu;
import net.java.naved.service.MenuService;

@Controller
public class MenuController {
    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/menu")
    public String showMenu(Model model) {
        List<Menu> menus = menuService.getAllEntities();
        model.addAttribute("menus", menus);
        return "menu";
    }
}
