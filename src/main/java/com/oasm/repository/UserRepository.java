package com.oasm.repository;

import com.oasm.domain.UserList;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserList, Long> {
    Optional<UserList> findByUserId(String user_id);
    @NonNull List<UserList> findAll();
}
