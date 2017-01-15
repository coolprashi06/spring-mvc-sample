package com.prashast.repository;

import com.prashast.dto.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface UserRepository extends MongoRepository<User,String> {

    User findByUsername(String username);
    List<User> findByLastName(String lastName);
}
