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