$(document).ready(
		function() {

			// SUBMIT FORM
			$("#getmacro").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				// PREPARE FORM DATA
				var formData = {
					hostids : $("#hostids").val(),
					output : $("#output").val()
				}

				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "zabbixapi/usermacros/get",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
						var response = result.data
						console.log(response)
						var resultArray = []
						resultArray = response.result
						console.log(resultArray)
						var trHTML = '';
						for (var i = 0; i < resultArray.length; i++) {
							trHTML += '<tr><td>' + resultArray[i].macro
									+ '</td><td>' + resultArray[i].hostmacroid
									+ '</td><td>' + resultArray[i].value
									+ '</td></tr>';

						}
						console.log("response: ", result);
						$('#location').append(trHTML);

					},
					error : function(e) {
						alert("Error!")
						console.log("ERROR: ", e);
					}
				});

			}

		})