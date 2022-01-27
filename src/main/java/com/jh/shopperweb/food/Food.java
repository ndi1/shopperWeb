package com.jh.shopperweb.food;

import javax.persistence.*;


    @Entity
    @Table(name = "foods")
    public class Food {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
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

        public Double getServingSize() {
            return servingSize;
        }

        public void setServingSize(Double servingSize) {
            this.servingSize = servingSize;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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
