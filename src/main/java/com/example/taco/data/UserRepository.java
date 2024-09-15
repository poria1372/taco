package com.example.taco.data;

import com.example.taco.UserObj;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserObj,Long> {
    UserObj findByUsername(String username);
}
