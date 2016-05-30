package com.spicytown.service.User;

import com.spicytown.model.entity.User;

/**
 * Created by cheyikung on 4/29/16.
 */
public interface UserServices {
    public long saveUserAccount(User user);
    public User findUserAccountById(long id);
    public User findUserAccountByName(String name);
    public User findUserAccountByNameAndPassword(User user);
    public void deleteUserAccount(User user);
    public String verifyUserAccount(long id);
}
