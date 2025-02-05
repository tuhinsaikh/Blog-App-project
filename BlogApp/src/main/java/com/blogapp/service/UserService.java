package com.blogapp.service;

import com.blogapp.model.CurrentUserSession;
import com.blogapp.model.User;

public interface UserService {

    public User saveUer(User user) throws Exception;

    public User updateUserCredential(User user,String key) throws Exception;

    public String userLogout(String key) throws Exception;
}
