/**
 * member.js
 * /membet/signup.html
 */


window.addEventListener('DOMContentLoaded', function() {
    const userId = document.querySelector('#userId')
    const userIdValue = userId.value;
    const formPasswordChange = document.querySelector('#formPasswordChange');

    // 뒤로가기 버튼
    const btnCancel = document.querySelector('#btnCancel');
    btnCancel.addEventListener('click', function() {
        const result = confirm('뒤로 가시겠습니까?');
        if (result) {
            formPasswordChange.action = '/user/myPage?userId=' + userIdValue;
            formPasswordChange.method = 'post';
            formPasswordChange.submit();
        }

    });


    // 비밀번호 변경 버튼 TODO
    const btnPasswordChange = document.querySelector('#btnPasswordChange');
    btnPasswordChange.addEventListener('click', function() {
        const result = confirm('비밀번호를 변경하시겠습니까?');

        if (result) {
            formPasswordChange.action = '/user/passwordChange';
            formPasswordChange.method = 'post';
            formPasswordChange.submit();
        }

    })

    // 비밀번호 확인 기능 (입력한 비밀번호가 DB에 저장된 값과 일치하는지)
    const password = document.querySelector('#password');
    const pcheck = document.getElementById("pcheck");
    const pinput = document.getElementById("pinput");

    password.addEventListener('change', function() {
        if (password.value != '') {
            pinput.style.display = "none";

            if ((changePassword.value == changePasswordCheck.value)) {

            }

        } else {
            pinput.style.display = "block";
            btnDisable();
        }

        axios.get('/user/checkpw?password=' + password.value + '&userId=' + userIdValue)
            .then(response => { checkPwResult(response.data) })
            .catch(err => { console.log(err) });


    });

    // 입력한 비밀번호와 DB에 저장된 비밀번호가 일치하는지에 대한 결과
    function checkPwResult(data) {
        console.log(data)
        if (data == 'ok') {
            pcheck.style.display = "none";
            if ((changePassword.value == changePasswordCheck.value) & (changePassword.value != '')) {
                btnAble();
            }
        } else {
            pcheck.style.display = "block";
            btnDisable();
            if (password.value == '') {
                pcheck.style.display = "none";
                pinput.style.display = "block";
            }
        }
    }

    // 비밀번호 확인 기능 (입력한 두 비밀번호(변경하고자 하는 비밀번호)가 일치 하는지)
    const changePassword = document.querySelector('#changePassword');
    const changePasswordCheck = document.querySelector('#changePasswordCheck');
    const pokDiv = document.querySelector('#pok');
    const pnokDiv = document.querySelector('#pnok');

    changePasswordCheck.addEventListener('change', function() {

        if (changePassword.value == changePasswordCheck.value) {
            pokDiv.style.display = "block";
            pnokDiv.style.display = "none";

            if ((pokDiv.style.display == "block") & (pcheck.style.display == "none")) {
                btnAble();

            }

        } else {
            pokDiv.style.display = "none";
            pnokDiv.style.display = "block";
            btnDisable();
        }

    });


    function btnDisable() {
        btnPasswordChange.disabled = true;
    }

    function btnAble() {
        btnPasswordChange.disabled = false;
    }

});