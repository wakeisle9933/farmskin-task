package com.farmskin.task.controller;

import com.farmskin.task.domain.Category;
import com.farmskin.task.domain.FarmskinBook;
import com.farmskin.task.repository.BookEntityRepository;
import com.farmskin.task.repository.JpaBookEntityRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final JpaBookEntityRepository jpaBookEntityRepository;

    @GetMapping("all")
    public List<FarmskinBook> findAll() {
        return jpaBookEntityRepository.findAll();
    }

    @GetMapping("findByCategory")
    public List<FarmskinBook> findByCategory(@RequestParam(value = "category") String category) {
        return jpaBookEntityRepository.findByCategory(category);
    }

    @GetMapping("findByAuthorAndName")
    public List<FarmskinBook> findByAuthorAndName(@RequestParam(value = "author") String author, @RequestParam(value = "name") String name) {
        return jpaBookEntityRepository.findByAuthorAndName(author, name);
    }
}
