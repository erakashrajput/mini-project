
var latValue = $('#lat').val();
var longValue = $('#long').val();

// Initialize and add the map
function initMap() {
  // The location of Uluru

  var uluru = {lat: parseFloat(latValue), lng: parseFloat(longValue)};
  // The map, centered at Uluru
  var map = new google.maps.Map(
      document.getElementById('map'), {zoom: 4, center: uluru});
  // The marker, positioned at Uluru
  var marker = new google.maps.Marker({position: uluru, map: map});
}



$.getScript("https://maps.googleapis.com/maps/api/js?key=AIzaSyD3-3bevazGKb-5eTltn5_wkj6Py_8HwgA&callback=initMap",function(){});
// <script async defer
  //  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD3-3bevazGKb-5eTltn5_wkj6Py_8HwgA&callback=initMap">    </script>


$(document).ready(function () {


});