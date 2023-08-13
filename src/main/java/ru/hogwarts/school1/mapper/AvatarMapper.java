package ru.hogwarts.school1.mapper;

import org.springframework.stereotype.Component;
import ru.hogwarts.school1.dto.AvatarDTO;
import ru.hogwarts.school1.model.Avatar;

@Component
public class AvatarMapper {
    public AvatarDTO mapToDTO(Avatar avatar){
        return new AvatarDTO(avatar.getId(),
                avatar.getFilePath(),
                avatar.getFileSize(),
                avatar.getMediaType(),
                avatar.getStudent().getId());
    }

}
