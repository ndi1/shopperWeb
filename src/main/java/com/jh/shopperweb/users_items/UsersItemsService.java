package com.jh.shopperweb.users_items;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersItemsService {

@Autowired
private UsersItemsRepository repo;


    public void save(UsersItems usersItems) {

        repo.save(usersItems);
    }

    public UsersItems get(Integer id) throws UsersItemsNotFoundException {
        Optional<UsersItems> result = repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new UsersItemsNotFoundException("Could not find User Item with ID "+id);
    }

    public void delete(Integer id) throws UsersItemsNotFoundException {

        repo.deleteById(id);

    }



}
