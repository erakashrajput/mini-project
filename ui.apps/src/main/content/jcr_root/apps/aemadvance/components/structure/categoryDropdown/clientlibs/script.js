function onSelectCategory() {
    var faqCategory = document.getElementById("dropMenu").value;
    //Use JQuery AJAX request to post data to a Sling Servlet
    $.ajax({
        type: 'POST',
        url: '/bin/faqServlet',
        data: 'faqCategory=' + faqCategory,
        success: function(response) {
            var resonseData;
            if (response != "") {
                resonseData = response;
            } else {
                resonseData = "<div class=\"alert alert-danger text-center\">No FAQs found for above Category!</div>";
            }
            document.getElementById("resonseData").innerHTML = resonseData;
        }
    });
}