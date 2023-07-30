package ru.hogwarts.school1.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school1.model.Avatar;

import java.io.IOException;

public interface AvatarService {
    void uploadAvatar(Long studentId, MultipartFile avatarFile ) throws IOException;
    Avatar findAvatar(Long studentId);
}
