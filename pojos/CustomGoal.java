package pojos;

import java.math.BigDecimal;

public class CustomGoal {
    private String name;
    private BigDecimal amountNeeded;
    private int userID;

    public CustomGoal() {}

    public CustomGoal(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmountNeeded() {
        return amountNeeded;
    }

    public void setAmountNeeded(BigDecimal amountNeeded) {
        this.amountNeeded = amountNeeded;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
