package com.wac.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wac.domain.Cart;
import com.wac.domain.Menu;
import com.wac.domain.Order;
import com.wac.domain.OrderLog;
import com.wac.dto.MenuSimpleDto;
import com.wac.dto.OrderReadDto;
import com.wac.dto.OrderTossDto;
import com.wac.service.CartService;
import com.wac.service.ImagesService;
import com.wac.service.MenuService;
import com.wac.service.OrderService;
import com.wac.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 주문하기위한 Controller
 * 
 * @author 서범수, 추지훈
 */
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping
public class OrderController {
    
    private final MenuService menuService;
    
    private final ImagesService imagesService;
    
    private final OrderService orderService;
    
    private final UserService userService;
    
    private final CartService cartService;
    
    /**
     * order 전체 리스트 사용자별로 불러오는 메서드.
     * @param userName
     * @return
     * @author 추지훈
     */
    @GetMapping("/order/all")
    public ResponseEntity<List<OrderReadDto>> readAllPosts(String userName) {
        log.info("readAllPosts()");
        Integer userId = userService.getUserIdByUserName(userName);
        log.info("userId ={}", userId);
        List<OrderReadDto> list = orderService.readOrders(userId);
        log.info("OrderReadDto list() = {}", list);
        
        return ResponseEntity.ok(list);
    }
    
    /**
     * order 전체 리스트 사용자별로 불러오는 메서드.
     * @param userName
     * @return
     * @author 추지훈
     */
    @GetMapping("/order/orderComplete")
    public void readRecipt() {
        log.info("readAllPosts 호출(userId={})");
        
        
    }
    
    /**
     * 결제 상세내역으로 넘어갈때 필요한 리스트 넘겨주기
     * @author 추지훈
     */
    @GetMapping("/order/orderCompleteDetail")
    public void detail(@RequestParam("orderId") Integer orderId, Model model) {
        log.info("order/orderCompleteDetail(orderId = {})", orderId);
//        Order order = orderService.readOrder(orderId);
        Integer userId = orderService.getUserIdByOrderId(orderId);
        log.info("order/orderCompleteDetail(orderId = {})", orderId);
        String storeName = orderService.getStoreNameByOrderId(orderId);
        String userAddress = orderService.getUserAddressByOrderId(orderId);
        String paymentName = orderService.getPaymentName(orderId); 
        
        List<Menu> sideList = cartService.readAllSideMenuByKind();
        log.info("sideList 호출 완료");
        List<Menu> drinkList = cartService.readAllDrinkMenuByKind();
        log.info("drinkList 호출 완료");
        
        model.addAttribute("sideList", sideList);
        model.addAttribute("drinkList", drinkList);
        model.addAttribute("paymentName", paymentName);
        
        List<OrderLog> orderLogList = orderService.getOrderLogListByOrderId(orderId);
        log.info("order/orderCompleteDetail(orderId = {})", orderLogList);
        model.addAttribute("cartList", orderLogList);
        model.addAttribute("storeName",storeName);
        model.addAttribute("userAddress",userAddress);
        
        
    }
    
    
    @GetMapping("/order/cart")
    public void gotoCart(String userName, Model model) {
        log.info("gotoCart(userName={})", userName);
        
        List<Cart> cartList = cartService.readAllByUserName(userName);
        log.info("cartList 호출 완료");
        List<Menu> sideList = cartService.readAllSideMenuByKind();
        log.info("sideList 호출 완료");
        List<Menu> drinkList = cartService.readAllDrinkMenuByKind();
        log.info("drinkList 호출 완료");
        
        // 필요한 곳이 있다면.. 복사..
        String cokeName = "코카-콜라";
        String friesName = "후렌치 후라이";
        
        Integer cocaCola = menuService.readDefaultPriceByName(cokeName);
        Integer frenchFries = menuService.readDefaultPriceByName(friesName);
        model.addAttribute("cocaCola", cocaCola);
        model.addAttribute("frenchFries", frenchFries);
        
        
        model.addAttribute("cartList", cartList);
        model.addAttribute("sideList", sideList);
        model.addAttribute("drinkList", drinkList);

        
    }
    
    /**
     * 매장 선택 값을 오더 메뉴선택 페이지로 넘겨주는 기능
     * @author 장민석
     * @param storeName 매장이름
     * @param userAddress 매장에서 배달가야될 유저 주소
     * @param model 메뉴오더 페이지로 넘어온값을 model객체에 담아 전달해줌.
     * @return
     */
    @PostMapping("/order")
    public String jump(String storeName, String userAddress, String userName, Model model) {
		log.info("storeName={}",storeName);
		log.info("userAddress={}",userAddress);
		
		model.addAttribute("storeName",storeName);
		model.addAttribute("userName",userName);
		model.addAttribute("userAddress",userAddress);
    	return "/order/orderDetail";
    }
    
    /**
     * 결제 페이지로 넘겨줘서 결제 상세정보, 결제할때 주소랑 결제수단 선택하는 페이지에 넘겨주기 위한 메서드 
     * @param model
     * @author 추지훈
     */
    @GetMapping("/order/orderDetail")
    public void create(String storeName, String userAddress, String userName, Model model) {
        log.info("storeName={}",storeName);
        log.info("userAddress={}",userAddress);
        log.info("userName={}", userName);

        List<Cart> cartList = cartService.readAllByUserName(userName);
        List<MenuSimpleDto> sideList = cartService.readSimpleMenuByKind(3);
        List<MenuSimpleDto> drinkList = cartService.readSimpleMenuByKind(4);

        model.addAttribute("cartList", cartList);
        model.addAttribute("sideList", sideList);
        model.addAttribute("drinkList", drinkList);

        model.addAttribute("storeName",storeName);
        model.addAttribute("userAddress",userAddress);
        model.addAttribute("userName",userName);
    }
    
    /**
     * 그러고 난 뒤 여기서 order 에 해당 유저의 cart 상세내역을 옮겨담고 order_log에 필요한 정보만 남긴뒤 
     * 해당 카트 전체를 삭제한다.  
     * @param data
     * @return
     * @author 추지훈
     */
    @PostMapping("/order/create")
    public String create(OrderTossDto data) {
        log.info("create menuId = {}, userName = {}", data);
        Integer pickUp = data.getPickupService(); // 수력? 주문? 방법 order 테이블가보면 알 수 있음. 
        Integer payment = data.getPayment(); // 결제 수단 
        String address = data.getUserAddress(); // 상세주소
        String storeName = data.getStoreName(); // 매장명
        String userName = data.getUserName(); // 주문자명
        
        Integer userId = userService.getUserIdByUserName(userName);
        List<Cart> cartList = cartService.readCartList(userId);
        Integer totalPrice = 0;
        
        for (Cart c : cartList) {
            totalPrice += c.getPrice();
            log.info("totalPrice = {} ", totalPrice);
        }
        log.info("totalPrice = {} ", totalPrice);
        
        // 주문 영수증
        Order order = orderService.create(userName, storeName, address, pickUp, payment, userId, totalPrice);
        
        
        
        // 주문 내역
        for (Cart c : cartList) {
            Integer cartId = c.getCartId();
            OrderLog orderlog = orderService.create(order.getOrderId(), cartId, userId, userName);
            log.info("{}", order.getOrderId(), " 번 영수증의 상세 내역 = {}", orderlog);
            
            log.info("저장한 장바구니내역은 바로 지워짐 지울 카트번호 cartId = {}", c.getCartId());
            cartService.delete(c.getCartId());
        }
        log.info("카트에서 정보빼내서 영수증만들고 장바구니 내역을 주문 상세내역에 저장후 그 장바구니 삭제 => 완료");
        
        // 주소는 이제 어디로 넘길지.. 결제 완료 페이지를 띄울껀지 다시 홈으로 갈껀지 일단 home 으로 두겠음 ㅇㅇ 
        return "redirect:/order/orderCompleteDetail?orderId=" + order.getOrderId();
    }
    
    

    
}

