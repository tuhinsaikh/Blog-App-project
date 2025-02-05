package com.blogapp.repository;
import com.blogapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends JpaRepository<User, Integer>{

    public User findByMobile(String mobile);

//	public User findByUserId(Integer userId);
}
