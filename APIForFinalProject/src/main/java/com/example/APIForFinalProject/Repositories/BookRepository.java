package com.example.APIForFinalProject.Repositories;

import com.example.APIForFinalProject.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    @Query(value = "SELECT * FROM product inner join shop on product_id = id_product where address =:address and name=:name", nativeQuery = true)
    List<Book> findBookByNameAndAddress(@Param("address")String address, @Param("name")String name);
}
