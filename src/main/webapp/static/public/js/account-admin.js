$("#AdminButton").click(function (e) {

	location.href = "admin";

});


const vac_list_2 = new dhx.Grid("list_vacation_2", {
		css: "dhx_widget--bordered dhx_widget--no-border_top ",
		columns: [
			{id: "vacationFrom",type:"date",dateFormat:"%d-%M-%Y", align:"left", gravity: 1, header: [{text: "From", align: "center" }]},
			{id: "vacationTo",type:"date",dateFormat:"%d-%M-%Y", align:"left", gravity: 1, header: [{text: "To", align: "center" }]},
			{id: "reason", gravity: 1, header: [{ text: "Reason", align: "center" }]},
			{id: "status", gravity: 1, header: [{ text: "Status", align: "center" }]},
			{id: "action", gravity: 1, header: [{ text: "Action", align: "center" }],
			htmlEnable: true,
			template: function(){
				return "<span class='action-buttons'><a class='btn btn-success accept'>Accept</a><a class='btn btn-danger decline'>Decline</a></span>";
			},
			}
		],
		height:350,
		autoWidth: true,
		selection: "row",
		eventHandlers: {
			onclick:{
				"accept": function(e, data){
					dhx.ajax.post("vacation?action=Accepted", data.row.id).then(function (data) {
					location.reload();
				}).catch(function (err) {
					errorMsg(err)
				});
				},
				"decline": function(e, data){
					dhx.ajax.post("vacation?action=Declined", data.row.id).then(function (data) {
					location.reload();
				}).catch(function (err) {
					errorMsg(err);
				});
				}
			}
		}
	});
	vac_list_2.data.load("vacation?id=" + userid + "&filter='Pending'");
	
	function errorMsg(err) {
	bootbox.dialog({
		title: 'Failed',
		message: "<p>" + err.message + "</p>",
		size: 'lg',
		buttons: {
			cancel: {
				label: "ok",
				className: 'btn-warning'
			}
		}
	});
}
