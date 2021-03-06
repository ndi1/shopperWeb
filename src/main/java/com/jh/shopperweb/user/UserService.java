package com.jh.shopperweb.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

//Service for implementing user queries
@Service
public class UserService {
    @Autowired
    private UserRepository repo;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public List<User> listAll(){
        return (List<User>) repo.findAll();
    }

    public void save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
       Optional<User> result = repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not find users with ID "+id);
    }

    public void delete(Integer id) throws UserNotFoundException {

        repo.deleteById(id);

    }

}
