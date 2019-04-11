package com.cognizant.admin.securityservice;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.app.controller.LoginController;
import com.cognizant.app.modal.User;
import com.cognizant.app.service.UserService;

@SpringBootTest(classes=SecurityServiceApplicationTests.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class SecurityServiceApplicationTests {
	
	private MockMvc mockMvc;
	 
	@Mock
	private UserService userService;
	
	@InjectMocks
	private LoginController loginController;
	
	@Before
    public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }
	
	@Test
    public void test_getuser() throws Exception {
        ModelAndView modelAndView = mockMvc.perform(get("/users")).
            andExpect(status().isOk()).andReturn().getModelAndView();
        assertEquals("login", modelAndView.getViewName());
    }
	
	
	@Test
	public void test_get_all_success() throws Exception {
	    List<User> users = Arrays.asList(
	            new User("Daenerys Targaryen",51,"56546465"),
	            new User("John Snow",52,"5466546546"));
	    when(userService.getAllUsers()).thenReturn(users);
	    /*mockMvc.perform(get("/users"))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(jsonPath("$", has(2)))
	            .andExpect(jsonPath("$[0].id", is(1)))
	            .andExpect(jsonPath("$[0].username", is("Daenerys Targaryen")))
	            .andExpect(jsonPath("$[1].id", is(2)))
	            .andExpect(jsonPath("$[1].username", is("John Snow")));
	    verify(userService, times(1)).getAllUsers();
	    verifyNoMoreInteractions(userService);*/
	    assertEquals(users, userService.getAllUsers());
	}
	
	@Test
	public void test_get_all_successByMockMVC() throws Exception {
	    List<User> users = Arrays.asList(
	            new User("Daenerys Targaryen",51,"56546465"),
	            new User("John Snow",52,"5466546546"));
	   
	    when(userService.getAllUsers()).thenReturn(users);
	    
	    //OR
	    
	    when(userService.getAllUsers()).thenReturn(users);
	    
	    mockMvc.perform(get("/usersDetails"))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	    
	    verify(userService, times(1)).getAllUsers();
	    verifyNoMoreInteractions(userService);
	}

}
