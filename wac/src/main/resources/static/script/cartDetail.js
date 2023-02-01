/**
 * 
 */
window.addEventListener('DOMContentLoaded', function() {
    var kind = 3;
    
    showMenuList(kind);
    
    function showMenuList(kind) {
        axios
        .get('/menu/all', {params: {kind}})
        .then(response => {updateMenuList(response.data)})
        .catch(err => {console.log(err)});
    }
    
    function updateMenuList(data) {
        const divSideMenuList = document.querySelector('#sideMenuList');
        
        let str = '';
        for (let m of data) {
            str += '<li class="card my-5 col-3">'
                + '<img src="/image/display?fid='
                + m.image
                + '" alt="사이드 이미지" style="width:350px; height: 200px;" />'
                + '<div>'
                + m.menuName
                + '</div>'
                + '</li>';
        }
        divSideMenuList.innerHTML = str;
    }
});