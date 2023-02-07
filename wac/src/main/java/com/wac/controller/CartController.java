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
        log.info("create1={}", menuId);
        Menu menu = menuService.readById(menuId);
        return ResponseEntity.ok(menu);
    }
	
    /**
     * 장바구니 추가시 유저정보, 주소와 해당 메뉴를 메뉴 종류에 맞게 카트에 저장하고 페이지를 카트로 넘겨줌.  
     * @param data = menuId, userName
     * @return
     * @author 추지훈
     */
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Cart> create(@RequestBody CartTossDto data) {
        log.info("create menuId = {}, userName = {}", data);
        String userName = data.getUserName(); // 주문자명
        Integer data_menuId = data.getMenuId(); // 주문한 메뉴아이디
        
        // 메뉴 정보불러오기
        Menu menu = menuService.readMenu(data_menuId);
        log.info("메뉴 정보 = {}", menu);
        
        // 초기 카트 생성 
        Cart cart = cartService.create(data_menuId, userName);
//        CartCreateDto cart = null;
        cart.setUserName(data.getUserName()); // 유저 이름 저장.
        log.info("메뉴 점검 전 카트 = {}", cart);
        cart.setStoreName(data.getStoreName()); // 매장명 저장. 
        cart.setAddress(data.getUserAddress()); // 상세주소 저장.
        cart.setImage(menu.getImage()); // 이미지 저장.
        if (menu.getKind() == 2) {
            cart.setMenuId2(data_menuId);
            log.info("중간점검 전 세트 카트 = {}", cart);
            Integer bugerByMealId = menuService.readBugerByMeal(data_menuId); // 세트에 해당하는 버거를 가져온다
            cart.setMenuId1(bugerByMealId); // 버거 아이디 저장.
            log.info("중간점검 후 세트 카트 = {}", cart);
            cart.setMenuId3(1); // 무조건 감튀 저장. 감튀 아이디를 나중에 넣어주면됨.
            cart.setMenuId4(1); // 무조건 콜라 저장. 동일.. 
            
        } else if (menu.getKind() == 1) { // 버거 단품일 경우
            cart.setMenuId1(data_menuId);
            
        } else if (menu.getKind() == 3) { // 감튀 단품일 경우.
            cart.setMenuId3(data_menuId);
            
        } else if (menu.getKind() == 4) { // 음료 단품일 경우. 
            cart.setMenuId4(data_menuId);
            
        } else if (menu.getKind() == 5) { // 맥모닝 단품일 경우. 
            cart.setMenuId5(data_menuId);
            
        } else if (menu.getKind() == 6) { // 맥모닝 세트일 경우. 
            cart.setMenuId6(data_menuId);
            Integer bugerByMealId = menuService.readBugerByMeal(data_menuId); // 세트에 해당하는 버거를 가져온다
            cart.setMenuId1(bugerByMealId);
            cart.setMenuId3(1); // 해쉬브라운 디폴트
            cart.setMenuId4(1); // 콜라 디폴트
            
        }  
        
        log.info("메뉴 점검 후 카트 = {}", cart);
        Cart cartAfter = cartService.create(cart);
        log.info("새카트에 정보넣고 빈카트 삭제하기 전 cartAfter = {}", cartAfter);
        cartService.delete(cart.getCartId());
        log.info("초기 빈카트 삭제 = {}", cart);

        return ResponseEntity.ok(cartAfter);
    }
    
    
    /**
     * 카트에서 수량을 하나씩 줄이는 메서드.
     * @param cartListId
     * @return 줄인 값(=1)
     * @author 서범수
     */
    @PostMapping("/lessQ")
    @ResponseBody
    public ResponseEntity<Integer> lessQ(@RequestBody String cartListId) {
        log.info("lessQ(cartListId={})", cartListId);
        Integer lessQ = cartService.lessQ(cartListId);
        return ResponseEntity.ok(lessQ);
    }
    
    /**
     * 카트에서 수량을 하나씩 늘리는 메서드.
     * @param cartListId
     * @return 늘린 값(=1) 사실 return 값 없어도 됨.
     * @author 서범수
     */
    @PostMapping("/moreQ")
    @ResponseBody
    public ResponseEntity<Integer> moreQ(@RequestBody String cartListId) {
        log.info("moreQ(cartListId={})", cartListId);
        Integer moreQ = cartService.moreQ(cartListId);
        return ResponseEntity.ok(moreQ);
    }
    
    /**
     * 카트에서 사이드 메뉴를 변경하는 메서드.
     * @param menuId, cartId
     * @author 서범수.
     */
    @PostMapping("/changeSideMenu")
    @ResponseBody
    public void changeSideMenu(Integer cartId, Integer menuId) {
        log.info("changeSideMenu(cartId={}, menuId={})", cartId, menuId);

        cartService.changeSideMenu(cartId, menuId);

    }
    
    /**
     * 카트에서 음료수 메뉴를 변경하는 메서드.
     * @param menuId, cartId
     * @author 서범수.
     */
    @PostMapping("/changeDrinkMenu")
    @ResponseBody
    public void changeDrinkMenu(Integer cartId, Integer menuId) {
        log.info("changeSideMenu(cartId={}, menuId={})", cartId, menuId);

        cartService.changeDrinkMenu(cartId, menuId);

    }
    
    /**
     * 카트에서 메뉴 지우는 메서드
     * @param menuId, cartId
     * @author 서범수.
     */
    @PostMapping("/deleteCartItem")
    @ResponseBody
    public void deleteCartItem(Integer cartId) {
        log.info("deleteCartItem(cartId={})", cartId);
        cartService.deleteCartItem(cartId);
    }
    
}