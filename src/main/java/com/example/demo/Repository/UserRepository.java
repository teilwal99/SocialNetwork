
package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //findByUsername is not needed as we are using a custom method in UserService
    Optional<User> findByUsername(String username);
}