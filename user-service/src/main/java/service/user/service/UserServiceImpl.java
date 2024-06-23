package service.user.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import service.user.dto.OrderDTO;
import service.user.dto.UserDTO;
import service.user.entity.Users;
import service.user.mapper.UserMapper;
import service.user.repositry.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        log.info("Saving user: {}", userDTO);
        Users user = userMapper.userDTOToUser(userDTO);
        return userMapper.userToUserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO getUserById(String userId) {
        return userMapper
                .userToUserDTO(userRepository.findById(UUID.fromString(userId))
                        .orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        log.info("Updating user: {}", userDTO);
        Users user = userMapper.userDTOToUser(userDTO);
        if(user != null){
            return userMapper.userToUserDTO(userRepository.save(user));
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(UUID.fromString(userId));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        log.info("Getting all users");
        return userMapper.usersToUserDTOs(userRepository.findAll());
    }

    @Override
    @CircuitBreaker(name = "orderService", fallbackMethod = "getCachedOrdersByUserId")
    public List<OrderDTO> getAllOrdersByUserId(String userId) {
        try {
            List<OrderDTO> orderDTOS = restTemplate.getForObject("http://ORDER-SERVICE/order/getOrdersByCustomerId/" + userId, List.class);
            return orderDTOS;
        } catch (Exception e){
            log.error("Error while getting orders for user: {}", ExceptionUtils.getStackTrace(e));
            throw new RuntimeException("Error while getting orders for user: " + userId);
        }
    }

    public List<OrderDTO> getCachedOrdersByUserId(String userId, Throwable throwable) {
        log.error("Error while getting orders for user: {}", userId);
        return List.of(OrderDTO.builder().build());
    }
}
