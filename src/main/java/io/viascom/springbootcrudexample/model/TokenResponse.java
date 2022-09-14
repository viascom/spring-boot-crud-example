package io.viascom.springbootcrudexample.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;

@Data
@AllArgsConstructor
public class TokenResponse {

    @JsonProperty("access_token")
    String accessToken;

    @JsonProperty("refresh_token")
    String refreshToken;

    @JsonProperty("token_type")
    String tokenType;

    @JsonProperty("refresh_expires_in")
    Long refreshExpiresIn = Duration.ofDays(14).getSeconds();

    @JsonProperty("expires_in")
    Long expiresIn = Duration.ofDays(1).getSeconds();

}
