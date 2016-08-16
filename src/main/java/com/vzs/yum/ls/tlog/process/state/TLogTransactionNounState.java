package com.vzs.yum.ls.tlog.process.state;

import com.vzs.yum.ls.tlog.util.NumberUtils;
import com.vzs.yum.ls.tlog.util.StringUtils;
import com.vzs.yum.ls.tlog.vo.TLogTransactionNoun;
import com.vzs.yum.ls.tlog.vo.TLogTransactionSingleton;

/**
 * Created by byao on 8/7/16.
 */
public class TLogTransactionNounState extends TLogState{
    boolean isMealDeal = false;
    TLogTransactionSingleton tLogTransactionSingleton = TLogTransactionSingleton.getInstance();
    public void Handle(TLogStateContext context) {
        String[] splitedLine = StringUtils.splictStringBySpace(getTLogLine());
        if (TLogTransactionSign.isNoun(getTLogLine()) && isMealDeal) {
            TLogTransactionNoun setNoun = tLogTransactionSingleton.createSetNoun();
            fillNoun(splitedLine, setNoun);
            isMealDeal = false;
        } else if (TLogTransactionSign.isNoun(getTLogLine())) {
            TLogTransactionNoun noun = tLogTransactionSingleton.createNoun();
            fillNoun(splitedLine, noun);
        } else if (TLogTransactionSign.isExisting(getTLogLine())) {
            TLogTransactionNoun noun = tLogTransactionSingleton.getCurrentTransactionNoun();
            noun.setExisting(true);
        } else if (TLogTransactionSign.isNounMode(getTLogLine())) {
            String mod = splitedLine[4];
            TLogTransactionNoun currentTransactionNoun = tLogTransactionSingleton.getCurrentTransactionNoun();
            currentTransactionNoun.setMod(mod);
        } else if (TLogTransactionSign.isMealDeal(getTLogLine())) {
            isMealDeal = true;
        } else if (TLogTransactionSign.isTotal(getTLogLine()) || TLogTransactionSign.isTender(getTLogLine())) {
            goToFooter(context);
        } else if (TLogTransactionSign.isFooter(getTLogLine())) {
            goToFooter(context);
        }

    }

    private void fillNoun(String[] splitedLine, TLogTransactionNoun noun) {
        String nounSing = splitedLine[2];
        String count = splitedLine[4];
        String dishName = splitedLine[5];

        boolean isSet = dishName.contains("T");
        noun.setName(dishName);
        noun.setIsSet(isSet);
        noun.setQuantity(NumberUtils.phraseLong(count));


        if (nounSing.contains("**")) {
            String unitPrice = splitedLine[7].replace("$", "");
            noun.setUnitPrice(NumberUtils.pharseDouble(unitPrice));
            noun.setPostDelete(true);
        } else if (nounSing.contains("*")) {
            String unitPrice = splitedLine[8].replace("$", "");
            noun.setUnitPrice(NumberUtils.pharseDouble(unitPrice));
            noun.setPreDelete(true);
        } else {
            String unitPrice = splitedLine[7].replace("$", "");
            String totalPrice = splitedLine[8].replace("$", "");
            noun.setUnitPrice(NumberUtils.pharseDouble(unitPrice));
            noun.setTotalPrice(NumberUtils.pharseDouble(totalPrice));
        }
    }
}
