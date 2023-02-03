/**
 * 카트창 페이지...
 * 서범수
 */
window.addEventListener('DOMContentLoaded', function() {
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
    
    console.log(menuIdList);
    
    // menuIdList의 있는 menuId를 통해 메뉴 정보 가져오기.
    for (let i = 0 ; i < menuIdList.length ; i++) {
        menuId = menuIdList[i];
        console.log(menuId);

        axios
        .get('/cart/toss/' + menuId)
        .then(response => {writeMenuName(response.data)})
        .catch(err => {console.log(err)});     
        
        function writeMenuName(data) {
            const menuNameInfo = document.querySelectorAll('.menuNameInfo');
            const menuPriceInfo = document.querySelectorAll('.menuPriceInfo');
            const menuImageInfo = document.querySelectorAll('.menuImageInfo');
            const eachMenuPriceTotalInfo = document.querySelectorAll('.eachMenuPriceTotalInfo');
            // 세트 메뉴가 아니라면, 사이드 변경 칸 사라짐.
            const isSetMenuInfo = document.querySelectorAll('.isSetMenuInfo');
            
            let eachMenuPriceTotalVariable= data.price;
          
            menuNameInfo[i].innerHTML = data.menuName;
            menuPriceInfo[i].innerHTML = data.price;
            menuImageInfo[i].src = "/image/display?fid=" + data.image;
            eachMenuPriceTotalInfo[i].innerHTML = eachMenuPriceTotalVariable;
            if (data.kind != 2) {
            isSetMenuInfo[i].style.display = 'none';
            
            
            }
    }   
    }
    
    
    // 수량 버튼
    // 수량 현황
    for (let i = 0 ; i < cartList.length ; i++) {
        
        const menuQuantityInfo = document.querySelectorAll('.menuQuantityInfo');
        console.log("cartList[i]" + i +'번 째:' + cartList[i].cartId);
        console.log(cartList[i].quantity);
        menuQuantityInfo[i].setAttribute('value', cartList[i].quantity);
    
    }
    
    const btnMinus = document.querySelectorAll('.btn_minus');

    btnMinus.forEach(btn => btn.addEventListener('click', console.log('dd')));
    
    
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
        
    






});