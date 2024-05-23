package ru.skypro.homework.mappers;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CommentMapperTest {
    @Test
    public void shouldCommentEntityToComment() {

        CommentEntity commentEntity = CommentEntity.builder()
                .id(1)
                .text("Message")
                .build();
        Comment comment = CommentMapper.INSTANCE.commentEntityToComment(commentEntity);
        assertThat(comment).isNotNull();
        assertThat(comment.getPk()).isEqualTo(1);
        assertThat(comment.getText()).isEqualTo("Message");
    }

}
