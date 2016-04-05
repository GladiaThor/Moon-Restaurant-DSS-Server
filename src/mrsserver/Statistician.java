package mrsserver;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
/**
 * Tools for storing and returning statistics.
 * Optimized for minimal impact on return speed.
 * @author (>^_^)> Claxxess<(^_^<)
 * @version 1.6
 * 
 */
public class Statistician extends Thread {

    String style;
    String priceRange;
    boolean takeaway;
    int fTid;
    float revScore;
    String date;
    String eTime;
    String sTime;
    static Statistics stat = new Statistics();

    /**
     * Runs all checkers in order to build a statistic object with valid values.
     *
     * @param input
     */
    public Statistician(String input) {
        checkStyle(input);
        checkPriceRange(input);
        checkTakeAway(input);
        checkSDiscount(input);
        checkAfterWork(input);
        checkFTid(input);
        checkRevScore(input);
        checkETime(input);
        getDate();
        getSTime();

    }

    /**
     * Builds and executes the statement when intiated through the .start();
     * method.
     */
    public void run() {
        String statement = stat.getStatement();
        MRSServer.add("Loging statiscs from incoming query:"+ statement);
        InsertHandler.insertHandler(statement);
    }

    /**
     * Sets the time the query reached the server
     */
    public static void getSTime() {
        String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        stat.setsTime(time);
    }

    /**
     * Extracts eTime from incoming query.
     *
     * @param input
     */
    public static void getETime(String input) {
        String[] phaseOne = input.split("eTime='", 0);
        String tmi = phaseOne[1];
        String[] phaseTwo = tmi.split("'", 0);
        stat.seteTime(phaseTwo[0]);
        System.out.println(phaseTwo[0]);

    }

    /**
     * Extracts date from incoming query.
     *
     */
    public static void getDate() {
        String date = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
        stat.setDate(date);

    }

    /**
     * Extracts revScore from incoming query.
     *
     * @param input
     */
    public static void getRevScore(String input) {
        String[] phaseOne = input.split("averageScore='", 0);
        String tmi = phaseOne[1];
        String[] phaseTwo = tmi.split("'", 0);
        stat.setRevScore(Float.parseFloat(phaseTwo[0]));
        System.out.println(phaseTwo[0]);

    }

    /**
     * Extracts fTid from incoming query.
     *
     * @param input
     */
    public static void getFTid(String input) {
        String[] phaseOne = input.split("fTID='", 0);
        String tmi = phaseOne[1];
        String[] phaseTwo = tmi.split("'", 0);
        stat.setFTid(Integer.parseInt(phaseTwo[0]));
        System.out.println(phaseTwo[0]);

    }

    /**
     * Extracts after work from incoming query.
     *
     * @param input
     */
    public static void getAfterWork(String input) {
        String[] phaseOne = input.split("afterWork='", 0);
        String tmi = phaseOne[1];
        String[] phaseTwo = tmi.split("'", 0);
        stat.setAfterWork(Integer.parseInt(phaseTwo[0]));
        System.out.println(phaseTwo[0]);

    }

    public static void checkAfterWork(String input) {
        if (input.contains("afterWork='")) {
            getAfterWork(input);
        } else {
        }
    }

    /**
     * Extracts student discount from incoming query.
     *
     * @param input
     */
    public static void getSDiscount(String input) {
        String[] phaseOne = input.split("studentR='", 0);
        String tmi = phaseOne[1];
        String[] phaseTwo = tmi.split("'", 0);
        stat.setsDiscount(Integer.parseInt(phaseTwo[0]));
        System.out.println(phaseTwo[0]);

    }

    public static void checkSDiscount(String input) {
        if (input.contains("studentR='")) {
            getSDiscount(input);
        } else {
        }
    }

    /**
     * Extracts takeAway from incoming query.
     *
     * @param input
     */
    public static void getTakeAway(String input) {
        String[] phaseOne = input.split("takeAway='", 0);
        String tmi = phaseOne[1];
        String[] phaseTwo = tmi.split("'", 0);
        stat.setTakeaway(Integer.parseInt(phaseTwo[0]));
        System.out.println(phaseTwo[0]);

    }

    /**
     * Extracts style from incoming query.
     *
     * @param input
     */
    public static void getStyle(String input) {
        String[] phaseOne = input.split("style='", 0);
        String tmi = phaseOne[1];
        String[] phaseTwo = tmi.split("'", 0);
        stat.setStyle(phaseTwo[0]);
        System.out.println(phaseTwo[0]);

    }

    /**
     * Extracts priceRange from incoming query.
     *
     * @param input
     */
    public static void getPriceRange(String input) {
        String[] phaseOne = input.split("menu.price<='", 0);
        String tmi = phaseOne[1];
        String[] phaseTwo = tmi.split("'", 0);
        stat.setPriceRange(phaseTwo[0]);
        System.out.println(phaseTwo[0]);

    }

    /**
     * Checks for existence of style in incoming query.
     *
     * @param input
     */
    public static void checkStyle(String input) {
        if (input.contains("style='")) {
            getStyle(input);
        } else {
        }
    }

    /**
     * Checks for existence of priceRange in incoming query.
     *
     * @param input
     */
    public static void checkPriceRange(String input) {
        if (input.contains("menu.price<='")) {
            getPriceRange(input);
        } else {
        }
    }

    /**
     * Checks for existence of takeAawy in incoming query.
     *
     * @param input
     */
    public static void checkTakeAway(String input) {
        if (input.contains("takeAway='")) {
            getTakeAway(input);
        } else {
        }
    }

    /**
     * Checks for existence of fTid in incoming query.
     *
     * @param input
     */
    public static void checkFTid(String input) {
        if (input.contains("fTID='")) {
            getTakeAway(input);
        } else {
        }
    }

    /**
     * Checks for existence of revScore in incoming query.
     *
     * @param input
     */
    public static void checkRevScore(String input) {
        if (input.contains("averageScore='")) {
            getRevScore(input);
        } else {
        }
    }

    /**
     * Checks for existence of eTime in incoming query.
     *
     * @param input
     */
    public static void checkETime(String input) {
        if (input.contains("eTime='")) {
            getETime(input);
        } else {
        }
    }

    /**
     * Calculates the amount of searches for specified price ranges.
     *
     * @return statistics for price range searches
     */
    public static ArrayList<String> priceRanger() {
        ArrayList<String> returnAL = new ArrayList();
        int twentyeighttosparta = 0, twentysixtotwentyeght = 0, twentyfourtotwentysix = 0, twentytwotwentyfour = 0, twentytotwentytwo = 0, noPref = 0, ztofour = 0, fourtosix = 0, sixtoeight = 0, eighttoten = 0, tentotwelve = 0, twelvetofourteen = 0, fourteentosixteen = 0, sixteentoeighteen = 0, eighteentotwenty = 0;
        ArrayList<String> statAL = QueryHandler.queryHandler("SELECT priceRange FROM rdds_moon.statistics");
        for (int counter = 0; counter < statAL.size(); counter++) {
            if (statAL.get(counter).equals("$null$")) {
                noPref++;
            } else if (Integer.parseInt(statAL.get(counter).replace("$", "")) >= 0 && Integer.parseInt(statAL.get(counter).replace("$", "")) <= 40) {
                ztofour++;
            } else if (Integer.parseInt(statAL.get(counter).replace("$", "")) >= 41 && Integer.parseInt(statAL.get(counter).replace("$", "")) <= 60) {
                fourtosix++;
            } else if (Integer.parseInt(statAL.get(counter).replace("$", "")) >= 61 && Integer.parseInt(statAL.get(counter).replace("$", "")) <= 80) {
                sixtoeight++;
            } else if (Integer.parseInt(statAL.get(counter).replace("$", "")) >= 81 && Integer.parseInt(statAL.get(counter).replace("$", "")) <= 100) {
                eighttoten++;
            } else if (Integer.parseInt(statAL.get(counter).replace("$", "")) >= 101 && Integer.parseInt(statAL.get(counter).replace("$", "")) <= 120) {
                tentotwelve++;
            } else if (Integer.parseInt(statAL.get(counter).replace("$", "")) >= 121 && Integer.parseInt(statAL.get(counter).replace("$", "")) <= 140) {
                twelvetofourteen++;
            } else if (Integer.parseInt(statAL.get(counter).replace("$", "")) >= 141 && Integer.parseInt(statAL.get(counter).replace("$", "")) <= 160) {
                fourteentosixteen++;
            } else if (Integer.parseInt(statAL.get(counter).replace("$", "")) >= 161 && Integer.parseInt(statAL.get(counter).replace("$", "")) <= 180) {
                sixteentoeighteen++;
            } else if (Integer.parseInt(statAL.get(counter).replace("$", "")) >= 181 && Integer.parseInt(statAL.get(counter).replace("$", "")) <= 200) {
                eighteentotwenty++;
            } else if (Integer.parseInt(statAL.get(counter).replace("$", "")) >= 201 && Integer.parseInt(statAL.get(counter).replace("$", "")) <= 220) {
                twentytotwentytwo++;
            } else if (Integer.parseInt(statAL.get(counter).replace("$", "")) >= 221 && Integer.parseInt(statAL.get(counter).replace("$", "")) <= 240) {
                twentytwotwentyfour++;
            } else if (Integer.parseInt(statAL.get(counter).replace("$", "")) >= 241 && Integer.parseInt(statAL.get(counter).replace("$", "")) <= 260) {
                twentyfourtotwentysix++;
            } else if (Integer.parseInt(statAL.get(counter).replace("$", "")) >= 261 && Integer.parseInt(statAL.get(counter).replace("$", "")) <= 280) {
                twentysixtotwentyeght++;
            } else if (Integer.parseInt(statAL.get(counter).replace("$", "")) >= 281 && Integer.parseInt(statAL.get(counter).replace("$", "")) < 300) {
                twentyeighttosparta++;
            }


        }
        returnAL.add("$" + noPref + "$");
        returnAL.add("$" + ztofour + "$");
        returnAL.add("$" + fourtosix + "$");
        returnAL.add("$" + sixtoeight + "$");
        returnAL.add("$" + eighttoten + "$");
        returnAL.add("$" + tentotwelve + "$");
        returnAL.add("$" + twelvetofourteen + "$");
        returnAL.add("$" + fourteentosixteen + "$");
        returnAL.add("$" + sixteentoeighteen + "$");
        returnAL.add("$" + eighteentotwenty + "$");
        returnAL.add("$" + twentytotwentytwo + "$");
        returnAL.add("$" + twentytwotwentyfour + "$");
        returnAL.add("$" + twentyfourtotwentysix + "$");
        returnAL.add("$" + twentysixtotwentyeght + "$");
        returnAL.add("$" + twentyeighttosparta + "$");
        returnAL.add("$ Order: [noPref][0-40][41-60][61-80][81-100][101-120]"
                + "[121-140][141-160][161-180][181-200][201-220][221-240]"
                + "[241-260][261-280][281-300]etc.");
        return returnAL;
    }

    /**
     * calculates the searches for all food type searches
     *
     * @return the statistics for food type searches
     */
    public static ArrayList<String> foodType() {
        ArrayList<String> foodTypeTitleAL = QueryHandler.queryHandler("SELECT fTTitle FROM rdds_moon.foodtype");
        ArrayList<String> foodTypeIDStringAL = QueryHandler.queryHandler("SELECT fTID FROM rdds_moon.foodtype");
        ArrayList<Integer> foodTypeIDIntAL = new ArrayList();
        for (int counter = 0; counter < foodTypeIDStringAL.size(); counter++) {
            foodTypeIDIntAL.add(Integer.parseInt((foodTypeIDStringAL.get(counter)).replace("$", "")));
        }


        String[] typeName = new String[foodTypeTitleAL.size()];
        int[] typeID = new int[foodTypeTitleAL.size()];
        int[] typeCount = new int[foodTypeTitleAL.size()];

        for (int counter = 0; counter < foodTypeTitleAL.size(); counter++) {
            typeName[counter] = foodTypeTitleAL.get(counter);
            typeID[counter] = foodTypeIDIntAL.get(counter);
            typeCount[counter] = 0;
        }
        ArrayList<String> statisticsValuesStringAL = QueryHandler.queryHandler("SELECT FTID FROM rdds_moon.statistics");
        ArrayList<Integer> statisticsValuesIntAL = new ArrayList();
        for (int counter = 0; counter < statisticsValuesStringAL.size(); counter++) {
            statisticsValuesIntAL.add(Integer.parseInt((statisticsValuesStringAL.get(counter)).replace("$", "")));
        }
        for (int counter = 0; counter < statisticsValuesIntAL.size(); counter++) {
            int compare = statisticsValuesIntAL.get(counter);
            for (int inCounter = 0; inCounter < typeID.length; inCounter++) {
                if (compare == typeID[inCounter]) {
                    typeCount[inCounter]++;
                } else {
                }
            }
        }
        ArrayList<String> returnList = new ArrayList();
        for (int counter = 0; counter < typeID.length; counter++) {
            returnList.add(typeName[counter] + typeCount[counter] + "$");
        }
        return returnList;
    }

    /**
     * Calculates the spread of total searches on certain review scores.
     *
     * @return a string of values for from 0 to 5 stars.
     */
    public static ArrayList<String> revScoreTotals() {
        int noPref = 0, oneStar = 0, twoStar = 0, threeStar = 0, fourStar = 0, fiveStar = 0;
        ArrayList<String> revScoreAL = QueryHandler.queryHandler("SELECT reviewScore FROM rdds_moon.statistics");
        for (int counter = 0; counter < revScoreAL.size(); counter++) {
            if (Float.parseFloat(revScoreAL.get(counter).replace("$", "")) == 5) {
                fiveStar++;
            } else if (Float.parseFloat(revScoreAL.get(counter).replace("$", "")) == 4) {
                fourStar++;
            } else if (Float.parseFloat(revScoreAL.get(counter).replace("$", "")) == 3) {
                threeStar++;
            } else if (Float.parseFloat(revScoreAL.get(counter).replace("$", "")) == 2) {
                twoStar++;
            } else if (Float.parseFloat(revScoreAL.get(counter).replace("$", "")) == 1) {
                oneStar++;
            } else if (Float.parseFloat(revScoreAL.get(counter).replace("$", "")) == 0) {
                noPref++;
            }
        }
        ArrayList<String> returnAL = new ArrayList();
        returnAL.add("$" + noPref + "$");
        returnAL.add("$" + oneStar + "$");
        returnAL.add("$" + twoStar + "$");
        returnAL.add("$" + threeStar + "$");
        returnAL.add("$" + fourStar + "$");
        returnAL.add("$" + fiveStar + "$");
        returnAL.add("$ Order:[no pref][1][2][3][4][5]");
        return returnAL;
    }

    /**
     * compiles score averages for a restaurant using the restID
     *
     * @param restID
     * @return The average of the scores of all categories
     */
    public static ArrayList<String> restScoreStatsSingleRestaurant(String restID) {
        ArrayList<String> avgScoreAL = QueryHandler.queryHandler("SELECT avg(averageScore) FROM rdds_moon.reviews WHERE restID='" + restID + "'");
        ArrayList<String> atmosScoreAL = QueryHandler.queryHandler("SELECT avg(atmosScore) FROM rdds_moon.reviews WHERE restID='" + restID + "'");
        ArrayList<String> foodQualScoreAL = QueryHandler.queryHandler("SELECT avg(foodQualScore) FROM rdds_moon.reviews WHERE restID='" + restID + "'");
        ArrayList<String> affordableScoreAL = QueryHandler.queryHandler("SELECT avg(affordableScore) FROM rdds_moon.reviews WHERE restID='" + restID + "'");
        ArrayList<String> staffScoreAL = QueryHandler.queryHandler("SELECT avg(staffScore) FROM rdds_moon.reviews WHERE restID='" + restID + "'");
        avgScoreAL.addAll(atmosScoreAL);
        avgScoreAL.addAll(foodQualScoreAL);
        avgScoreAL.addAll(affordableScoreAL);
        avgScoreAL.addAll(staffScoreAL);
        avgScoreAL.add("Order:[Avgstar][Atmosphere][Foodquality][Affordable][Staff]");
        return avgScoreAL;
    }

    /**
     * Calculates the percentage of total hits on a restaurants style based on
     * the ID.
     *
     * @param restID
     * @return percentage of hits on chosen style.
     */
    public static ArrayList<String> styleSearchChecker(String restID) {
        int counter = 0;
        double intCounter = 0, totalCounter = 0;
        ArrayList<String> styleAL = QueryHandler.queryHandler("SELECT style FROM rdds_moon.statistics");
        ArrayList<String> restStyleAL = QueryHandler.queryHandler("SELECT style FROM rdds_moon.restaurant WHERE restID=" + restID);
        for (counter = 0; counter < styleAL.size(); counter++) {
            totalCounter = totalCounter + 1;
            if ((styleAL.get(counter).isEmpty()) == true) {
            } else if (styleAL.get(counter).equals("$null$")) {
            } else if (Integer.parseInt(styleAL.get(counter).replace("$", "")) == Double.parseDouble(restStyleAL.get(0).replace("$", ""))) {
                intCounter = intCounter + 1.0;
            } else {
            }

        }
        System.out.println(intCounter + ";;" + counter);
        double doubleCounter = (intCounter / totalCounter) * 100;
        ArrayList<String> returnAL = new ArrayList();
        returnAL.add("$" + doubleCounter + "$");
        returnAL.add("$ [This is the number of style searches that match the rest IDs Style] $");
        return returnAL;
    }

    /**
     * Calculates the percentage of the total of searches who look for takeaway.
     *
     * @return percentage of the total searches
     */
    public static ArrayList<String> takeAwayTotal() {
        double inCounterDouble = 0, takeAwayCounterDouble = 0;
        ArrayList<String> takeAwayAL = QueryHandler.queryHandler("SELECT takeaway FROM rdds_moon.statistics");
        for (int counter = 1; counter < takeAwayAL.size(); counter++) {
            inCounterDouble++;
                       if (takeAwayAL.get(counter).replace("$","").equals("null")){
                
            }
            else if (Integer.parseInt(takeAwayAL.get(counter).replace("$", "")) == 1) {
                takeAwayCounterDouble++;
            }
        }
        double doubleCounter = (takeAwayCounterDouble/inCounterDouble ) * 100;
        ArrayList<String> returnAL = new ArrayList();
        returnAL.add("$" + doubleCounter + "$");
        returnAL.add("$ [This is the percentage of searches who look for takeaway] $");
        return returnAL;
    }

    /**
     * Calculates the percentage of the total of searches who look for student
     * discount.
     *
     * @return percentage of the total searches
     */
    public static ArrayList<String> studentDiscountTotal() {
        double inCounterDouble = 0, sDicountDouble = 0;
        ArrayList<String> sDiscountAL = QueryHandler.queryHandler("SELECT studentR FROM rdds_moon.statistics");
        for (int counter = 1; counter < sDiscountAL.size(); counter++) {
            inCounterDouble++;
                        if (sDiscountAL.get(counter).replace("$","").equals("null")){
                
            }
            else if (Integer.parseInt(sDiscountAL.get(counter).replace("$", "")) == 1) {
                sDicountDouble++;
            }
        }
        double doubleCounter = ( sDicountDouble/inCounterDouble) * 100;
        ArrayList<String> returnAL = new ArrayList();
        returnAL.add("$" + doubleCounter + "$");
        returnAL.add("$ [This is the percentage of searches who look for student discounts] $");
        return returnAL;
    }

    /**
     * Calculates the percentage of the total of searches who look for after
     * work.
     *
     * @return percentage of the total searches
     */
    public static ArrayList<String> afterWorkTotal() {
        double inCounterDouble = 0, afterWorkCounterDouble = 0;
        ArrayList<String> afterWorkAL = QueryHandler.queryHandler("SELECT afterWork FROM rdds_moon.statistics");
        for (int counter = 1; counter < afterWorkAL.size(); counter++) {
            inCounterDouble++;
            if (afterWorkAL.get(counter).replace("$","").equals("null")){
                
            }
            else if (Integer.parseInt(afterWorkAL.get(counter).replace("$", "")) == 1) {
                afterWorkCounterDouble++;
            }
        }
        double doubleCounter = ( afterWorkCounterDouble/inCounterDouble ) * 100;
        ArrayList<String> returnAL = new ArrayList();
        returnAL.add("$" + doubleCounter + "$");
        returnAL.add("$ [This is the percentage of searches who look for after work] $");
        return returnAL;
    }

    /**
     * Calculates the distribution of style searches.
     *
     * @return distribution
     */
    public static ArrayList<String> restStyleTotal() {
        ArrayList<String> foodTypeTitleAL = QueryHandler.queryHandler("SELECT styleTitle FROM rdds_moon.style");
        ArrayList<String> foodTypeIDStringAL = QueryHandler.queryHandler("SELECT styleID FROM rdds_moon.style");
        ArrayList<Integer> foodTypeIDIntAL = new ArrayList();
        for (int counter = 0; counter < foodTypeIDStringAL.size(); counter++) {
            foodTypeIDIntAL.add(Integer.parseInt((foodTypeIDStringAL.get(counter)).replace("$", "")));
        }


        String[] typeName = new String[foodTypeTitleAL.size()];
        int[] typeID = new int[foodTypeTitleAL.size()];
        int[] typeCount = new int[foodTypeTitleAL.size()];

        for (int counter = 0; counter < foodTypeTitleAL.size(); counter++) {
            typeName[counter] = foodTypeTitleAL.get(counter);
            typeID[counter] = foodTypeIDIntAL.get(counter);
            typeCount[counter] = 0;
        }
        ArrayList<String> statisticsValuesStringAL = QueryHandler.queryHandler("SELECT style FROM rdds_moon.statistics");
        ArrayList<Integer> statisticsValuesIntAL = new ArrayList();
        for (int counter = 0; counter < statisticsValuesStringAL.size(); counter++) {
            if (statisticsValuesStringAL.get(counter).equals("$null$")) {
            } else {
                statisticsValuesIntAL.add(Integer.parseInt((statisticsValuesStringAL.get(counter)).replace("$", "")));
            }
        }
        for (int counter = 0; counter < statisticsValuesIntAL.size(); counter++) {
            int compare = statisticsValuesIntAL.get(counter);
            for (int inCounter = 0; inCounter < typeID.length; inCounter++) {
                if (compare == typeID[inCounter]) {
                    typeCount[inCounter]++;
                } else {
                }
            }
        }
        ArrayList<String> returnList = new ArrayList();
        for (int counter = 0; counter < typeID.length; counter++) {
            returnList.add(typeName[counter] + typeCount[counter] + "$");
        }
        return returnList;
    }
}
