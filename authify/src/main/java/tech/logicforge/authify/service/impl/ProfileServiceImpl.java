package tech.logicforge.authify.service.impl;

//import org.modelmapper.ModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.logicforge.authify.dto.ProfileRequestDTO;
import tech.logicforge.authify.dto.ProfileResponseDTO;
import tech.logicforge.authify.entity.UserEntity;
import tech.logicforge.authify.exception.EmailAlreadyExistsException;
import tech.logicforge.authify.mapper.UserMapper;
import tech.logicforge.authify.repository.UserRepository;
import tech.logicforge.authify.service.ProfileService;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ProfileResponseDTO createProfile(ProfileRequestDTO requestDTO) {

        if(userRepository.existsByEmail(requestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email Already Exists");
        }
        UserEntity userEntity = userMapper.toEntity(requestDTO);
        UserEntity savedUser = userRepository.save(userEntity);
        return userMapper.toResponse(savedUser);
    }

//    private UserEntity convertToUserEntity(ProfileRequestDTO requestDTO) {
//        return UserEntity.builder()
//                .userId(UUID.randomUUID().toString())
//                .name(requestDTO.getName())
//                .email(requestDTO.getEmail())
//                .password(requestDTO.getPassword())
//                .isAccountVerified(false)
//                .verifyOtp(null)
//                .verifyOtpExpireAt(0L)
//                .resetOtp(null)
//                .resetOtpExpireAt(0L)
//                .build();
//    }

//    private ProfileResponseDTO convertToProfileResponseDTO(UserEntity userEntity) {
//        return ProfileResponseDTO.builder()
//                .userId(userEntity.getUserId())
//                .name(userEntity.getName())
//                .email(userEntity.getEmail())
//                .isAccountVerified(userEntity.getIsAccountVerified())
//                .build();
//    }
}