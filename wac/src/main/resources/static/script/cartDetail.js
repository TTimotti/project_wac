/**
 * 카트창 페이지...
 * 서범수
 */
window.addEventListener('DOMContentLoaded', function() {
    let kinds = [3, 4];
    let prices = {3: 2600, 4: 2200};
    let divListNames = {3: '#sideMenuList', 4: '#drinkMenuList'};
    
    for (let kind of kinds) {
        console.log('for문:' + kind);
        showMenuList(kind);
        
        function showMenuList(kind) {
        console.log('showMenuList: ' + kind);
        axios
        .get('/menu/all', {params: {kind}})
        .then(response => {updateMenuList(response.data)})
        .catch(err => {console.log(err)});
        } // showMenuList 끝.
        
        function updateMenuList(data) {
        console.log('updateMenuList: '+ kind);
        let divListName = divListNames[kind];
        let price = prices[kind];
        let divMenuList = document.querySelector(divListName);
        
        let str = '';
        for (let m of data) {
            let surcharge = Number(m.price) - Number(price);
            str += '<li style="display:inline-block;">'
                + '</div>'
                + '<label>'
                + '<input type="radio" name="test"/>'
                + '<img src="/image/display?fid='
                + m.image
                + '" alt="사이드 이미지" style="width:350px; height: 200px;" />'
                + '</label>'
                + '<div style="text-align: center; margin: 10px 0;">'
                + m.menuName
                + '</div>'
                + '<div style="text-align: center; margin: 10px 0;">'
                + '+'
                + surcharge
                + '원'
                + '</li>';
        }
        console.log(str);
        divMenuList.innerHTML = str;
        } // updateMenuList 끝.   
    }
        




});