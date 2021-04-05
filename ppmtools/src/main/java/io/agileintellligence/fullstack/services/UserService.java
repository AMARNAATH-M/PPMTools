package io.agileintellligence.fullstack.services;

import io.agileintellligence.fullstack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;
}
