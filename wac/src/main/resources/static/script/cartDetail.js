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
    const sideMenuInfo = document.querySelectorAll('.sideMenuInfo');
    const extraChargeInfo = document.querySelectorAll('.extraChargeInfo');
    const drinkMenuInfo = document.querySelectorAll('.drinkMenuInfo');
    const extraChargeInfo2 = document.querySelectorAll('.extraChargeInfo2');
    const sideMenuChange = document.getElementsByName('sideMenuChange');
    const drinkMenuChange = document.getElementsByName('drinkMenuChange');
    const btnDelete = document.querySelectorAll('.btnDelete');
    const cocaCola = 2200;
    const frenchFries = 2600;
    var totalPrice = 0;
    
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
    

    
    // menuIdList의 있는 menuId를 통해 메뉴 정보 가져오기.
    for (let i = 0 ; i < menuIdList.length ; i++) {
        menuId = menuIdList[i];

        axios
        .get('/cart/toss/' + menuId)
        .then(response => {writeMenuName(response.data)})
        .catch(err => {console.log(err)});     
        
    // 가져온 menuId를 통해 장바구니에 보여줄 각각의 정보 작성하기.
        function writeMenuName(data) {
            let eachMenuPriceTotalVariable= data.price;
            let sideExtraFee = 0;
            let drinkExtraFee = 0;
            let eachMenuPriceTotal = 0;
            
            // 메뉴 이름.
            menuNameInfo[i].innerHTML = data.menuName;
            
            // 메뉴 가격.
            menuPriceInfo[i].innerHTML = data.price;
            
            // 메뉴 사진.
            menuImageInfo[i].src = "/image/display?fid=" + data.image;
            
            // 세트 메뉴 시, 사이드 메뉴 이름과 추가 요금.
            for (side of sideList) {
                if (side.menuId == cartList[i].menuId3) {
                    sideExtraFee = side.price - frenchFries;
                    sideMenuInfo[i].innerHTML = side.menuName;
                    extraChargeInfo[i].innerHTML = side.price - frenchFries;
                }
            }
            
            // 세트 메뉴 시, 음료 메뉴 이름과 추가 요금.
            for (drink of drinkList) {
                if (drink.menuId == cartList[i].menuId4) {
                    drinkExtraFee = drink.price - cocaCola;
                    drinkMenuInfo[i].innerHTML = drink.menuName;
                    extraChargeInfo2[i].innerHTML = drinkExtraFee;
                }
            }
            
            // 메뉴 + 사이드 변경 + 음료 변경 가격.
            
            eachMenuPriceTotal += (eachMenuPriceTotalVariable + sideExtraFee + drinkExtraFee) * cartList[i].quantity;
            eachMenuPriceTotalInfo[i].innerHTML = eachMenuPriceTotal;
            
            totalPrice += eachMenuPriceTotal;
            // console.log('??');
            // console.log(totalPrice);
            // 세트 메뉴가 아니라면, 사이드 변경 칸 사라짐. (타임리프에서 없앨 수도...)
            if (data.kind != 2) {
            isSetMenuInfo[i].style.display = 'none';
            }
            
            if (i + 1 == menuIdList.length) {
                setTimeout(total, 100);
            }
        } // writeMenuName() 끝.
    } // for문 끝.

    function total() {
        totalPriceInfo.innerHTML = totalPrice;
    }



// 수량 관련.
    // 수량 현황 보여주기
    for (let i = 0 ; i < cartList.length ; i++) {   
        menuQuantityInfo[i].setAttribute('value', cartList[i].quantity);
    }
    
    
// UPDATE
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
            
            // 가격 변동
            let parentLi = btn.closest('li');
            // console.log(parentLi);
            newEachPriceTotal(parentLi);
            //eachMenuPriceTotalInfo[i].innerHTML = (eachMenuPriceTotalVariable + sideExtraFee + drinkExtraFee) * cartList[i].quantity;
            //let lessPrice = Number(eachMenuPriceTotalInfo[index].innerHTML) - Number(menuPriceInfo[index].innerHTML);
            //eachMenuPriceTotalInfo[index].innerHTML = lessPrice;
        }
        }
        
    }));
    
    // 수량 버튼 만들기. (+)
    btnPlus.forEach((btn, index) => btn.addEventListener('click', function() {
        if  (menuQuantityInfo[index].value <= 9) {
            let cartListId = cartList[index].cartId;
            
            axios
            .post('/cart/moreQ/', cartListId)
            .then(response => {increaseQ(response.data)})
            .catch(err => {console.log(err)})
            
            function increaseQ(data) {
                menuQuantityInfo[index].value = Number(menuQuantityInfo[index].value) + 1;
            
            // 가격 변동
            let parentLi = btn.closest('li');
            // console.log(parentLi);
            newEachPriceTotal(parentLi);
            //let morePrice = Number(eachMenuPriceTotalInfo[index].innerHTML) + Number(menuPriceInfo[index].innerHTML);
            //eachMenuPriceTotalInfo[index].innerHTML = morePrice;
            }
        }
    }));
    
    // side 메뉴 변경
    sideMenuChange.forEach((radio) => radio.addEventListener('click', function() {
        let menuId = Number(radio.dataset.menuid);
        // console.log(menuId);
        let closetLi = radio.closest('.cartListInfo'); // 가까운 리스트 태그.
        let cartId = Number(closetLi.dataset.index);
        
        // console.log(cartId);
        
        axios
        .post('/cart/changeSideMenu/',null ,{params: {cartId: cartId, menuId: menuId}})
        .then(response => {changeSideMenu(response.data)})
        .catch(err => console.log(err));
        
        function changeSideMenu() {
            // console.log(radio);
            let sideMenu = radio.closest('.isSetMenuInfo');
            let childSideMenuInfo = sideMenu.querySelector('#sideMenuInfo');
            childSideMenuInfo.innerHTML = radio.dataset.menuname;
            let childExtraChargeInfo = sideMenu.querySelector('#extraChargeInfo');

            childExtraChargeInfo.innerHTML = radio.value;
            
            // 가격 변동
            let parentLi = radio.closest('.cartListInfo');
            // console.log(parentLi);
            newEachPriceTotal(parentLi);
            
        }
    }));
    
    // drink 메뉴 변경
    drinkMenuChange.forEach((radio) => radio.addEventListener('click', function() {
        let menuId = Number(radio.dataset.menuid);
        // console.log(menuId);
        let closetLi = radio.closest('.cartListInfo'); // 가까운 리스트 태그.
        let cartId = Number(closetLi.dataset.index);
        
        // console.log(cartId);
        
        axios
        .post('/cart/changeDrinkMenu/',null ,{params: {cartId: cartId, menuId: menuId}})
        .then(response => {changeDrinkMenu(response.data)})
        .catch(err => console.log(err));
        
        function changeDrinkMenu() {
            // console.log(radio);
            let drinkMenu = radio.closest('.isSetMenuInfo');
            let childDrinkMenuInfo = drinkMenu.querySelector('#drinkMenuInfo');
            childDrinkMenuInfo.innerHTML = radio.dataset.menuname;
            let childExtraChargeInfo2 = drinkMenu.querySelector('#extraChargeInfo2');

            childExtraChargeInfo2.innerHTML = radio.value;
            
            // 가격 변동
            let parentLi = radio.closest('.cartListInfo');
            // console.log(parentLi);
            newEachPriceTotal(parentLi);
        }
    }));
    

    // 개별 메뉴의 가격 합산하기
    // closest로?
    // text에 있는 값 가져와서 합치기.
    function newEachPriceTotal(parentLi) {
        let eachPrice = Number(parentLi.querySelector('#menuPriceInfo').innerHTML);
        let eachSide = Number(parentLi.querySelector('#extraChargeInfo').innerHTML);
        let eachDrink = Number(parentLi.querySelector('#extraChargeInfo2').innerHTML);
        let eachQuantity = parentLi.querySelector('#menuQuantityInfo').value;
        /*
        console.log('...');
        console.log(eachPrice);
        console.log(eachSide);
        console.log(eachDrink);
        console.log(eachQuantity);
        console.log(totalPrice);
        */
        parentLi.querySelector('#eachMenuPriceTotalInfo').innerHTML = (eachPrice + eachSide + eachDrink) * eachQuantity;
        
        newTotal();
    }
    
    function newTotal() {
        let newSum = 0;
        let eachMenuPriceTotalInfo2 = document.querySelectorAll('.eachMenuPriceTotalInfo');
        for (eachTotal of eachMenuPriceTotalInfo2) {
            
             console.log('새값:');
             console.log(eachTotal.innerHTML);
            newSum += Number(eachTotal.innerHTML);
        }
        totalPriceInfo.innerHTML = newSum;
    }
    
    // 삭제하기 버튼
    btnDelete.forEach((btn, index) => btn.addEventListener('click', function() {
        let cartId = cartList[index].cartId;
        console.log(cartId);
        
        axios
        .post('/cart/deleteCartItem/', null, {params: {cartId: cartId}})
        .then(response => {deleteItem()})
        .catch(err => console.log(err));
        
            function deleteItem() {
            let deleteList = btn.closest('.cartListInfo');
        
        
            deleteList.remove();
            newTotal();
        
    }
    }));
    

    

            // 사이드 변경 칸 불러오기.
            /*
            let kinds = [3, 4];
            let prices = {3: 2600, 4: 2200};
            let divListNames = {3: '.sideMenuList', 4: '.drinkMenuList'};
            
            for (let kind of kinds) {
                showMenuList(kind);
        
                function showMenuList(kind) {
                axios
                .get('/menu/all', {params: {kind}})
                .then(response => {updateMenuList(response.data)})
                .catch(err => {console.log(err)});
                } // showMenuList 끝.
        
        
                function updateMenuList(data) {
                    let divListName = divListNames[kind];
                    let price = prices[kind];
                    // 사이드메뉴
                    let divMenuList = document.querySelectorAll(divListName);
                
                    for (let i = 0; i < divMenuList.length; i++) {
                        let str = '';
                        for (let m of data) {
                            let surcharge = Number(m.price) - Number(price);
                            str += '<li style="display:inline-block;" id="sideChangeInfo" class="sideChangeInfo" data-menuId='
                                + m.menuId
                                + '>'
                                + '<label>'
                                + '<input type="radio" name="test"'
                                + '/>'
                                + '<img id="sideChangeInfo" class="sideChangeInfo" src="/image/display?fid='
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
                        
                        divMenuList[i].innerHTML = str;
                    } 
                } // updateMenuList 끝.
                
            } // for문 끝.*/        
    






});