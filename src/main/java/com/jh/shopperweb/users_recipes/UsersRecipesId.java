package com.jh.shopperweb.users_recipes;

import java.io.Serializable;
import java.util.Objects;

public class UsersRecipesId implements Serializable {

    private Integer recipeId;

    private Integer userId;

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersRecipesId)) return false;
        UsersRecipesId that = (UsersRecipesId) o;
        return recipeId.equals(that.recipeId) && userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, userId);
    }
}
