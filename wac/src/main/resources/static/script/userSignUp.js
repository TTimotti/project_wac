/**
 * user/signUp page script
 */

// 비밀번호 확인 (입력한 두 값이 일치하는지)

window.addEventListener('DOMContentLoaded', function() {
    const password = document.querySelector('#userPassword');
    const passwordCheck = document.querySelector('#userPasswordCheck');

    const pokDiv = document.querySelector('#pok');
    const pnokDiv = document.querySelector('#pnok');

    const btnCreate = document.getElementById('btnCreate')

    passwordCheck.addEventListener('change', function() {

        if (password.value == passwordCheck.value) {
            pokDiv.style.display = "block";
            pnokDiv.style.display = "none";
            btnCreate.disabled = false;

            if (idnokDiv.style.display == "block") {
                btnCreate.disabled = true;
            }


        } else {
            pokDiv.style.display = "none";
            pnokDiv.style.display = "block";
            btnCreate.disabled = true;
        }

    });

    password.addEventListener('change', function() {
        if (password.value == passwordCheck.value) {
            pokDiv.style.display = "block";
            pnokDiv.style.display = "none";
            btnCreate.disabled = false;

            if (idnokDiv.style.display == "block") {
                btnCreate.disabled = true;
            }

        } else {
            pokDiv.style.display = "none";
            pnokDiv.style.display = "block";
            btnCreate.disabled = true;
        }

    });

    // 아이디 중복확인 
    const usernameInput = document.querySelector('#userName');
    const idokDiv = document.querySelector('#idok');
    const idnokDiv = document.querySelector('#idnok');

    usernameInput.addEventListener('change', function() {
        const userName = usernameInput.value;

        axios.get('/user/checkid?userName=' + userName)
            .then(response => { displayCheckResult(response.data) })
            .catch(err => { console.log(err); });
    });

    function displayCheckResult(data) {
        if (data == 'ok') {
            idokDiv.style.display = "block";
            idnokDiv.style.display = "none";
            btnCreate.disabled = false;

            if (password.value != passwordCheck.value) {
                btnCreate.disabled = true;
            }

        } else {

            idokDiv.style.display = "none";
            idnokDiv.style.display = "block";
            btnCreate.disabled = true;

        }

    }

    // 주소 찾기 기능
    const btnAddress = document.querySelector('#btnAddress');

    btnAddress.addEventListener('click', function() {

        new daum.Postcode({
            oncomplete: function(data) {
                document.getElementById("address").value = data.address; // 주소 넣기
                document.querySelector('#address2').focus(); //상세입력 포커싱
            }

        }).open();
    });




});