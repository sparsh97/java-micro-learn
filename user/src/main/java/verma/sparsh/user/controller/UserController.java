package verma.sparsh.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import verma.sparsh.user.dto.UserDto;
import verma.sparsh.user.entity.Users;
import verma.sparsh.user.service.UserService;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Users> createUser(@RequestBody UserDto user) {
        Users users = userService.createUser(user);
        return ResponseEntity.ok(users);
    }

    @PostMapping(params = "/update/amount")
    public ResponseEntity<Users> updateUserAmount(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateAccountStatus(userDto.getId(), userDto.getAmount()));
    }
}
