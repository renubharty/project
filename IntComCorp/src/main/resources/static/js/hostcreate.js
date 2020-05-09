$(document).ready(
		function() {

			// SUBMIT FORM
			console.log("welcome")
			$("#createhost").submit(function(event) {

				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				// PREPARE FORM DATA

				var formData = {
					hostname : $("#hostname").val(),
					groupid : $("#groupid").val(),
					ip : $("#ip").val(),
					dns : $("#dns").val(),
					port : $("#port").val(),
					useip : $("#useip").val(),
					main : $("#main").val(),
					type : $("#type").val(),
					templateid : $("#templateid").val(),
					macro : $("#macro").val(),
					value : $("#value").val()
				};

				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "zabbixapi/hosts/createhost",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
						alert("Host Created" + result);
						var response = result.jsonObject;
						console.log(response);
						var resultArray = [];
						resultArray = response.data;
						console.log(resultArray)
						var trHTML = '';
						for (var i = 0; i < resultArray.length; i++) {
							trHTML += '<tr><td>' + resultArray[i].hostids
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