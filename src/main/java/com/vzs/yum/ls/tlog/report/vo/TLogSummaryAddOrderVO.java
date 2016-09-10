package com.vzs.yum.ls.tlog.report.vo;

import lombok.Getter;

/**
 * Created by byao on 9/4/16.
 */
@Getter
public class TLogSummaryAddOrderVO {
    private long totalAddCount;
    private long totalAddCountWithSetDetail;

    public void addBoth() {
        totalAddCount ++;
        totalAddCountWithSetDetail ++;
    }

    public void addNormalCount() {
        totalAddCount ++;
    }

    public void addCountWithSetDetail () {
        totalAddCountWithSetDetail ++;
    }

}
