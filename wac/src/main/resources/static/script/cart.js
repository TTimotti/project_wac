/**
 * user/myPage page script
 */



window.addEventListener('DOMContentLoaded', function() {

    // 로그인 유저 체크
    const ifDifferent = document.querySelector('#ifDifferent');
    const ifSame = document.querySelector('#ifSame');

    console.log(" login user = ", loginUser);
    console.log("username =", userName);
    console.log("Cart =", myCart);
    console.log("Order = ", myOrder);

    if (userName == loginUser) {
        ifSame.style.display = "block";

    } else {
        ifDifferent.style.display = "block";
    }

    // 버튼 기능 활성화
    const cartDiv = document.querySelector('#cartDiv');
    const orderDiv = document.querySelector('#orderDiv');
    const btnOrder = document.querySelector('#btnOrder');
    const btnCart = document.querySelector('#btnCart');

    // Order 버튼 누르면 Order Dive 활성화
    btnOrder.addEventListener('click', function() {
        orderDivOn();
    });

    // Cart 버튼 누르면 Cart div 활성화
    btnCart.addEventListener('click', function() {
        cartDivOn();
    });

    // order 보여주기
    // order가 null인 경우, order 페이지로 이동하는 버튼만 보여주도록 함
    function orderDivOn() {
        orderDiv.style.display = "block";
        cartDiv.style.display = "none";

        const orderIsNotNull = document.querySelector('#orderIsNotNull');
        const orderIsNull = document.querySelector('#orderIsNull');

        if (myOrder == null) {
            orderIsNotNull.style.display = "none";
            orderIsNull.style.display = "block";
        } else {
            orderIsNotNull.style.display = "block";
            orderIsNull.style.display = "none";
        }

    }

    // cart 보여주기
    // cart가 null인 경우, order 페이지로 이동하는 버튼만 보여주도록 함
    function cartDivOn() {
        orderDiv.style.display = "none";
        cartDiv.style.display = "block";
        
        const cartIsNotNull = document.querySelector('#cartIsNotNull');
        const cartIsNull = document.querySelector('#cartIsNull');

        if (myCart == null) {
            cartIsNotNull.style.display = "none";
            cartIsNull.style.display = "block";
        } else {       
            cartIsNotNull.style.display = "block";
            cartIsNull.style.display = "none";
        }

    }


});