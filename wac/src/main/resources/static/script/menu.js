/**
 * menu.js
 * 추지훈
 */
window.addEventListener('DOMContentLoaded', () => {
    var kind;
    
    const btnBuger = document.querySelector('#buger').value;
    const btnMeal = document.querySelector('#meal').value;
    const btnSide = document.querySelector('#side').value;
    const btnDrink = document.querySelector('#drink').value;
    const btnMorning = document.querySelector('#morning').value;
    const btnMorningMeal = document.querySelector('#morningMeal').value;
    
    btnBuger.addEventListener('click')
    
    showMenuList(kind);
    
      

function showMenuList(kind) {
    

if (kind == 1) {
   
} else {
    
}

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
        + '<a href="/">' /* 여기 링크에 상세페이지 달기 */
        + '<div class="card col-3 my-5">' + m.menuName
        + '<p>' + m.menuId + '</p>'
        + '</div></a>'
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

