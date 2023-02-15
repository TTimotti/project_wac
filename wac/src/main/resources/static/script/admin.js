/*!
* 추지훈
*/

window.addEventListener('DOMContentLoaded', event => {


showOrderList()
    // Activate Bootstrap scrollspy on the main nav element
    const mainNav = document.body.querySelector('#mainNav');
    if (mainNav) {
        new bootstrap.ScrollSpy(document.body, {
            target: '#mainNav',
            offset: 74,
        });
    };

    // Collapse responsive navbar when toggler is visible
    const navbarToggler = document.body.querySelector('.navbar-toggler');
    const responsiveNavItems = [].slice.call(
        document.querySelectorAll('#navbarResponsive .nav-link')
    );
    responsiveNavItems.map(function (responsiveNavItem) {
        responsiveNavItem.addEventListener('click', () => {
            if (window.getComputedStyle(navbarToggler).display !== 'none') {
                navbarToggler.click();
            }
        });
    });


function showOrderList() {
    axios
    .get('/admin/orders')
    .then(response => { updateList(response.data) })
    .catch(err => { console.log(err) });
}

// 메뉴 그려주는 함수
function updateList(data) {
    const userTable = document.querySelector('#tbody');  

    let str = '';
    for (let i of data) {
        str += '<tr class="text-center">'
        + '<th>'
        + i.userId
        + '</th><th>'
        + i.storeName
        + '</th><th>'
        + i.address
        + '</th><th>'
        + i.totalPrice
        + '</th><th>'
        + i.createdTime
        + '</th></tr>';
        
    }
    userTable.innerHTML = str;

}





});
