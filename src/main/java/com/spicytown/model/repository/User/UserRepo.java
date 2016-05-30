package com.spicytown.model.repository.User;

import com.spicytown.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yunlongxu on 4/27/16.
 */

@Repository
public interface UserRepo extends CrudRepository<User, Long>, UserCustom {
    public User findByName(String name);
}
