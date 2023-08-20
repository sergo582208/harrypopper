package ru.hogwarts.school1.service.impl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school1.dto.AvatarDTO;
import ru.hogwarts.school1.mapper.AvatarMapper;
import ru.hogwarts.school1.model.Avatar;
import ru.hogwarts.school1.model.Student;
import ru.hogwarts.school1.repository.AvatarRepository;
import ru.hogwarts.school1.repository.StudentRepository;
import ru.hogwarts.school1.service.AvatarService;

import java.awt.print.Pageable;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Nodes;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static java.util.stream.Nodes.collect;

@Service
public class AvatarServiceImpl implements AvatarService {
    private final StudentRepository studentRepository;

    private final AvatarRepository avatarRepository;

    private final AvatarMapper avatarMapper;

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    private final Logger logger = (Logger) LoggerFactory.getLogger(AvatarServiceImpl.class);
    public AvatarServiceImpl(StudentRepository studentRepository, AvatarRepository avatarRepository, AvatarMapper avatarMapper, String avatarsDir) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;

        this.avatarMapper = avatarMapper;
        this.avatarsDir = avatarsDir;
    }

    @Override
    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.info("Method uploadAvatar was invoked!");
        Student student = studentRepository.findById(studentId).orElseThrow();


        Path path = saveToDisk(student, avatarFile);
        saveToDb(student,avatarFile,path);


    }
    private void saveToDb(Student student, MultipartFile avatarFile, Path filePath) throws IOException {
        logger.info("Method saveToDb was invoked!");
        Avatar avatar = findAvatar(student.getId());
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);

    }
private Path saveToDisk(Student student, MultipartFile avatarFile) throws IOException {
    logger.info("Method saveToDisk was invoked!");

    Path filePath = Path.of(avatarsDir,
            student.getId() + "." + getExtensions(avatarFile.getOriginalFilename()));
    Files.createDirectories(filePath.getParent());
    Files.deleteIfExists(filePath);
    try (
            InputStream is = avatarFile.getInputStream();
            OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
            BufferedInputStream bis = new BufferedInputStream(is, 1024);
            BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
    ) {
        bis.transferTo(bos);
    }
    return filePath;
}

public Avatar findAvatar(Long studentId){
    logger.info("Method findAvatar was invoked!");
        return avatarRepository.findByStudent_id(studentId).orElse(new Avatar());
}

    @Override
    public List<AvatarDTO> getPaginatedAvatars(int pageNumber, int pageSize) {
        logger.info("Method getPaginatedAvatars was invoked!");
        Pageable pageable = (Pageable) PageRequest.of(pageNumber - 1, pageSize);
        return (List<AvatarDTO>) avatarRepository.findAll((org.springframework.data.domain.Pageable) pageable)
                .getContent()
                .stream()
                .map(avatarMapper::mapToDTO);
                .collect(Collectors.toList());
    }


    private String getExtensions(String fileName) {
        logger.info("Method getExtensions was invoked!");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    }

