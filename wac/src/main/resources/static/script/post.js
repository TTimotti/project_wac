/**
 * post.js
 * 추지훈
 */
window.addEventListener('DOMContentLoaded', function() {
    var kind = 1;
    showPostList(kind); 
    const btnPromotion = document.querySelector('#promotion');
    const btnNews = document.querySelector('#news');
    
    console.log(btnPromotion);
    btnPromotion.addEventListener("click", () => {
        kind = 1;
        console.log(kind);
        showPostList(kind);       
    });
    
    console.log(btnNews);
    btnNews.addEventListener("click", () => {
        kind = 2;
        console.log(kind);
        showPostList(kind);       
    });
    

function showPostList(kind) {
    axios
    .get('/post/all', {params: {kind}})
    .then(response => { updatePostList(response.data) })
    .catch(err => { console.log(err) });
}

function updatePostList(data) {
    const divPostList = document.querySelector('#posts');  

    let str = '';
    for (let p of data) {
        str += '<li class="card my-5 col-3">'
        + '<a href="/">' /* 여기 링크에 상세페이지 달기 */
        + '<div class="card col-3 my-5">' + p.title
        + '<p>' + p.content + '</p>'
        + '</div></a>'
        + '</li>';
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

