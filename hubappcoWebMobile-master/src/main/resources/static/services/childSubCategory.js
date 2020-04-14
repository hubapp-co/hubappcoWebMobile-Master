GET:$(document).ready(function(){
	$('#childCategory').on('change', function(){
		var id = $(this).val();
		$.ajax({
			type: 'GET',
			url: 'localhost:8080/api/user/categories-sub-child/' + id,
			success: function(result) {
				var result = JSON.parse(result);
				var s = '';
				for(var i = 0; i < result.length; i++) {
					s += '<option value="' + result[i].id + '">' + result[i].categorySubChildName + '</option>';
				}
				$('#childSubCategory').html(s);
			}
		});
	});
});