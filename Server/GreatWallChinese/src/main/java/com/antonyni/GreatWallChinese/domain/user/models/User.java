package com.antonyni.GreatWallChinese.domain.user.models;

import com.antonyni.GreatWallChinese.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Slf4j
@Table(name="Users")
public class User extends BaseEntity {



    @NonNull
    private String username;

    @NonNull
    private String email;

    @NonNull
    private String password;







}
