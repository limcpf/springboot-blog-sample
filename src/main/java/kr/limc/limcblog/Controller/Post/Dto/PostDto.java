package kr.limc.limcblog.Controller.Post.Dto;


import kr.limc.limcblog.Controller.Comm.Dto.BaseTimeDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto extends BaseTimeDto{
    private Long id;
    private String title;
    private String contents;
    private int show = 0; // 0 : hide Post, 1 : show Post

    @Builder
    public PostDto(Long id, String title, String contents, int show, String createdDate, String modifiedDate) {
        super(createdDate, modifiedDate);
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.show = show;
    }
}
