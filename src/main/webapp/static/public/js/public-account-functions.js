function create_vacation_account_form(){
	 form = new dhx.Form("form_vacation_account", {
		css: "dhx_widget--bg_white dhx_widget--bordered",
		align: "center",
		padding: "40px",
		rows: [
			
    		{
				name: "vacationFrom",
      			type: "datepicker",
      			label: "From",
      			dateFormat: "%d-%M-%Y",  
      			labelPosition: "top",
      			labelWidth: 140,		 	      			
     			required: true,
     			validation: function (value) {
                    fromvalue = value;
                    var state = value && new Date(fromvalue) > new Date();
                    if (state) {
                        form.getItem('vacationTo').enable();
                    }
                    return state;
                },
                errorMessage: "Date is from the past!"
            	},
   			
   			 {
				name: "vacationTo",
      			type: "datepicker",
      			label: "To",
      			dateFormat: "%d-%M-%Y",  
      			labelPosition: "top",
      			disabled: true,
      			labelWidth: 140, 	      			
     			required: true,   			
     			validation: function (value) {
                    endValue = value;
                    return value && new Date(endValue) > new Date(fromvalue);
             },
                errorMessage: "Invalid end Date!"
            
   			 },
   			 
   			 {
				name: "reason",
      			type: "combo",
      			label: "Reason",    
      			labelPosition: "top", 
      			labelWidth: 140,			
     			required: true,
     			newOptions: false,
     			data: [
		{ value: "Holiday",id: "Holiday" },
        { value: "Sick",id: "Sick" },
        { value: "Migraine",id: "Migraine" },
        { value: "Anxiety",id: "Anxiety" },
        { value: "Stress",id: "Stress" },
        { value: "Injury",id: "Injury" }
    ]
   			 }

		]
		
	});
	form.events.on("change", (date) => {
        if (date != 'reason') {
            form.getItem(date).validate();
        }
    });
}

function view_vacation_account_form(){
	const vac_list = new dhx.Grid("view_vacation_account", {
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
	vac_list.data.load("vacation?id=" + userid);
}