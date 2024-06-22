package service.user.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import service.user.dto.UserDTO;
import service.user.entity.Users;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Users userDTOToUser(UserDTO userDTO);

    @InheritInverseConfiguration
    UserDTO userToUserDTO(Users users);

    List<UserDTO> usersToUserDTOs(List<Users> users);
    List<Users> userDTOsToUsers(List<UserDTO> userDTOs);
}