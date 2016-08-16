package com.vzs.yum.ls.tlog.process.state;

/**
 * Created by byao on 8/7/16.
 */
public class TLogFileStartState extends TLogState{

    public void Handle(TLogStateContext context) {
        if (TLogTransactionSign.isHeaderStart(getTLogLine())) {
            TLogTransactionStartState tLogTransactionStartState = new TLogTransactionStartState();

            context.setTLogState(tLogTransactionStartState);
            context.request(getTLogLine());
        }
    }
}
