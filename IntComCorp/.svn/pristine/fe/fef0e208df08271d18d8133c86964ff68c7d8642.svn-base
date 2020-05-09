$(document).ready(
		function() {

			// SUBMIT FORM
			$("#gettrigger").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxGet();
			});

			function ajaxGet() {

				// PREPARE FORM DATA
				var formData = {
					triggerids : $("#triggerids").val(),
					output : $("#output").val(),
					selectFunctions : $("#selectFunctions").val()
				}

				// DO GET
				$.ajax({
					type : "GET",
					contentType : "application/json",
					url : "zabbixapi/triggers/get",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(data) {
//						var response = result.data
						console.log(data.response)
						console.log(data.jsonObject.result[0])
//						var resultArray = []
//						resultArray = response.result
//						console.log(resultArray)
						var trHTML = '';
						for (var i = 0; i < data.jsonObject.result.length; i++) {
							trHTML += '<tr><td>' + resultArray[i].triggerid
									+ '</td><td>' + resultArray[i].templateid
									+ '</td><td>' + resultArray[i].functions
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