package algebraforge.pracitalengineering3.main;

import algebraforge.pracitalengineering3.components.Item;
import algebraforge.pracitalengineering3.components.Tab;
import algebraforge.pracitalengineering3.methods.BruteForceMethod;
import algebraforge.pracitalengineering3.methods.Method;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.*;

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

    public List<Item> getItems() {
        return items;
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

    private final LinkedHashMap<String, Method> methods = new LinkedHashMap<>();

    {
        methods.put("Метод грубої сили", new BruteForceMethod());
        methods.put("Рекурсивний метод", new BruteForceMethod());
        methods.put("Метод динамічного програмування", new BruteForceMethod());
        methods.put("Жадібний алгоритм", new BruteForceMethod());
        methods.put("Метод гілок", new BruteForceMethod());
    }

    public Tab[] getMethodTabs() {
        return methodTabs;
    }

    private final Tab[] methodTabs = new Tab[methods.size()];


    private int selectedTabIndex = 0;

    public void setSelectedTabIndex(int index) {
        methodTabs[selectedTabIndex].setActive(false);
        selectedTabIndex = index;
        methodTabs[index].setActive(true);
        parent.setMethod(methodTabs[index].getMethod());
    }

    private final MainController parent;

    public Model(MainController parent) {
        this.parent = parent;

        int index = 0;
        for (var entry : methods.entrySet()) {
            Tab newTab = new Tab(entry.getKey(), entry.getValue());

            int finalI = index;
            newTab.setOnMouseClicked(_ -> setSelectedTabIndex(finalI));
            methodTabs[index] = newTab;

            HBox.setHgrow(newTab, Priority.ALWAYS);

            index++;
        }

        setSelectedTabIndex(0);
    }

    public void startCurrentMethod() {
        methodTabs[selectedTabIndex].getMethod().start();
    }

    public void cancelCurrentMethod() {
        methodTabs[selectedTabIndex].getMethod().cancel();
    }

}
