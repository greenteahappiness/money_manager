package monikamichael.money_manager.engine;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Card {
    private String name;
    private String description;
    private float price;
    private String queueName;
}

