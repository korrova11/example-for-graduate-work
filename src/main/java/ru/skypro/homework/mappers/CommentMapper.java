package ru.skypro.homework.mappers;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;
@NoArgsConstructor
@Mapper
@Data
public abstract class  CommentMapper {
    static CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    @Mappings({
            @Mapping(source = "id", target = "pk"),
            @Mapping(source = "commentEntity.userEntity.id", target = "author"),
            @Mapping(source = "commentEntity.userEntity.firstName", target = "authorFirstName"),
            @Mapping(expression = "java(toMillisec(commentEntity))", target = "createdAt"),
            @Mapping(target = "authorImage", expression = "java(commentEntity.getUserEntity().getImageEntity()==null?\"\":\"/image/download/\"+commentEntity.getUserEntity().getImageEntity().getId())")
    })

    public abstract Comment commentEntityToComment(CommentEntity commentEntity);

    /*@Mapping(source = "pk", target = "id")
    public abstract CommentEntity commentToCommentEntity(Comment comment);

    public abstract CreateOrUpdateComment commentEntityToCreateOrUpdateComment(CommentEntity commentEntity);*/

    public abstract CommentEntity createOrUpdateCommentToCommentEntity(CreateOrUpdateComment createOrUpdateComment);
    public Long toMillisec(CommentEntity commentEntity){
        return commentEntity.getCreatedAt().getTime();
    }
}
