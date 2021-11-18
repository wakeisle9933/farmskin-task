package com.farmskin.task.repository;

import com.farmskin.task.domain.FarmskinBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface BookEntityRepository {
    List<FarmskinBook> findAll();
    List<FarmskinBook> findByCategory(@RequestParam(value = "category") String category);
    List<FarmskinBook> findByAuthorAndName(@RequestParam(value = "author") String author, @RequestParam(value = "name") String name);
    @Transactional
    String rentBook(@RequestParam(value = "category") String category, @RequestParam(value = "name") String name);
    @Transactional
    String returnBook(@RequestParam(value = "category") String category, @RequestParam(value = "name") String name);
    @Transactional
    String lossBook(@RequestParam(value = "category") String category, @RequestParam(value = "name") String name, @RequestParam(value = "status") String status, @RequestParam(value = "remark") String remark);
    @Transactional
    String categoryChangeBook(@RequestParam(value = "category") String category, @RequestParam(value = "name") String name, @RequestParam(value = "newCategory") String newCategory);
    @Transactional
    String addBook(@RequestParam(value = "category") String category, @RequestParam(value = "author") String author, @RequestParam(value = "name") String name);
}