package kr.limc.limcblog.Config.Security;

import static kr.limc.limcblog.Config.Security.SecurityConstants.JWT_ACCESS_TOKEN_VALIDITY;
import static kr.limc.limcblog.Config.Security.SecurityConstants.JWT_REFRESH_TOKEN_VALIDITY;

import java.util.Objects;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.limc.limcblog.Controller.Comm.Dto.LoginDto;
import kr.limc.limcblog.Controller.Comm.Dto.LoginResponse;
import kr.limc.limcblog.Controller.Comm.Dto.UserDto;
import kr.limc.limcblog.Entity.Comm.User;
import kr.limc.limcblog.Repository.Comm.UserRepository;
import kr.limc.limcblog.Util.Exceptions.BlogException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtTokenService {
    private final UserRepository userRepository;
    private final JwtTokenManager jwtTokenManager;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginResponse getLoginResponse(LoginDto loginDto) {
        final String loginId = loginDto.getUserId();

        User user = userRepository.findByUserId(loginId);

        if(Objects.isNull(user)) {
            throw new BlogException("올바른 아이디가 아닙니다.");
        } else if(!bCryptPasswordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new BlogException("올바른 비밀번호가 아닙니다.");
        }

        final UserDto userDto = user.toDto();

        final UsernamePasswordAuthenticationToken token 
            = new UsernamePasswordAuthenticationToken(loginDto.getUserId(), loginDto.getPassword());
    
        try {
            authenticationManager.authenticate(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        final String accessToken = jwtTokenManager.getToken(userDto, JWT_ACCESS_TOKEN_VALIDITY);
        final String refreshToken = jwtTokenManager.getToken(userDto, JWT_REFRESH_TOKEN_VALIDITY);
    
        return new LoginResponse(loginId, accessToken, refreshToken, user.getRole());
    }

    public LoginResponse logout(String loginId) {
        User user = userRepository.findByUserId(loginId);
        String userId = user.getUserId();
        return new LoginResponse(userId, "", "", "");
    }
}
