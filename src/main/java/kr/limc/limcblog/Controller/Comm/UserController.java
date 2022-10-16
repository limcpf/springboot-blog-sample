package kr.limc.limcblog.Controller.Comm;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.limc.limcblog.Config.Security.JwtTokenService;
import kr.limc.limcblog.Controller.Comm.Dto.LoginDto;
import kr.limc.limcblog.Controller.Comm.Dto.LoginResponse;
import kr.limc.limcblog.Controller.Comm.Dto.UserDto;
import kr.limc.limcblog.Controller.Comm.Dto.UserUpdateDto;
import kr.limc.limcblog.Service.Comm.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenService jwtTokenService;

    @PostMapping(path = "/public/user")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PostMapping(path = "/public/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto) {
        final LoginResponse loginResponse = jwtTokenService.getLoginResponse(loginDto);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping(path = "/private/user")
    public UserDto getUser(@PathVariable String id) {
        return userService.getUserById(id).toDto();
    }

    @PatchMapping(path = "/private/user")
    public UserDto updateUser(@RequestBody UserUpdateDto uDto) {
        return userService.updateUser(uDto);
    }
}
