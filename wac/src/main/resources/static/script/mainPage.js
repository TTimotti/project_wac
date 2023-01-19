/*
header.js / script
*/

const bannerImage = document.querySelector('#bannerImg');
const banner = document.querySelector('.bImg');
const prevBtn = document.querySelector('#prevBtn');
const nextBtn = document.querySelector('#nextBtn');
nextBtn.addEventListener("click", () => {
	console.log('bannerImageChange')
	bannerImage.setAttribute('src', '/img/banner/1.jpg')
});

prevBtn.addEventListener("click", () => {
	console.log('bannerImageChange')
	bannerImage.setAttribute('src', '/img/banner/2.jpg')
});    
    