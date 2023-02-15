/**
 * 
 */

// 주소 찾기 기능
const btnAddress = document.querySelector('#btnAddress');

btnAddress.addEventListener('click', function() {

	new daum.Postcode({
		oncomplete: function(data) {
			document.getElementById("storeAddress").value = data.address; // 주소 넣기
			document.querySelector('#storeAddress2').focus(); //상세입력 포커싱
		}
	}).open();
});


// 전화번호 양식
function oninputPhone(target) {
    target.value = target.value
        .replace(/[^0-9]/g, '')
        .replace(/(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/g, "$1-$2-$3");
}

const loginUser = document.querySelector(".loginUser").value;

if (loginUser != "admin"){
	
	alert("관리자만 접근이 가능 합니다.");
	
	location.href = "/user/signIn"
	
}