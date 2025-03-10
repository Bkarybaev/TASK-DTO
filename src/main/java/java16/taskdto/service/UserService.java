package java16.taskdto.service;

import java16.taskdto.entityes.User;
import java16.taskdto.request.LoginDto;
import java16.taskdto.request.RegisterRequest;
import java16.taskdto.request.SimpleRequest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {
    User login(LoginDto user);

    SimpleRequest save(RegisterRequest newUser);

    Optional<User> findByEmail(String username);

    boolean deleteByIdUser(Long id);
}

