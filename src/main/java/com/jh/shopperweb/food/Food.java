package com.jh.shopperweb.food;


import com.jh.shopperweb.recipe.Recipe;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
    @Table(name = "foods")
    public class Food {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer foodId;
        @Column
        private String foodName;
        @Column
        private String foodDesc;
        @Column
        private Double calories;
        @Column
        private Double carbs;
        @Column
        private Double fats;
        @Column
        private Double protein;
        @Column
        private Double servingSize;
        @Column
        private Double servings;
        @Column
        private String servType;
        @Column
        private Double price;
    @ManyToMany(mappedBy = "foods")
    private Set<Recipe> recipes = new HashSet<>();




    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Double getServingSize() {
            return servingSize;
        }

        public void setServingSize(Double servingSize) {
            this.servingSize = servingSize;
        }

        public String getFoodName() {
            return foodName;
        }

        public void setFoodName(String foodName) {
            this.foodName = foodName;
        }

        public String getFoodDesc() {
            return foodDesc;
        }

        public void setFoodDesc(String foodDesc) {
            this.foodDesc = foodDesc;
        }

        public Double getCalories() {
            return calories;
        }

        public void setCalories(Double calories) {
            this.calories = calories;
        }

        public Double getCarbs() {
            return carbs;
        }

        public void setCarbs(Double carbs) {
            this.carbs = carbs;
        }

        public Double getFats() {
            return fats;
        }

        public void setFats(Double fats) {
            this.fats = fats;
        }

        public Double getProtein() {
            return protein;
        }

        public void setProtein(Double protein) {
            this.protein = protein;
        }

        public Double getServings() {
            return servings;
        }

        public void setServings(Double servings) {
            this.servings = servings;
        }

        public String getServType() {
            return servType;
        }

        public void setServType(String servType) {
            this.servType = servType;
        }
    }
