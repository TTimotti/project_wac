/**
 * member.js
 * /membet/signup.html
 */


window.addEventListener('DOMContentLoaded', function() {
    // 뒤로가기 버튼
    const btnCancel = document.querySelector('#btnCancel');
    btnCancel.addEventListener('click', function() {
        const result = confirm('뒤로 가시겠습니까?');
        if (result) {
            formUpdate.action = '/user/myPage?userId=' + userIdValue;
            formUpdate.method = 'POST';
            formUpdate.submit();
        }

    });

    // 수정 완료 버튼 찾아서 이벤트 리스너 등록 TODO
    const btnUpdate = document.querySelector('#btnUpdate');
    btnUpdate.addEventListener('click', function() {
        const result = confirm('정말 수정?');
        if (result) {
            formUpdate.action = '/user/update';
            formUpdate.method = 'post';
            formUpdate.submit();

        }
    })

    // 회원 탈퇴 버튼
    const btnDelete = document.querySelector('#btnDeleteUser');
    btnDelete.addEventListener('click', function() {
        const result = confirm('정말 삭제?');
        if (result) {
            formUpdate.action = '/user/delete';
            formUpdate.method = 'post';
            formUpdate.submit();
        }
    });



    // 비밀번호 변경 버튼
    const btnPasswordChange = document.querySelector('#btnPasswordChange');
    btnPasswordChange.addEventListener('click', function() {
        const result = confirm('비밀번호 변경?');
        if (result) {
            formUpdate.action = '/user/passwordChange';
            formUpdate.method = 'get';
            formUpdate.submit();
        }
    })

});