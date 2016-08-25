package com.vzs.yum.ls.tlog.ui.DateUI;

import org.jdesktop.swingx.decorator.ComponentAdapter;
import org.jdesktop.swingx.plaf.basic.CalendarState;

import java.util.Calendar;

/**
 * Created by byao on 8/25/16.
 */
public class CalendarAdapterCustom extends ComponentAdapter {

    Calendar calendar;
    CalendarState dayState;

    /**
     * @param component
     */
    public CalendarAdapterCustom(JXMonthViewCustom component) {
        super(component);
    }

    /**
     * @param calendar2
     * @param dayState2
     * @return
     */
    public CalendarAdapterCustom install(Calendar calendar, CalendarState dayState) {
        this.calendar = calendar;
        this.dayState = dayState;
        return this;
    }


    @Override
    public JXMonthViewCustom getComponent() {
        return (JXMonthViewCustom) super.getComponent();
    }

    public CalendarState getCalendarState() {
        return dayState;
    }

    public boolean isFlagged() {
        if (getComponent() == null || calendar == null) {
            return false;
        }
        return getComponent().isFlaggedDate(calendar.getTime());
    }

    public boolean isUnselectable() {
        if (getComponent() == null || calendar == null || !isSelectable()) {
            return false;
        }
        return getComponent().isUnselectableDate(calendar.getTime());
    }

    private boolean isSelectable() {
        return (CalendarState.IN_MONTH == getCalendarState()) || (CalendarState.TODAY == getCalendarState());
    }

    @Override
    public boolean isSelected() {
        if (getComponent() == null || calendar == null) {
            return false;
        }
        return getComponent().isSelected(calendar.getTime());
    }


    @Override
    public Object getFilteredValueAt(int row, int column) {
        return getValueAt(row, column);
    }

    @Override
    public Object getValueAt(int row, int column) {
        return calendar;
    }

    @Override
    public boolean hasFocus() {
        return false;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public boolean isEditable() {
        return false;
    }
}
