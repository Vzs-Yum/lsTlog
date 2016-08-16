package com.vzs.yum.ls.tlog.process.state;

import com.vzs.yum.ls.tlog.util.DateUtils;
import com.vzs.yum.ls.tlog.util.NumberUtils;
import com.vzs.yum.ls.tlog.util.StringUtils;
import com.vzs.yum.ls.tlog.vo.TLogTransactionFooter;
import com.vzs.yum.ls.tlog.vo.TLogTransactionSingleton;

/**
 * Created by byao on 8/14/16.
 */
public class TLogTransactionFooterState extends TLogState {
    TLogTransactionSingleton tLogTransactionSingleton = TLogTransactionSingleton.getInstance();
    public void Handle(TLogStateContext context) {
        String[] splitedTlog = StringUtils.splictStringBySpace(getTLogLine());
        TLogTransactionFooter currentTLogTransactionFooter = tLogTransactionSingleton.getCurrentTLogTransactionFooter();
        if (TLogTransactionSign.isTotal(getTLogLine())) {
            String totalPrice = splitedTlog[5].replace("$", "");

            currentTLogTransactionFooter.setTotal(NumberUtils.pharseDouble(totalPrice));
        } else if (TLogTransactionSign.isTender(getTLogLine()) && splitedTlog.length > 4) {
            String tenderTotal = splitedTlog[5].replace("$", "");
            currentTLogTransactionFooter.setTender(NumberUtils.pharseDouble(tenderTotal));
        } else if (TLogTransactionSign.isFooter(getTLogLine())) {
            String dateStr = splitedTlog[4] + " " + splitedTlog[5];
            currentTLogTransactionFooter.setTransactionFooterDate(DateUtils.formatTransactionTime(dateStr));

            TLogTransactionStartState tLogTransactionStartState = new TLogTransactionStartState();
            context.setTLogState(tLogTransactionStartState);
        }
    }
}
