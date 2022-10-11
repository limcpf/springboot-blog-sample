package kr.limc.limcblog.Controller.Post.Dto;


import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto{
    private Long id;
    private String title;
    private String contents;
    private int show = 0; // 0 : hide Post, 1 : show Post
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public PostDto(Long id, String title, String contents, int show, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.show = show;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
