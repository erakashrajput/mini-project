package org.aemadvance.aemadvance.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Contact us servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_POST, "sling.servlet.paths=" + "/bin/contactus"

})

/**
 * @author Akash Rajput
 *
 */
public class ContactUs extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1L;

	// Injecting the MessageGatewayService in the Servlet
	@Reference
	private transient MessageGatewayService messageGatewayService;

	protected final transient Logger log = LoggerFactory.getLogger(this.getClass());

	// Function to read the Post Call
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

		String email = request.getParameter("email");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String phonenumber = request.getParameter("phonenumber");
		String question = request.getParameter("question");
		String ccMe = request.getParameter("ccMe");

		// Variable to read all the receiver emails
		String emails = request.getParameter("receiverMails");

		// String array to store individual receiver email
		String[] recEmails = emails.split(",");
		response.getWriter().write(sendEmail(email, firstname, lastname, phonenumber, question, recEmails, ccMe));

	}

	// Function to send an email
	private String sendEmail(String email, String firstname, String lastname, String phonenumber, String question,
			String[] recEmails, String ccMe) {

		try {

			String msg = "Hello This message has been sent by";
			msg += " " + firstname + " " + lastname + "\n";
			msg += "Email Id: " + email + "\n";
			msg += "Phone Number: " + phonenumber + "\n";
			msg += "Questions: " + question;

			log.info("Message to be send is ready.");

			// Declare a MessageGateway service
			MessageGateway<Email> messageGateway;

			// Set up the Email message
			Email e = new SimpleEmail();

			for (String emailsStr : recEmails) {
				if (!(emailsStr.isEmpty() || emailsStr.equals(""))) {
					log.info("emails str: {}", emailsStr);

					e.addTo(emailsStr);
				}
			}

			if (ccMe.equals("yes")) {
				e.addCc(email);
			}

			e.setSubject("Contact Details");
			e.setFrom(email);
			e.setMsg(msg);

			// Inject a MessageGateway Service and send the message
			messageGateway = messageGatewayService.getGateway(Email.class);

			// Check the logs to see that messageGateway is not null
			messageGateway.send((Email) e);

			return ("SUCCESS");

		}

		catch (Exception e) {
			log.error("{0} {1}", "error", e);
			return ("FAILURE");

		}

	}

}
