package com.solvd.course.lawoffice.service;

import com.solvd.course.lawoffice.domain.exception.ResourceDoesNotExistException;
import com.solvd.course.lawoffice.domain.user.Role;
import com.solvd.course.lawoffice.domain.user.User;
import com.solvd.course.lawoffice.domain.user.UserStatus;
import com.solvd.course.lawoffice.persistence.UserRepository;
import com.solvd.course.lawoffice.service.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void verifyUserIsFoundByLoginTest() {
        //given
        String login = "user1";

        User user = new User();
        user.setUserId(1L);
        user.setLogin(login);
        user.setPassword("1234");
        user.setRole(Role.ROLE_LAWYER);
        user.setStatus(UserStatus.ACTIVE);

        Mockito.when(userRepository.findByLogin(login)).thenReturn(Optional.of(user));

        //when
        UserDetails userDetails = userDetailsService.loadUserByUsername(login);

        //then
        assertNotNull(userDetails);
        assertEquals(user.getLogin(), userDetails.getUsername());
    }

    @Test
    void verifyUserIsNotFoundByLoginTest() {
        //given
        String login = "user1";
        ;

        Mockito.when(userRepository.findByLogin(login)).thenReturn(Optional.empty());

        //then
        assertThrows(ResourceDoesNotExistException.class, () -> {
            userDetailsService.loadUserByUsername(login);
        });
    }

}