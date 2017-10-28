package monikamichael.money_manager.engine;

import java.sql.Date;

public class Goal {
    private String name;
    private Date dueDate;

    private int price;
    private int collectedMoney;

    public Goal() {
        this.collectedMoney = 0;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }
    public void setDueDate(Date date) {
        this.dueDate = date;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public int getCollectedMoney() {
        return collectedMoney;
    }
    public void transferMoneyForGoal(int money) {
        collectedMoney += money;
    }
    public float getAlreadySavedMoney() {
        return collectedMoney;
    }


}

