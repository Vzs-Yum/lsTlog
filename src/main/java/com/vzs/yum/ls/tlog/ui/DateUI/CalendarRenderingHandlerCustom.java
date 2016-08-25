package com.vzs.yum.ls.tlog.ui.DateUI;

import org.jdesktop.swingx.plaf.basic.CalendarState;

import javax.swing.*;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by byao on 8/25/16.
 */
public interface CalendarRenderingHandlerCustom {
    public JComponent prepareRenderingComponent(JXMonthViewCustom monthView,
                                                Calendar calendar, CalendarState state);

    /**
     * Updates internal state to the given Locale.
     *
     * PENDING JW: ideally, the handler should be stateless and this method
     * removed. Currently needed because there is no way to get the Locale
     * from a Calendar.
     *
     * @param locale the new Locale.
     */
    public void setLocale(Locale locale);
}
