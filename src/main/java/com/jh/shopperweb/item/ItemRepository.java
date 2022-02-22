package com.jh.shopperweb.item;


import com.jh.shopperweb.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Integer> {

    @Query(value = "Select * from items i where i.item_name like %:keyword% or i.item_desc like %:keyword%", nativeQuery = true)
    List<Item> findByKeyword(@Param("keyword") String keyword);

    @Query(value = "Select * from items i where (i.item_name like %:keyword% or i.item_desc like %:keyword%) and i.user_id=:userId", nativeQuery = true)
    List<Item> findUserItemByKeyword(@Param("keyword") String keyword, @Param("userId")Integer userId);

    @Query(value = "Select * from items i where i.user_id = ?1",nativeQuery = true)
    List<Item> findUserItems(Integer userId);

    @Query(value = "Select items.* from items inner JOIN users_items on items.item_id = users_items.item_id where users_items.date=:date and users_items.user_id=:userId", nativeQuery = true)
    List<Item> findUserItemsByDate(@Param("date")String date, @Param("userId")Integer userId);



}
