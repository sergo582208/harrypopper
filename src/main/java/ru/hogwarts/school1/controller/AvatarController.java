package ru.hogwarts.school1.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school1.dto.AvatarDTO;
import ru.hogwarts.school1.service.AvatarService;

import java.util.List;

@RestController
@RequestMapping("/avatars")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @GetMapping
    public List<AvatarDTO> getPaginatedAvatars
            (@RequestParam int pageNumber,
             @RequestParam int pageSize) {

        return avatarService.getPaginatedAvatars(pageNumber, pageSize);

    }
}
