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
						dhx.ajax.post("viewemployee?action=add"  , form.getValue()).then(function(){
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
				dhx.ajax.post("viewemployee?action=delete", data.row.id).then(function () {
					grid.data.load("viewemployee");
					confirm.modal('hide');
				});
				return false;
			}
		}
	});
	
}

function add_vacation_bootbox(id, action){
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
					fullForm.employeeId = id;
					dhx.ajax.post("vacation?action="+action, fullForm).then(function () {						
						dialog.modal('hide');
						location.reload();
					}).catch(function (err) {
							bootbox.dialog({
								title: 'Failed',
								message: "<p>" + err.message + "</p>",
								size: 'sm',
								buttons: {
									cancel: {
										label: "ok",
										className: 'btn-warning'
									}
								}
							});
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

function add_upload_bootbox(){
	var dialog = bootbox.dialog({
		title: "Upload Vacation",
		message: '<form id="upload" enctype="multipart/form-data"><input type="file" name="file" /></form>',
		closeButton: false,		
		buttons:{
			cancel:{
				label: "Cancel",
				className: 'btn btn-secondary',
				callback: function(){
					
				}
			},
			
			submit:{
				label: "Upload",
				className: 'btn btn-primary',
				callback: function(){
					var form = $('#upload')[0];
					var data = new FormData(form);
					$.ajax({
						type: "POST",
						url: "csv",
						data: data,
						enctype: 'multipart/form-data',
						processData: false,
						contentType: false,
						cache: false,
						success: function(data){
							dialog.modal('hide');
							bootbox.alert("Upload Successful");	
						},
						error: function(data){
							bootbox.dialog({
								title: 'Failed to upload',
								message: "<p>"+data.responseText+"</p>",
								size: 'large',
								buttons: {
										cancel: {
											label: "ok",
											className: 'btn-warning'
									}
								}
							});
						}
					});
					return false;
				}
			}
		}
	});	
}
