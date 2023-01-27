/**
 * user/signIn page script
 */


    
const err = document.querySelector('#signInErr');
    
    var url = window.location.search;
    console.log(url);
    if (url=='?error') {
        console.log('Sign In Error');
    err.style.display="block";
    }
