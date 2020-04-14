GET:$(document).ready(
		function(){
			$("#categoryName").change(function(event) {
				event.preventDefault();
				ajaxGet();
				alert("IN first function");
			});	
			
			function ajaxGet() {
				var id = $(this).val();
				$.ajax({
					type: 'GET',
					url: '/api/user/categories-child/' + id,
					success: function(result) {
						var result = JSON.parse(result);
						alert(result);
						var s = '';
						for(var i = 0; i < result.length; i++) {
							s += '<option value="' + result[i].id + '">' + result[i].categoryChildName + '</option>';
						}
						$('#childCategory').html(s);
					}
				});	
			}
		})


