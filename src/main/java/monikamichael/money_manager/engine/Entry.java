package monikamichael.money_manager.engine;

import java.util.List;

// An entry is a financial entry in tables.
// It is a pair (description, value).
public class Entry {
    String description;
    int value;

    public static int sum(List<Entry> entries) {
        int result = 0;
        for (Entry entry : entries)
            result += entry.value;

        return result;
    }
}
