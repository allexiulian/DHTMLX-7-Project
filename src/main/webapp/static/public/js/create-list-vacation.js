function create_list_vacation(data){
	const vac_list = new dhx.Grid("list_vacation", {
		css: "dhx_widget--bordered dhx_widget--no-border_top ",
		columns: [
			{id: "vacationFrom",type:"date",dateFormat:"%d-%M-%Y", align:"left", gravity: 1, header: [{text: "From", align: "center" }]},
			{id: "vacationTo",type:"date",dateFormat:"%d-%M-%Y", align:"left", gravity: 1, header: [{text: "To", align: "center" }]},
			{id: "reason", gravity: 1, header: [{ text: "Reason", align: "center" }]},
			{id: "status", gravity: 1, header: [{ text: "Status", align: "center" }]}
		],
		height:350,
		autoWidth: true,
	});
	vac_list.data.load("vacation?id=" + data.row.id);
}
