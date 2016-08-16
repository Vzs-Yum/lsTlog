package com.vzs.yum.ls.tlog.process.state;

/**
 * Created by byao on 8/7/16.
 */
public class TLogTransactionSign {
    private static String transactionHeaderStart = "Transaction Header";
    private static String transactionNoun = "Noun";
    private static String NounMode = "Mod";
    private static String mealDeal = "Mealdeal";
    private static String total = ".*\\sTotal\\s.*";
    private static String footer = "Transaction Footer";
    private static String tender = "Tender";
    private static String logged = "Logged";
    private static String checkNo = "Check No";
    private static String nounExisting = "Security -";

    public static boolean isNoun(String string) {
        return string.contains(transactionNoun) && !string.contains("Promo");
    }

    public static boolean isHeaderStart(String string) {
        return string.contains(transactionHeaderStart);
    }

    public static boolean isNounMode(String tLogLine) {
        return tLogLine.contains(NounMode);
    }

    public static boolean isMealDeal(String tLogLine) {
        return tLogLine.contains(mealDeal);
    }

    public static boolean isTotal (String line) {
        return line.matches(total);
    }

    public static boolean isExisting(String line) {
        return line.contains(nounExisting);
    }

    public static boolean isFooter (String line) {
        return line.contains(footer);
    }

    public static boolean isTender (String line) {
        return line.contains(tender);
    }

    public static boolean isLogged (String line) {
        return line.contains(logged);
    }

    public static boolean isCheckedNo (String line) {
        return line.contains(checkNo);
    }
}
