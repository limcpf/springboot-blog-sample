package kr.limc.limcblog.Controller.Comm.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private String userId;
    private String accessToken;
    private String refreshToken;
    private String memberRole;
}