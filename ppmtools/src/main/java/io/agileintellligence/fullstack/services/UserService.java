package io.agileintellligence.fullstack.services;

import io.agileintellligence.fullstack.domain.User;
import io.agileintellligence.fullstack.exceptions.UsernameAlreadyExistsException;
import io.agileintellligence.fullstack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser)
    {
        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

            //USername must be unique
            newUser.setUsername(newUser.getUsername());
            //Make Sure Password and ConfirmPassword Match
            //Dont Persist ConfirmPass
            return userRepository.save(newUser);
        }catch (Exception e)
        {
            throw new UsernameAlreadyExistsException("Username:" + newUser.getUsername() + " already exists");
        }


    }


}
