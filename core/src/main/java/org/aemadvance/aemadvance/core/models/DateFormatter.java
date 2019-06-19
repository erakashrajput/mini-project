package org.aemadvance.aemadvance.core.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;

@Model(adaptables=SlingHttpServletRequest.class)

/**
 * @author Akash Rajput
 *
 */
public class DateFormatter {

    @Inject
    private Calendar date;

    @Inject
    private String format;

    public String value;

    @PostConstruct
    protected void init() {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        value = formatter.format(date.getTime());
    }
}