package kr.limc.limcblog.Controller.Comm.Dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseTimeDto {
    @Schema(example = "2022-08-08T11:29:55.678Z")
    private String createdDate;
    @Schema(example = "2022-08-08T11:29:55.678Z")
    private String modifiedDate;
}
