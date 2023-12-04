package com.example.APIForFinalProject.Repositories;

import com.example.APIForFinalProject.Models.UserBuy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserBuyRepository extends JpaRepository<UserBuy,Long> {

    @Query(value="SELECT * FROM user_buy where user_id = :c", nativeQuery=true)
    List<UserBuy> findPersonShopByPerson (@Param("c")Long idPerson);
}
