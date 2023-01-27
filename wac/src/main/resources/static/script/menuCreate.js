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
   
   
   
   
    
});