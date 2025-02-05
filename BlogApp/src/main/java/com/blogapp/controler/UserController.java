package com.blogapp.controler;
import javax.validation.Valid;

import com.blogapp.model.CurrentUserSession;
import com.blogapp.model.User;
import com.blogapp.model.UserDTO;
import com.blogapp.service.UserLoginService;
import com.blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService uService;

    @Autowired
    private UserLoginService ulogService;

    @PostMapping("/regisrtration")

    public ResponseEntity<User> saveUserController(@Valid @RequestBody User user) throws Exception {
        User responseUser =  uService.saveUer(user);
        return new  ResponseEntity<User>(responseUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<CurrentUserSession> userLoginController(@Valid @RequestBody UserDTO userDto) throws Exception {
        CurrentUserSession currentUserSession =  ulogService.userLogin(userDto);
        if(currentUserSession==null) return new ResponseEntity<CurrentUserSession>((CurrentUserSession) null,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<CurrentUserSession>(currentUserSession,HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateUser/{key}")
    public ResponseEntity<User> updateUserCredentialController(@Valid @RequestBody User user, @PathVariable String key) throws Exception {
        User responseUser =  uService.updateUserCredential(user, key);
        return new ResponseEntity<User>(responseUser,HttpStatus.ACCEPTED);
    }

    @PostMapping("/logout/{key}")
    public ResponseEntity<String> userLogoutController(@Valid @PathVariable String key) throws Exception {
        String msg =  uService.userLogout(key);
        return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
    }

}
