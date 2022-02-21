package com.jh.shopperweb.users_foods;

import com.jh.shopperweb.users_recipes.UsersRecipes;
import com.jh.shopperweb.users_recipes.UsersRecipesNotFoundException;
import com.jh.shopperweb.users_recipes.UsersRecipesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersFoodsService {

    @Autowired
    private UsersFoodsRepository repo;


    public void save(UsersFoods usersFoods) {

        repo.save(usersFoods);
    }

    public UsersFoods get(Integer id) throws UsersRecipesNotFoundException {
        Optional<UsersFoods> result = repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new UsersRecipesNotFoundException("Could not find User Food with ID "+id);
    }

    public void delete(Integer id) throws UsersFoodsNotFoundException {

        repo.deleteById(id);

    }


}
