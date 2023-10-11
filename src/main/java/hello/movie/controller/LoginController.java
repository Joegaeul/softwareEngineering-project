package hello.movie.controller;

import hello.movie.dto.LoginForm;
import hello.movie.model.Member;
import hello.movie.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginForm loginForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity
                    .badRequest()
                    .body(bindingResult.getAllErrors());
        }

        Member loginMember = loginService.login(loginForm.getEmail(), loginForm.getPassword());

        if(loginMember == null){
            return ResponseEntity
                    .badRequest()
                    .body("이메일 또는 비밀번호가 맞지 않습니다.");
        }

        return ResponseEntity.ok().build();
    }
}
