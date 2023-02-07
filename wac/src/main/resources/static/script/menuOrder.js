/**
 * menu.js
 * 추지훈
 */
window.addEventListener('DOMContentLoaded', function() {
    var kind = 1; // 오더들어가면 기본으로 버거메뉴가 뿌려짐 ,  버거: 1
    showMenuList(kind); 
    const btnBuger = document.querySelector('#buger');
    const btnMeal = document.querySelector('#meal');
    const btnSide = document.querySelector('#side');
    const btnDrink = document.querySelector('#drink');
    const btnMorning = document.querySelector('#morning');
    const btnMorningMeal = document.querySelector('#morningMeal');
    
    console.log(storeName);
    console.log(userAddress);
    console.log(btnBuger);
    btnBuger.addEventListener("click", () => {
        kind = 1;
        console.log(kind);
        showMenuList(kind);       
    });
    
    console.log(btnMeal);
    btnMeal.addEventListener("click", () => {
        kind = 2;
        console.log(kind);
        showMenuList(kind);       
    });
    
    console.log(btnSide);
    btnSide.addEventListener("click", () => {
        kind = 3;
        console.log(kind);
        showMenuList(kind);       
    });
    
    console.log(btnDrink);
    btnDrink.addEventListener("click", () => {
        kind = 4;
        console.log(kind);
        showMenuList(kind);       
    });
    
    console.log(btnMorning);
    btnMorning.addEventListener("click", () => {
        kind = 5;
        console.log(kind);
        showMenuList(kind);       
    });
    
    console.log(btnMorningMeal);
    btnMorningMeal.addEventListener("click", () => {
        kind = 6;
        console.log(kind);
        showMenuList(kind);       
    });

// 메뉴뿌려주는 함수
function showMenuList(kind) {
    axios
    .get('/menu/all', {params: {kind}})
    .then(response => { updateMenuList(response.data) })
    .catch(err => { console.log(err) });
}

// 메뉴 그려주는 함수
function updateMenuList(data) {
    const divMenuList = document.querySelector('#menuList');  

    let str = '';
    for (let m of data) {
        str += '<li class="card my-5 col-3 float-left" style="margin: 0px 0px 55px 35px">'
        + '<a href="/order/cart'
        + '">'
        + '<img src="/image/display?fid=' + m.image 
        + '" alt="메뉴 이미지" style="width:250px; height:200px;"/>'
        + '<div>' + m.menuName
        + '<p>' + m.menuEnName + '</p>'
        + '</div></a>'
        + '<div><form>'
        + `<a class="btnTossCart btn btn-success" id="btnTossCart" data-menuId="${ m.menuId }" data-storeName="${ storeName }" data-userAddress="${ userAddress }">`
        + '장바구니 추가'
        + '</button></form></div>'
        + '</li>';
    }
    divMenuList.innerHTML = str;
 
    const btnTossCart = document.querySelectorAll('.btnTossCart');

    btnTossCart.forEach(btn => {
        btn.addEventListener('click', tossCartModal);
    })

}    
  
// 장바구니 추가 함수  
function tossCartModal(event) {
    
    const data_menuId = event.target.getAttribute('data-menuId');
    const data_storeName = event.target.getAttribute('data-storeName');
    const data_userAddress = event.target.getAttribute('data-userAddress');
    console.log(storeName);
    console.log("카트로 넘어가는 메뉴", data_menuId);
    console.log("유저이름", loginUser);
    
    btnTossCart.href = "/order/cart?userName=" + loginUser;
    
    const data = {
            menuId: data_menuId,
            userName: loginUser,
            storeName: data_storeName,
            userAddress: data_userAddress
        }
    
     axios
    .post('/cart/create/', data )
    .then(response => { 
        response.data; 
        
        })
    .catch(err => { console.log(err)});   
};

});





/*
let totalData;
let dataPerPage;
let pageCount = 4;
let globalCurrentPage = 1;

var noticeMenuGlobalData;

function showMenuList() {
    const foodId = document.querySelector('#foodId').value;

    $(documnet).ready(function() {
        dataPerPage = 8;

        axios
        .get("/menu/menuListView")
        .then(response => {
            console.log(response.data);
            totalData = response.data.length -1;
            noticeMenuGlobalData = response.data;

            noticeDisplayData(1, dataPerPage, response.data)
            noticeMenuPaging(totalData, dataPerPage, pageCount, 1)
            })
        .catch(err => { console.log(err) });
    });

}

function noticeDisplayData(currentPage, dataPerPage, data) {
    let chartHtml = "";
    var i;
    
    currentPage = Number(currentPage);
    dataPerPage = Number(dataPerPage);

    chartHtml += 

        if ()



}
*/

