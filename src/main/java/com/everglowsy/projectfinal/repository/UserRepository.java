package com.everglowsy.projectfinal.repository;

import com.everglowsy.projectfinal.model.Role;
import com.everglowsy.projectfinal.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel,Long> {

    UserModel findByEmail(String email);
    List<UserModel> findAllByRole(Role role);
    Optional<UserModel> findById(Long id_user);
    void deleteById(Long id_user);
}
