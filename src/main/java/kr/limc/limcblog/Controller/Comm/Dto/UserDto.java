package kr.limc.limcblog.Controller.Comm.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto extends BaseTimeDto{
    private String id;
    private String userId;
    private String password;
    private String role;

    @Builder
    public UserDto(String id, String userId, String password, String role, String createdDate, String modifiedDate) {
        super(createdDate, modifiedDate);
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.role = role;
    }
}
