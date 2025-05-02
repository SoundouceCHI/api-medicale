package com.example.api_medicale.dto;

import com.example.api_medicale.entities.BaseEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto extends BaseEntity {

    private String username;
    private String role;

}