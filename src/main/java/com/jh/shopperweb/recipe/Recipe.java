package com.jh.shopperweb.recipe;

import com.jh.shopperweb.food.Food;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToMany()
    @JoinTable(
            name = "recipes_foods",
            joinColumns = { @JoinColumn(name = "recipeId") },
            inverseJoinColumns = { @JoinColumn(name = "foodId") }
    )
    List<Food> foods = new ArrayList<>();

    public Integer getRecipeId() {
        return recipeId;
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

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
