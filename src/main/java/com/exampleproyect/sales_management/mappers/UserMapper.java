package com.exampleproyect.sales_management.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.exampleproyect.sales_management.domain.models.entities.User;
import com.exampleproyect.sales_management.dto.UserDto;

public class UserMapper {

    public static UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setLastname(user.getLastname());
 
        return userDto;
    }

    public static List<UserDto> listToDto (List<User> users) {
        return users.stream()
        .map((user) -> UserMapper.toDto(user))
        .collect(Collectors.toList());
    }

}
