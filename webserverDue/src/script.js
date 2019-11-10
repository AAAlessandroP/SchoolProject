$(()=>{
	$.get("/all",function appendData(data, status){
            data.split("|").forEach((prof=>{
                console.log(prof);
                $("#combobox").append("<option value='"+prof+"'> "+prof+ "</option>")
            }));
             
        });     
        
	$("#submit").click(function(){
		
		$.get("/search", {nome: $("#combobox").find(":selected").text()}, function got(data, status){
			$("body").append("<div>"+data+"</div>");
		});
	}); 
	
});
