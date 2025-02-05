//package com.blogapp.service;
//
//import com.blogapp.model.User;
//import com.blogapp.repository.UserDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class CustomUserDetails implements UserDetailsService {
//
//    @Autowired
//    private UserDao userDao;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> optUser = userDao.findByEmail(username);
//        if (optUser.isEmpty()) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        User user = optUser.get();
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),      // Username
//                user.getPassword(),   // Password
//                new ArrayList<>()
//        );
//    }
//
//}
