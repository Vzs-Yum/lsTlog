package com.vzs.yum.ls.tlog.report.vo;

import lombok.Getter;

/**
 * Created by byao on 9/4/16.
 */
public class TLogSummaryGuestTotalVO {
    @Getter
    private long guestTotalCount;
    private long tempGuestCount;

    public void addGeustCount(Long guestCount) {
        if (guestCount != null) {
            tempGuestCount = guestCount;
        }
    }

    public void commit() {
        guestTotalCount += tempGuestCount;
        tempGuestCount = 0L;
    }
}
