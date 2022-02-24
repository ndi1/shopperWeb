package com.jh.shopperweb.users_goals;

import com.jh.shopperweb.user.MyUserDetailsService;
import com.jh.shopperweb.user.User;
import com.jh.shopperweb.users_items.UsersItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsersGoalsService {

    @Autowired
    private UsersGoalsRepository repo;
    @Autowired
    private MyUserDetailsService userDetailsService;

    public void save(UsersGoals usersGoals) {

        repo.save(usersGoals);
    }

    public UsersGoals findUserGoals(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String curUserName = authentication.getName();
        User user = userDetailsService.loadUser(curUserName);
       return repo.fetchUserGoals(user.getUserId());
    }

}
