package com.farmskin.task.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="farmskin_book")
public class FarmskinBook {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;
    private String category;
    private String author;
    private String name;
    private String status;

}
