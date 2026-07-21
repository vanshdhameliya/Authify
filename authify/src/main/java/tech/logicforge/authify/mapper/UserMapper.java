package tech.logicforge.authify.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.logicforge.authify.dto.ProfileRequestDTO;
import tech.logicforge.authify.dto.ProfileResponseDTO;
import tech.logicforge.authify.entity.UserEntity;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", expression = "java(UUID.randomUUID().toString())")
    @Mapping(target = "isAccountVerified", constant = "false")
    @Mapping(target = "verifyOtp", ignore = true)
    @Mapping(target = "verifyOtpExpireAt", expression = "java(0L)")
    @Mapping(target = "resetOtp", ignore = true)
    @Mapping(target = "resetOtpExpireAt", expression = "java(0L)")
    UserEntity toEntity(ProfileRequestDTO requestDTO);

    ProfileResponseDTO toResponse(UserEntity userEntity);

}