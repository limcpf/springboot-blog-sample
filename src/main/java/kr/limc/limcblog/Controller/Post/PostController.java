package kr.limc.limcblog.Controller.Post;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.limc.limcblog.Controller.Post.Dto.PostDto;
import kr.limc.limcblog.Service.Post.PostService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*")
public class PostController {
    
    private final PostService postService;

    @GetMapping(path = "/public/post")
    public List<PostDto> getPosts(Pageable pageable) {
        return postService.getPosts(pageable);
    }
    @GetMapping(path = "/public/post/{id}")
    public PostDto getPost(@PathVariable Long id) {
        System.out.println(postService.getPost(id).getContents());
        return postService.getPost(id);
    }
    @PostMapping(path = "/private/post")
    public Long savePost(@RequestBody PostDto postDto) {
        return postService.createPost(postDto);
    }
    @PatchMapping(path = "/private/post")
    public Long updatePost(@RequestBody PostDto postDto) {
        return postService.updatePost(postDto);
    }
    @PatchMapping(path = "/private/post/{id}")
    public int toggleShow(@PathVariable Long id) {
        return postService.toggleShow(id);
    }
    @DeleteMapping(path = "/private/post/{id}")
    public boolean deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }
}
