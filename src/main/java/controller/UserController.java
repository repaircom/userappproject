package controller;

import model.User;
import service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint to create or update a user
    @PostMapping("/create")
    public ResponseEntity<?> createOrUpdateUser(@RequestParam String phoneNumber,
                                                @RequestParam(required = false) String name,
                                                @RequestParam(required = false) String location) {
        User user = userService.saveOrUpdateUser(phoneNumber, name, location);
        return ResponseEntity.ok(user);
    }

    // Endpoint to get user details by phone number
    @GetMapping("/{phoneNumber}")
    public ResponseEntity<?> getUserByPhoneNumber(@PathVariable String phoneNumber) {
        User user = userService.getUserByPhoneNumber(phoneNumber);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to get all users
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
