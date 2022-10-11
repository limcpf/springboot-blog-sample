package kr.limc.limcblog.Controller.Comm.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserChgRoleDto{
    // TODO : 이거 없애고 patch 하나로 만들자
    private String id;
    private String role;
}
