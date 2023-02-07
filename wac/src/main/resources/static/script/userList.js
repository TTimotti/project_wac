showUserList()

function showUserList() {
    axios
    .get('/admin/users')
    .then(response => { updateList(response.data) })
    .catch(err => { console.log(err) });
}

// 메뉴 그려주는 함수
function updateList(data) {
    const userTable = document.querySelector('#tbody');  

    let str = '';
    for (let i of data) {
		if (i.userName=='admin') {continue};		
        str += '<tr class="text-center">'
        + '<th>'
        + i.userId /* 여기 링크에 상세페이지 달기 */
        + '</th><th>'
        + i.userName
        + '</th><th>'
        + i.phone
        + '</th><th>'
        + i.email
        + '</th><th>'
        + i.address
        + '</th><th>'
        + i.age
        + '</th><th>'
        + i.gender
        + '</th></tr>';
        
    }
    userTable.innerHTML = str;

}
