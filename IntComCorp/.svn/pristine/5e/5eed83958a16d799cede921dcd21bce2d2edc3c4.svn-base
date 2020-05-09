$(document).ready(function(){
		
		
				
		
					// SUBMIT FORM
					$("#gethost").submit(function(event) {
						// Prevent the form from submitting via the browser.
						event.preventDefault();
						ajaxGet();
					});

					function ajaxGet() {

						var hostName = $("#hostNames").val()
						// DO GET
						$
								.ajax({

									type : "GET",
									contentType : "application/json",
									url : "zabbixapi/hosts/" + hostName,
									
									dataType : 'json',
									success : function(data) {

										console.log(data.jsonObject)
										console.log(data.jsonObject.result[0])

										var trHTML = '';
										for (var i = 0; i < data.jsonObject.result.length; i++) {
											trHTML += '<tr><td>'
													+ data.jsonObject.result[i].host
													+ '</td><td>'
													+ data.jsonObject.result[i].hostid
													+ '</td><td>'
													+ data.jsonObject.result[i].inventory_mode
													+ '</td><td>'
													+ availability([data.jsonObject.result[i].available, data.jsonObject.result[i].snmp_available,data.jsonObject.result[i].jmx_available,data.jsonObject.result[i].ipmi_available])
													+ '</td><td>'
													+ data.jsonObject.result[i].ipmi_privilege
													+ '</td><td>'
													+ data.jsonObject.result[i].auto_compress
													+ '</td><td>'
													+ data.jsonObject.result[i].ipmi_authtype
													+ '</td><td>'
													+ data.jsonObject.result[i].tls_connect
													+ '</td></tr>';

										}
										console.log("response: ",
												data.jsonObject.result);
										$('#location').append(trHTML);

									},
									error : function(e) {
										alert("Error!")
										console.log("ERROR: ", e);
									}
								});

					}

				})