package com.wac.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wac.domain.User;
import com.wac.dto.UserCreateDto;
import com.wac.dto.UserUpdateDto;
import com.wac.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * User를 관리하기 위한 controller
 * @author 이존규
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    
    private final UserService userService;
    
    /**
     * sign up 버튼을 누르면 회원가입 페이지로 이동
     * 생성자 : 이존규
     */
    @GetMapping("/signUp")
    public void signUp() {
        log.info("wac - sign Up");
    }
    
    /**
     * @param : web page에서 입력받은 정보를 dto로 변환하여 입력받음
     * @return : main page로 redirect
     * 생성자 : 이존규
     */
    @PostMapping("/signUp")
    public String create(UserCreateDto dto) {
        
        userService.createUser(dto);
        
        return "redirect:/";
    }
    
    /**
     * 모든 유저 리스트를 띄워주는 페이지 (삭제 예정. Read, Update, Delete 기능 생성용 더미 페이지)
     */
    @GetMapping("/userList")
    public String signIn(Model model) {
        log.info("wac - userList");
        List<User> list = userService.read();
        model.addAttribute("list", list);
        return "/user/userList";
    }
    
    /**
     * 개인정보 확인 및 변경을 위한 myPage
     * @param userId 값을 입력 받음
     * return : userId와 일치하는 계정을 가진 사람의 홈페이지로 이동
     */
    @GetMapping("/myPage")
    public void myPage(Model model, Integer userId) {
        log.info("mypage(id = {})", userId);
        User user = userService.read(userId);
        model.addAttribute("user", user);

    } 
    
    /**
     * 개인정보 확인 및 변경을 위한 myPage
     * @param userId 값을 입력 받음
     * return : userId와 일치하는 계정을 가진 사람의 홈페이지로 이동
     * 작성자 : 이존규
     */
    @PostMapping("/myPage")
    public void myPagePOST(Model model, Integer userId) {
        log.info("mypage(id = {})", userId);
        User user = userService.read(userId);
        model.addAttribute("user", user);

    }
    
    /**
     * 개인정보 병경을 위한 update 페이지
     * @param : user Id 값을 입력받음
     * return : user Id와 일치하는 계정을 가진 개인정보 변경 페이지로 이동
     * 작성자 : 이존규
     * 
     */
    @GetMapping("/update")
    public void update(Integer userId, Model model) {
        log.info("update(id) id = {}", userId);

        User user = userService.read(userId);
        log.info("user = {}", user);

        model.addAttribute("user", user);
    }
    
    /**
     * 개인정보 변경 페이지에서 해당 정보를 저장
     * @param dto 입력받은 값을 UserUpadateDto 형태로 입력받음
     * @return
     */
    @PostMapping("/update")
    public String updatePost(UserUpdateDto dto) {
        log.info("updateDto(dto) ={}", dto);
        Integer userId = userService.update(dto);
        
        log.info("updated user id = {}", userId);
        return "redirect:/user/myPage?userId=" + dto.getUserId();
    }

    
    @GetMapping("/wth")
    public void wth() {
        log.info("wac - sign Up");
    }

}
