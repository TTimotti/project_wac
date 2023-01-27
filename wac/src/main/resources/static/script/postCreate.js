/**
 * 
 */
 
 window.addEventListener('DOMContentLoaded', function() {
   
   const titleInput = document.querySelector('#title');
   const kindInput = document.querySelector('#kind');
   const authorInput = document.querySelector('#author');
   const contentInput = document.querySelector('#content');
   const imageInput = document.querySelector('#image');
   const btnCreatePost = document.querySelector('#btnSubmit');
   const formCreatePost = document.querySelector('#formCreatePost');
   
   btnCreatePost.addEventListener('click', function() {
    const title = titleInput.value;
    const kind = kindInput.value;
    const author = authorInput.value;
    const content = contentInput.value;
    const image = imageInput.value;
    
    if (title.length == 0 || kind.length == 0 || author.length == 0 || content.length == 0) {
        alert("모두 작성해주세요.");
    } else {
        const result = confirm('생성하시겠습니까');
        if (result) {
            formCreatePost.action = '/post/create';
            formCreatePost.method = 'post';
            formCreatePost.submit();
            
            alert("추가됨.")
        }
    }
    
    
    
    
})
   
   
   
   
    
});