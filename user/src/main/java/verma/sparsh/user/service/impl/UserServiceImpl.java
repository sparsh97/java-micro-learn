package verma.sparsh.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import verma.sparsh.user.dto.UserDto;
import verma.sparsh.user.entity.Users;
import verma.sparsh.user.mapper.UserMapper;
import verma.sparsh.user.repository.UserRepository;
import verma.sparsh.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private final UserMapper userMapper;



    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.userMapper = mapper;
    }

    @Override
    public Users findUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Users createUser(UserDto user) {
        return userRepository.save(userMapper.userDtoToUsers(user));
    }

    @Override
    public Users updateAccountStatus(Integer userId, Double userAmount) {
        Users userDetails = findUserById(userId);
        userDetails.setAmount(userDetails.getAmount() - userAmount);
        return userRepository.save(userDetails);
    }
}
