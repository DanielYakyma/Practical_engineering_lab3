package algebraforge.pracitalengineering3.main;

import algebraforge.pracitalengineering3.components.Item;
import algebraforge.pracitalengineering3.components.Tab;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.ArrayList;
import java.util.List;

class Model {
    public void createItem() {
        Item newItem = new Item();

        newItem.getNumberProperty().bind(Bindings.createStringBinding(
                () -> String.valueOf(itemViewChildren.indexOf(newItem)), itemViewChildren));

        items.add(newItem);
        itemViewChildren.add(itemViewChildren.size() - 1, newItem);

        newItem.addEventFilter(MouseEvent.MOUSE_CLICKED, _ -> setSelectedItemIndex(items.indexOf(newItem)));
    }

    public void deleteSelectedItem() {
        if (selectedItemIndex == -1)
            return;

        itemViewChildren.remove(items.remove(selectedItemIndex));
        selectedItemIndex = -1;
    }

    private final List<Item> items = new ArrayList<>();

    private int selectedItemIndex = -1;

    public void setSelectedItemIndex(int index) {
        if (selectedItemIndex != -1) {
            items.get(selectedItemIndex).setActive(false);
        }

        selectedItemIndex = index;
        items.get(index).setActive(true);
    }


    public ObservableList<Node> itemViewChildrenProperty() {
        return itemViewChildren;
    }

    private final ObservableList<Node> itemViewChildren = FXCollections.observableArrayList();


    private final String[] methodNames = {"Метод грубої сили", "Рекурсивний метод", "Метод динамічного програмування", "Жадібний алгоритм", "Метод гілок"};

    public Tab[] getMethodTabs() {
        return methodTabs;
    }

    private final Tab[] methodTabs = new Tab[5];


    private int selectedTabIndex = 0;

    public void setSelectedTabIndex(int index) {
        methodTabs[selectedTabIndex].setActive(false);
        selectedTabIndex = index;
        methodTabs[index].setActive(true);
    }

    public Model() {
        for (int i = 0; i < methodNames.length; i++) {
            Tab newTab = new Tab(methodNames[i]);

            int finalI = i;
            newTab.setOnMouseClicked(_ -> setSelectedTabIndex(finalI));
            methodTabs[i] = newTab;

            HBox.setHgrow(newTab, Priority.ALWAYS);
        }

        methodTabs[0].setActive(true);
    }

}
