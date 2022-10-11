package kr.limc.limcblog.Entity.Post;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.util.StringUtils;

import kr.limc.limcblog.Controller.Post.Dto.PostDto;
import kr.limc.limcblog.Entity.Comm.BaseTimeEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity
public class Post extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @Column()
    private String title;

    @Column()
    private String contents;

    @Column()
    private int show;

    public Post(String title, String contents) {
        setTitle(title);
        setContents(contents);
    }

    public Post(PostDto postDto) {
        this.id = postDto.getId();
        setTitle(postDto.getTitle());
        setContents(postDto.getContents());
        setShow(postDto.getShow());
    }

    public void toggleShow() {
        this.show = (this.show == 0 ? 1 : 0);
    }

    public boolean canDeletePost() {
        if(this.show != 0) throw new IllegalArgumentException("게시중인 게시글은 삭제할 수 없습니다.");
        return true;
    }

    private void setTitle(String title) {
        if(!StringUtils.hasLength(title)) throw new IllegalArgumentException("제목은 공백일 수 없습니다.");
        else if (title.length() > 255) throw new IllegalArgumentException("제목은 255자 이상일 수 없습니다.");
        this.title = title;
    }

    private void setContents(String contents) {
        if(!StringUtils.hasLength(contents)) throw new IllegalArgumentException("내용은 공백일 수 없습니다.");
        this.contents = contents;
    }

    private void setShow(int show) {
        if(show != 0 && show != 1)  throw new IllegalArgumentException("공개 여부엔 0 또는 1이 들어가야 합니다.");
        this.show = show;
    }

    public PostDto toDto() {
        return PostDto
                .builder()
                .id(id)
                .title(title)
                .contents(contents)
                .show(show)
                .createdDate(super.getCreatedDate())
                .modifiedDate(super.getModifiedDate())
                .build();
    }
}
