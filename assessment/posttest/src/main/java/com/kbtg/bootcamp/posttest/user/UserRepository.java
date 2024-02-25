package com.kbtg.bootcamp.posttest.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT l FROM User  l WHERE l.id = :id")
    User findUserById(String id);
}
