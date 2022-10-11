package kr.limc.limcblog.Entity.Comm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

import kr.limc.limcblog.Controller.Comm.Dto.UserDto;
import kr.limc.limcblog.Entity.Comm.Enum.UserRole;
import kr.limc.limcblog.Util.Exceptions.BlogException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity{
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Column() // 유니크 걸어
    private String userId;

    @Column()
    private String password;

    @Column()
    private String role = UserRole.USER.toString();

    public User(String userId, String password) {
        setUserId(userId);
        setPassword(password);
    }

    public User(UserDto userDto) {
        this(userDto.getUserId(), userDto.getPassword());
    }

    public void setRole(UserRole role) {
        if(this.role.equals(role.toString())) {
            throw new BlogException("같은 권한으로는 권한 변경할 수 없습니다.");
        } else {
            this.role = role.getRole();
        }
    }

    private void setUserId(String userId) {
        if(!StringUtils.hasLength(userId)) throw new IllegalArgumentException("Id는 공백일 수 없습니다.");
        else if(userId.length() > 25) throw new IllegalArgumentException("Id는 25자 이하여야 합니다.");
        this.userId = userId;
    }

    private void setPassword(String password) {
        if(!StringUtils.hasLength(password)) throw new IllegalArgumentException("Password는 공백일 수 없습니다.");
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public UserDto toDto() {
        return UserDto
                .builder()
                .id(id)
                .userId(userId)
                .password(password)
                .role(role)
                .createdDate(super.getCreatedDate())
                .modifiedDate(super.getModifiedDate())
                .build();
    }



}
