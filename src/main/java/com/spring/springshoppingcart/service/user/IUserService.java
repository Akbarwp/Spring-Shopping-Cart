package com.spring.springshoppingcart.service.user;

import com.spring.springshoppingcart.dto.UserDto;
import com.spring.springshoppingcart.model.User;
import com.spring.springshoppingcart.request.CreateUserRequest;
import com.spring.springshoppingcart.request.UpdateUserRequest;

public interface IUserService {
    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UpdateUserRequest request, Long userId);
    void deleteUser(Long userId);
    UserDto convertUserToDto(User user);
}
