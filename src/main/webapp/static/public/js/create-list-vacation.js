function create_list_vacation(data){
	const vac_list = new dhx.Grid("list_vacation", {
		css: "dhx_widget--bordered dhx_widget--no-border_top ",
		columns: [
			{id: "vacationFrom",type:"date",dateFormat:"%d-%M-%Y", align:"left", gravity: 2, header: [{text: "From", align: "left" }]},
			{id: "vacationTo",type:"date",dateFormat:"%d-%M-%Y", align:"left", gravity: 2, header: [{text: "To", align: "left" }]},
			{id: "reason", gravity: 2, header: [{ text: "Reason", align: "left" }]}
		],
		height:350,
		autoWidth: true,
	});
	vac_list.data.load("vacation?id=" + data.row.id);
}
