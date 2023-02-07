/**
 * 
 */
 
 window.addEventListener('DOMContentLoaded', function() {
   
   const menuNameInput = document.querySelector('#menuName');
   const kindInput = document.querySelector('#kind');
   const priceInput = document.querySelector('#price');
   const contentInput = document.querySelector('#content');
   const imageInput = document.querySelector('#image');
   const btnCreateMenu = document.querySelector('#btnSubmit');
   const formCreateMenu = document.querySelector('#formCreateMenu');
   
   btnCreateMenu.addEventListener('click', function() {
    const menuName = menuNameInput.value;
    const kind = kindInput.value;
    const price = priceInput.value;
    const content = contentInput.value;
    const image = imageInput.value;
    
    if (menuName.length == 0 || kind.length == 0 || price.length == 0 || content.length == 0) {
        alert("모두 작성해주세요.");
    } else {
        const result = confirm('생성하시겠습니까');
        if (result) {
            formCreateMenu.action = '/menu/create';
            formCreateMenu.method = 'post';
            formCreateMenu.submit();
            
            alert("추가됨.")
        }
    }
      
})

let img = document.querySelector('input#image'); 
         console.log(img);
         
         function readImage(input) {
                // 인풋 태그에 파일이 있는 경우
                if(input.files && input.files[0]) {
                    // 이미지 파일인지 검사 (생략)
                    // FileReader 인스턴스 생성
                    const reader = new FileReader();
                    // 이미지가 로드가 된 경우
                    reader.onload = e => {
                        const previewImage = document.querySelector('img#menuImage');
                        previewImage.setAttribute('src', e.target.result);
                    }
                    // reader가 이미지 읽도록 하기
                    reader.readAsDataURL(input.files[0]);
                }
            }
            // input file에 change 이벤트 부여

            img.addEventListener("change", e => {
                console.log('change');
                readImage(e.target);
            })
   
   
   
    
});