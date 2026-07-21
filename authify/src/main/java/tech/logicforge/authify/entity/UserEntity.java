package tech.logicforge.authify.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true)
        private String userId;

        private String name;

        @Column(unique = true)
        private String email;
        private String password;
        private String verifyOtp;
        private Boolean isAccountVerified;
        private Long verifyOtpExpireAt;
        private String resetOtp;
        private Long resetOtpExpireAt;

        @CreationTimestamp
        @Column(updatable = false)
        private Timestamp createdAt;

        @UpdateTimestamp
        private Timestamp updatedAt;

    }

