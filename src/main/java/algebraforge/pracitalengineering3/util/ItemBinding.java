package algebraforge.pracitalengineering3.util;

import algebraforge.pracitalengineering3.components.Item;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ItemBinding {
    public static StringBinding createItemBinding(String name, ObservableList<Item> items) {
        return Bindings.createStringBinding(() -> name + convertItemsToString(items), items);
    }

    public static String convertItemsToString(List<Item> items){
        List<String> numbers = new ArrayList<>();

        for (var item : items) {
            numbers.add(item.getNumberProperty().get());
        }

        return "[" + String.join(", ", numbers) + "]";
    }
}
