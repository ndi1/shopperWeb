package com.jh.shopperweb.users_goals;

import com.jh.shopperweb.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersGoalsRepository extends JpaRepository<UsersGoals,Integer> {

    @Query(value = "Select * from users_goals where user_id=:userId",nativeQuery = true)
    UsersGoals fetchUserGoals (@Param("userId")Integer userId);

}
