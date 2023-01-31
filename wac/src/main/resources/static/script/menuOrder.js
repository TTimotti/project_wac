/**
 * menu.js
 * 추지훈
 */
window.addEventListener('DOMContentLoaded', function() {
    var kind = 1;
    showMenuList(kind); 
    const btnBuger = document.querySelector('#buger');
    const btnMeal = document.querySelector('#meal');
    const btnSide = document.querySelector('#side');
    const btnDrink = document.querySelector('#drink');
    const btnMorning = document.querySelector('#morning');
    const btnMorningMeal = document.querySelector('#morningMeal');
    
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

function showMenuList(kind) {
    axios
    .get('/menu/all', {params: {kind}})
    .then(response => { updateMenuList(response.data) })
    .catch(err => { console.log(err) });
}

function updateMenuList(data) {
    const divMenuList = document.querySelector('#menuList');  

    let str = '';
    for (let m of data) {
        str += '<li class="card my-5 col-3">'
        + '<a href="/order/menuOrder'
        + '">'
        + '<img src="/image/display?fid=' + m.image 
        + '" alt="메뉴 이미지" style="width:350px; height:200px;"/>'
        + '<div>' + m.menuName
        + '<p>' + m.content + '</p>'
        + '</div></a>'
        + '<div><form>'
        + `<a class="btnTossCart btn btn-success" id="btnTossCart" data-menuId="${ m.menuId }">`
        + '장바구니 추가'
        + '</button></form></div>'
        + '</li>';
    }
    divMenuList.innerHTML = str;
 
    const btnTossCart = document.querySelectorAll('.btnTossCart');

    btnTossCart.forEach(btn => {
        btn.addEventListener('click', tossCart);
    })
}    
    
function tossCart(data) {
    
    const menuId = data.target.getAttribute('data-menuId');
    console.log("카트로 넘어가는 메뉴", menuId);
    console.log("유저이름", loginUser);
    
    
    axios
    .get('/order/menuOrder', {params : {menuId, loginUser}})
    .then(response => { response.data })
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

