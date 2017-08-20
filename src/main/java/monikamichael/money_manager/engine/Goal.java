package monikamichael.money_manager.engine;

import java.util.Date;

public class Goal {
    private String name;
    private Date dueDate;

    private int price;
    private int collectedMoney;

    public Goal(String name, Date dueDate, int price) {
        this.name = name;
        this.dueDate = dueDate;
        this.price = price;

        this.collectedMoney = 0;
    }
    public void setDueDate(Date date) {
        this.dueDate = date;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public void transferMoneyForGoal(int money) {
        collectedMoney += money;
    }
    public float getInfoAboutAlreadyCollectedMoney() {
        return collectedMoney;
    }


}

