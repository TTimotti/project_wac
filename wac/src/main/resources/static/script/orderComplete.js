/**
 * order.js
 * 추지훈
 */
window.addEventListener('DOMContentLoaded', function() {
    const userName = loginUser;
    console.log("유저이름", userName);
    showOrderList(userName);
    
    
   
function showOrderList(userName) {
    axios
    .get('/order/all', {params: {userName}})
    .then(response => { updateOrderList(response.data) })
    .catch(err => { console.log(err) });
}

function updateOrderList(data) {
    const divPostList = document.querySelector('#orders');  


    let str = '';
    for (let o of data) {
        str +='<li class="card my-5 col-3 notice-2">' 
        +'<a class="notice" href="/order/orderCompleteDetail?orderId='+ o.orderId +'">'
        + '<div id="orderId">' + o.createdTime + '</div>'
        + '<div id="userName"><strong>' + o.storeName + o.address + '</strong></div>'
        + '<div id="createdTime">' + o.totalPrice + '</div>'
        + '</a>'
        + '</li>';
        console.log("주문 번호", o.orderId);
    }
    divPostList.innerHTML = str;
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

