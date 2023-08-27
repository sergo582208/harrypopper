package ru.hogwarts.school1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school1.model.Avatar;

import java.util.List;
import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional <Avatar> findByStudent_id(Long studentId);

    List<Avatar> findAll();
}
