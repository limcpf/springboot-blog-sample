package kr.limc.limcblog.Service.Post;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kr.limc.limcblog.Controller.Post.Dto.PostDto;
import kr.limc.limcblog.Entity.Post.Post;
import kr.limc.limcblog.Repository.Post.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<PostDto> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable).map(Post::toDto).toList();
    }

    public PostDto getPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 id 입니다.")).toDto();
    }

    public Long createPost(PostDto postDto) {
        Post post = new Post(postDto.getTitle(), postDto.getContents());
        return postRepository.save(post).getId();
    }

    public Long updatePost(PostDto postDto) {
        return postRepository.save(new Post(postDto)).getId();
    }

    public int toggleShow(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 id 입니다."));
        post.toggleShow();
        return postRepository.save(post).getShow();
    }

    public boolean deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 id 입니다."));
        post.canDeletePost();
        try {
            postRepository.deleteById(post.getId());
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
