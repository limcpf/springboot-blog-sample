package kr.limc.limcblog.Controller.Comm.Dto;

import javax.validation.constraints.NotEmpty;

import kr.limc.limcblog.Entity.Comm.Enum.UserUpdateMethod;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserUpdateDto{
    @NotEmpty(message = "{member_update_id_not_empty}")
    private String id;
    @NotEmpty(message = "{member_update_method_not_empty}")
    private String payload;
    @NotEmpty(message = "{member_update_payload_not_empty}")
    private UserUpdateMethod method;
}
