package com.spring.springshoppingcart.service.user;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springshoppingcart.dto.UserDto;
import com.spring.springshoppingcart.exception.ResourceException;
import com.spring.springshoppingcart.model.User;
import com.spring.springshoppingcart.repository.UserRepository;
import com.spring.springshoppingcart.request.CreateUserRequest;
import com.spring.springshoppingcart.request.UpdateUserRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new ResourceException("User not found!"));
    }

    @Override
    @Transactional
    public User createUser(CreateUserRequest request) {
        return Optional.of(request)
            .filter(user -> !userRepository.existsByEmail(request.getEmail()))
            .map(req -> {
                User user = new User();
                user.setName(request.getName());
                user.setEmail(request.getEmail());
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                return userRepository.save(user);
            })
            .orElseThrow(() -> new ResourceException(request.getEmail() + " already exists!"));
    }

    @Override
    @Transactional
    public User updateUser(UpdateUserRequest request, Long userId) {
        return userRepository.findById(userId)
            .map(user -> {
                user.setName(request.getName());
                return userRepository.save(user);
            })
            .orElseThrow(() -> new ResourceException("User not found!"));
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
            .ifPresentOrElse(
                userRepository::delete,
                () -> {throw new ResourceException("User not found!");}
            );
    }

    @Override
    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return userRepository.findByEmail(email);
    }
}
