package com.jh.shopperweb.item;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Integer> {

    @Query(value = "Select * from items i where i.item_name like %:keyword% or i.item_desc like %:keyword%", nativeQuery = true)
    List<Item> findByKeyword(@Param("keyword") String keyword);

}
