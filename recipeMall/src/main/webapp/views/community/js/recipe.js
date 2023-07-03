/**
 * 
 *///제목, id검색 
$('#sel_search').on('change', function() {
	let selectedValue = $(this).val();
	let inputName = (selectedValue === 'search_title') ? 'recipeTitle' : 'recipeId';
	$('input[type="search"]').attr('name', inputName);
});

$('.r_btn_search').on('click', function() {
	$('form').submit();
});

//카테고리 선택 
function toCate(element) {
	let cate = $(element).attr('value');
	let path1 = window.location.origin;
	let path2 = window.location.pathname;
	window.location.href = path1 + path2 + "?cate=" + cate;
}