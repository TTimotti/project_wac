/**
 * 
 */
 
window.addEventListener('DOMContentLoaded', function() {
 /**
    메뉴 하단에 있는 탭들을 펼치고 닫는 기능.
  */
const toggle = document.querySelectorAll('.toggle > button');
// 배열이니까 toggle[i]도 가능함.
console.log(toggle);
for (let i = 0 ; i < toggle.length; i++) {
    toggle[i].addEventListener("click", function() {
        const btn = '#toggle0' + (i+2);
        const toggle2 = document.querySelector(btn);

        let toggleDisplay = toggle2.style.display;
            if (toggleDisplay=='none' || toggleDisplay =='') {
                toggle2.style.display = 'block';
                toggle[i].ariaSelected='true';
                toggle[i].ariaExpanded='true';
            } else {
                toggle2.style.display ='none';
                toggle[i].ariaSelected='false';
                toggle[i].ariaExpanded='false';
            }
    });
}

});