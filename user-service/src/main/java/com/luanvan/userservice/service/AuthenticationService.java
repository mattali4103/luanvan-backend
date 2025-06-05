package com.luanvan.userservice.service;

import com.luanvan.userservice.entity.User;
import com.luanvan.userservice.exception.AppException;
import com.luanvan.userservice.exception.ErrorCode;
import com.luanvan.userservice.model.Request.LoginRequest;
import com.luanvan.userservice.model.Response.LoginResponse;
import com.luanvan.userservice.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    @Value("${signer_key}")
    private String signerKey = System.getProperty("signer_key");

    private final UserRepository userRepository;

    public LoginResponse authenticate(LoginRequest request) {
        User user = userRepository.findById(request.getMaSo()).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        boolean isAuthenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!isAuthenticated) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }

        String token = generateToken(user);
        return LoginResponse.builder()
                .token(token)
                .isAuthenticated(true)
                .build();
    }

    private String generateToken(User user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getMaSo())
                .issuer("luongb2110945@student.ctu.edu.vn")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", createScope(user))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try{
            jwsObject.sign(new MACSigner(signerKey));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    private String createScope(User user){
        String role = "";
        if(user.getRole() != null){
            role  = user.getRole().toString();
        }
        return role;
    }
//    public void logout(AuthenticationRequest request) throws ParseException, JOSEException {
//        var signToken = verifyToken(request.getToken(), false);
//        String jit =  signToken.getJWTClaimsSet().getJWTID();
//        Date ExpiryTime = signToken.getJWTClaimsSet().getExpirationTime();
//        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
//                .id(jit)
//                .expiredTime(ExpiryTime)
//                .build();
//        invalidatedRepository.save(invalidatedToken);
//    }
    private SignedJWT verifyToken(String token, Boolean isRefresh) throws JOSEException, ParseException {

        JWSVerifier verifier = new MACVerifier(signerKey.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

//        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        Date expiryTime = (isRefresh) ? new Date(signedJWT.getJWTClaimsSet().getIssueTime().toInstant().plus(1, ChronoUnit.DAYS).toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!(verified && expiryTime.after(new Date())))
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        return signedJWT;
    }
    public boolean isValidate(String token) throws ParseException, JOSEException {
        boolean isValid = true;
        try {
            verifyToken(token, false);
        } catch (AppException e) {
            isValid = false;
        }
        return isValid;
    }
}
