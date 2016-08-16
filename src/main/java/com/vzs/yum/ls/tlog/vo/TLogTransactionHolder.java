package com.vzs.yum.ls.tlog.vo;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Date;
import java.util.Map;

/**
 * Created by byao on 8/7/16.
 */
public class TLogTransactionHolder {
    @Getter
    Map<String, TLogTransaction> logTransactionMap = Maps.newHashMap(); // key is transactionHeaderId
    public TLogTransaction findTransationLog(String transactionId, Date transactionDate) {
        TLogTransaction tLogTransaction = logTransactionMap.get(transactionId);
        if (tLogTransaction == null) {
            tLogTransaction = new TLogTransaction();
            tLogTransaction.setTransactionHeaderId(transactionId);
            tLogTransaction.setTransactionHeaderTime(transactionDate);
        }
        logTransactionMap.put(transactionId, tLogTransaction);
        return tLogTransaction;
    }
}
