package algebraforge.pracitalengineering3.methods;

import algebraforge.pracitalengineering3.MainApplication;
import algebraforge.pracitalengineering3.components.Filler;
import algebraforge.pracitalengineering3.components.Item;
import algebraforge.pracitalengineering3.components.LargeText;
import algebraforge.pracitalengineering3.util.Style;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Comparator;
import java.util.List;

public class BranchMethod extends Method {
    private final IntegerProperty iteration = new SimpleIntegerProperty();

    public BranchMethod() {
        iterationText.textProperty().bind(Bindings.concat("Ітерація: ", iteration));

        itemIndexText.textProperty().bind(Bindings.concat("Глибина: ", currentIndex));

        highestValueText.textProperty().bind(Bindings.concat("Найбільша цінність: ", highestValue));
        boundText.textProperty().bind(Bindings.concat("Межа гілки: ", currentBound));
    }

    @Override
    Solution findSolution() throws InterruptedException {
        this.finalSolution.setSolution(new Solution());
        this.previousSolution.setSolution(new Solution());
        this.newSolution.setSolution(new Solution());

        items = MainApplication.main.getItems();
        maxWeight = MainApplication.main.getMaximalWeight();

        Platform.runLater(() -> {
            iteration.set(0);
            highestValue.set(0);
            currentBound.set(0);
            currentIndex.set(0);
        });

        items.sort(Comparator.comparing(Item::getRatio).reversed());

        return recursiveMethod(0, new Solution());
    }

    List<Item> items;
    double maxWeight;

    private final DoubleProperty highestValue = new SimpleDoubleProperty();
    private final DoubleProperty currentBound = new SimpleDoubleProperty();
    private final IntegerProperty currentIndex = new SimpleIntegerProperty();

    public Solution recursiveMethod(int currentItemIndex, Solution previousSolution) throws InterruptedException {
        Platform.runLater(() -> iteration.set(iteration.get() + 1));
        Thread.sleep(DELAY);

        if (currentItemIndex >= items.size()) {
            if (previousSolution.totalValue > highestValue.get()) {
                highestValue.set(previousSolution.totalValue);
            }
            return previousSolution;
        }

        Platform.runLater(() -> currentIndex.set(currentItemIndex));

        this.previousSolution.setSolution(previousSolution);

        Solution newSolution = new Solution(previousSolution);

        newSolution.totalWeight += items.get(currentItemIndex).getWeight();
        newSolution.totalValue += items.get(currentItemIndex).getValue();
        newSolution.optimalItems.add(items.get(currentItemIndex));

        this.newSolution.setSolution(newSolution);

        double bound = newSolution.totalValue;
        double totalWeight = newSolution.totalWeight;

        int j = currentItemIndex + 1;

        while (j < items.size() && totalWeight + items.get(j).getWeight() <= totalWeight) {
            bound += items.get(j).getValue();
            totalWeight += items.get(j).getWeight();
            j++;
        }

        if (j < items.size()) {
            bound += (maxWeight - totalWeight) * items.get(j).getRatio();
        }

        double finalBound = bound;
        Platform.runLater(() -> currentBound.set(finalBound));

        Thread.sleep(DELAY);

        if (newSolution.totalWeight > maxWeight || bound < highestValue.get()) {
            Solution finalSolution = recursiveMethod(currentItemIndex + 1, previousSolution);
            this.finalSolution.setSolution(finalSolution);

            return finalSolution;
        }

        Solution finalSolution1 = recursiveMethod(currentItemIndex + 1, newSolution);
        Solution finalSolution2 = recursiveMethod(currentItemIndex + 1, previousSolution);

        if (finalSolution1.totalValue > finalSolution2.totalValue) {
            this.finalSolution.setSolution(finalSolution1);
            return finalSolution1;
        } else {
            this.finalSolution.setSolution(finalSolution2);
            return finalSolution2;
        }
    }


    private final LargeText iterationText = new LargeText();

    private final LargeText itemIndexText = new LargeText();

    private final TextSolution previousSolution = new TextSolution("Попередня вибірка: ");
    private final TextSolution newSolution = new TextSolution("Нова вибірка: ");
    private final TextSolution finalSolution = new TextSolution("Остаточна вибірка: ");

    private final LargeText highestValueText = new LargeText();

    private final LargeText boundText = new LargeText();

    private final HBox branch = new HBox(previousSolution.content, new Filler(), newSolution.content);


    private final VBox content = new VBox(iterationText, new Filler(), itemIndexText, new Filler(), branch, new Filler(), highestValueText, boundText, new Filler(), finalSolution.content);

    {
        HBox.setHgrow(content, Priority.ALWAYS);
        content.setPadding(Style.largePadding);
        content.setSpacing(16);
    }


    @Override
    public Node getView() {
        return content;
    }
}
