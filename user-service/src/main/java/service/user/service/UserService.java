package service.user.service;

import org.springframework.stereotype.Service;
import service.user.dto.OrderDTO;
import service.user.dto.UserDTO;

import java.util.List;

@Service
public interface UserService {

    UserDTO saveUser(UserDTO userDTO);
    UserDTO getUserById(String userId);
    UserDTO updateUser(UserDTO userDTO);
    void deleteUser(String userId);
    List<UserDTO> getAllUsers();
    List<OrderDTO> getAllOrdersByUserId(String userId);
}
