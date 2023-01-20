package com.solvd.course.lawoffice.service.impl;

import com.solvd.course.lawoffice.domain.exception.ResourceDoesNotExistException;
import com.solvd.course.lawoffice.domain.security.UserDetailsImpl;
import com.solvd.course.lawoffice.domain.user.User;
import com.solvd.course.lawoffice.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(login);
        if (user.isPresent()) return UserDetailsImpl.fromUserEntityToUserDetails(user.get());
        else throw new ResourceDoesNotExistException("User (login=" + login + ") does not exist");
    }

}
