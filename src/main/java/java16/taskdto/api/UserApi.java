package java16.taskdto.api;

import jakarta.annotation.security.PermitAll;
import java16.taskdto.entityes.User;
import java16.taskdto.request.LoginDto;
import java16.taskdto.request.RegisterRequest;
import java16.taskdto.request.SimpleRequest;
import java16.taskdto.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserApi {
    private final UserService userService;


    // auth method


    //this is login
    @GetMapping("/login")
    public ResponseEntity<?>  login(@RequestBody LoginDto user) {
        return userService.login(user);
    }

    //this is register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest newUser) {
        return userService.save(newUser);
    }

    //delete by id user
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleteByIdUser/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        boolean res = userService.deleteByIdUser(id);
        if (res) {
            return ResponseEntity.ok("Deleted product with id " + id);
        }
        return ResponseEntity.badRequest().build();
    }

}
