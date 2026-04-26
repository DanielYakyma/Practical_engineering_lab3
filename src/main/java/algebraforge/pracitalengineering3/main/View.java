package algebraforge.pracitalengineering3.main;

import algebraforge.pracitalengineering3.components.IconButton;
import algebraforge.pracitalengineering3.components.NormalIconButton;
import algebraforge.pracitalengineering3.components.NormalText;
import algebraforge.pracitalengineering3.util.Style;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

class View extends VBox {
    public HBox getMethodPanel() {
        return methodPanel;
    }

    private final HBox methodPanel = new HBox();

    {
        methodPanel.setBackground(Style.pageBackground);
    }

    private final VBox problemPanel = new VBox();

    private final VBox solutionPanel = new VBox();

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

    private final VBox itemPanel = new VBox( itemView);

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
