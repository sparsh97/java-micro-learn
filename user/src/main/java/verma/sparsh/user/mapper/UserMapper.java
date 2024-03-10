package verma.sparsh.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import verma.sparsh.user.dto.UserDto;
import verma.sparsh.user.entity.Users;

@Mapper(componentModel = "spring")
public interface UserMapper {

    Users userDtoToUsers(UserDto userDto);
    UserDto usersToUserDto(Users users);

}
