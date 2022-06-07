package ir.fathi.taskmanagement.controller;

import ir.fathi.taskmanagement.config.security.JwtTokenUtil;
import ir.fathi.taskmanagement.dto.PostUserDto;
import ir.fathi.taskmanagement.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;


    @PostMapping
    public ResponseEntity<Object> login(@RequestBody @Valid PostUserDto request){

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.username(),request.password()));
            User user= (User) authentication.getPrincipal();
            return ResponseEntity.accepted().body(jwtTokenUtil.generateAccessToken(user));

        }catch (BadCredentialsException exception){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }


    }


}
