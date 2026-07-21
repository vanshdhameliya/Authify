package tech.logicforge.authify.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileRequestDTO {

    @NotBlank(message = "name should not be empty")
    private String name;

    @Email(message = "Enter valid email")
    @NotNull(message = "email should not be null")
    private String email;

    @Size(min = 5,message = "minimum 5 char need in password")
    private String password;
}
