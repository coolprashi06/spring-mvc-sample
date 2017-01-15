package com.prashast.service;

import com.prashast.dto.User;
import com.prashast.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecuredRestService {

    @Autowired
    UserRepository userRepository;

    public User getUser(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> findUsersByLastName(String lastName){
        return userRepository.findByLastName(lastName);
    }

}
