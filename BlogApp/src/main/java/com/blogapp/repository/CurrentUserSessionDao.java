package com.blogapp.repository;

import java.util.List;
import java.util.Optional;

import com.blogapp.model.CurrentUserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CurrentUserSessionDao extends JpaRepository<CurrentUserSession, Integer>{

//    public Optional<CurrentUserSession> findBy(Integer userId);

    public CurrentUserSession  findByUniqueId(String uniqueId);

    @Query("select c.Id from CurrentUserSession c where c.UserId=?1")
    public String findByUserId(Integer userId);


}
