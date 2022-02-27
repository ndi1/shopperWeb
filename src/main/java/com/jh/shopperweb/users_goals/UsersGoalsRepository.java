package com.jh.shopperweb.users_goals;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//Repository for all queries related to a user's goals
public interface UsersGoalsRepository extends JpaRepository<UsersGoals,Integer> {

    @Query(value = "Select * from users_goals where user_id=:userId",nativeQuery = true)
    UsersGoals fetchUserGoals (@Param("userId")Integer userId);

}
