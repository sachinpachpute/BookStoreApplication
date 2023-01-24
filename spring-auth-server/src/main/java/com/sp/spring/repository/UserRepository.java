package com.sp.spring.repository;

import com.sp.spring.repository.dao.User;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserName(String username);

    Optional<User> findByUserNameOrEmail(String uName, String eMail);

    Optional<User> findByUserId(String userId);

    Optional<User> findByEmail(String emailId);

    void deleteByUserId(String userId);

    Boolean existsByUserName(String userName);

    Boolean existsByEmail(String email);

}
