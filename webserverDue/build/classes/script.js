$(()=>{
    
	var mymap = L.map('mapid').setView([45.68946, 9.68226], 20);
	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
		maxZoom: 18,
		id: 'mapbox.streets'
	}).addTo(mymap);



    
    $.get("/all",function appendData(data, status){
        data.split("|").forEach((prof=>{
                console.log(prof);
                $("#combobox").append("<option value='"+prof+"'>"+prof+ "</option>");
                $("#combobox2").append("<option value='"+prof+"'>"+prof+ "</option>");
                
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
            if(data!="docente non occupato in quell'ora"){
                let x = data.indexOf("POS:")+4;
                let output = data.substring(2,x-4);
                output = "con classe: "+ output.substring(0,3) +  " " + output.substring(3);
                $("body").append("<div>"+output+"</div>");
            }
            $("body").append("<div>"+data+"</div>");

            let y = data.substring(x).split(",")[1];
            x = parseFloat(data.substring(x));
            y = parseFloat(y);

            L.marker([x,y]).addTo(mymap);
        });
    }); 
    
    $("#submitAula").click(function(){

        let obj = {
                aula: $("#aula").val()/*.find(":selected").val()*/   /*TODO COMBOBOX AULE*/                 
        };

        if($("#ora").val()!=0)
           obj.ora = $("#ora").val()-1;

        if($("#giorno").find(":selected").val()!="-1")
           obj.giorno = $("#giorno").find(":selected").val();

        console.log(obj);
        $.get("/aula", obj, function gotData(data, status){
            $("body").append("<div>"+data+"</div>");

        });
    }); 
    
    $("#submitOraBuca").click(function(){

        let obj = {
                doc1: $("#combobox").find(":selected").val(),
                doc2: $("#combobox2").find(":selected").val()
        };

        console.log(obj);
        $.get("/oraBuca", obj, function gotData(data, status){
            $("body").append("<div>"+data+"</div>");

        });
    }); 

    $("#submit2").click(function(){

        let obj = {
                            nome: $("#combobox").find(":selected").val(),
                            settimanale: true
                    };

                    $.get("/search", obj, function gotData(data, status){

                            /*$("body").append("<div>"+data+"</div>");*/
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
                            /*creo un <table>di celle vuote, sotto poi, le riempio(di quelle di cui ho info)*/


                            $("body").append("</table>");
                            arr.forEach(ora=>{
                               $("#tr"+ora[0]+ " #td"+ora[1]).append(ora);
                               
                            });


                    });
    }); 
        
        
});