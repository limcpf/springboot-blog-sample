package kr.limc.limcblog.Controller.Comm.Dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto{
    private String id;
    private String userId;
    private String password;
    private String role;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public UserDto(String id, String userId, String password, String role, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.role = role;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
