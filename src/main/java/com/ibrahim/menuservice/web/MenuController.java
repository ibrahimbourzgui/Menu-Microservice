package com.ibrahim.menuservice.web;

import com.ibrahim.menuservice.entities.Menu;
import com.ibrahim.menuservice.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("/menus/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable("id") long id) {
        Optional<Menu> menu = menuRepository.findById(id);
        if (menu.isPresent()) {
            return new ResponseEntity<>(menu.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/menus")
    public ResponseEntity<Menu> createMenu(@RequestBody Menu m) {
        try {
            Menu _m = menuRepository.save(new Menu(null,m.getTitle(), m.getParent(), m.getLink()));
            return new ResponseEntity<>(_m, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/menus/{id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable("id") long id, @RequestBody Menu m) {
        Menu menu = menuRepository.findById(id).get();
        if (menu != null) {
            menu.setTitle(m.getTitle());
            menu.setParent(m.getParent());
            menu.setLink(m.getLink());
            return new ResponseEntity<>(menuRepository.save(menu), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/menus/{id}")
    public ResponseEntity<HttpStatus> deleteMenu(@PathVariable("id") long id) {
        try {
            menuRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/menus")
    public ResponseEntity<List<Menu>> findAllMenus() {
        try {
            List<Menu> menus = menuRepository.findAll();
            if (menus.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(menus, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
