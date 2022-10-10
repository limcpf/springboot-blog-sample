package kr.limc.limcblog.Controller.Comm.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserChgRoleDto{
    private String id;
    private String role;
}
