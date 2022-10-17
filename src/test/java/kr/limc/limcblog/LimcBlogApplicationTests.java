package kr.limc.limcblog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import kr.limc.limcblog.Controller.Comm.UserController;
import kr.limc.limcblog.Controller.Comm.Dto.LoginDto;
import kr.limc.limcblog.Controller.Comm.Dto.LoginResponse;
import kr.limc.limcblog.Controller.Comm.Dto.UserDto;
import kr.limc.limcblog.Controller.Comm.Dto.UserUpdateDto;
import kr.limc.limcblog.Controller.Post.PostController;
import kr.limc.limcblog.Entity.Comm.Enum.UserUpdateMethod;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(Lifecycle.PER_CLASS)
class LimcBlogApplicationTests {

	@Autowired
	private UserController userController;

	@Autowired
	private PostController postController;

	private static UserDto u = new UserDto();

	@DisplayName("controller load")
	@Test
	void contextLoads() {
		assert userController != null;
		assert postController != null;
	}

	@DisplayName("post test")
	@Test
	void user_1_createUser() {
		UserDto userDto = new UserDto();
		userDto.setUserId("user");
		userDto.setPassword("password");
		UserDto user = userController.createUser(userDto);
		u = user;

		assertEquals("user", user.getUserId());
	}

	@DisplayName("get user")
	@Test
	void user_2_getUser() {
		UserDto user = userController.getUser(u.getId());
		assertEquals(user.getUserId(), u.getUserId());
	}

	@DisplayName("login")
	@Test
	void user_3_login() {
		LoginDto loginDto = new LoginDto();
		loginDto.setUserId("user");
		loginDto.setPassword("password");

		ResponseEntity<LoginResponse> lEntity = userController.login(loginDto);
		if(lEntity != null) {
			LoginResponse loginResponse = lEntity.getBody();
			if(loginResponse != null) {
				assertEquals(loginResponse.getUserId(), u.getUserId());
				assertNotNull(loginResponse.getAccessToken());
			} else {
				fail();
			}
		} else {
			fail();
		}
	}

	@DisplayName("update Role")
	@Test
	void user_4_updateUserRole() {
		UserUpdateDto updateDto = getUpdateDto(UserUpdateMethod.ROLE, "ADMIN");

		UserDto user = userController.updateUser(updateDto);
		assertEquals(user.getRole(), "ADMIN");
	}

	@DisplayName("update Password")
	@Test
	void user_5_updateUserPassword() {
		UserUpdateDto updateDto = getUpdateDto(UserUpdateMethod.PASSWORD, "password2");

		UserDto userDto = userController.updateUser(updateDto);
		assertNotEquals(u.getPassword(), userDto.getPassword());

		u = userDto;
	}


	private UserUpdateDto getUpdateDto(UserUpdateMethod method, String payload) {
		UserUpdateDto updateDto = new UserUpdateDto();
		updateDto.setId(u.getId());
		updateDto.setMethod(method);
		updateDto.setPayload(payload);
		return updateDto;
	}
}
