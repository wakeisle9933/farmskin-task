package com.farmskin.task.repository;

import com.farmskin.task.domain.FarmskinBook;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.*;
import java.util.List;

@Repository
public class JpaBookEntityRepository implements BookEntityRepository {

    @PersistenceContext
    private final EntityManager em;

    public JpaBookEntityRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<FarmskinBook> findAll() {
        List<FarmskinBook> result = em.createQuery("select m from farmskin_book m", FarmskinBook.class).getResultList();
        return result;
    }

    @Override
    public List<FarmskinBook> findByCategory(String category) {
        String query = "select m "              +
                       "  from farmskin_book m " +
                       " where m.category = '" + category + "' ";
        List<FarmskinBook> result = em.createQuery(query, FarmskinBook.class).getResultList();
        return result;
    }

    @Override
    public List<FarmskinBook> findByAuthorAndName(String author, String name) {
        String query = "select m "              +
                       "  from farmskin_book m " +
                       " where m.author = '" + author + "' " +
                       "   and m.name = '" + name + "' ";
        List<FarmskinBook> result = em.createQuery(query, FarmskinBook.class).getResultList();
        return result;
    }

    @Override
    public String rentBook(String category, String name) {
        String findQuery = "select status" +
                           "  from farmskin_book " +
                           " where category = :category " +
                           "   and name = :name ";

        Object exist = em.createQuery(findQuery)
                         .setParameter("category", category)
                         .setParameter("name", name)
                         .getResultList().get(0);

        if(exist != null) {
            if(exist.equals("대여")) {
                return "현재 대여중인 항목입니다";
            } else if (exist.equals("훼손") || exist.equals("분실")) {
                return "훼손/분실된 항목입니다";
            } else {
                return "대여/반납할 수 없는 상태값입니다 상태값 : " + exist;
            }
        }

        String query = "update farmskin_book m " +
                       "   set m.status = '대여'" +
                       " where m.category = :category" +
                       "   and m.name = :name";

        int resultCnt = em.createQuery(query)
                          .setParameter("category", category)
                          .setParameter("name", name)
                          .executeUpdate();

        return name + " 대여 완료";
    }

}
