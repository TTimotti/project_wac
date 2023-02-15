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
    
    // 비동기 메뉴카테고리 선택
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
    
// 카테고리별 메뉴 불러오기
function showMenuList(kind) {
    axios
    .get('/menu/all', {params: {kind}})
    .then(response => { updateMenuList(response.data) })
    .catch(err => { console.log(err) });
}

// 메뉴 그려주기
function updateMenuList(data) {
    const divMenuList = document.querySelector('#menuList');
    const sell = "y";
    let str = '';
    for (let m of data) {
        // if (m.sellyn == sell) {
            str += '<li class="card my-5 col-3 float-left">'
            + '<a href="/menu/menuDetail?kind='
            + kind
            + '&menuId='
            + m.menuId /* 여기 링크에 상세페이지 달기 */
            + '">'
            + '<img src="/image/display?fid=' + m.image 
            + '" alt="메뉴 이미지" style="width:250px; height:200px;"/>'
            + '<div><strong class="ko">' + m.menuName
            + '</strong><p class="en">' + m.menuEnName + '</p>'
            + '</div></a>'
            
        //} else {
        //    str += '<li class="card my-5 col-3 float-left">'
        //}
        + '</li>';
        
    }
    divMenuList.innerHTML = str;
}


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

