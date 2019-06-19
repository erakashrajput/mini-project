package org.aemadvance.aemadvance.core;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

//This is a component so it can provide or consume services

@Component(service = WorkflowProcess.class, property = { "process.label= My Email Custom Step" })

/**
 * @author Akash Rajput
 *
 */
public class CustomStep implements WorkflowProcess {

	/** Default log. */
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	// Inject a MessageGatewayService
	@Reference
	private MessageGatewayService messageGatewayService;

	public void execute(WorkItem item, WorkflowSession wfsession, MetaDataMap args) throws WorkflowException {

		try {
			log.info("Here in execute method"); // ensure that the execute
												// method is invoked

			// Declare a MessageGateway service
			MessageGateway<Email> messageGateway;

			// Set up the Email message
			Email email = new SimpleEmail();

			// Set the mail values
			String emailToRecipients = "akshh2oo@gmail.com";
			String emailCcRecipients = "akshh2oo@gmail.com";

			email.addTo(emailToRecipients);
			email.addCc(emailCcRecipients);
			email.setSubject("AEM Custom Email Sending Setep");
			email.setFrom("akashrajput.er@gmail.com");
			email.setMsg("This message is to inform you that the mail service is working");

			// Inject a MessageGateway Service and send the message
			messageGateway = messageGatewayService.getGateway(Email.class);

			// Check the logs to see that messageGateway is not null
			messageGateway.send((Email) email);
		}

		catch (Exception e) {
			log.error("{0} {1}", "error", e);
		}
	}

}