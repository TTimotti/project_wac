package com.wac.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wac.dto.MenuCreateDto;
import com.wac.dto.MenuReadDto;
import com.wac.service.ImagesService;
import com.wac.service.MenuService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping
public class MenuController {
    
    private final MenuService menuService;
    
    private final ImagesService imagesService;
    
    
   
    
    @GetMapping("/menu/all")
    public ResponseEntity<List<MenuReadDto>> readAllMenus(Integer kind) {
        log.info("readAllMenus()");
        List<MenuReadDto> list = menuService.readMenus(kind);
        log.info("FoodReadDto list()", list);
        
        return ResponseEntity.ok(list);
    }
    
    @GetMapping("/menu/create")
    public void create(Integer id, Model model) {
        log.info("create");
        model.addAttribute("id", id);
    }
    
    @PostMapping("/menu/create")
    public String create(MenuCreateDto dto) { // ,  @RequestParam("image") MultipartFile file) throws IllegalStateException, IOException {
        log.info("create() dto = {}", dto);
        // Menu entity = 
        menuService.create(dto);
        
//        Integer img = imagesService.saveMenuImage(file);
//        dto.setImage(img);
        
        return "redirect:/menu";
    }
    
    @GetMapping("/menu/countMenus/{kind}")
    @ResponseBody
    public ResponseEntity<Integer> countMemus(@PathVariable Integer kind) {
        log.info("countMemus kind = {}", kind);
        Integer members = menuService.countMenus(kind);
        
        return ResponseEntity.ok(members);
    }
    
    /**
     * 메뉴 상세 페이지로 넘어갑니다.
     * 포스트매핑으로 넘길까 생각 중...(맥도날드처럼)
     * @author: 서범수
     */
    @GetMapping("/menuDetail")
    public String MenuDetail() {
        log.info("MenuDetail()");
        return "/menuDetail";
    }

    
}
