package com.jh.shopperweb.users_foods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface UsersFoodsRepository extends JpaRepository<UsersFoods,Integer> {

    @Modifying
    @Transactional
    @Query(value = "Delete FROM users_foods where date=:date and user_id=:userId and food_id=:foodId LIMIT 1",nativeQuery = true)
    void deleteFoodFromDay (@Param("date")String date,@Param("userId")Integer userId,@Param("foodId")Integer foodId);

}
