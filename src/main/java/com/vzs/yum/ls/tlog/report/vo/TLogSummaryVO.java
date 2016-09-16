package com.vzs.yum.ls.tlog.report.vo;

import lombok.Data;

/**
 * Created by byao on 9/4/16.
 */
@Data
public class TLogSummaryVO {
    private int totalOrderCount;
    private TLogSummaryUsageTimeVO tLogSummaryUsageTimeVO = new TLogSummaryUsageTimeVO();
    private TLogSummaryGuestTotalVO tLogSummaryGuestTotalVO = new TLogSummaryGuestTotalVO();
    private TLogSummaryAddOrderVO tLogSummaryAddOrderVO = new TLogSummaryAddOrderVO();
    private TLogSheepOrderVO tLogSheepOrderVO = new TLogSheepOrderVO();


}
