package java16.taskdto.repo;

import java16.taskdto.entityes.User;
import java16.taskdto.exceptions.UserNotFound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = :email")
    Optional<User> findByGmail(String email);

    default User findByIdExeption(Long id){
        return findById(id).orElseThrow(()
                -> new UserNotFound(String.format("User with email %s not found!", id)));
    }
}
