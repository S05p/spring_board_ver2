package com.seopseop.board.service.post;

import com.seopseop.board.DTO.post.PostSaveDTO;
import com.seopseop.board.DTO.post.PostUpdateDTO;
import com.seopseop.board.entity.member.Member;
import com.seopseop.board.entity.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface PostService {

    /**
     * 게시글 저장
     * @Param PostSaveDTO 게시글 저장을 위한 데이터 전송 객체
     * @Param username 작성사 이름
     * @return 지정된 게시글의 ID
     */
    Long savePost(PostSaveDTO postSaveDTO, String username);

    /**
     * 게시글 업데이트
     * @Param PostSaveDTO 게시글 업데이트를 위한 데이터 전송 객체
     */
    Long updatePost(PostUpdateDTO postUpdateDTO);

    /**
     * 게시글 찾기
     * @Param post_id 게시글을 찾기 위한 id
     * @return 입력한 post_id의 post
     */
    Post findById(Long post_id);

    Long deletePost(Long post_id, Member writer);

    Page<Post> findAllActivePostByMember(String username, Pageable pageable);

//    Page<Post> findByKeyword(String keyword, Pageable pageable);
}
