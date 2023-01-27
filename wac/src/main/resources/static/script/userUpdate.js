/**
 * member.js
 * /membet/signup.html
 */


window.addEventListener('DOMContentLoaded', function() {
    const userId = document.querySelector('#userId');
    const userIdValue = userId.value;
    const formUpdate = document.querySelector('#formUpdate');
    
    // 뒤로가기 버튼
    const btnCancel = document.querySelector('#btnCancel');
    btnCancel.addEventListener('click', function() {
        const result = confirm('뒤로 가시겠습니까?');
        if (result) {
            formUpdate.action = '/user/myPage?userId=' + userIdValue;
            formUpdate.method = 'post';
            formUpdate.submit();
        }

    });

    // 수정 완료 버튼
    const btnUpdate = document.querySelector('#btnUpdate');
    btnUpdate.addEventListener('click', function() {
        const result = confirm('입력한 값으로 개인정보를 변경하겠습니까?');

        if (result) {
            formUpdate.action = '/user/update';
            formUpdate.method = 'post';
            formUpdate.submit();

        }
    })

    // 회원 탈퇴 버튼
    const btnDelete = document.querySelector('#btnDeleteUser');
    btnDelete.addEventListener('click', function() {
        const result = confirm('계정을 삭제하시겠습니까?');
        if (result) {
            formUpdate.action = '/user/delete';
            formUpdate.method = 'post';
            formUpdate.submit();
        }
    });



    // 비밀번호 변경 버튼
    const btnPasswordChange = document.querySelector('#btnPasswordChange');
    btnPasswordChange.addEventListener('click', function() {

    })

    // 비밀번호 확인 기능 (입력한 비밀번호가 DB에 저장된 값과 일치하는지)
    const password = document.querySelector('#password');
    const passwordCheck = document.querySelector('#passwordCheck');
    const pcheck = document.getElementById("pcheck");
    const pinput = document.getElementById("pinput");

    password.addEventListener('change', function() {
        if (password.value != '') {
            pinput.style.display = "none";

            if (password.value != passwordCheck.value) {
                pcheck.style.display = "block";
                btnDisable();
            }


        } else {
            pinput.style.display = "block";
            btnDisable();
        }

        axios.get('/user/checkpw?password=' + password.value + '&userId=' + userIdValue)
            .then(response => { checkPwResult(response.data) })
            .catch(err => { console.log(err) });


    });

    // 비밀번호 확인 기능 (입력한 두 비밀번호가 일치 하는지)
    const pokDiv = document.querySelector('#pok');
    const pnokDiv = document.querySelector('#pnok');

    passwordCheck.addEventListener('change', function() {

        if (password.value == passwordCheck.value) {
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

    // 입력한 비밀번호와 DB에 저장된 비밀번호가 일치하는지에 대한 결과
    function checkPwResult(data) {
        console.log(data)
        if (data == 'ok') {
            pcheck.style.display = "none";
            if ((password.value == passwordCheck.value) & (password.value != "")) {
                pok.style.display = "block";
                pnok.style.display = "none";
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
    
    // 버튼 활성화 함수
    function btnDisable() {
        btnUpdate.disabled = true;
        btnDelete.disabled = true;
        btnPasswordChange.disabled = true;
    }

    // 버튼 비활성화 함수
    function btnAble() {
        btnUpdate.disabled = false;
        btnDelete.disabled = false;
        btnPasswordChange.disabled = false;
    }
    
    // 로그인 유저 체크
    const ifDifferent = document.querySelector('#ifDifferent');
    const ifSame = document.querySelector('#ifSame');
    
    console.log(loginUser);
    console.log(userName);
    
        if(userName == loginUser) {
            ifSame.style.display="block";
            
        } else{
            ifDifferent.style.display="block";
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