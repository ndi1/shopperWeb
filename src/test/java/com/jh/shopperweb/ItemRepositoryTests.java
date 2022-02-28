package com.jh.shopperweb;

import com.jh.shopperweb.food.Food;
import com.jh.shopperweb.food.FoodRepository;
import com.jh.shopperweb.item.Item;
import com.jh.shopperweb.item.ItemRepository;
import com.jh.shopperweb.user.User;
import com.jh.shopperweb.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ItemRepositoryTests {
@Autowired private ItemRepository repo;
@Autowired private UserRepository userRepository;

    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

@Test
    public void testAddNew(){

    Optional<User> optionalUser = userRepository.findById(2);
    User user = optionalUser.get();

    Item item = new Item();
    item.setUserItem(user);
    item.setItemId(9);
    item.setItemName("testItem");

    repo.save(item);

    Assertions.assertThat(item).isNotNull();
    Assertions.assertThat(item.getItemId()).isGreaterThan(0);
}

@Test
    public void testListAll(){
    Iterable<Item> items = repo.findAll();
    Assertions.assertThat(items).hasSizeGreaterThan(0);

    items.forEach(item -> logger.log(Level.WARNING,item.toString()));

}

@Test
    public void testUpdate(){
    Integer itemId = 4;
    Optional<Item> optionalItem = repo.findById(itemId);
    Item item = optionalItem.get();
    item.setItemDesc("Test");
    repo.save(item);

    Item updatedItem = repo.findById(itemId).get();
    Assertions.assertThat(updatedItem.getItemDesc()).isEqualTo("Test");
}

@Test
    public void testGet(){
    Integer itemId = 4;
    Optional<Item> optionalItem = repo.findById(itemId);
    Assertions.assertThat(optionalItem).isPresent();
    logger.log(Level.WARNING,optionalItem.get().toString());
}


}
