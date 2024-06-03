package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.AdEntity;
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
        Optional<AdEntity> adEntity = adService.findById(id.longValue());
        if (adEntity.isEmpty()){ return null;}
        CommentEntity commentEntity = CommentMapper.INSTANCE
                .createOrUpdateCommentToCommentEntity(updateComment);
        commentEntity.setCreatedAt(new Date());
        commentEntity.setUserEntity(userService.findByLogin(authentication.getName()).get());
        commentEntity.setAds(adEntity.get());
        CommentEntity commentEntity1 = commentRepository.save(commentEntity);

        Comment comment = CommentMapper.INSTANCE
                .commentEntityToComment(commentEntity1);
        return comment;
    }
    public HttpStatus deleteComment(Integer idAd, Integer idComment, Authentication authentication){
         if(isMainOrAdmin(idComment,authentication)) return HttpStatus.FORBIDDEN;
         if(findById(idComment).isEmpty()) return HttpStatus.NOT_FOUND;
        commentRepository.deleteById(idComment);
        return HttpStatus.OK;
    }
    public Optional<CommentEntity> findById(Integer id){
        return commentRepository.findById(id);
    }
    public boolean isMainOrAdmin(Integer id, Authentication authentication) {
        boolean admin = (userService.findByLogin(authentication.getName()).get().getRole()) == Role.ADMIN;
        return (authentication.getName()).equals(findById(id).get().getUserEntity().getLogin()) || admin;
    }
    public Comment changeComment(Integer id,CreateOrUpdateComment text){
        Optional<CommentEntity> comment = findById(id);
        if (comment.isEmpty()) return null;
        comment.get().setText(text.getText());

        return CommentMapper.INSTANCE
                .commentEntityToComment(commentRepository.save(comment.get()));

    }

}
