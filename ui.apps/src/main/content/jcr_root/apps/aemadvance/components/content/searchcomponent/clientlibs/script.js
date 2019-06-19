$(document).ready(function() {

	 $("#search-btn").click(function() {

		var path = $("#path").val(), 
			searchKey = $("#searchKey").val(),
			url = "http://localhost:4502/bin/aemadvance/searchJSON?path=" + path + "&searchKey=" + searchKey,
			$resultSec = $("#resultSection");

		if((searchKey == "") || (searchKey == null)) {
			alert("Enter some text to be searched.");
			return;
		}

				$.ajax({
			url: url, 

			success: function(res){	       

		    	$resultSec.html("");

		    	var results = res.results;
				var totalmatches = res.totalMatches;

		    	if(results.length == 0) {
		    		$resultSec.html("No Results Found");
		    	} else {
					$resultSec.html("Search Results for " +searchKey + " :" + "<br/>" + "Total Matches : " + totalmatches + "<br/>" + "<hr/>");
		    		for (i = 0; i < results.length; i++) {
			    	   	var href = "http://localhost:4502" + results[i].path + ".html";
			    	   	var resultLink = "<a href = '" + href + "'>" + results[i].title + "</a><br/>";

			    	   	$resultSec.html($resultSec.html() + resultLink);
			       	}	
		    	}

	 		},

	 		error: function(xhr, status, error) {
		  		alert("ERROR: " + error);
			}
		});	
	});


});
