package com.vzs.yum.ls.tlog.process.state;

import com.vzs.yum.ls.tlog.vo.TLogTransactionHolder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by byao on 8/7/16.
 */
@Data
public class TLogStateContext {
    private TLogState tLogState;
    private String fileName;
    private List<Date> selectionDate;
    public void request(String tLogLine) {
        tLogState.setTLogLine(tLogLine);
        tLogState.Handle(this);
    }
}
