package com.jh.shopperweb.food;


import com.jh.shopperweb.recipe.Recipe;
import com.jh.shopperweb.user.User;
import com.jh.shopperweb.users_foods.UsersFoods;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

//Entity for a food object
@Entity
    @Table(name = "foods")
    public class Food {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer foodId;
        @Column
        @NotEmpty(message = "Food name cannot be emtpy.")
        private String foodName;
        @Column
        private String foodDesc;
        @Column
        @NotNull(message = "Calories cannot be empty.")
        private Double calories;
        @Column
        @NotNull(message = "Carbs cannot be empty.")
        private Double carbs;
        @Column
        @NotNull(message = "Fats cannot be empty.")
        private Double fats;
        @Column
        @NotNull(message = "Protein cannot be empty.")
        private Double protein;
        @Column
        @NotNull
        private Double servingSize;
        @Column
        private Double servings;
        @Column
        private String servType;
        @Column
        @NotNull(message = "Price cannot be empty.")
        private Double price;
    @ManyToMany(mappedBy = "foods")
    private Set<Recipe> recipes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "foodId")
    private Set<UsersFoods> usersFoods;

    public Set<UsersFoods> getUsersFoods() {
        return usersFoods;
    }

    public void setUsersFoods(Set<UsersFoods> usersFoods) {
        this.usersFoods = usersFoods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    @Override
    public String toString() {
        return "Food{" +
                "foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", foodDesc='" + foodDesc + '\'' +
                ", calories=" + calories +
                ", carbs=" + carbs +
                ", fats=" + fats +
                ", protein=" + protein +
                ", servingSize=" + servingSize +
                ", servings=" + servings +
                ", servType='" + servType + '\'' +
                ", price=" + price +
                ", recipes=" + recipes +
                ", user=" + user +
                ", usersFoods=" + usersFoods +
                '}';
    }
}
