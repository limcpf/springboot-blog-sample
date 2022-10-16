package kr.limc.limcblog.Service.Comm;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import kr.limc.limcblog.Controller.Comm.Dto.UserDto;
import kr.limc.limcblog.Controller.Comm.Dto.UserUpdateDto;
import kr.limc.limcblog.Entity.Comm.User;
import kr.limc.limcblog.Entity.Comm.Enum.UserRole;
import kr.limc.limcblog.Entity.Comm.Enum.UserUpdateMethod;
import kr.limc.limcblog.Repository.Comm.UserRepository;
import kr.limc.limcblog.Util.Exceptions.BlogException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto createUser(UserDto userDto) {
        User user = new User();
        
        try {
            user = userRepository.save(new User(userDto));
        } catch(Exception e) {
            throw new BlogException("오류가 발생했습니다.");
        }
        
        return user.toDto();
    }

    public UserDto updateUser(UserUpdateDto userUpdateDto)  {
        UserUpdateMethod updateMethod = userUpdateDto.getMethod();
        String payload = userUpdateDto.getPayload();
        User user = getUserById(userUpdateDto.getId());

        try {
            if(UserUpdateMethod.ROLE.equals(updateMethod)) {
                user.setRole(UserRole.valueOf(payload));
            } else if(UserUpdateMethod.PASSWORD.equals(updateMethod)) {
                user.setPassword(payload);
            } else {
                throw new IllegalArgumentException("올바른 method가 아닙니다.");
            }

            user = userRepository.save(user);

        } catch(Exception e) {
            e.printStackTrace();
        }

        return user.toDto();
        
    }
    
    public User getUserById(String id) {
        if(!StringUtils.hasText(id)) new IllegalArgumentException("id값이 존재하지 않습니다.");
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않은 User 입니다."));
    }
}
