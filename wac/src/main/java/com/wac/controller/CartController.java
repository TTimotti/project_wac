package com.wac.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wac.domain.Cart;
import com.wac.domain.Menu;
import com.wac.domain.Order;
import com.wac.domain.Users;
import com.wac.dto.CartTossDto;
import com.wac.service.CartService;
import com.wac.service.MenuService;
import com.wac.service.OrderService;
import com.wac.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Cart를 관리하기 위한 controller
 * 
 * @author 이존규
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
	private final MenuService menuService;
	private final UserService userService;
	private final OrderService orderService;
	private final CartService cartService;

	/**
	 * username, userId를 입력 받아서 User 객체 생성. 생성된 User 객체의 .getUserId() 메서드로 userId값을
	 * 얻음. 얻은 userId값으로 cart, order 객체에서 일치하는 userId 값의 데이터가 있는지 탐색.
	 * 
	 * @param model
	 * @param userName
	 * @param userId
	 * @return userId가 포함된 Cart, Order 객체. 없으면 null을 html에 보냄
	 * @author 이존규
	 */
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("cart")
	public void cartPage(Model model, String userName, Integer userId) {
		log.info("cart page = userId{}, userName{]", userId, userName);
		Users user = null;
		Cart cart = null;
		Order order = null;

		if (userName == null) {
			user = userService.read(userId);
		} else {
			user = userService.read(userName);
		}

		cart = cartService.read(user.getUserId());
		order = orderService.read(user.getUserId());

		model.addAttribute("cart", cart);
		model.addAttribute("order", order);
		model.addAttribute("user", user);

	}

	/**
	 * 오더페이지로 선택하면 메뉴 정보 넘겨줌. menuId 만 보내서 오더에서 sql 문으로 조회 메뉴를 선택하는 동시에 유저 정보와 메뉴정보가
	 * 같이 cart 테이블에 저장되게 해야함 이 메서드안에서 메뉴조회 유저조회 cart 테이블 create 기능이 동시에 이러나야함.
	 * 
	 * @param menuId
	 * @return
	 * @author 추지훈
	 */
    @GetMapping("/toss/{menuId}")
    @ResponseBody
    public ResponseEntity<Menu> create1(@PathVariable Integer menuId) {
//        CartTossDto cartToss = null;
//        cartToss.setMenuId(menuId);
//        cartToss.setUserName(userName);
        
        Menu menu = menuService.readById(menuId);
        return ResponseEntity.ok(menu);
    }
	
    /**
     * 장바구니 추가시 해당 메뉴를 카트에 저장하고 
     * @param menuId
     * @param userName
     * @return
     */
    @GetMapping("/create")
    @ResponseBody
    public ResponseEntity<Cart> create(Integer data_menuId) {
        log.info("create menuId = {}, userName = {}", data_menuId);
//        log.info("loginUser = {}", userName);
//        Integer userId = userService.getUserIdByUserName(loginUser);
//        log.info("유저 아이디 = {}", userId);
//        Integer data_menuId = 1;
        

        Menu menu = menuService.readMenu(data_menuId);
        log.info("메뉴 정보 = {}", menu);
        
        
        Cart cart = cartService.create(data_menuId, "admin");

        log.info("메뉴 점검 전 카트 = {}", cart);
        if (menu.getKind() == 2) {
            cart.setMenuId2(data_menuId);
//            Integer bugerByMealId = menuService.readBugerByMeal(menuId); // 세트에 해당하는 버거를 가져온다
//            cart.setMenuId1(bugerByMealId);
            cart.setMenuId3(1); // 무조건 감튀 저장. 감튀 아이디를 나중에 넣어주면됨.
            cart.setMenuId4(1); // 무조건 콜라 저장. 동일.. 
        } else if (menu.getKind() == 1) { // 버거 단품일 경우
            cart.setMenuId1(data_menuId);
        } else if (menu.getKind() == 3) { // 감튀 단품일 경우.
            cart.setMenuId3(data_menuId);
        } else if (menu.getKind() == 4) { // 음료 단품일 경우. 
            cart.setMenuId4(data_menuId);
        } 
        log.info("메뉴 점검 후 카트 = {}", cart);
//        CartCreateDto dto = cartService.addDto(cart);
        Cart cartAfter = cartService.create(cart);


        return ResponseEntity.ok(cartAfter);
    }

}