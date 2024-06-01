package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.mappers.CommentMapper;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    CommentRepository commentRepository;
    UserServiceImpl userService;
    AdServiceImpl adService;
    public Optional<Comments> getAllByAd(Long id){
        List<Comment> comments = commentRepository.findAll().stream()
                .filter(commentEntity -> commentEntity.getAds().getId()==id)
                .map(commentEntity -> CommentMapper.INSTANCE.commentEntityToComment(commentEntity))
                .collect(Collectors.toList());

        return Optional.of(new Comments(comments.size(), comments));

    }
    public Comment createOrUpdate(Integer id, CreateOrUpdateComment updateComment,
                                  Authentication authentication){
        CommentEntity commentEntity = CommentMapper.INSTANCE
                .createOrUpdateCommentToCommentEntity(updateComment);
        commentEntity.setCreatedAt(new Date());
        commentEntity.setUserEntity(userService.findByLogin(authentication.getName()));
        commentEntity.setAds(adService.findById(id.longValue()));
        Comment comment = CommentMapper.INSTANCE
                .commentEntityToComment(commentRepository.save(commentEntity));
        return comment;
    }
    public void deleteComment(Integer idAd,Integer idComment){
        commentRepository.deleteById(idComment);
    }
}
