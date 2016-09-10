package com.vzs.yum.ls.tlog.report.vo;

import com.vzs.yum.ls.tlog.util.DateUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by byao on 9/4/16.
 */
public class TLogSummaryUsageTimeVO {
    @Getter
    private double totalSeconds;
    @Setter
    private Date tempBeginSeconds;
    @Setter
    private Date tempEndSeconds;

    public void commit() {
        double secondsGap = DateUtils.calculdateSecondsGap(tempBeginSeconds, tempEndSeconds);
        totalSeconds += secondsGap;
        tempBeginSeconds = null;
        tempEndSeconds = null;
    }


}
