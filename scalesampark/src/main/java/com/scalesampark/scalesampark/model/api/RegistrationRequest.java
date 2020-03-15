package com.scalesampark.scalesampark.model.api;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationRequest {
    private String email;
    private String nickname;
}
