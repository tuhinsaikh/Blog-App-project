package com.blogapp.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.blogapp.model.CurrentUserSession;
import com.blogapp.model.User;
import com.blogapp.model.UserDTO;
import com.blogapp.repository.CurrentUserSessionDao;
import com.blogapp.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;

@Service
public class UserLoginServiceImpl implements UserLoginService{

    @Autowired
    private CurrentUserSessionDao currentusersessionDao;

    @Autowired
    private UserDao uDao;

    @Override
    public CurrentUserSession userLogin(UserDTO userDto) throws Exception {

        User u = uDao.findByMobile(userDto.getMobileNo());

        if(u==null) {
            return null;
        }
        String currentUserId = null;
        currentUserId = currentusersessionDao.findByUserId(u.getUserId());
        CurrentUserSession currentUserSession = null;
        if(currentUserId!=null) {
            Optional<CurrentUserSession> opt = currentusersessionDao.findById(Integer.valueOf(currentUserId));
            if(opt.isEmpty()){
                throw new Exception("Server error");
            }
            currentUserSession = opt.get();
            return currentUserSession;
        }

        if(u.getPassword().equals(userDto.getPassword())) {
            String key = RandomString.make(6);
            currentUserSession = new CurrentUserSession(u.getUserId(), key,LocalDateTime.now());;
            currentusersessionDao.save(currentUserSession);
            return currentUserSession;

        }else return null;

    }

}
