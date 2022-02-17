package com.jh.shopperweb.users_recipes;

import com.jh.shopperweb.food.Food;
import com.jh.shopperweb.recipe.Recipe;
import com.jh.shopperweb.user.User;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "users_recipes")
@IdClass(UsersRecipesId.class)
public class UsersRecipes implements Serializable{


        @Id
        @ManyToOne
        @JoinColumn(name = "recipe_id")
        private Recipe recipeId;


        @Id
        @ManyToOne
        @JoinColumn(name = "user_id")
        private User userId;

        @Column
        private String date;

}
