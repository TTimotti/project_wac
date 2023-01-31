/**
 * user/myPage page script
 */



window.addEventListener('DOMContentLoaded', function() {

    // 로그인 유저 체크
    const ifDifferent = document.querySelector('#ifDifferent');
    const ifSame = document.querySelector('#ifSame');

    console.log(loginUser);
    console.log(userName);

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

    function orderDivOn() {
        orderDiv.style.display = "block";
        cartDiv.style.display = "none";
    }

    function cartDivOn() {
        orderDiv.style.display = "none";
        cartDiv.style.display = "block";
    }

});