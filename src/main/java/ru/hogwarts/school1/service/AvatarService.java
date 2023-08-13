package ru.hogwarts.school1.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school1.dto.AvatarDTO;
import ru.hogwarts.school1.model.Avatar;

import java.io.IOException;
import java.util.List;

public interface AvatarService {
    void uploadAvatar(Long studentId, MultipartFile avatarFile ) throws IOException;
    Avatar findAvatar(Long studentId);

    List<AvatarDTO> getPaginatedAvatars(int pageNumber, int pageSize);
}
