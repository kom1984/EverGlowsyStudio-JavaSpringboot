$('document').ready(function(){
$('table #deleteBtn').on('click',function(event){
event.preventDefault();
var href=$(this).attr('href');
$('#confirmDeleteButton').attr('href',href);
$('#deleteCategoryModal').modal();
});
/*$('table #editBtn').on('click',function(event){
event.preventDefault();
var href=$(this).attr('href');
$.get(href,function(category,status){
$('#editId_category').val(category.id_category);
$('#editName_category').val(category.name_category);
});

$('#editCategoryModal').modal();
});*/
});
 function redirectToAdmin() {
        window.location.href = "/admin"; // Redirect to the admin dashboard URL
    }
 function togglePasswordVisibility() {
            var passwordField = document.getElementById("password");
            var toggleIcon = document.querySelector(".toggle-password");

            if (passwordField.type === "password") {
                passwordField.type = "text";
                toggleIcon.classList.remove("fa-eye");
                toggleIcon.classList.add("fa-eye-slash");
            } else {
                passwordField.type = "password";
                toggleIcon.classList.remove("fa-eye-slash");
                toggleIcon.classList.add("fa-eye");
            }
        }
 function toggleSignupPasswordVisibility(inputId) {
     const input = document.getElementById(inputId);
     if (input.type === "password") {
         input.type = "text";
         icon.classList.remove("fa-eye");
         icon.classList.add("fa-eye-slash");
     } else {
         input.type = "password";
         icon.classList.remove("fa-eye-slash");
         icon.classList.add("fa-eye");
     }
 }
 function validatePasswords() {
     const password = document.getElementById('password').value;
     const passwordConfirm = document.getElementById('passwordConfirm').value;
     const errorMessage = document.getElementById('error-message');

     if (password !== passwordConfirm) {
         errorMessage.textContent = 'Les mots de passe ne correspondent pas';
         return false;
     }
     errorMessage.textContent = '';
     return true;
 }
