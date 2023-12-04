package com.hexaware.airticketbooking.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hexaware.airticketbooking.dto.AdminDTO;
import com.hexaware.airticketbooking.dto.UpdateAdminDTO;
import com.hexaware.airticketbooking.entities.Admin;
import com.hexaware.airticketbooking.repository.IAdminRepository;
@SpringBootTest
class AdminServiceImpTest {

	 @Mock
	 private PasswordEncoder passwordEncoder;

	 @Mock
	 private IAdminRepository adminRepository;

	 @InjectMocks
	 private AdminServiceImp adminService;

	 @BeforeEach
	 void setUp() {
	      MockitoAnnotations.openMocks(this);
	 }

	@Test
	void testAddAdmin() {
		AdminDTO adminDto = new AdminDTO(0,"admin1", "password");
        Admin admin = new Admin();
        admin.setAdminName(adminDto.getAdminName());
        admin.setAdminPassword("encodedPassword");
        admin.setAdminId(1);
        when(passwordEncoder.encode(adminDto.getAdminPassword())).thenReturn("encodedPassword");
        when(adminRepository.save(any())).thenReturn(admin);

        AdminDTO result = adminService.addAdmin(adminDto);

        assertNotNull(result);
        assertEquals(admin.getAdminName(), result.getAdminName());
        assertEquals(admin.getAdminPassword(), result.getAdminPassword());
	}

	@Test
	void testEditAdminProfile() {
		 UpdateAdminDTO adminDto = new UpdateAdminDTO(1, "admin1");
	        Admin admin = new Admin();
	        admin.setAdminId(adminDto.getAdminId());
	        admin.setAdminName(adminDto.getAdminName());

	        when(adminRepository.save(any())).thenReturn(admin);

	        UpdateAdminDTO result = adminService.editAdminProfile(adminDto,1);

	        assertNotNull(result);
	        assertEquals(admin.getAdminId(), result.getAdminId());
	        assertEquals(admin.getAdminName(), result.getAdminName());
	    }

	@Test
	void testDeleteAdminAccount() {
		int adminId = 1;

        adminService.deleteAdminAccount(adminId);
        verify(adminRepository, times(1)).deleteById(adminId);
	}

	@Test
	void testGetAdminProfileById() {
		int adminId = 1;
        Admin admin = new Admin();
        admin.setAdminId(adminId);
        admin.setAdminName("admin1");

        when(adminRepository.findById(adminId)).thenReturn(Optional.of(admin));

       UpdateAdminDTO result = adminService.getAdminProfileById(adminId);

        assertNotNull(result);
        assertEquals(admin.getAdminId(), result.getAdminId());
        assertEquals(admin.getAdminName(), result.getAdminName());
       
	}

	@Test
	void testViewAllAdmin() {
		List<Admin> admins = Arrays.asList(new Admin(), new Admin());

        when(adminRepository.findAll()).thenReturn(admins);

        List<Admin> result = adminService.viewAllAdmin();

        assertNotNull(result);
        assertEquals(admins.size(), result.size());
	}

}
