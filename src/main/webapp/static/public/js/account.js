$("#LogoutButton").click(function (e) {
	$.ajax({
		type: "GET",
		url: "logout",
		success: ()=> {
			location.href = "login";
		}
	});
})

$("#requestVacation").click(function add_vacation(data){
	
	var dialog = bootbox.dialog({
		title: 'Request Vacation',
		message: '<div id="form_vacation_account"></div>',
		closeButton: false,		
		buttons:{
			cancel:{
				label: "Cancel",
				className: 'btn btn-secondary',
				callback: function(){
					form.clear();
				}
			},
			
			submit:{
				label: "Submit",
				className: 'btn btn-primary',
				callback: function () {
					if(form.validate()){
					var fullForm = form.getValue();
					fullForm.employeeId = userid;
					dhx.ajax.post("vacation?action=add", fullForm).then(function () {						
						dialog.modal('hide');
						location.reload();
					});
					}
					return false;
				}
				
			}
		}
	});
	
create_vacation_account_form();
})

$("#viewVacation").click(function view_vacation(data){
	var dialog = bootbox.dialog({
		title: 'View Vacation',
		message: '<div id="view_vacation_account"></div>',
		closeButton: false,		
		buttons:{
			cancel:{
				label: "Cancel",
				className: 'btn btn-secondary',
				callback: function(){
					dialog.modal('hide');
				}
			}	
		}
	});
	
view_vacation_account_form();

})
