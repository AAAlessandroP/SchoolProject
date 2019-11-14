$(()=>{
        
        
	$.get("/all",function appendData(data, status){
            data.split("|").forEach((prof=>{
                console.log(prof);
                $("#combobox").append("<option value='"+prof+"'>"+prof+ "</option>")
            }));
            
        });     
        
	$("#submit").click(function(){

		let obj = {
                    nome: $("#combobox").find(":selected").val()                    
                };

                if($("#ora").val()!=0)
                   obj.ora = $("#ora").val()-1;

                if($("#giorno").find(":selected").val()!="-1")
                   obj.giorno = $("#giorno").find(":selected").val();
                
                console.log(obj);
		$.get("/search", obj, function gotData(data, status){
                    $("body").append("<div>"+data+"</div>")
                });
	}); 
	
        $("#submit2").click(function(){

		let obj = {
                    nome: $("#combobox").find(":selected").val(),
                    settimanale: true
                };

                $.get("/search", obj, function gotData(data, status){
                    
                    $("body").append("<div>"+data+"</div>");
                    let arr = [];
                    data.split(";").forEach((ora=>{
                        console.log("ora",ora);
                        arr.push( ora.split(",") );
                        
                    }));
                     
                    
                    $("body").append("<table style='border: 1px solid black;'>");
                    for(let i=0;i<8;i++){/*righe*/
                        $("body table").append("<tr style='border: 1px solid black;' id='tr"+i+"'></tr>"); 
                        for(let i=0;i<6;i++){/*col(gg)*/
                            $("body table tr:last").append("<td style='border: 1px solid black;' id='td"+i+"'></td>");  
                        }
                    }
                    
                    
                    
                    $("body").append("</table>");
                    arr.forEach(ora=>{
                       console.log(ora); 
                       $("#tr"+ora[0]+ " #td"+ora[1]).append(ora);
                    });                                        
                    
                    
                });
	}); 
        
        
});