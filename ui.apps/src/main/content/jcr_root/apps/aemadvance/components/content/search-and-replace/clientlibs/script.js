$(document).ready(function() {

 $("#replaceBtn").click(function(e) {

  var nodePath = $('#nodePath').val();
  var firstString = $('#firstString').val();
  var firstValue = $('#firstValue').val();
  var secondString = $('#secondString').val();
  var secondValue = $('#secondValue').val();
  var thirdString = $('#thirdString').val();
  var thirdValue = $('#thirdValue').val();

  if (nodePath == null || nodePath == "" || firstString == null || firstString == "" || firstValue == null || firstValue == "" || secondString == null || secondString == "" || secondValue == null || secondValue == "" || thirdString == null || thirdString == "" || thirdValue == null || thirdValue == "") {
   alert("Please Enter all the Fields!");
  }
     var data ='nodePath=' + nodePath + '&firstString=' + firstString + '&firstValue=' + firstValue + '&secondString=' + secondString + '&secondValue=' + secondValue + '&thirdString=' + thirdString + '&thirdValue=' + thirdValue;

     $.ajax({
            type: 'POST',    
            url:'/bin/searchReplace',
            data:'nodePath='+nodePath+'&firstString='+firstString+'&firstValue='+firstValue+'&secondString='+secondString+'&secondValue='+secondValue+'&thirdString='+thirdString+'&thirdValue='+thirdValue,
            success: function(replaceStatus) {
                var textToDisplay;
                if(replaceStatus=='SUCCESS'){
                    textToDisplay = "<div class=\"alert alert-success text-center\">All Node Values Replaced Successfully!</div>";
                } else {
                    textToDisplay = "<div class=\"alert alert-danger text-center\">Node Values Not Replaced!</div><br/>" + replaceStatus;
                }
                document.getElementById("replaceStatus").innerHTML = textToDisplay;
        		$("#replaceStatus").show();
            }
        });	
 });

});
