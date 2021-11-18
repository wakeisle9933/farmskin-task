package com.farmskin.task.controller;

import com.farmskin.task.repository.JpaBookEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/add")
public class AddController {

    private final JpaBookEntityRepository jpaBookEntityRepository;

    @GetMapping("addBook")
    public String addBook(@RequestParam(value = "category") String category, @RequestParam(value = "author") String author, @RequestParam(value = "name") String name) {
        return jpaBookEntityRepository.addBook(category, author, name);
    }

}