package com.vzs.yum.ls.tlog.vo;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * Created by byao on 8/6/16.
 */
@Data
public class TLogTransactionNoun {
    private Long quantity;
    private String name;
    private Double unitPrice;
    private Double totalPrice;
    private String mod;
    private Boolean isSet;
    private boolean preDelete;
    private boolean postDelete;
    private boolean isExisting;
    private List<TLogTransactionNoun> setDetailNouns = Lists.newArrayList();

    public TLogTransactionNoun createSetNoun() {
        TLogTransactionNoun noun = new TLogTransactionNoun();
        setDetailNouns.add(noun);
        return noun;
    }


}
