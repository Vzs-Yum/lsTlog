package com.vzs.yum.ls.tlog.process.state;

import com.vzs.yum.ls.tlog.util.DateUtils;
import com.vzs.yum.ls.tlog.util.ListUtils;
import com.vzs.yum.ls.tlog.util.NumberUtils;
import com.vzs.yum.ls.tlog.util.StringUtils;
import com.vzs.yum.ls.tlog.vo.TLogTransactionFooter;
import com.vzs.yum.ls.tlog.vo.TLogTransactionHolder;
import com.vzs.yum.ls.tlog.vo.TLogTransactionSingleton;

import java.util.Date;
import java.util.List;

/**
 * Created by byao on 8/7/16.
 */
public class TLogTransactionStartState extends TLogState {
    TLogTransactionSingleton tLogTransactionSingleton = TLogTransactionSingleton.getInstance();
    boolean isSkipFlag = false;
    public void Handle(TLogStateContext context) {

        String[] splitedTlog = StringUtils.splictStringBySpace(getTLogLine());
        if (TLogTransactionSign.isHeaderStart(getTLogLine()) || isSkipFlag) {
            if (!TLogTransactionSign.isHeaderStart(getTLogLine())) {
                return;
            }
            String transactionHeaderTime = splitedTlog[4] + " " + splitedTlog[5];
            String transactionId = splitedTlog[7];
            Date transactionDate = DateUtils.formatTransactionTime(transactionHeaderTime);
            if (neeedSelection(context, transactionDate)) {
                isSkipFlag = false;
                tLogTransactionSingleton.findTransationLogAndCreateFooter(transactionId, transactionDate, context.getFileName());
            } else {
                isSkipFlag = true;
            }
        } else  if (TLogTransactionSign.isLogged(getTLogLine())) {
            TLogTransactionFooter currentTLogTransactionFooter = tLogTransactionSingleton.getCurrentTLogTransactionFooter();
            String logged = splitedTlog[3];
            String owner = splitedTlog[6];
            currentTLogTransactionFooter.setLoggedOp(logged);
            currentTLogTransactionFooter.setOwnerOp(owner);
        } else if (TLogTransactionSign.isCheckedNo(getTLogLine())) {
            String tableNo = splitedTlog[5];
            String guesets = splitedTlog[7];
            TLogTransactionFooter currentTLogTransactionFooter = tLogTransactionSingleton.getCurrentTLogTransactionFooter();
            currentTLogTransactionFooter.setTableNo(NumberUtils.phraseLong(tableNo));
            currentTLogTransactionFooter.setGuestsNum(NumberUtils.phraseLong(guesets));
        } else if (TLogTransactionSign.isNoun(getTLogLine())) {
            TLogTransactionNounState nounState = new TLogTransactionNounState();
            context.setTLogState(nounState);
            context.request(getTLogLine());
        } else if (TLogTransactionSign.isFooter(getTLogLine()) || TLogTransactionSign.isTender(getTLogLine())) {
            goToFooter(context);
        }
    }

    private boolean neeedSelection(TLogStateContext context, Date transatcionDate) {
        boolean needSelect = true;
        List<Date> selectionDate = context.getSelectionDate();
        if (!ListUtils.isEmpty(selectionDate)) {
            for (Date filterDate : selectionDate) {
                needSelect = false;
                if (DateUtils.isSameDay(filterDate, transatcionDate)) {
                    needSelect = true;
                    break;
                }
            }

            if (!needSelect) {
                return false;
            }
        }

        Date startTimeDate = context.getStartTimeDate();
        Date endTImeDate = context.getEndTImeDate();
        if (startTimeDate != null) {
            if (!DateUtils.isAfterOrEqualForTime(startTimeDate, transatcionDate)) {
                return false;
            }
        }
        if (endTImeDate != null) {
            if (!DateUtils.isBeforeOrEqualForTime(endTImeDate, transatcionDate)) {
                return false;
            }
        }

        return needSelect;
    }
}
