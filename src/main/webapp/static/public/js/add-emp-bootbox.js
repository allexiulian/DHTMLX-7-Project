function add_emp_bootbox(){
	var dialog = bootbox.dialog({
		title: "New Employee",
		message: '<div id="form_emp"></div>',
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
				callback: function(){
					if(form.validate()){
						dhx.ajax.post("viewemployee", form.getValue()).then(function(){
							grid.data.load("viewemployee");
							dialog.modal('hide');
						})
					}
					return false;
				}
				
			}
		}
	});
	
	create_emp_form();
}

function delete_emp_bootbox(data){
	
	confirm = bootbox.confirm({
		size: "small",
		closeButton: false,
		title: "Are you sure? ",
		message: "Delete " + data.row.name + " ?",
		buttons: {
			confirm: {
				label: 'Yes',
				className: 'btn-danger'
			},
			cancel: {
				label: 'Cancel',
				className: 'btn-secondary'
			}
		},
		callback: function (result) {
			if (result) {
				dhx.ajax.post("deleteemployee", data.row.id).then(function () {
					grid.data.load("viewemployee");
					confirm.modal('hide');
				});
				return false;
			}
		}
	});
	
}

function add_vacation_bootbox(data){
	var dialog = bootbox.dialog({
		title: 'Add Vacation',
		message: '<div id="form_vacation"></div>',
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
					fullForm.employeeId = data.row.id;
					dhx.ajax.post("vacation", fullForm).then(function () {						
						dialog.modal('hide');
					});
					}
					return false;
				}
				
			}
		}
	});
	
	create_vacation_form();
}

function list_emp_bootbox(data){
	 dialog = bootbox.dialog({
		message: '<div id="list_vacation"></div>',
		title: 'Vacation for ' + data.row.name,
		closeButton: false,		
		buttons:{
			cancel:{
				label: "Cancel",
				className: 'btn btn-secondary'			
			}
			
		}
});

create_list_vacation(data);
}
