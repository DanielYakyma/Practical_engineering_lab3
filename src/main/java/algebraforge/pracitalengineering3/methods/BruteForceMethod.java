package algebraforge.pracitalengineering3.methods;

import algebraforge.pracitalengineering3.MainApplication;
import algebraforge.pracitalengineering3.components.Filler;
import algebraforge.pracitalengineering3.components.Item;
import algebraforge.pracitalengineering3.components.LargeText;
import algebraforge.pracitalengineering3.util.ItemBinding;
import algebraforge.pracitalengineering3.util.Style;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class BruteForceMethod extends Method {
    private final ObservableList<Item> currentItems = FXCollections.observableArrayList();
    private final DoubleProperty currentWeight = new SimpleDoubleProperty();
    private final DoubleProperty currentValue = new SimpleDoubleProperty();

    private final IntegerProperty iteration = new SimpleIntegerProperty();

    private final ObservableList<Item> optimalItems = FXCollections.observableArrayList();
    private final DoubleProperty totalWeight = new SimpleDoubleProperty();
    private final DoubleProperty totalValue = new SimpleDoubleProperty();

    @Override
    public Node getView() {
        return content;
    }


    public BruteForceMethod() {
        currentItemsText.textProperty().bind(ItemBinding.createItemBinding("Вибірка речей: ", currentItems));
        currentWeightText.textProperty().bind(Bindings.concat("Вага: ", currentWeight));
        currentValueText.textProperty().bind(Bindings.concat("Цінність: ", currentValue));

        iterationText.textProperty().bind(Bindings.concat("Ітерація: ", iteration));

        optimalItemsText.textProperty().bind(ItemBinding.createItemBinding("Найкраща вибірка: ", optimalItems));
        totalWeightText.textProperty().bind(Bindings.concat("Вага: ", totalWeight));
        totalValueText.textProperty().bind(Bindings.concat("Цінність: ", totalValue));
    }

    @Override
    Solution findSolution() throws InterruptedException {
        Solution solution = new Solution();

        Platform.runLater(()->{
            optimalItems.clear();
            totalWeight.set(0);
            totalValue.set(0);

            iteration.set(0);

            currentItems.clear();
            currentWeight.set(0);
            currentValue.set(0);
        });

        List<Item> items = MainApplication.main.getItems();
        double maxWeight = MainApplication.main.getMaximalWeight();

        for (int i = 0; i < (1 << items.size()); i++) {
            List<Item> currentItems = new ArrayList<>();
            double currentWeight = 0;
            double currentValue = 0;

            int finalI = i;
            Platform.runLater(()->this.iteration.set(finalI));

            Thread.sleep(DELAY);

            for (int j = 0; j < items.size(); j++) {
                if ((i & (1 << j)) > 0) {
                    Item newItem = items.get(j);
                    currentItems.add(newItem);
                    currentWeight += newItem.getWeight();
                    currentValue += newItem.getValue();
                }
            }

            Thread.sleep(DELAY);

            double finalCurrentWeight = currentWeight;
            double finalCurrentValue = currentValue;

            Platform.runLater(()->{
                this.currentItems.setAll(currentItems);
                this.currentWeight.set(finalCurrentWeight);
                this.currentValue.set(finalCurrentValue);

                if (finalCurrentWeight <= maxWeight && finalCurrentValue > totalValue.get()) {
                    optimalItems.setAll(currentItems);
                    totalWeight.set(finalCurrentWeight);
                    totalValue.set(finalCurrentValue);
                }
            });
        }

        solution.optimalItems = optimalItems;
        solution.totalWeight = totalWeight.get();
        solution.totalValue = totalValue.get();

        return solution;
    }

    private final LargeText currentItemsText = new LargeText();
    private final LargeText currentWeightText = new LargeText();
    private final LargeText currentValueText = new LargeText();

    private final LargeText iterationText = new LargeText();

    private final LargeText optimalItemsText = new LargeText();
    private final LargeText totalWeightText = new LargeText();
    private final LargeText totalValueText = new LargeText();


    private final VBox content = new VBox(iterationText, new Filler(), currentItemsText, currentWeightText, currentValueText, new Filler(), optimalItemsText, totalWeightText, totalValueText);

    {
        content.setPadding(Style.largePadding);
        content.setSpacing(16);
    }
}
