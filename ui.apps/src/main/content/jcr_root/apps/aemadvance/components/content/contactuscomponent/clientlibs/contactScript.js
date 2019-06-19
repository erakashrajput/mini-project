$(document).ready(function () {

   $('#contactForm').submit(function (e) {

      e.preventDefault();

      var email = $('#email').val();
      var firstname = $('#firstname').val();
      var lastname = $('#lastname').val();
      var phonenumber = $('#phonenumber').val();
      var question = $('#question').val();
      var receiverMails = $('#receiverMails').val();
      var ccMe = document.getElementById('ccMe').checked ? $('#ccMe').val() : 'no';

      var data = 'email=' + email + '&firstname=' + firstname + '&lastname=' + lastname + '&phonenumber=' + phonenumber + '&question=' + question + '&receiverMails=' + receiverMails + '&ccMe=' + ccMe;

      //Calling ContactUs Servlet using AJAX
      $.ajax({
         url: "/bin/contactus",
         data: data,
         type: "POST",
         success: function (response) {
            if (response == "SUCCESS") {
               alert("Mail sent");

            } else {

               alert("Mail sent failed");

            }

         },
         error: function (e) {
            alert("error");
         }
      });
   });


});