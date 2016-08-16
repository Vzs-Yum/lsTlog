package com.vzs.yum.ls.tlog.process.state;

import com.vzs.yum.ls.tlog.vo.TLogTransactionHolder;
import lombok.Data;

/**
 * Created by byao on 8/7/16.
 */
@Data
public class TLogStateContext {
    private TLogState tLogState;
    public void request(String tLogLine) {
        tLogState.setTLogLine(tLogLine);
        tLogState.Handle(this);
    }
}
