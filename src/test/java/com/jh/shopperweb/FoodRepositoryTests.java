package com.jh.shopperweb;

import com.jh.shopperweb.food.Food;
import com.jh.shopperweb.food.FoodRepository;
import com.jh.shopperweb.user.User;
import com.jh.shopperweb.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FoodRepositoryTests {
@Autowired private FoodRepository repo;
@Autowired private UserRepository userRepository;


@Test
    public void testAddNew(){

    Optional<User> optionalUser = userRepository.findById(2);
    User user = optionalUser.get();

    Food food = new Food();
    food.setFoodId(11);
    food.setUser(user);
    food.setCalories(200.0);
    food.setFoodName("Cheese");
    repo.save(food);

    Assertions.assertThat(food).isNotNull();
    Assertions.assertThat(food.getFoodId()).isGreaterThan(0);
}

@Test
    public void testListAll(){
    Iterable<Food> foods = repo.findAll();
    Assertions.assertThat(foods).hasSizeGreaterThan(0);

    for (Food food: foods){
        System.out.println(food);
    }
}

@Test
    public void testUpdate(){
    Integer foodId = 4;
    Optional<Food> optionalFood = repo.findById(foodId);
    Food food = optionalFood.get();
    food.setFoodDesc("Test");
    repo.save(food);

    Food updatedFood = repo.findById(foodId).get();
    Assertions.assertThat(updatedFood.getFoodDesc()).isEqualTo("Test");
}

@Test
    public void testGet(){
    Integer foodId = 4;
    Optional<Food> optionalFood = repo.findById(foodId);
    Assertions.assertThat(optionalFood).isPresent();
    System.out.println(optionalFood.get());
}


}
