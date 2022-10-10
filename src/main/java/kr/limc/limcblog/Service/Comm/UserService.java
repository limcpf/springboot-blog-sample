package kr.limc.limcblog.Service.Comm;

import org.springframework.stereotype.Service;

import kr.limc.limcblog.Controller.Comm.Dto.UserChgRoleDto;
import kr.limc.limcblog.Controller.Comm.Dto.UserDto;
import kr.limc.limcblog.Entity.Comm.User;
import kr.limc.limcblog.Entity.Comm.Enum.UserRole;
import kr.limc.limcblog.Repository.Comm.UserRepository;
import kr.limc.limcblog.Util.Exceptions.BlogException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(UserDto userDto) {
        User user = new User();

        try {
            user = userRepository.save(new User(userDto));
        } catch(Exception e) {
            throw new BlogException("오류가 발생했습니다.");
        }
        
        return user;
    }

    public String changeRole(UserChgRoleDto uDto) {
        User user = userRepository.findById(uDto.getId()).get();

        try {
            user.setRole(UserRole.valueOf(uDto.getRole()));
        } catch(IllegalArgumentException e) {
            throw new BlogException("변경 권한이 올바르지 않습니다.");
        }

        User result = userRepository.save(user);
        System.out.println(result.getModifiedDate() + " : " + result.getRole());
        return result.getRole();
    }
}
