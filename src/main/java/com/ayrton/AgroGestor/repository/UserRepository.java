package com.ayrton.AgroGestor.repository;


import com.ayrton.AgroGestor.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    UserDetails findByLogin(String login);
}
