var nodeData="";
var res = [];
var start = 0;

$(document).ready(function(){	

	var apiUrlLink = $("#apiUrlLink").val();
    console.log(apiUrlLink);

	// Using AJAX to hit the URL
    // We will get XML as a response
	$.ajax({  
		url: apiUrlLink,  
		type: 'GET',  
		dataType: 'xml', 
		crossDomain: true,
		success: function (data) {  
            console.log(data);

		    $(data).find('program-availability-fos').each(function(){
				$(this).find("fieldOfStudy").each(function(){
					$(this).find("areas").each(function(){
						$(this).find("levels").each(function(){
							$(this).find("programs").each(function(){
								var obj = {};
								var name = $(this).find("title").text();
								var link = $(this).find("attributes").find("ref").text();
								obj['title'] = name;
								obj['link'] = link;
								res.push(obj);

							});
						});
					});
				});
		    });
		},  
		error: function (xhr, textStatus, errorThrown) {  
			alert("Failed");  
		}  
	});

	//This code will do the pagination of the page.

	$("#pagination").click(function(e) {

		var end = start + 10;
		var i;
		for(i=start;i<end;i++){
			$("#apiUrlData").append("<li class=\"bottom-border\"><a href=\""+res[i].link+"\" >"+res[i].title 
									 + "&nbsp;&nbsp;<i class=\"fa fa-angle-right\" style=\"font-size:12px\"></i> </a>"
									 + "<i class=\"fa fa-plus\" style=\"font-size:12px; float: right\"></i></li>");
		}
		start = start + 10;
	});
});