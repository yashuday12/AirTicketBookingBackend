package com.hexaware.airticketbooking.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hexaware.airticketbooking.dto.UpdateUserDTO;
import com.hexaware.airticketbooking.dto.UserDTO;
import com.hexaware.airticketbooking.entities.User;
import com.hexaware.airticketbooking.exceptions.UserNotFoundException;
import com.hexaware.airticketbooking.repository.IUserRepository;
@SpringBootTest
class UserServiceImpTest {
	 @Mock
	    private PasswordEncoder passwordEncoder;

	    @Mock
	    private IUserRepository userRepository;

	    @InjectMocks
	    private UserServiceImp userService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void testRegisterUser() {
	   
	        UserDTO userDto = createUserDTO();
	        User user = createUser();
	        when(userRepository.save(any(User.class))).thenReturn(user);
	        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

	        
	        UserDTO result = userService.registerUser(userDto);

	      
	        assertNotNull(result);
	        assertEquals(user.getUserId(), result.getUserId());
	        assertEquals(user.getUserName(), result.getUserName());
	        assertEquals(user.getGender(), result.getGender());
	        assertEquals(user.getContactNumber(), result.getContactNumber());
	        assertEquals(user.getAddress(), result.getAddress());
	        assertEquals(user.getDateOfBirth(), result.getDateOfBirth());
	        assertEquals(user.getUserEmail(), result.getUserEmail());
	        assertEquals("encodedPassword", result.getPassword());
	        assertEquals(user.getWallet(), result.getWallet());
	    }

	    @Test
	    void testEditUserProfile() {
	        
	        UpdateUserDTO userDto = new UpdateUserDTO();
	        User user = createUser();
	        when(userRepository.save(any(User.class))).thenReturn(user);
	      
	        
	        UpdateUserDTO result = userService.editUserProfile(userDto,1);

	        assertNotNull(result);
	        assertEquals(user.getUserId(), result.getUserId());
	        assertEquals(user.getUserName(), result.getUserName());
	        assertEquals(user.getGender(), result.getGender());
	        assertEquals(user.getContactNumber(), result.getContactNumber());
	        assertEquals(user.getAddress(), result.getAddress());
	        assertEquals(user.getDateOfBirth(), result.getDateOfBirth());
	        assertEquals(user.getUserEmail(), result.getUserEmail());
	    }

	    @Test
	    void testDeleteUserProfile() {
	       
	        int userId = 1;

	        
	        userService.deleteUserProfile(userId);

	        
	        verify(userRepository, times(1)).deleteById(userId);
	    }

	    @Test
	    void testGetByUserId() throws UserNotFoundException {
	       
	        int userId = 1;
	        User user = createUser();
	        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

	        
	        UserDTO result = userService.getByUserId(userId);

	        
	        assertNotNull(result);
	        assertEquals(user.getUserId(), result.getUserId());
	        assertEquals(user.getUserName(), result.getUserName());
	        assertEquals(user.getGender(), result.getGender());
	        assertEquals(user.getContactNumber(), result.getContactNumber());
	        assertEquals(user.getAddress(), result.getAddress());
	        assertEquals(user.getDateOfBirth(), result.getDateOfBirth());
	        assertEquals(user.getUserEmail(), result.getUserEmail());
	        assertEquals(user.getPassword(), result.getPassword());
	        assertEquals(user.getWallet(), result.getWallet());
	    }

	    @Test
	    void testGetAllUsers() {
	        // Arrange
	        User user1 = createUser();
	        User user2 = createUser();
	        List<User> users = List.of(user1, user2);
	        when(userRepository.findAll()).thenReturn(users);

	        
	        List<UserDTO> result = userService.getAllUsers();

	        
	        assertNotNull(result);
	        assertEquals(2, result.size());
	    }

	    @Test
	    void testChangePassword() {
	        
	        int userId = 1;
	        String newPassword = "newPassword";
	        User user = createUser();
	        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
	        when(userRepository.save(any(User.class))).thenReturn(user);

	       
	        UserDTO result = userService.changePassword(userId, newPassword);

	       
	        assertNotNull(result);
	        assertEquals(user.getUserId(), result.getUserId());
	        assertEquals(user.getPassword(), result.getPassword());
	    }

	    @Test
	    void testRecharge() {
	        
	        int userId = 1;
	        long amount = 100;
	        User user = createUser();
	        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
	        when(userRepository.save(any(User.class))).thenReturn(user);

	        
	        UserDTO result = userService.rechargeWallet(userId, amount);

	        
	        assertEquals(user.getWallet() + amount, result);
	    }

	    private UserDTO createUserDTO() {
	        return new UserDTO(1, "John Doe", "Male", "1234567890", "Address", LocalDate.now(), "john@example.com", "password", 100);
	    }

	    private User createUser() {
	        User user = new User();
	        user.setUserId(1);
	        user.setUserName("John Doe");
	        user.setGender("Male");
	        user.setContactNumber("1234567890");
	        user.setAddress("Address");
	        user.setDateOfBirth(LocalDate.now());
	        user.setUserEmail("john@example.com");
	        user.setPassword("password");
	        user.setWallet(100);
	        return user;
	    }
}
