package com.manh596.user.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.manh596.user.utils.Constant.SecurityConstant.TOKEN_PREFIX;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = TOKEN_PREFIX;
}
