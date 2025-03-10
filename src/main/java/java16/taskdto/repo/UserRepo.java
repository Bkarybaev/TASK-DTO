package java16.taskdto.repo;

import java16.taskdto.entityes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = :email")
    Optional<User> findByGmail(String email);
}
