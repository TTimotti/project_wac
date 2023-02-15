package com.wac.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.wac.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	/**
	 * user Id를 이용해서 Cart 내역 검색
	 * 
	 * @param userId
	 * @return
	 */
	List<Cart> findByUserIdOrderByCartId(Integer userId);

	Optional<Cart> findByUserId(Integer userId);
	
	@Modifying
	@Transactional
	@Query("update CARTS c set c.quantity = c.quantity - 1 where c.cartId = :cartId")
    Integer lessQBycartId(@Param(value = "cartId") Integer cartId);
	
    @Modifying
    @Transactional
    @Query("update CARTS c set c.quantity = c.quantity + 1 where c.cartId = :cartId")
    Integer moreQBycartId(@Param(value = "cartId") Integer cartId);
    
    @Modifying
    @Transactional
    @Query("update CARTS c set c.menuId3 = :menuId where c.cartId = :cartId")
    void changeSideMenu(@Param(value = "cartId") Integer cartId, @Param(value="menuId") Integer menuId);

    @Modifying
    @Transactional
    @Query("update CARTS c set c.menuId4 = :menuId where c.cartId = :cartId")
    void changeDrinkMenu(@Param(value = "cartId") Integer cartId, @Param(value="menuId") Integer menuId);

    @Modifying
    @Transactional
    void deleteBycartId(Integer cartId);

    
//    @Query("select c from CARTS c where c.cartId = :cartId")
    Cart getCartByCartId(@Param("cardId") Integer cartId);	

}
