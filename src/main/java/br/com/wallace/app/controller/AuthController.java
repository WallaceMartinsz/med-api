package br.com.wallace.app.controller;


import br.com.wallace.app.domain.user.UserRequest;
import br.com.wallace.app.domain.user.Users;
import br.com.wallace.app.web.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity authLogin(@RequestBody @Valid UserRequest userRequest){
        var usernamePassword = new UsernamePasswordAuthenticationToken(userRequest.login(), userRequest.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.GenereteToken((Users) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(token);
    }
}
