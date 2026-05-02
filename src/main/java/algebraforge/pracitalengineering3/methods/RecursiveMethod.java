package algebraforge.pracitalengineering3.methods;

import algebraforge.pracitalengineering3.MainApplication;
import algebraforge.pracitalengineering3.components.Filler;
import algebraforge.pracitalengineering3.components.Item;
import algebraforge.pracitalengineering3.components.LargeText;
import algebraforge.pracitalengineering3.util.ItemBinding;
import algebraforge.pracitalengineering3.util.Style;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

public class RecursiveMethod extends Method {
    private final IntegerProperty iteration = new SimpleIntegerProperty();

    public RecursiveMethod() {
        iterationText.textProperty().bind(Bindings.concat("Ітерація: ", iteration));
    }

    @Override
    Solution findSolution() throws InterruptedException {
        iteration.set(0);

        this.finalSolution.setSolution(new Solution());
        this.previousSolution.setSolution(new Solution());
        this.newSolution.setSolution(new Solution());

        items = MainApplication.main.getItems();
        maxWeight = MainApplication.main.getMaximalWeight();

        return recursiveMethod(0, new Solution());
    }

    List<Item> items;
    double maxWeight;

    public Solution recursiveMethod(int currentItemIndex, Solution previousSolution) throws InterruptedException {
        iteration.set(iteration.get() + 1);
        Thread.sleep(DELAY);

        if (currentItemIndex >= items.size()) {
            return previousSolution;
        }


        this.previousSolution.setSolution(previousSolution);

        Solution newSolution = new Solution(previousSolution);

        newSolution.totalWeight += items.get(currentItemIndex).getWeight();
        newSolution.totalValue += items.get(currentItemIndex).getValue();
        newSolution.optimalItems.add(items.get(currentItemIndex));

        this.newSolution.setSolution(newSolution);


        if (newSolution.totalWeight > maxWeight) {
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

    class TextSolution {
        private final LargeText optimalItemsText = new LargeText();
        private final LargeText totalWeightText = new LargeText();
        private final LargeText totalValueText = new LargeText();

        private final String firstString;

        TextSolution(String firstString) {
            this.firstString = firstString;

            setSolution(new Solution());
        }


        void setSolution(Solution solution) {
            optimalItemsText.setText(firstString + ItemBinding.convertItemsToString(solution.optimalItems));

            totalWeightText.setText("Вага: " + solution.totalWeight);
            totalValueText.setText("Цінність: " + solution.totalValue);
        }

        private final VBox content = new VBox(optimalItemsText, totalWeightText, totalValueText);
    }

    private final TextSolution previousSolution = new TextSolution("Попередня вибірка: ");
    private final TextSolution newSolution = new TextSolution("Нова вибірка: ");
    private final TextSolution finalSolution = new TextSolution("Остаточна вибірка: ");

    private final HBox branch = new HBox(previousSolution.content, new Filler(), newSolution.content);


    private final VBox content = new VBox(iterationText, branch, finalSolution.content);

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
