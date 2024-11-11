package org.example.graduatemanage.loginController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.graduatemanage.dox.User;
import org.example.graduatemanage.exception.Code;
import org.example.graduatemanage.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.example.graduatemanage.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class Login {

    private final UserService userService;
    @PostMapping("login")
    public ResultVO login(@RequestBody User user){
        User userR = userService.getUserByNumber(user.getNumber(),user.getPassword());
        if (userR==null){
            return ResultVO.error(Code.LOGIN_ERROR);
        }
        return ResultVO.success(userR);
    }
}
