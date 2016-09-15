package com.vzs.yum.ls.tlog.report.vo;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by byao on 9/4/16.
 */
@Getter
@Slf4j
public class TLogSummaryAddOrderVO {
    private long totalAddCount;
    private long totalAddCountWithSetDetail;

    public void addBoth() {
        totalAddCount ++;
        totalAddCountWithSetDetail ++;
    }

    public void addNormalCount() {
        totalAddCount ++;
        //        log.debug("normal add one " + totalAddCount);
    }

    public void addCountWithSetDetail () {
        totalAddCountWithSetDetail ++;
//        log.debug("set add one " + totalAddCountWithSetDetail);
    }

}
