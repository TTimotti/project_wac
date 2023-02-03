/**
 * 생성자 장민석
 */

 const formDetailStore = document.querySelector('#formDetailStore');

// 삭제 이벤트 리스너.
const btnDelete = document.querySelector('.dbuttons');
btnDelete.addEventListener('click', function () {
    const result = confirm('정말 삭제하시겠습니까?');
    if (result) {
        formDetailStore.action = '/store/storeDelete';
        formDetailStore.method = 'post';
        formDetailStore.submit();
    }
});