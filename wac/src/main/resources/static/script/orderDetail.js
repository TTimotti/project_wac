/**
 * 카트창 페이지...
 * 서범수
 */
window.addEventListener('DOMContentLoaded', function() {

       
    // 변수들 저장하기
    const menuNameInfo = document.querySelectorAll('.menuNameInfo');
    const menuPriceInfo = document.querySelectorAll('.menuPriceInfo');
    const menuImageInfo = document.querySelectorAll('.menuImageInfo');
    const eachMenuPriceTotalInfo = document.querySelectorAll('.eachMenuPriceTotalInfo');
    const isSetMenuInfo = document.querySelectorAll('.isSetMenuInfo');
    const menuQuantityInfo = document.querySelectorAll('.menuQuantityInfo');
    const totalPriceInfo = document.querySelector('#totalPriceInfo');
    const sideMenuInfo = document.querySelectorAll('.sideMenuInfo');
    const extraChargeInfo = document.querySelectorAll('.extraChargeInfo');
    const drinkMenuInfo = document.querySelectorAll('.drinkMenuInfo');
    const extraChargeInfo2 = document.querySelectorAll('.extraChargeInfo2');
    const btnPayment = document.querySelectorAll('#btnSubmit');
    const frenchFries = 2600;
    const cocaCola = 2200;
    console.log("결제버튼 찾기", btnPayment);
    var totalPrice = 0;
    
   // console.log("수령 방법", pickupService);
   // console.log("결제 수단", payment);
   // console.log("주소", address);
   // console.log("매장명", storeName);
   console.log("사용자 이름", loginUser);
    
    
    // READ
    // cartList에 있는 메뉴아이디 가져오기. (menuId1 ~ menuId4)
    let menuIdList = [];
    
    for (let i = 0 ; i < cartList.length ; i++) {
        
        if (cartList[i].menuId1 != null) {
            menuIdList.push(cartList[i].menuId1);
        } else if (cartList[i].menuId2 != null) {
            menuIdList.push(cartList[i].menuId2);
        } else if (cartList[i].menuId3 != null) {
            menuIdList.push(cartList[i].menuId3);
        } else {
            menuIdList.push(cartList[i].menuId4);
        }
    }
    
    console.log(menuIdList);
    
    // menuIdList를 Controller로 넘겨서
    // 필요한 정보들을 가져온다.
    // menuId, menuName, price, image, kind (DTO?)
    
    
    console.log('ddd');


    
    axios
    .post("/cart/getMenuInfo", menuIdList)
    .then(response => {setMenuInfo(response.data)})
    .catch(err => {console.log(err)})
    
    function setMenuInfo(data) {
        console.log('ddsad');
        console.log(data);
        for (let i = 0 ; i < menuIdList.length ; i++) {
            let eachMenuPriceTotalVariable= data.price;
            let sideExtraFee = 0;
            let drinkExtraFee = 0;
            let eachMenuPriceTotal = 0;

            // 메뉴 이름.
            menuNameInfo[i].innerHTML = data[i].menuName;
            
            // 메뉴 가격.
            let menuPrice = data[i].price;
            menuPriceInfo[i].innerHTML = menuPrice;
            
            // 메뉴 사진.
            menuImageInfo[i].src = "/image/display?fid=" + data[i].image;
            
            // 메뉴 수량.
            let menuQuantity = cartList[i].quantity;
            menuQuantityInfo[i].value = menuQuantity;
   
            // 세트 메뉴일 경우에만(kind=2일) 보여주는 란
            if (data[i].kind != 2) {
            isSetMenuInfo[i].style.display = 'none';
            }
            
            let extraCharge = 0;
            let extraCharge2 = 0;
            // 사이드 이름과 추가요금
            // 사이드에 대한 정보 다 가져오지 말고, menuId, menuName, price만 가져오자!
            for (side of sideList) {
                /*
                console.log(i +'번 째리스트');
                console.log('사이드메뉴');
                console.log(side.menuId);
                console.log('카트리스트사이드메뉴');
                console.log(cartList[i].menuId3);
                */
                
                if (side.menuId == cartList[i].menuId3) {
                    sideMenuInfo[i].innerHTML = side.menuName;
                    extraCharge = side.price - frenchFries;
                    extraChargeInfo[i].innerHTML = extraCharge;
                }
            }
            
            // 음료 이름과 추가요금
            for (drink of drinkList) {
                if (drink.menuId == cartList[i].menuId4) {
                    drinkMenuInfo[i].innerHTML = drink.menuName;
                    extraCharge2 = drink.price - cocaCola;
                    extraChargeInfo2[i].innerHTML = extraCharge2;
                }
            }
            
            
            // 개별 메뉴의 총액
            // (단품 가격 + 사이드 가격 + 음료 가격) * 수량
            console.log(menuPrice);
            console.log(extraCharge);
            console.log(extraCharge2);
            console.log(menuQuantity);
            
            let eachTotal = (menuPrice + extraCharge + extraCharge2) * menuQuantity;
            eachMenuPriceTotalInfo[i].innerHTML = eachTotal;

            
            totalPrice += eachTotal;
            // console.log('??');
            // console.log(totalPrice);
            // 세트 메뉴가 아니라면, 사이드 변경 칸 사라짐.
            
            // 영수증 총액 보여주기.
            if (i + 1 == menuIdList.length) {
                setTimeout(total, 100);
            }          
            function total() {
                totalPriceInfo.innerHTML = totalPrice;
            }          
        }
    } // setMenuInfo 끝.
       
 
const btnCreateOrder = document.querySelector('#btnSubmit');    
const formCreateCart = document.querySelector('#formCreateCart'); 
console.log("결제버튼 찾기", btnCreateOrder);
      
btnCreateOrder.addEventListener('click', function() {
    const menuName = menuNameInput.value;
    const kind = kindInput.value;
    const price = priceInput.value;
    const content = contentInput.value;
    
    if (menuName.length == 0 || kind.length == 0 || price.length == 0 || content.length == 0) {
        alert("결제내역을 체크해 주십시오");
    } else {
        const result = confirm('결제하시겠습니까');
        if (result) {
            formCreateCart.action = '/order/create';
            formCreateCart.method = 'post';
            formCreateCart.submit();
            
            alert("결제 완료")
        }
    }
      
})
      
      
      
      
    btnPayment.forEach(btn => {
        btnPayment.href = "/";
        btn.addEventListener('click', tossOrder);
        console.log("결제 버튼 클릭");
        btnPayment.href = "/";
    })

// $(document).on("click", "#btnPayment", tossOrder);

function tossOrder(event) {
    btnPayment.href = "/";
    const data_storeName = event.target.getAttribute('data-storeName');
    const data_userAddress = event.target.getAttribute('data-userAddress');
    console.log(storeName);
    console.log("유저이름", loginUser);

    btnPayment.href = "/";
    console.log("페이지넘어가기", loginUser);

    const data = {
            cartId: 1,
            userName: loginUser,
            storeName: data_storeName,
            userAddress: data_userAddress,
            pickupService: 1,
            payment: 1
        }
    console.log("데이터 확인", data);

     axios
    .post('/order/create/', data )
    .then(response => { 
        response.data; 

        console.log("컨트롤러 넘어가는지 확인", data);    
        })
    .catch(err => { console.log(err)});   

    console.log("성공");
};


 /**
    메뉴 하단에 있는 탭들을 펼치고 닫는 기능.
  */
const btnDetail = document.querySelector('.orderList > button');
// 배열이니까 toggle[i]도 가능함.
console.log(btnDetail);
    btnDetail.addEventListener("click", function() {
        const orderDisplay = document.querySelector('.cart_list');

        let orderDisplay2 = orderDisplay.style.display;
        
            if (orderDisplay2=='none' || orderDisplay2 =='') {
                orderDisplay.style.display = 'block';
            } else {
                orderDisplay.style.display ='none';
            }
    });




});