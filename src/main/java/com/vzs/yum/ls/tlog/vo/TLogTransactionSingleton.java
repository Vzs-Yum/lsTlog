package com.vzs.yum.ls.tlog.vo;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by byao on 8/9/16.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TLogTransactionSingleton {
    private static final TLogTransactionSingleton _instance = new TLogTransactionSingleton();
    public static TLogTransactionSingleton getInstance () {
        return _instance;
    }


    private TLogTransactionHolder tLogTransactionHolder = new TLogTransactionHolder();

    private TLogTransaction currentTLogTransaction;
    private TLogTransactionFooter currentTLogTransactionFooter;
    private TLogTransactionNoun currentTransactionNoun;

    public TLogTransaction findTransationLogAndCreateFooter(String transactionId, Date transactionDate, String fileName) {
        TLogTransaction transationLog = tLogTransactionHolder.findTransationLog(transactionId, transactionDate);
        currentTLogTransaction = transationLog;
        currentTLogTransactionFooter = currentTLogTransaction.createTLogTransactionFooter();
        currentTLogTransactionFooter.setFileName(fileName);
        return transationLog;
    }

    public TLogTransactionNoun createNoun() {
        currentTransactionNoun = currentTLogTransactionFooter.createNoun();
        return currentTransactionNoun;
    }


    public TLogTransactionNoun createSetNoun() {
        TLogTransactionNoun setNoun = currentTransactionNoun.createSetNoun();
        currentTransactionNoun = setNoun;
        return setNoun;
    }

    public List<TLogTransaction> findAllTLogTransaction() {
        Map<String, TLogTransaction> logTransactionMap = tLogTransactionHolder.getLogTransactionMap();
        return Lists.newArrayList(logTransactionMap.values());
    }



}
