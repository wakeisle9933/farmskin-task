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

        String query = "update farmskin_book " +
                       "   set status = '대여'" +
                       "       , mod_dttm = NOW()" +
                       " where category = :category" +
                       "   and name = :name";

        int resultCnt = em.createQuery(query)
                          .setParameter("category", category)
                          .setParameter("name", name)
                          .executeUpdate();

        return name + " 대여 완료";
    }

    @Override
    public String returnBook(String category, String name) {
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
                // pass
            } else {
                return "대여된 항목만 반납 가능합니다";
            }
        } else {
            return "대여된 항목만 반납 가능합니다";
        }

        String query = "update farmskin_book m " +
                       "   set m.status = NULL" +
                       "       , mod_dttm = NOW()" +
                       " where m.category = :category" +
                       "   and m.name = :name";

        int resultCnt = em.createQuery(query)
                          .setParameter("category", category)
                          .setParameter("name", name)
                          .executeUpdate();

        return name + " 반납 완료";
    }

    @Override
    public String lossBook(String category, String name, String status, String remark) {
        String findQuery = "select status" +
                "  from farmskin_book " +
                " where category = :category " +
                "   and name = :name ";

        Object exist = em.createQuery(findQuery)
                .setParameter("category", category)
                .setParameter("name", name)
                .getResultList().get(0);

        if(exist != null) {
            if(exist.equals("훼손") || exist.equals("분실")) {
                return "이미 훼손/분실된 항목입니다";
            }
        }

        String query = "update farmskin_book " +
                       "   set status = :status" +
                       "       , remark = :remark" +
                       "       , mod_dttm = NOW()" +
                       " where category = :category" +
                       "   and name = :name";

        int resultCnt = em.createQuery(query)
                .setParameter("status", status)
                .setParameter("remark", remark)
                .setParameter("category", category)
                .setParameter("name", name)
                .executeUpdate();

        return name + " " + status + " 처리 완료";
    }

}
