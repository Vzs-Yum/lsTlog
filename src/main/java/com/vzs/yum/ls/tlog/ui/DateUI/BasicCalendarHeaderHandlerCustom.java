package com.vzs.yum.ls.tlog.ui.DateUI;

import org.jdesktop.swingx.JXHyperlink; 
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.hyperlink.AbstractHyperlinkAction;
import org.jdesktop.swingx.plaf.basic.BasicCalendarHeaderHandler;
import org.jdesktop.swingx.plaf.basic.CalendarHeaderHandler;
import org.jdesktop.swingx.renderer.StringValue;
import org.jdesktop.swingx.renderer.StringValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by byao on 8/25/16.
 */
public class BasicCalendarHeaderHandlerCustom  extends CalendarHeaderHandlerCustom {


    
    public void install(JXMonthViewCustom monthView) {
        super.install(monthView);
        getHeaderComponent().setActions(monthView.getActionMap().get("previousMonth"),
                monthView.getActionMap().get("nextMonth"),
                monthView.getActionMap().get("zoomOut"));

    }



    
    protected void installNavigationActions() {
        // TODO Auto-generated method stub
        super.installNavigationActions();
        BasicCalendarHeaderHandlerCustom.ZoomOutAction zoomOutAction = new BasicCalendarHeaderHandlerCustom.ZoomOutAction();
        zoomOutAction.setTarget(monthView);
        monthView.getActionMap().put("zoomOut", zoomOutAction);
    }



    
    public void uninstall(JXMonthViewCustom monthView) {
        getHeaderComponent().setActions(null, null, null);
        super.uninstall(monthView);
    }


    
    public BasicCalendarHeaderHandlerCustom.BasicCalendarHeader getHeaderComponent() {
        // TODO Auto-generated method stub
        return (BasicCalendarHeaderHandlerCustom.BasicCalendarHeader) super.getHeaderComponent();
    }

    
    protected BasicCalendarHeaderHandlerCustom.BasicCalendarHeader createCalendarHeader() {
        return new BasicCalendarHeaderHandlerCustom.BasicCalendarHeader();
    }

    /**
     * Quick fix for Issue #1046-swingx: header text not updated if zoomable.
     *
     */
    protected static class ZoomOutAction extends AbstractHyperlinkAction<JXMonthViewCustom> {

        private PropertyChangeListener linkListener;
        // Formatters/state used by Providers. 
        /** Localized month strings used in title. */
        private String[] monthNames;
        private StringValue tsv ;

        public ZoomOutAction() {
            super();
            tsv = new StringValue() {

                
                public String getString(Object value) {
                    if (value instanceof Calendar) {
                        String month = monthNames[((Calendar) value)
                                .get(Calendar.MONTH)];
                        return month + " "
                                + ((Calendar) value).get(Calendar.YEAR);
                    }
                    return StringValues.TO_STRING.getString(value);
                }

            };
        }

        
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

        }


        /**
         * installs a propertyChangeListener on the target and
         * updates the visual properties from the target.
         */
        
        protected void installTarget() {
            if (getTarget() != null) {
                getTarget().addPropertyChangeListener(getTargetListener());
            }
            updateLocale();
            updateFromTarget();
        }

        /**
         *
         */
        private void updateLocale() {
            Locale current = getTarget() != null ? getTarget().getLocale() : Locale.getDefault();
            monthNames = DateFormatSymbols.getInstance(current).getMonths();
        }

        /**
         * removes the propertyChangeListener. <p>
         *
         * Implementation NOTE: this does not clean-up internal state! There is
         * no need to because updateFromTarget handles both null and not-null
         * targets. Hmm...
         *
         */
        
        protected void uninstallTarget() {
            if (getTarget() == null) return;
            getTarget().removePropertyChangeListener(getTargetListener());
        }

        protected void updateFromTarget() {
            // this happens on construction with null target
            if (tsv == null) return;
            Calendar calendar = getTarget() != null ? getTarget().getCalendar() : null;
            setName(tsv.getString(calendar));
        }

        private PropertyChangeListener getTargetListener() {
            if (linkListener == null) {
                linkListener = new PropertyChangeListener() {
 
                    public void propertyChange(PropertyChangeEvent evt) {
                        if ("firstDisplayedDay".equals(evt.getPropertyName())) {
                            updateFromTarget();
                        } else if ("locale".equals(evt.getPropertyName())) {
                            updateLocale();
                            updateFromTarget();
                        }
                    }

                };
            }
            return linkListener;
        }


    }


    /**
     * Active header for a JXMonthView in zoomable mode.<p>
     *
     *  PENDING JW: very much work-in-progress.
     */
    static class BasicCalendarHeader extends JXPanel {

        protected AbstractButton prevButton;
        protected AbstractButton nextButton;
        protected JXHyperlink zoomOutLink;

        public BasicCalendarHeader() {
            setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
            prevButton = createNavigationButton();
            nextButton = createNavigationButton();
            zoomOutLink = createZoomLink();
            add(prevButton);
            add(Box.createHorizontalGlue());
            add(zoomOutLink);
            add(Box.createHorizontalGlue());
            add(nextButton);
            setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
        }

        /**
         * Sets the actions for backward, forward and zoom out navigation.
         *
         * @param prev
         * @param next
         * @param zoomOut
         */
        public void setActions(Action prev, Action next, Action zoomOut) {
            prevButton.setAction(prev);
            nextButton.setAction(next);
            zoomOutLink.setAction(zoomOut);
        }


        /**
         * {@inheritDoc} <p>
         *
         * Overridden to set the font of the zoom hyperlink.
         */
        
        public void setFont(Font font) {
            super.setFont(font);
            if (zoomOutLink != null)
                zoomOutLink.setFont(font);
        }

        private JXHyperlink createZoomLink() {
            JXHyperlink zoomOutLink = new JXHyperlink();
            Color textColor = new Color(16, 66, 104);
            zoomOutLink.setUnclickedColor(textColor);
            zoomOutLink.setClickedColor(textColor);
            zoomOutLink.setFocusable(false);
            return zoomOutLink;
        }

        private AbstractButton createNavigationButton() {
            JXHyperlink b = new JXHyperlink();
            b.setContentAreaFilled(false);
            b.setBorder(BorderFactory.createEmptyBorder());
            b.setRolloverEnabled(true);
            b.setFocusable(false);
            return b;
        }
    }

}
    