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
    const btnMinus = document.querySelectorAll('.btn_minus');
    const btnPlus = document.querySelectorAll('.btn_plus');
    const menuQuantityInfo = document.querySelectorAll('.menuQuantityInfo');
    const totalPriceInfo = document.querySelector('#totalPriceInfo');
    let totalPrice = 0;
    
    // cartList의 메뉴아이디 가져오기. (menuId1 ~ menuId4)
    let menuIdList = [];
    
    for (let i = 0 ; i < cartList.length ; i++) {
        console.log(cartList[i].menuId1);
        
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
    
    // menuIdList의 있는 menuId를 통해 메뉴 정보 가져오기.
    for (let i = 0 ; i < menuIdList.length ; i++) {
        menuId = menuIdList[i];
        console.log(menuId);

        axios
        .get('/cart/toss/' + menuId)
        .then(response => {writeMenuName(response.data)})
        .catch(err => {console.log(err)});     
        
        function writeMenuName(data) {
            let eachMenuPriceTotalVariable= data.price;
          
            menuNameInfo[i].innerHTML = data.menuName;
            menuPriceInfo[i].innerHTML = data.price;
            menuImageInfo[i].src = "/image/display?fid=" + data.image;
            eachMenuPriceTotalInfo[i].innerHTML = eachMenuPriceTotalVariable * cartList[i].quantity;
            
            // 세트 메뉴가 아니라면, 사이드 변경 칸 사라짐.
            if (data.kind != 2) {
            isSetMenuInfo[i].style.display = 'none';
            }
            

        }   
    }
    

// 수량 관련.
    // 수량 현황 보여주기
    for (let i = 0 ; i < cartList.length ; i++) {   
        menuQuantityInfo[i].setAttribute('value', cartList[i].quantity);
    }
    
    for (let i = 0 ; i < cartList.length ; i++) {
        console.log(i+'번째: ' + btnMinus[i].dataset.menuId);
    }

    // 수량 버튼 만들기. (-)
    // 해당 메뉴의 cart_id 찾기.
    btnMinus.forEach((btn,index) => btn.addEventListener('click', function() {
        //let cartListId = btn.closest('li').dataset.index;
        
        if  (menuQuantityInfo[index].value > 1) {
        let cartListId = cartList[index].cartId;
        
        axios
        .post('/cart/lessQ/', cartListId)
        .then(response => {reduceQ(response.data)})
        .catch(err => {console.log(err)});
        
        function reduceQ(data) {
            
            //let onePrice = Number(eachMenuPriceTotalInfo[index].innerHTML) / menuQuantityInfo[index].value ; 
            //console.log('여기:' + onePrice);
            // 다른 것을 작성하지 않아도 자동으로 값이 바뀜.
            menuQuantityInfo[index].value -= 1;
            console.log( eachMenuPriceTotalInfo[index].innerHTML);

            let lessPrice = Number(eachMenuPriceTotalInfo[index].innerHTML) - Number(menuPriceInfo[index].innerHTML);
            console.log(lessPrice);
            eachMenuPriceTotalInfo[index].innerHTML = lessPrice;
        }
        }
        
    }));
    
    // 수량 버튼 만들기. (+)
    btnPlus.forEach((btn, index) => btn.addEventListener('click', function() {
        if  (menuQuantityInfo[index].value <= 10) {
            let cartListId = cartList[index].cartId;
            
            axios
            .post('/cart/moreQ/', cartListId)
            .then(response => {increaseQ(response.data)})
            .catch(err => {console.log(err)})
            
            function increaseQ(data) {
                menuQuantityInfo[index].value = Number(menuQuantityInfo[index].value) + 1;
                console.log(menuQuantityInfo[index].value);
                
            let morePrice = Number(eachMenuPriceTotalInfo[index].innerHTML) + Number(menuPriceInfo[index].innerHTML);
            console.log(morePrice);
            eachMenuPriceTotalInfo[index].innerHTML = morePrice;
            }
        }
    }));
    
    // 사이드 메뉴 추가하기.
    let kinds = [3, 4];
    let prices = {3: 2600, 4: 2200};
    let divListNames = {3: '.sideMenuList', 4: '.drinkMenuList'};
    
    for (let kind of kinds) {
        console.log('for문:' + kind);
        showMenuList(kind);
        
        function showMenuList(kind) {
        console.log('showMenuList: ' + kind);
        axios
        .get('/menu/all', {params: {kind}})
        .then(response => {updateMenuList(response.data)})
        .catch(err => {console.log(err)});
        } // showMenuList 끝.
        
        function updateMenuList(data) {
        console.log('updateMenuList: '+ kind);
        let divListName = divListNames[kind];
        let price = prices[kind];
        let divMenuList = document.querySelectorAll(divListName);
        
        for (let i = 0; i < divMenuList.length; i++) {
            let str = '';
            for (let m of data) {
                let surcharge = Number(m.price) - Number(price);
                str += '<li style="display:inline-block;">'
                    + '</div>'
                    + '<label>'
                    + '<input type="radio" name="test"/>'
                    + '<img src="/image/display?fid='
                    + m.image
                    + '" alt="사이드 이미지" style="width:350px; height: 200px;" />'
                    + '</label>'
                    + '<div style="text-align: center; margin: 10px 0;">'
                    + m.menuName
                    + '</div>'
                    + '<div style="text-align: center; margin: 10px 0;">'
                    + '+'
                    + surcharge
                    + '원'
                    + '</li>';
            }
            console.log(str);
            divMenuList[i].innerHTML = str;
        } 
        } // updateMenuList 끝.
    }
    
// 총액 반영하기
// 처음..

for (price of eachMenuPriceTotalInfo) {
    console.log(price.innerHTML);
}
console.log(totalPrice);
        
    






});