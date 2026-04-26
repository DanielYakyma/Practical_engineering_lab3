package algebraforge.pracitalengineering3.main;

import algebraforge.pracitalengineering3.components.*;
import algebraforge.pracitalengineering3.util.ProcessorData;
import algebraforge.pracitalengineering3.util.Style;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

class View extends VBox {
    public HBox getMethodPanel() {
        return methodPanel;
    }

    private final HBox methodPanel = new HBox();

    {
        methodPanel.setBackground(Style.pageBackground);
    }


    private final SecondaryButton startButton = new SecondaryButton("Старт");


    private final NormalText maxWeightMessage = new NormalText("Максимальна вага рюкзака:");

    {
        HBox.setMargin(maxWeightMessage, new Insets(8, 0, 0, 0));
    }


    private final InputProcessor maxWeightInput = new InputProcessor(ProcessorData.createPositiveProcessor("W >= 0"));


    private final NormalText optimalItemsText = new NormalText("Оптимальна вибірка речей: []");

    {
        HBox.setMargin(optimalItemsText, new Insets(8, 0, 0, 0));
    }


    private final SecondaryButton stopButton = new SecondaryButton("Стоп");


    private final NormalText totalWeight = new NormalText("Загальна вага: ");

    private final NormalText totalValue = new NormalText("Загальна цінність: ");

    private final NormalText totalTime = new NormalText("Час обчислення: ");


    private final VBox solutionPanel = new VBox();

    {
        HBox firstLine = new HBox(startButton, new Filler(), maxWeightMessage, maxWeightInput, new Filler(), optimalItemsText, new Filler(), stopButton);
        firstLine.setMaxWidth(Double.MAX_VALUE);
        firstLine.setSpacing(8);

        HBox secondLine = new HBox(totalWeight, new Filler(), totalValue, new Filler(), totalTime);
        secondLine.setMaxWidth(Double.MAX_VALUE);
        secondLine.setSpacing(8);

        solutionPanel.getChildren().addAll(firstLine, secondLine);
        solutionPanel.setBorder(Style.normalBorder);
        solutionPanel.setBackground(Style.surfaceBackground);
        solutionPanel.setMaxWidth(Double.MAX_VALUE);
        solutionPanel.setPadding(Style.normalPadding);
    }


    private final VBox problemPanel = new VBox(solutionPanel);

    {
        problemPanel.setAlignment(Pos.BOTTOM_CENTER);
        problemPanel.setMaxWidth(Double.MAX_VALUE);
        problemPanel.setFillWidth(true);
        HBox.setHgrow(problemPanel, Priority.ALWAYS);
    }


    public IconButton getCreateItemButton() {
        return createItemButton;
    }

    private final NormalIconButton createItemButton = new NormalIconButton("Add", "createButton");


    public VBox getItemContent() {
        return itemContent;
    }

    private final VBox itemContent = new VBox();

    {
        itemContent.setAlignment(Pos.CENTER);
    }


    private final ScrollPane itemView = new ScrollPane();

    {
        itemView.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        itemView.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        VBox.setVgrow(itemView, Priority.ALWAYS);

        itemView.setContent(itemContent);
        itemView.setFitToWidth(true);
    }

    private final VBox itemPanel = new VBox(itemView);

    {
        itemPanel.setFillWidth(true);

        itemPanel.setSpacing(16);
        itemPanel.setPadding(Style.largePadding);

        itemPanel.setBackground(Style.surfaceBackground);
        itemPanel.setBorder(Style.normalBorder);
    }


    private final HBox mainContent = new HBox(itemPanel, problemPanel);

    {
        VBox.setVgrow(mainContent, Priority.ALWAYS);
        mainContent.setBackground(Style.pageBackground);
    }


    public View() {
        setBackground(Style.pageBackground);

        getChildren().addAll(methodPanel, mainContent);
    }
}
