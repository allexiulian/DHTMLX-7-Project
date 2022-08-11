function create_emp_form(){
	 form = new dhx.Form("form_emp", {
		css: "dhx_widget--bg_white dhx_widget--bordered",
		align: "center",
		padding: "40px",
		rows: [
			{
				name: "name",
				type: "input",
				label: "Name",
				labelPosition: "top",
				labelWidth: 140,
				required: true   			     		
      			
			},
			{
				name: "phone",
				type: "input",
				label: "Phone",
				labelPosition: "top",
				labelWidth: 140,
				validation: "numeric",	
				preMessage: "Type only Digits!",			
				errorMessage: "Only digits are allowed!",			
				required: true   			     		
      			
			},
			{
				name: "email",
      			type: "input",
      			label: "Email",
      			labelPosition: "top",
      			labelWidth: 140,
      			placeholder: "jd@mail.name",
      			validation: "email",
      			errorMessage: "Only email format!",      				
      			required: true
      			
    		},
    		{
				name: "birthdate",
      			type: "datepicker",
      			label: "Birthdate",
      			dateFormat: "%d-%M-%Y",  
      			labelPosition: "top",
      			labelWidth: 140,
      			editable: false, 	      			
     			required: true
   			 },
    		{
				name: "address",
      			type: "input",
      			label: "Adress",    
      			labelPosition: "top", 
      			labelWidth: 140,			
     			required: true
   			 },
   			 {
				name: "country",
      			type: "combo",
      			label: "Country",    
      			labelPosition: "top", 
      			labelWidth: 140,			
     			required: true,
     			newOptions: false,
     			data: [
        { value: "Romania",id: "Romania" },
        { value: "Afghanistan",id: "Afghanistan" },
        { value: "Albania",id: "Albania" },
        { value: "Algeria",id: "Algeria" },
        { value: "Andorra",id: "Andorra" },
        { value: "Angola",id: "Angola" },
        { value: "Antigua and Barbuda",id: "Antigua and Barbuda" },
        { value: "Argentina",id: "Argentina" },
        { value: "Armenia",id: "Armenia" },
        { value: "Australia",id: "Australia" },
        { value: "Austria",id: "Austria" },
        { value: "Azerbaijan",id: "Azerbaijan" },
        { value: "Bahamas",id: "Bahamas" },
        { value: "Bahrain",id: "Bahrain" },
        { value: "Bangladesh	",id: "Bangladesh	" },
        { value: "Barbados",id: "Barbados" },
        { value: "Belarus",id: "Belarus" },
        { value: "Belgium",id: "Belgium" },
        { value: "Belize",id: "Belize" },
        { value: "Benin",id: "Benin" },
        { value: "Bhutan",id: "Bhutan" },
        { value: "Bolivia",id: "Bolivia" },
        { value: "Bosnia and Herzegovina",id: "Bosnia and Herzegovina" },
        { value: "Botswana",id: "Botswana" },
        { value: "Brazil",id: "Brazil" },
        { value: "Brunei",id: "Brunei" },
        { value: "Bulgaria",id: "Bulgaria" },
        { value: "Burkina Faso",id: "Burkina Faso" },
        { value: "Burundi",id: "Burundi" },
        
    ]
   			 }

		]
	});
}
