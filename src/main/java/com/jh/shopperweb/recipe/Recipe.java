package com.jh.shopperweb.recipe;

import com.jh.shopperweb.food.Food;
import com.jh.shopperweb.user.User;
import com.jh.shopperweb.users_recipes.UsersRecipes;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recipeId;
    @Column
    private String recipeName;
    @Column
    private String recipeDesc;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "recipes_foods",
            joinColumns = { @JoinColumn(name = "recipeId") },
            inverseJoinColumns = { @JoinColumn(name = "foodId") }
    )
    Set<Food> foods = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userRecipe;

    @OneToMany(mappedBy = "recipeId")
    private Set<UsersRecipes> usersRecipe;

    public Set<UsersRecipes> getUsersRecipe() {
        return usersRecipe;
    }

    public void setUsersRecipe(Set<UsersRecipes> usersRecipe) {
        this.usersRecipe = usersRecipe;
    }

    public User getUserRecipe() {
        return userRecipe;
    }

    public void setUserRecipe(User userRecipe) {
        this.userRecipe = userRecipe;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public Set<Food> getFoods() {
        return foods;
    }

    public void setFoods(Set<Food> foods) {
        this.foods = foods;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDesc() {
        return recipeDesc;
    }

    public void setRecipeDesc(String recipeDesc) {
        this.recipeDesc = recipeDesc;
    }


}
