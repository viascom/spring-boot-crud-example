package io.viascom.springbootcrudexample.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.viascom.springbootcrudexample.model.MemberEntity;
import io.viascom.springbootcrudexample.repository.MemberRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class JwtServiceHMAC implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;

    private final String secret = "YcMyGyq?q&SAy86MR!h";

    public UserDetails getUserDetails(MemberEntity user, List<String> requestedAuthorities) {
        return new User(user.getId().toString(), "", getAuthority(requestedAuthorities));
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        val user = memberRepository.findById(UUID.fromString(userId)).orElseGet(null);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new User(user.getId().toString(), "", new ArrayList<SimpleGrantedAuthority>());
    }

    public List<SimpleGrantedAuthority> getAuthority(List<String> requestedAuthorities) {
        return requestedAuthorities.stream().map(SimpleGrantedAuthority::new).toList();
    }

    public ArrayList<String> getRequestedAuthorities(DecodedJWT decoded) {
        var rolesRaw = decoded.getClaim("roles").asList(String.class);
        if(rolesRaw == null){
            rolesRaw = new ArrayList<>();
        }
        var scopesRaw = decoded.getClaim("scope").asList(String.class);
        if(scopesRaw == null){
            scopesRaw = new ArrayList<>();
        }

        val requestedAuthorities = new ArrayList<String>();
        requestedAuthorities.addAll(rolesRaw.stream().map(role -> "ROLE_" + role).toList());
        requestedAuthorities.addAll(scopesRaw.stream().map(scope -> "ROLE_" + scope).toList());

        return requestedAuthorities;
    }

    /**
     * Perform the verification against the given Token
     *
     * @param encodedJwt        to verify.
     * @param expectAccessToken is the provided token an access token.
     * @return a verified and decoded JWT.
     * @throws AlgorithmMismatchException     if the algorithm stated in the token's header it's not equal to the one defined in the JWTVerifier.
     * @throws SignatureVerificationException if the signature is invalid.
     * @throws TokenExpiredException          if the token has expired.
     * @throws InvalidClaimException          if a claim contained a different value than the expected one.
     */
    public DecodedJWT verifyJwt(String encodedJwt, Boolean expectAccessToken) throws AlgorithmMismatchException, SignatureVerificationException, TokenExpiredException, InvalidClaimException, GeneralSecurityException, IOException {
        var algorithm = Algorithm.HMAC512(getSecret());

        var verifier = JWT.require(algorithm).withClaim("typ", "Bearer").build();

        if (!expectAccessToken) {
            verifier = JWT.require(algorithm).withClaim("typ", "Refresh").build();
        }

        return verifier.verify(encodedJwt);
    }

    public String getSecret() {
        return secret;
    }

    public String resolveKey(HttpServletRequest request) {
        val bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        } else {
            return null;
        }
    }

    public String createNewJWT(String JWTId, String userId, String name, List<String> scopes) throws GeneralSecurityException, IOException {
        val now = LocalDateTime.now();

        val newJWT = JWT.create();
        newJWT.withClaim("jti", JWTId);
        newJWT.withClaim("name", name);
        newJWT.withClaim("user_id", userId);
        newJWT.withClaim("typ", "Bearer");
        newJWT.withClaim("scope", scopes);
        newJWT.withIssuedAt(now.toInstant(ZoneOffset.UTC));
        newJWT.withExpiresAt(now.plusDays(1).toInstant(ZoneOffset.UTC));

        val algorithm = Algorithm.HMAC512(getSecret());

        return newJWT.sign(algorithm);
    }
}
