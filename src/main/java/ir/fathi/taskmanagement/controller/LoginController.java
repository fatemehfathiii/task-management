package ir.fathi.taskmanagement.controller;

import ir.fathi.taskmanagement.config.aspect.MethodDurationLog;
import ir.fathi.taskmanagement.config.security.JwtTokenUtil;
import ir.fathi.taskmanagement.dto.PostUserDto;
import ir.fathi.taskmanagement.model.User;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;



    @PostMapping("/login")
    @MethodDurationLog
    public ResponseEntity<Object> login(@RequestBody @Valid PostUserDto request){

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
            return ResponseEntity.accepted().body(jwtTokenUtil.generateAccessToken(request.username()));
        }catch (BadCredentialsException exception){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED ,"your username or password is incorrect!",exception);
        }
    }

}
