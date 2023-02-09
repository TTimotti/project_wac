/**
 * postDetail.js :: post/detail.html
 * @author 김지훈
 */
const formDelete = document.querySelector('#formDelete');

// 삭제 이벤트 리스너.
const btnDelete = document.querySelector('#btnDelete');
btnDelete.addEventListener('click', function () {
    const result = confirm('정말 삭제하시겠습니까?');
    if (result) {
        formDelete.action = '/post/delete';
        formDelete.method = 'post';
        formDelete.submit();
    }
});
