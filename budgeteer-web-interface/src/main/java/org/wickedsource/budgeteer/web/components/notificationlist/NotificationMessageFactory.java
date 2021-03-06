package org.wickedsource.budgeteer.web.components.notificationlist;

import org.wickedsource.budgeteer.service.notification.*;
import org.wickedsource.budgeteer.web.PropertyLoader;

import java.io.Serializable;

public class NotificationMessageFactory implements Serializable {



    public String getMessageForNotification(Notification notification) {
        if (notification instanceof MissingDailyRateNotification) {
            MissingDailyRateNotification n = (MissingDailyRateNotification) notification;
            return String.format(PropertyLoader.getProperty(NotificationMessageAnchor.class, "message.missingDailyRate"), n.getPersonName(), n.getStartDate(), n.getEndDate());
        } else if (notification instanceof MissingBudgetTotalNotification) {
            MissingBudgetTotalNotification n = (MissingBudgetTotalNotification) notification;
            return String.format(PropertyLoader.getProperty(NotificationMessageAnchor.class,"message.missingBudgetTotal"), n.getBudgetName());
        } else if (notification instanceof EmptyWorkRecordsNotification) {
            return PropertyLoader.getProperty(NotificationMessageAnchor.class,"message.emptyWorkRecords");
        } else if (notification instanceof EmptyPlanRecordsNotification) {
            return PropertyLoader.getProperty(NotificationMessageAnchor.class,"message.emptyPlanRecords");
        } else if (notification instanceof MissingDailyRateForBudgetNotification) {
            MissingDailyRateForBudgetNotification n = (MissingDailyRateForBudgetNotification) notification;
            return String.format(PropertyLoader.getProperty(NotificationMessageAnchor.class,"message.missingDailyRateForBudget"), n.getBudgetName(), n.getStartDate(), n.getEndDate());
        }else if (notification instanceof MissingContractForBudgetNotification) {
            return String.format(PropertyLoader.getProperty(NotificationMessageAnchor.class,"message.missingContract"));
        } else {
            throw new IllegalArgumentException(String.format("Notifications of type %s are not supported!", notification.getClass()));
        }
    }
}
