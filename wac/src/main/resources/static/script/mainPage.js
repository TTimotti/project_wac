/*
header.js / script
*/

const bannerImage = document.querySelector('#bannerImg');
const banner = document.querySelector('.bImg');
const prevBtn = document.querySelector('#prevBtn');
const nextBtn = document.querySelector('#nextBtn');
let i = 0;
nextBtn.addEventListener("click", () => {		
	if (i == 0) {
		i = 1;
	}
	i += 1;	
	if (i == 5) {
		i = 1;	
	} 
	bannerImage.setAttribute('src', '/img/banner/' + i + '.jpg');
});

prevBtn.addEventListener("click", () => {
	if (i == 0) {
		i = 5;
	}
	i -= 1;
	if (i == 0) {
		i = 4;
	}		
	bannerImage.setAttribute('src', '/img/banner/' + i + '.jpg');
	
});    
   
