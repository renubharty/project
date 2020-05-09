$(document).ready(function() {

	console.log('');
	// SUBMIT FORM
	$("#createhost").load(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		console.log('Before ajax call');
		ajaxGet();
	});

	function ajaxGet() {

		// DO POST
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/hostgroups",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				var response = result.data
				console.log(response)
				var resultArray = []
				resultArray = response.result
				console.log(resultArray)

			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});

	}

})