package tech.logicforge.authify.service;

import tech.logicforge.authify.dto.ProfileRequestDTO;
import tech.logicforge.authify.dto.ProfileResponseDTO;

public interface ProfileService {

    ProfileResponseDTO createProfile(ProfileRequestDTO requestDTO);
}
