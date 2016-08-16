package com.vzs.yum.ls.tlog.vo;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by byao on 8/6/16.
 */
@Data
public class TLogTransaction {
    private Date transactionHeaderTime;
    private String transactionHeaderId;
    private List<TLogTransactionFooter> transactionFooters = Lists.newArrayList();
    public TLogTransactionFooter createTLogTransactionFooter () {
        TLogTransactionFooter tLogTransactionFooter = new TLogTransactionFooter();
        transactionFooters.add(tLogTransactionFooter);
        return tLogTransactionFooter;
    }
}
