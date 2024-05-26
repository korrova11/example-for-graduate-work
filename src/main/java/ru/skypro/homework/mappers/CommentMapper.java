package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;

@Mapper
public interface  CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    @Mappings({
            @Mapping(source = "id", target = "pk"),
            @Mapping(source = "commentEntity.userEntity.id", target = "author"),
            @Mapping(source = "commentEntity.userEntity.firstName", target = "authorFirstName")


    })

    Comment commentEntityToComment(CommentEntity commentEntity);

    @Mapping(source = "pk", target = "id")
    CommentEntity commentToCommentEntity(Comment comment);

    CreateOrUpdateComment commentEntityToCreateOrUpdateComment(CommentEntity commentEntity);

    CommentEntity createOrUpdateCommentToCommentEntity(CreateOrUpdateComment createOrUpdateComment);
}
