package monikamichael.money_manager.engine;

public class Category {

    public static String parseCategory(String category) {
        if (category.equals("clothes") || category.equals("Clothes")) {
            return "Clothes";
        } else if (category.equals("cosmetics") || category.equals("Cosmetics")) {
            return "Cosmetics";
        } else if (category.contains("hobby") || category.contains("Hobby")) {
            return "Hobby and books";
        } else if (category.contains("meetings") || category.contains("Meetings")) {
            return "Dates and meetings";
        } else {
            return "Other";
        }
    }
}
