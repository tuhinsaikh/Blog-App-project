package com.blogapp.service;

import java.util.Optional;

import com.blogapp.model.CurrentUserSession;
import com.blogapp.model.User;
import com.blogapp.repository.CurrentUserSessionDao;
import com.blogapp.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao uDao;

    @Autowired
    private CurrentUserSessionDao cuserDao;

    @Override
    public User saveUer(User user) throws Exception {
        User u = uDao.findByMobile(user.getMobile());
        if(u==null) return uDao.save(user);
        else throw new Exception("User Already Exists");
    }

    @Override
    public User updateUserCredential(User user, String key) throws Exception {
        CurrentUserSession cuser =  cuserDao.findByUniqueId(key);
        if(cuser==null) throw new Exception("user not loged in");
        Optional<User> opt = uDao.findById(cuser.getUserId());
        if(opt.isEmpty()) throw new Exception("user not found");
        User userWithSameNumber = uDao.findByMobile(user.getMobile());
        if(userWithSameNumber!=null) throw new Exception("one user find with the same mobile number");
        User saveUser = uDao.save(user);
        cuserDao.delete(cuser);
        uDao.delete(opt.get());
        return saveUser;
    }

    @Override
    public String userLogout(String key) throws Exception {
        CurrentUserSession cuser =  cuserDao.findByUniqueId(key);
        if(cuser==null) throw new Exception("user not loged in");
        cuserDao.delete(cuser);
        return "Logged Out successfully";
    }



}
