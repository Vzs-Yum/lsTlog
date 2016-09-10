package com.vzs.yum.ls.tlog.report.vo;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Map;

/**
 * Created by byao on 9/4/16.
 */
@Getter
public class TLogSheepOrderVO {
    private Map<Long, Long> sheepCountMap = Maps.newHashMap();
    private Map<Long, Long> excludeSheepCountMap = Maps.newHashMap();

    public void addProd(Long guestNumber, String productName, Long orderCount) {
        if (orderCount == null || guestNumber == null) {
            return;
        }
        Map<Long, Long> dishCountMap;
        if (productName.contains("羊")) {
            dishCountMap = sheepCountMap;
        } else {
            dishCountMap = excludeSheepCountMap;
        }

        Long disCount = dishCountMap.get(guestNumber);
        if (disCount == null) {
            disCount = 0L;
        }
        disCount += orderCount;
        dishCountMap.put(guestNumber, disCount);
    }
}
