package monikamichael.money_manager.engine;

import lombok.Data;

import java.util.List;

// An entry is a financial entry in tables.
// It is a pair (description, value, category).

@Data
public class Entry {
    public String entryType;
    public String description;
    public String category;
    public String queueName;
    public int value;

    public Entry() {
    }

    public Entry(EntryBuilder entryBuilder) {
        this.description = entryBuilder.description;
        this.category = entryBuilder.category;
        this.entryType = entryBuilder.entryType;
        this.value = entryBuilder.value;
        this.queueName = entryBuilder.queueName;
    }

    public static int sum(List<Entry> entries) {
        int result = 0;
        for (Entry entry : entries)
            result += entry.value;

        return result;
    }
}