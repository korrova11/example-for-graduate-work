package ru.skypro.homework.mappers;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CommentMapperTest {
    @Test
    public void shouldCommentEntityToComment() {

        CommentEntity commentEntity = CommentEntity.builder()
                .id(1)
                .text("Message")
                .userEntity( UserEntity.builder()
                        .id(3L).build())
                .build();
        Comment comment = CommentMapper.INSTANCE.commentEntityToComment(commentEntity);
        assertThat(comment).isNotNull();
        assertThat(comment.getPk()).isEqualTo(1);
        assertThat(comment.getText()).isEqualTo("Message");
        assertThat(comment.getAuthor()).isEqualTo(3);
    }
    @Test
    public void shouldCommentToCommentEntity() {

        Comment comment = Comment.builder()
                .pk(2)
                .text("Message test")
                .build();
        CommentEntity commentEntity = CommentMapper.INSTANCE.commentToCommentEntity(comment);
        assertThat(commentEntity).isNotNull();
        assertThat(commentEntity.getId()).isEqualTo(2);
        assertThat(commentEntity.getText()).isEqualTo("Message test");
    }

    @Test
    public void shouldCommentEntityToCreateOrUpdateComment() {

        CommentEntity commentEntity = CommentEntity.builder()
                .text("Message")
                .build();
        CreateOrUpdateComment createOrUpdateComment =
                CommentMapper.INSTANCE.commentEntityToCreateOrUpdateComment(commentEntity);
        assertThat(createOrUpdateComment).isNotNull();
        assertThat(createOrUpdateComment.getText()).isEqualTo("Message");
    }

    @Test
    public void shouldCreateOrUpdateCommentCommentEntity() {

        CreateOrUpdateComment createOrUpdateComment = CreateOrUpdateComment.builder()
                .text("Message")
                .build();
        CommentEntity commentEntity =
                CommentMapper.INSTANCE.createOrUpdateCommentToCommentEntity(createOrUpdateComment);
        assertThat(commentEntity).isNotNull();
        assertThat(commentEntity.getText()).isEqualTo("Message");
    }

}
