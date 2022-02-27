package com.jh.shopperweb.users_items;


import org.springframework.data.jpa.repository.JpaRepository;

//Repository which contains all queries related to a user's items
public interface UsersItemsRepository extends JpaRepository<UsersItems,Integer> {
}
