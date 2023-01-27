/**
 * user/myPage page script
 */


    
window.addEventListener('DOMContentLoaded', function() {

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


});