package com.jh.shopperweb.user;

import org.springframework.data.jpa.repository.JpaRepository;

//Repository for user queries
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);


}
