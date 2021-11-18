package com.farmskin.task.controller;

import com.farmskin.task.repository.JpaBookEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/edit")
public class EditContoller {

    private final JpaBookEntityRepository jpaBookEntityRepository;

    @GetMapping("lossBook")
    public String lossBook(@RequestParam(value = "category") String category, @RequestParam(value = "name") String name, @RequestParam(value = "status") String status, @RequestParam(value = "remark") String remark) {
        return jpaBookEntityRepository.lossBook(category, name, status, remark);
    }

    @GetMapping("categoryChangeBook")
    public String categoryChangeBook(@RequestParam(value = "category") String category, @RequestParam(value = "name") String name, @RequestParam(value = "newCategory") String newCategory) {
        return jpaBookEntityRepository.categoryChangeBook(category, name, newCategory);
    }
}
