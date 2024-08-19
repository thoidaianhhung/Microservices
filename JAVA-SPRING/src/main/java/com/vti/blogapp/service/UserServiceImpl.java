package com.vti.blogapp.service;

import com.vti.blogapp.dto.UserDto;
import com.vti.blogapp.entity.User;
import com.vti.blogapp.form.UserCreateForm;
import com.vti.blogapp.mapper.UserMapper;
import com.vti.blogapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDto create(UserCreateForm form) {
        var user = UserMapper.map(form);
        var encodedPassword = passwordEncoder.encode(form.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(User.Role.EMPLOYEE);
        var savedUser = userRepository.save(user);
        return UserMapper.map(savedUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        var user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        var role = user.getRole().toString();
        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                AuthorityUtils.createAuthorityList(role)
        );
    }
}
