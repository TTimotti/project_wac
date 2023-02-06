/**
 *  Store 자바 스크립트 파일
 *  생성자 - 장민석
 */


/**
 *  카카오 맵 구현.
 */
var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
	mapOption = {
		center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		level: 4 // 지도의 확대 레벨
	};

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
var mapTypeControl = new kakao.maps.MapTypeControl();

// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
var zoomControl = new kakao.maps.ZoomControl();
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = {
		center: new kakao.maps.LatLng(37.54699, 127.09598), // 지도의 중심좌표
		level: 9 // 지도의 확대 레벨
	};


// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();
var imageSrc = 'https://cdn.discordapp.com/attachments/685937478435471438/1065524257003683920/wac.png', // 마커이미지의 주소입니다    
    imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);


	axios
	.get("/storeList")
	.then(response => {
		for (let data of response.data ){
			mapViewMarker(data)}
		})
	.catch(err => { console.log(err) });

/**
 * 반복문을 쓰기위한 마커표시 기능
 */
function mapViewMarker(data) {
		
	geocoder.addressSearch(data.storeAddress, function(result, status) {

		// 정상적으로 검색이 완료됐으면 
		if (status === kakao.maps.services.Status.OK) {

			var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

			// 결과값으로 받은 위치를 마커로 표시합니다
			var marker = new kakao.maps.Marker({
				map: map,
				position: coords,
				image: markerImage
			});

			// 인포윈도우로 장소에 대한 설명을 표시합니다
			var infowindow = new kakao.maps.InfoWindow({
				content: '<span class="info-marker">' + data.storeName + '</span>'
				
			});
			infowindow.open(map, marker);

			// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			map.setCenter(coords);
		}
	});
}

/** 
 * 주문 매장 선택시 맵에서 주문매장 마커로 위치이동해주는 기능.
 */
function mapViewMarker2(data) {
	
	geocoder.addressSearch(data[1], function(result, status) {
		
		// 정상적으로 검색이 완료됐으면 
		if (status === kakao.maps.services.Status.OK) {

			var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

			// 결과값으로 받은 위치를 마커로 표시합니다
			var marker = new kakao.maps.Marker({
				map: map,
				position: coords,
				image: markerImage
			});

			// 인포윈도우로 장소에 대한 설명을 표시합니다
			var infowindow = new kakao.maps.InfoWindow({
				content: '<span class="info-marker">' + data[0]+ '</span>'

			});
			infowindow.open(map, marker);

			// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			map.setCenter(coords);
		}
		else {
			console.log('error')
		}
	});
}
/**
 * 지점명 선택해서 선택한 지점 데이터 표시.
 */

$(document).on("click",".storeInfo .storeName", function(event){
	let storeInfo = [];
	const storeName = event.target.getAttribute('data-storeName');
	const storeAddress = event.target.getAttribute('data-storeAddress');
	
	storeInfo.push(storeName);
	storeInfo.push(storeAddress);
	
	$('.dInfo').val(storeName);
	$('.dInfo2').val(storeAddress);
	
	document.querySelector('.storeInfo').value;

	mapViewMarker2(storeInfo);
});

// 주소 찾기 기능
const btnAddress = document.querySelector('.buttonAddress');

btnAddress.addEventListener('click', function() {

	new daum.Postcode({
		oncomplete: function(data) {
			document.getElementById("userAddress").value = data.address; // 주소 넣기
			document.getElementById("userAddress2").focus(); //상세입력 포커싱
		}
	}).open();
});

$(document).on("click", ".nextPage", function() {

	const storeName = document.querySelector(".dInfo").value;
	const userAddressf = document.getElementById("userAddress").value
		+ document.getElementById("userAddress2").value;
			
	if (storeName == "" || userAddressf == "") {

		alert("정확히 입력 부탁드립니다.");

	} else {
		$('.storeName').val(storeName);
		$('.userAddressf').val(userAddressf);

		document.value.action = "/order"
		
		document.value.submit()
	}
});

