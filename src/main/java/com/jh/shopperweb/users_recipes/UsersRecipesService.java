package com.jh.shopperweb.users_recipes;

import com.jh.shopperweb.user.User;
import com.jh.shopperweb.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersRecipesService{

    @Autowired
    private UsersRecipesRepository repo;


    public void save(UsersRecipes usersRecipes) {

        repo.save(usersRecipes);
    }

    public UsersRecipes get(Integer id) throws UsersRecipesNotFoundException {
        Optional<UsersRecipes> result = repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new UsersRecipesNotFoundException("Could not find User Recipe with ID "+id);
    }

    public void delete(Integer id) throws UsersRecipesNotFoundException {

        repo.deleteById(id);

    }

    public void deleteFromDay(String date, Integer userId, Integer recipeId){
        repo.deleteRecipesFromDay(date,userId,recipeId);
    }

}
