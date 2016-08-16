package com.vzs.yum.ls.tlog.process.state;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by byao on 8/7/16.
 */
@Setter
@Getter
public abstract class TLogState {
    private String tLogLine;
    public abstract void Handle(TLogStateContext context);
    protected void goToFooter(TLogStateContext context) {
        TLogTransactionFooterState footerState = new TLogTransactionFooterState();
        context.setTLogState(footerState);
        context.request(getTLogLine());
    }
}
