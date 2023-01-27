/*
header.js / script
*/

//배너 이미지 관련
const bannerImage = document.querySelector('#bannerImg');
const banner = document.querySelector('.bImg');
const prevBtn = document.querySelector('#prevBtn');
const nextBtn = document.querySelector('#nextBtn');
let i = 1;

window.onload = ()=>{
	bannerImage.setAttribute('src', '/img/banner/' + i + '.jpg');
}
//3초마다 이미지 변경
setInterval(() => {
	i += 1;	
	if (i == 5) {i = 1} 
	bannerImage.setAttribute('src', '/img/banner/' + i + '.jpg');
}, 3000);
//이미지 넘기기 버튼
nextBtn.addEventListener("click", () => {		
	i += 1;	
	if (i == 5) {i = 1} 
	bannerImage.setAttribute('src', '/img/banner/' + i + '.jpg');
});

prevBtn.addEventListener("click", () => {
	i -= 1;
	if (i == 0) {i = 4;}		
	bannerImage.setAttribute('src', '/img/banner/' + i + '.jpg');
	
});



 
   
