package com.jh.shopperweb.users_recipes;

import com.jh.shopperweb.food.Food;
import com.jh.shopperweb.recipe.Recipe;
import com.jh.shopperweb.user.User;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

//Entity which describes a user's recipes
@Entity
@Table(name = "users_recipes")
public class UsersRecipes implements Serializable{


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer urId;

        @ManyToOne
        @JoinColumn(name = "recipe_id")
        private Recipe recipeId;


        @ManyToOne
        @JoinColumn(name = "user_id")
        private User userId;

        @Column
        private String date;

        public Integer getUrId() {
                return urId;
        }

        public void setUrId(Integer urId) {
                this.urId = urId;
        }

        public Recipe getRecipeId() {
                return recipeId;
        }

        public void setRecipeId(Recipe recipeId) {
                this.recipeId = recipeId;
        }

        public User getUserId() {
                return userId;
        }

        public void setUserId(User userId) {
                this.userId = userId;
        }

        public String getDate() {
                return date;
        }

        public void setDate(String date) {
                this.date = date;
        }
}
