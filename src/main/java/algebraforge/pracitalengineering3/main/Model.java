package algebraforge.pracitalengineering3.main;

import algebraforge.pracitalengineering3.components.Item;
import algebraforge.pracitalengineering3.components.Tab;
import algebraforge.pracitalengineering3.methods.*;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Pair;

import java.util.*;

class Model {
    private final List<Pair<Number, Number>> vector = new ArrayList<>(List.of(
            new Pair<>(7, 12),
            new Pair<>(3, 14),
            new Pair<>(5, 3),
            new Pair<>(2, 11),
            new Pair<>(4, 9),
            new Pair<>(4, 12),
            new Pair<>(2, 11),
            new Pair<>(4, 11)
    ));

    private void recreateDefaultItems(List<Pair<Number, Number>> vector) {
        for (var pair : vector) {
            Item newItem = createItem();

            newItem.setWeight(pair.getKey());
            newItem.setValue(pair.getValue());
        }
    }


    public Item createItem() {
        Item newItem = new Item();

        newItem.getNumberProperty().bind(Bindings.createStringBinding(
                () -> String.valueOf(itemViewChildren.indexOf(newItem)), itemViewChildren));

        items.add(newItem);
        itemViewChildren.add(itemViewChildren.size() - 1, newItem);

        newItem.addEventFilter(MouseEvent.MOUSE_CLICKED, _ -> setSelectedItemIndex(items.indexOf(newItem)));

        return newItem;
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
        methods.put("Рекурсивний метод", new RecursiveMethod());
        methods.put("Метод динамічного програмування", new DynamicMethod());
        methods.put("Жадібний алгоритм", new GreedyMethod());
        methods.put("Метод гілок", new BranchMethod());
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

        Platform.runLater(()-> {
            setSelectedItemIndex(0);
            deleteSelectedItem();

            recreateDefaultItems(vector);
            parent.setMaxWeight(26);
        });
    }

    public void startCurrentMethod() {
        methodTabs[selectedTabIndex].getMethod().start();
    }

    public void cancelCurrentMethod() {
        methodTabs[selectedTabIndex].getMethod().cancel();
    }

}
