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
		level: 6 // 지도의 확대 레벨
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
		level: 7 // 지도의 확대 레벨
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
				image: markerImage,
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
 * 현재 위치 마커 표시 기능
 */
function locationLoadSuccess(pos){
    // 현재 위치 받아오기
    var currentPos = new kakao.maps.LatLng(pos.coords.latitude,pos.coords.longitude);

    // 지도 이동(기존 위치와 가깝다면 부드럽게 이동)
    map.panTo(currentPos);

    // 마커 생성
    var marker = new kakao.maps.Marker({
        position: currentPos
    });

    // 기존에 마커가 있다면 제거
    marker.setMap(null);
    marker.setMap(map);
};

function locationLoadError(pos){
    alert('위치 정보를 가져오는데 실패했습니다.');
};

// 위치 가져오기 버튼 클릭시
function getCurrentPosBtn(){
    navigator.geolocation.getCurrentPosition(locationLoadSuccess,locationLoadError);
};

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


/**
 * 매장 목록 테이블 비동기 페이징 기능
 */

let totalData; // 총 데이터의 수
let dataPerPage; // 한 페이지에 나타낼 글 목록의 수
let pageCount = 3; // 페이징 목록에 나타낼 페이지의 수
let globalCurrentPage = 1; // 처음 나타낼 현재 페이지

let globalData;

storeViewList();

function storeViewList(){
	
	$(document).ready(function(){
		
		dataPerPage = 3;
		
		axios
		.get("/storeList")
		.then(response => {
			totalData = response.data.length - 1;
			globalData = response.data;
			displayData(1, dataPerPage, response.data)
			pasing(totalData, dataPerPage, pageCount, 1)			
		})
		.catch(err => { console.log(err) });
	});
}

const userId = document.querySelector(".loginUser").value;

function displayData(currentPage, dataPerPage, data) {

	let tableHtml = "";
	let i;

	// 스트링이 결합되는걸 방지하기위해 Number로 변환
	currentPage = Number(currentPage);
	dataPerPage = Number(dataPerPage);

	tableHtml += '<thead>'
		+ '<tr>'
		+ '<th>매장명</th>'
		+ '<th>전화번호</th>'
		+ '<th>주소</th>'
		+ '<th>영업시간</th>'
		+ '<th>이용가능 서비스</th>';
		if(userId == "admin") {
		tableHtml += '<th>관리자 메뉴</th>'
		}
		tableHtml += '</tr>'
		+ '</thead>'
		+ '<tbody>'

	for (i = (currentPage - 1) * dataPerPage; i < (currentPage - 1) * dataPerPage + dataPerPage; i++) {
		if (data[i] == undefined) {
			break;
		}

		tableHtml += '<tr class="storeInfo">'
			+ `<td class="storeName" 
			  	data-storeName="${data[i].storeName}" 
			  	data-storeAddress="${data[i].storeAddress}">` + data[i].storeName + '</td>'
			+ '<td>' + data[i].storePhone + '</td>'
			+ '<td>' + data[i].storeAddress + '</td>'
			+ '<td>' + data[i].storeTime + '</td>'
			+ '<td>' + data[i].drinkExplain + '</td>';
			if(userId == "admin") {
			tableHtml += '<td>' + `<button class="storeUpdate" onclick="location.href='store/storeDetail?storeId=${data[i].storeId}'">수정</button>` + '</td>'
			}
			tableHtml += '</tr>'
			+ '</tbody>';
		$('.storeTable').empty();
		$('.storeTable').append(tableHtml);
	}

}

function pasing(totalData, dataPerPage, pageCount, currentPage) {
	
	totalPage = Math.ceil(totalData / dataPerPage); // 총 페이지수
	
	if (totalPage < pageCount) {
		pageCount = totalPage;
	}
	let pageGroup = Math.ceil(currentPage / pageCount); // 페이지 그룹
	let last = pageGroup * pageCount; // 화면에 보여질 마지막 페이지 번호
	
	if(last > totalPage) {
		last = totalPage;
	}
	
	let first = last - (pageCount - 1); // 화면에 보여질 첫번째 페이지 번호
	let next = last + 1;
	let prev = first - 1;
	
	let storeTableNavHtml = "";
	
	if (prev > 0) {
		storeTableNavHtml += "<li class='prev'><a href='#' id='prev'>이전</a></li>";
	}
	
	// 페이징 목록 번호 표시
    for (var i = first; i <= last; i++) {
        if (currentPage == i) {
            storeTableNavHtml +=
                "<li class='on'><a href='#' id='" + i + "'>" + i + "</a></li>";
        } else {
            storeTableNavHtml += "<li><a href='#' id='" + i + "'>" + i + "</a></li>";
        }
    }

    if (last < totalPage) {
        storeTableNavHtml += "<li class='next'><a href='#' id='next'>다음</a></li>";
    }

	
	$("#storeTableNav").html(storeTableNavHtml);
	
	$("#storeTableNav li a").off().on('click',(function (event) {
		
		globalData;
		
		let $id = $(this).attr("id");
		let selectedPage = $(this).text();
		
		if ($id == "next") selectedPage = next;
		if ($id == "prev") selectedPage = prev;
		
		// 전역 변수에 선택한 페이지 번호를 넣음.
		currentPage = selectedPage;
		// 페이징 표시 기능 재호출
		pasing(totalData, dataPerPage, pageCount, selectedPage);
		// 매장 목록 테이블 표시 재호출
		displayData(selectedPage, dataPerPage, globalData);
		
		// 마우스 스크롤 동작 제한 기능
		event.preventDefault();
		event.stopPropagation();
		return false;
	}));
} 	
	