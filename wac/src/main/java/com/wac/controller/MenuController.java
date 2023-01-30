package com.wac.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wac.domain.Menu;
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
    
    @GetMapping("/menu/toss")
    public ResponseEntity<MenuReadDto> tossCart(Integer menuId) {
        log.info("tossCart menuId = {}", menuId);
        MenuReadDto menu = menuService.readMenu(menuId);
        return ResponseEntity.ok(menu);
    }
   
    /**
     * 메뉴 전체 리스트 kind(종료)별로 불러오는 메서드.
     * @param kind
     * @return
     * @author 추지훈
     */
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
    
    /**
     * 메뉴 생성메서드 이미지는 보류
     * @param dto, image
     * @return
     * @author 추지훈
     * @throws IOException 
     * @throws IllegalStateException 
     */
    @PostMapping("/menu/create")    
    public String create(MenuCreateDto dto, @RequestParam("image") MultipartFile file) throws IllegalStateException, IOException { 
        log.info("create() dto = {}, file = {}", dto, file);
        Integer fid = imagesService.saveImage(file);
        menuService.create(dto, fid);
        
        return "redirect:/menu";
    }
    
    /**
     * 메뉴판에 개수를 새는 메서드 왼쪽 상단에 몇개의 메뉴가 표출되어있는지 표시하기위함.
     * 아직 view에 넣진않음.
     * @param kind
     * @return
     * @author 추지훈
     */
    @GetMapping("/menu/countMenus/{kind}")
    @ResponseBody
    public ResponseEntity<Integer> countMemus(Integer kind) {
        log.info("countMemus kind = {}", kind);
        Integer members = menuService.countMenus(kind);
        
        return ResponseEntity.ok(members);
    }
    
    /**
     * 메뉴 상세 페이지로 넘어갑니다.
     * 포스트매핑으로 넘길까 생각 중...(맥도날드처럼)
     * @author: 서범수
     */
    @GetMapping("/menu/menuDetail")
    public void MenuDetail(Integer menuId, Model model) {
        log.info("MenuDetail()");

        Menu menu = menuService.readById(menuId);
        model.addAttribute("menu", menu);

        Menu prevMenu = menuService.readPrevMenuById(menuId);
        model.addAttribute("prevMenu", prevMenu);        
        
        Menu nextMenu = menuService.readNextMenuById(menuId);
        model.addAttribute("nextMenu", nextMenu);
    }

    
}