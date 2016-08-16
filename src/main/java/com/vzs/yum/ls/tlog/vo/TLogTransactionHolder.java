package com.vzs.yum.ls.tlog.vo;

import com.google.common.collect.Maps;
import com.vzs.yum.ls.tlog.util.DateUtils;
import lombok.Getter;

import java.util.Date;
import java.util.Map;

/**
 * Created by byao on 8/7/16.
 */
public class TLogTransactionHolder {
    @Getter
    Map<String, TLogTransaction> logTransactionMap = Maps.newHashMap(); // key is combine id, different date with same transaction id is different transaction
    public TLogTransaction findTransationLog(String transactionId, Date transactionDate) {
        String key = combineKey(transactionId, transactionDate);
        TLogTransaction tLogTransaction = logTransactionMap.get(key);
        if (tLogTransaction == null) {
            tLogTransaction = new TLogTransaction();
            tLogTransaction.setTransactionHeaderId(transactionId);
            tLogTransaction.setTransactionHeaderTime(transactionDate);
        }
        logTransactionMap.put(key, tLogTransaction);
        return tLogTransaction;
    }

    private String combineKey(String transactionId, Date date) {
        return transactionId + "|" + DateUtils.formatToDateOnly(date);
    }
}
