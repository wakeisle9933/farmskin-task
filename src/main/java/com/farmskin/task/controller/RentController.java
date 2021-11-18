package com.farmskin.task.controller;

import com.farmskin.task.repository.JpaBookEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rent")
public class RentController {

    private final JpaBookEntityRepository jpaBookEntityRepository;

    @GetMapping("rentBook")
    public String rentBook(@RequestParam(value = "category") String category, @RequestParam(value = "name") String name) {
        return jpaBookEntityRepository.rentBook(category, name);
    }

    @GetMapping("returnBook")
    public String returnBook(@RequestParam(value = "category") String category, @RequestParam(value = "name") String name) {
        return jpaBookEntityRepository.returnBook(category, name);
    }

    @GetMapping("lossBook")
    public String lossBook(@RequestParam(value = "category") String category, @RequestParam(value = "name") String name, @RequestParam(value = "status") String status, @RequestParam(value = "remark") String remark) {
        return jpaBookEntityRepository.lossBook(category, name, status, remark);
    }

}
