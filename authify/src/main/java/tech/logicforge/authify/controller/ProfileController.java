package tech.logicforge.authify.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.logicforge.authify.dto.ProfileRequestDTO;
import tech.logicforge.authify.dto.ProfileResponseDTO;
import tech.logicforge.authify.service.ProfileService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/register")
    public ResponseEntity<ProfileResponseDTO> register(
            @Valid @RequestBody ProfileRequestDTO profileRequestDTO) {
        ProfileResponseDTO responseDTO = profileService.createProfile(profileRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
