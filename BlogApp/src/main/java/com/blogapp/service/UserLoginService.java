package com.blogapp.service;

import com.blogapp.model.CurrentUserSession;
import com.blogapp.model.UserDTO;

public interface UserLoginService {
    public CurrentUserSession userLogin(UserDTO userDto) throws Exception;
}
