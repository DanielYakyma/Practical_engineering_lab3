package algebraforge.pracitalengineering3.methods;

import algebraforge.pracitalengineering3.MainApplication;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GreedyMethod extends Method {
    private final IntegerProperty iteration = new SimpleIntegerProperty();

    private final ObservableList<Item> optimalItems = FXCollections.observableArrayList();
    private final DoubleProperty totalWeight = new SimpleDoubleProperty();
    private final DoubleProperty totalValue = new SimpleDoubleProperty();

    public GreedyMethod() {
        iterationText.textProperty().bind(Bindings.concat("Ітерація: ", iteration));

        optimalItemsText.textProperty().bind(ItemBinding.createItemBinding("Найкраща вибірка: ", optimalItems));
        totalWeightText.textProperty().bind(Bindings.concat("Вага: ", totalWeight));
        totalValueText.textProperty().bind(Bindings.concat("Цінність: ", totalValue));
    }

    @Override
    Solution findSolution() throws InterruptedException {
        List<Item> items = MainApplication.main.getItems();
        final double maxWeight = MainApplication.main.getMaximalWeight();

        items.sort(Comparator.comparing(Item::getRatio).reversed());

        Solution solution = new Solution();

        Platform.runLater(() -> {
            optimalItems.clear();
            totalWeight.set(0);
            totalValue.set(0);

            iteration.set(0);
        });

        double newWeight = 0;
        double newValue = 0;

        for (int i = 0; i < items.size(); i++) {
            int finalI = i;
            Platform.runLater(() -> iteration.set(finalI));

            newWeight += items.get(i).getWeight();
            newValue += items.get(i).getValue();

            if (newWeight <= maxWeight) {
                double finalNewValue = newValue;
                double finalNewWeight = newWeight;

                Platform.runLater(() -> {
                    totalValue.set(finalNewValue);
                    totalWeight.set(finalNewWeight);
                    optimalItems.add(items.get(finalI));
                });
            } else {
                break;
            }

            Thread.sleep(DELAY);
        }

        solution.optimalItems = new ArrayList<>(optimalItems);
        solution.totalWeight = totalWeight.get();
        solution.totalValue = totalValue.get();

        return solution;
    }

    private final LargeText iterationText = new LargeText();

    private final LargeText optimalItemsText = new LargeText();
    private final LargeText totalWeightText = new LargeText();
    private final LargeText totalValueText = new LargeText();


    private final VBox content = new VBox(iterationText, new Pane(), optimalItemsText, totalWeightText, totalValueText);

    {
        content.setPadding(Style.largePadding);
        content.setSpacing(16);
    }

    @Override
    public Node getView() {
        return content;
    }
}
