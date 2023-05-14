package com.example.travelleronline.conrollers;

import com.example.travelleronline.model.entities.User;
import com.example.travelleronline.model.repositories.UserRepository;
import com.example.travelleronline.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    @Test
    public void  testGetUserById(){
        //note: Given: : Setting up the preconditions for the test
        //todo: User user = new User(/*...*/);
        //todo: when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        //note: When: Performing the action to be tested.
        //todo: User result = userService.getUserWithSubById(1L);
        //note: Then: Asserting that the expected outcome has been achieved.
        //todo: assertEquals(user, result);
        //todo: verify(userRepository, times(1)).findById(1L);
        //todo: <<<log4J logs>>>
    }
}
