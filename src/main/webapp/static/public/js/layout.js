const layout = new dhx.Layout("layout", {
    cols: [
        {
            rows: [
                {
                    id: "toolbar",
                    height: "content"
                },
                {
                    type: "space",
                    rows: [
                        {
                            id: "grid"
                        }
                    ]
                }
            ]
        }
    ]
});


// Toolbar initialization
const toolbar = new dhx.Toolbar(null, {
    css: "toolbar_template_a"
});
// loading structure into Toolbar
toolbar.data.parse(toolbarData);
// Event for toolbar button
toolbar.events.on("click", function (id) {
    if (id === "add") {
    add_emp_bootbox();
    }
    if (id === "upload") {
    add_upload_bootbox();
    }
    if(id == "logout"){
	location.href= "logout";	
}
	if(id == "download"){
	window.location= "csv";	
}
    }
);

// initializing Grid for data vizualization
const grid = new dhx.Grid(null, {
    css: "dhx_demo-grid",
    columns: [
        { id: "name", gravity: 1, header: [{ text: "Name" }] },
        { id: "phone", gravity: 1, header: [{ text: "Phone" }] },
        { id: "email", header: [{ text: "Email" }] },
        { id: "birthdate", header: [{ text: "Birthdate",dateFormat: "%d-%M-%Y", align: "left" }] },
        { id: "address", header: [{ text: "Address" }] },
        { id: "country", header: [{ text: "Country" }] },
        {
            id: "action", gravity: 1.2, header: [{ text: "Actions", align: "center" }],
            htmlEnable: true, align: "center",
            template: function () {
                return "<span class='action-buttons'><a class='btn btn-outline-success add-button'>Add</a><a class='btn btn-outline-info list-button'>List</a><a class='btn btn-outline-danger delete-button'>Delete</a></span>"
            }
        }
    ],
    autoWidth: true,
    eventHandlers: {
        onclick: {
			"add-button": function (e, data){
				console.log(data);
				add_vacation_bootbox(data);
			},
			
			"list-button": function (e, data) {
                list_emp_bootbox(data);
            },
			
            "delete-button": function (e, data) {
                delete_emp_bootbox(data);
            }
            
        }
    }
});
// loading data into Grid
grid.data.load("viewemployee");

// attaching widgets to Layout cells
layout.getCell("toolbar").attach(toolbar);
layout.getCell("grid").attach(grid);

