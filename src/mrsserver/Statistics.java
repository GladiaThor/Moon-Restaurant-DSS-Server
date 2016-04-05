package mrsserver;

/**
 * Object Class for server statistics.<\br> Only has set methods.
 *
 * @author (>^_^)> Claxxess<(^_^<)
 * @version 1.5
 */
public class Statistics {

    String style = "null";
    String priceRange = "null";
    int takeaway = 0;
    int afterWork = 0;
    int studentR = 0;
    int fTid = 1;
    float revScore = 0;
    String date = "null";
    String eTime = "null";
    String sTime = "null";

    /**
     * Statistic object default and only constructor. values must be set through
     * the set statements.
     */
    public Statistics() {
    }

    /**
     * Sets the style in the statistics object.
     *
     * @param style
     */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * Sets the price range in the statistics object.
     *
     * @param priceRange
     */
    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    /**
     * Sets the after work in the statistics object.
     *
     * @param afterWork
     */
    public void setAfterWork(int afterWork) {
        this.afterWork = afterWork;
    }

    /**
     * Sets the student discount in the statistics object.
     *
     * @param sDiscount
     */
    public void setsDiscount(int sDiscount) {
        this.studentR = sDiscount;
    }

    /**
     * Sets the take away in the statistics object.
     *
     * @param takeaway
     */
    public void setTakeaway(int takeaway) {
        this.takeaway = takeaway;
    }

    /**
     * Sets the fTid in the statistics object.
     *
     * @param fTid
     */
    public void setFTid(int fTid) {
        this.fTid = fTid;
    }

    /**
     * Sets the revScore in the statistics object.
     *
     * @param revScore
     */
    public void setRevScore(float revScore) {
        this.revScore = revScore;
    }

    /**
     * Sets the date in the statistics object.
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Sets the eTime in the statistics object.
     *
     * @param eTime
     */
    public void seteTime(String eTime) {
        this.eTime = eTime;
    }

    /**
     * Sets the sTime in the statistics object.
     *
     * @param sTime
     */
    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    /**
     * Creates a DB statement for inserting the information from this statistics
     * object into a database through JDBC.
     *
     * @return Database Statement
     */
    public String getStatement() {
        String style = nullifier(this.style);
        String priceRange = nullifier(this.priceRange);
        String eTime = nullifier(this.eTime);



        return "INSERT INTO `rdds_moon`.`statistics` (`style`, `priceRange`, `takeaway`, `fTID`,  `reviewScore`, `searchDate`,`eatingTime`, `searchTime`,`studentR`,`afterWork`) "
                + "VALUES (" + style + "," + priceRange + ",'" + takeaway + "','" + fTid + "','" + revScore + "','" + date + "','" + eTime + "','" + sTime + "','" + studentR + "','" + afterWork + "' )";

    }

    /**
     * Adds the correct marker to values to counter rejection in case of null
     *
     * @param originalString
     * @return Fixed String
     */
    public String nullifier(String originalString) {
        String finalString;
        if (originalString.equals("null")) {
            finalString = originalString;
        } else {
            finalString = "'" + originalString + "'";
        }
        return finalString;

    }
}
