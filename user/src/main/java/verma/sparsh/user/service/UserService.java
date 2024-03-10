package verma.sparsh.user.service;

import verma.sparsh.user.dto.UserDto;
import verma.sparsh.user.entity.Users;

public interface UserService {

    public Users findUserById(Integer id);

    public Users createUser(UserDto user);
}
