package monikamichael.money_manager.engine;

public class EntryBuilder {

    public String entryType;
    public String description;
    public String category;
    public String queueName;
    public int value;

    public static EntryBuilder entry() {
        return new EntryBuilder();
    }

    public EntryBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public EntryBuilder withCategory(String category) {
        this.category = category;
        return this;
    }

    public EntryBuilder withEntryType(String entryType) {
        this.entryType = entryType;
        return this;
    }

    public EntryBuilder withValue(int value) {
        this.value = value;
        return this;
    }
    public EntryBuilder withQueueName(String queueName) {
        this.queueName = queueName;
        return this;
    }

    public Entry build() {
        return new Entry(this);
    }
}
