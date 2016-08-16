package com.vzs.yum.ls.tlog.vo;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by byao on 8/6/16.
 */
@Data
public class TLogTransactionFooter {
    private Date transactionFooterDate;
    private String loggedOp;
    private String ownerOp;
    private Double total;
    private Double tender;
    private Long tableNo;
    private Long guestsNum;
    private List<TLogTransactionNoun> nouns = Lists.newArrayList();

    public TLogTransactionNoun createNoun() {
        TLogTransactionNoun noun = new TLogTransactionNoun();
        nouns.add(noun);
        return noun;
    }
}
