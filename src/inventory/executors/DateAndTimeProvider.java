/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.executors;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Eddy
 */
public class DateAndTimeProvider {

//    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";
//    private static final String DATE_FORMAT = "dd-MM-yyyy";
//    private static final String TIME_FORMAT = "HH:mm:ss";
    private static final String BILL_EXT_DATE_FORMAT = "yyyyMMddHHmmss";
    private static final String DATE_VALIDATOR_FORMAT = "yyMMdd";
//    private static Calendar calendarInstance = Calendar.getInstance();
    private static Timestamp currentDateTime;

    public static Timestamp getCurrentDateTimeRaw() {
//          return (new java.util.Date().getTime());
        currentDateTime = new Timestamp(new Date().getTime());
        return currentDateTime;
    }

    public static String getBillExtDate() {
        SimpleDateFormat billExtDateFormat = new SimpleDateFormat(BILL_EXT_DATE_FORMAT);
        currentDateTime = new Timestamp(new Date().getTime());
        return billExtDateFormat.format(currentDateTime);
    }

    public static boolean searchDateValidator(Timestamp fromDate, Timestamp toDate) {
        SimpleDateFormat validatorDateFormat = new SimpleDateFormat(DATE_VALIDATOR_FORMAT);
        int result = Integer.parseInt(validatorDateFormat.format(toDate))
                - Integer.parseInt(validatorDateFormat.format(fromDate));
        boolean permission;
        if (result > 0) {
            permission = true;
        } else {
            permission = false;
        }
        return permission;
    }
}
