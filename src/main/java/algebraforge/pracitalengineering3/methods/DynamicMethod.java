package algebraforge.pracitalengineering3.methods;

import algebraforge.pracitalengineering3.MainApplication;
import algebraforge.pracitalengineering3.components.Item;
import algebraforge.pracitalengineering3.components.LargeText;
import algebraforge.pracitalengineering3.components.NormalText;
import algebraforge.pracitalengineering3.util.Style;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class DynamicMethod extends Method {
    static class Cell {
        void setValue(int value, int weight, List<Item> optimalItems) {
            this.value = value;
            this.weight = weight;

            this.optimalItems = new ArrayList<>(optimalItems);

            Platform.runLater(() -> text.setText(String.valueOf(value)));
        }

        void setCell(Cell cell) {
            setValue(cell.value, cell.weight, cell.optimalItems);
        }


        private List<Item> optimalItems = new ArrayList<>();

        int value = 0;
        int weight = 0;

        private final NormalText text = new NormalText("0");

        private final HBox pane = new HBox(text);

        {
            pane.setPadding(Style.largePadding);
            pane.setBorder(Style.normalBorder);
        }
    }

    private final IntegerProperty iteration = new SimpleIntegerProperty();

    public DynamicMethod() {
        iterationText.textProperty().bind(Bindings.concat("Ітерація: ", iteration));
    }


    @Override
    Solution findSolution() throws InterruptedException {
        Solution solution = new Solution();

        Platform.runLater(() -> iteration.set(0));

        List<Item> items = MainApplication.main.getItems();
        final int maxWeight = MainApplication.main.getMaximalWeight();

        int height = maxWeight + 1;

        Cell[][] elements = new Cell[items.size() + 1][height];

        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements[i].length; j++) {
                elements[i][j] = new Cell();
            }
        }

        Platform.runLater(() -> {
            gridPane.getChildren().clear();

            for (int i = 0; i < elements.length; i++) {
                for (int j = 0; j < elements[i].length; j++) {
                    gridPane.add(elements[i][j].pane, i, j);
                }
            }
        });

        for (int i = 1; i <= items.size(); i++) {
            Item currentItem = items.get(i - 1);

            for (int w = 0; w < height; w++) {
                Platform.runLater(() -> iteration.set(iteration.get() + 1));

                Thread.sleep(DELAY);
                if (w < currentItem.getWeight()) {
                    elements[i][w].setCell(elements[i - 1][w]);
                    continue;
                }

                int without = elements[i - 1][w].value;
                Cell withCell = elements[i - 1][w - currentItem.getWeight()];
                int with = withCell.value + currentItem.getValue();

                Thread.sleep(DELAY);
                if (without >= with) {
                    elements[i][w].setCell(elements[i - 1][w]);
                } else {
                    List<Item> optimalItems = new ArrayList<>(withCell.optimalItems);
                    optimalItems.add(currentItem);
                    elements[i][w].setValue(with, withCell.weight + currentItem.getWeight(), optimalItems);
                }
            }
        }

        Cell lastCell = elements[items.size()][height - 1];

        solution.optimalItems = lastCell.optimalItems;
        solution.totalValue = lastCell.value;
        solution.totalWeight = lastCell.weight;

        return solution;
    }

    @Override
    public Node getView() {
        return scrollPane;
    }


    private final LargeText iterationText = new LargeText();

    private final GridPane gridPane = new GridPane();


    private final VBox content = new VBox(iterationText, gridPane);

    {
        content.setPadding(Style.largePadding);
        content.setSpacing(16);
    }


    private final ScrollPane scrollPane = new ScrollPane();

    {
        HBox.setHgrow(scrollPane, Priority.ALWAYS);

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(content);
    }
}
