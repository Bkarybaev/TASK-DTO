package java16.taskdto.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import java16.taskdto.entityes.User;
import java16.taskdto.enums.RoleUser;
import java16.taskdto.exceptions.EmailInvalid;
import java16.taskdto.exceptions.PasswordInvalid;
import java16.taskdto.exceptions.UserNotFound;
import java16.taskdto.repo.UserRepo;
import java16.taskdto.request.LoginDto;
import java16.taskdto.request.RegisterRequest;
import java16.taskdto.request.SimpleRequest;
import java16.taskdto.service.UserService;
import java16.taskdto.validation.RegexPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class UserSerImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    //auth


    //login
    @Override
    public User login(LoginDto user) {
        // email pattern check
        if (!RegexPattern.emailPattern(user.getEmail())) {
            throw new EmailInvalid(user.getEmail() + " try again!!");
        }
        //password pattern check
        if (!RegexPattern.passwordPattern(user.getPassword())) {
            throw new PasswordInvalid(user.getPassword() + " try again!!");
        }

        // find by email user
        User first = userRepo.findByGmail(user.getEmail()).orElseThrow(() -> new UserNotFound(user.getEmail() + " user po etaoi email not found!"));

        boolean matches = passwordEncoder.matches(user.getPassword(),first.getPassword());

        //check password and login password
        if (!matches) {
             throw new UserNotFound("User password дал келбеди!");
        }
            return first;
    }

    //register
    @Override
    public SimpleRequest save(RegisterRequest newUser) {

        //check email pattern
        if (!RegexPattern.emailPattern(newUser.getEmail())) {
            return SimpleRequest.builder()
                    .message("email invalid try again!!")
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        //check password pattern
        if (!RegexPattern.passwordPattern(newUser.getPassword())) {
            return SimpleRequest.builder()
                    .message("password invalid try again!!")
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        //check email don't already in use
        User user = userRepo.findByGmail(newUser.getEmail()).orElse(null);
        if (user != null) {
            return SimpleRequest.builder()
                    .message("email already in use try again!!")
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        User user1 = new User();
        user1.setUserName(newUser.getUsername());
        String encode = passwordEncoder.encode(newUser.getPassword());
        user1.setPassword(encode);
        user1.setEmail(newUser.getEmail());
        user1.setRoleUser(newUser.getRole());
        //save user
        userRepo.save(user1);
        return SimpleRequest.builder()
                .message("success")
                .status(HttpStatus.OK)
                .build();
    }


    //get by email
    @Override
    public Optional<User> findByEmail(String username) {
        return Optional.ofNullable(userRepo.findByGmail(username).orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found")));
    }

    @Override
    public boolean deleteByIdUser(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFound("User " + id + " not found"));
        if (user != null) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @PostConstruct
    public void init() {
        String email = "admin@admin.com";
        User byGmail = userRepo.findByGmail(email).orElse(null);
        User user = new User();
        if (byGmail == null) {
            user.setRoleUser(RoleUser.ADMIN);
            user.setUserName("admin");
            user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("Admin123!"));
            user.setEmail(email);
            userRepo.save(user);
        }

    }
}
