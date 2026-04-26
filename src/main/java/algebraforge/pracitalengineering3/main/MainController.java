package algebraforge.pracitalengineering3.main;

import javafx.beans.binding.Bindings;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.Map;

public class MainController {
    public Parent getView() {
        return view;
    }

    private final View view = new View();

    private final Model model = new Model();


    public Map<KeyCombination, Runnable> getAccelerators() {
        return accelerators;
    }

    private final Map<KeyCombination, Runnable> accelerators = new HashMap<>(Map.of(
            new KeyCodeCombination(KeyCode.DELETE), model::deleteSelectedItem
    ));


    public MainController() {
        view.getMethodPanel().getChildren().addAll(model.getMethodTabs());

        model.itemViewChildrenProperty().add(view.getCreateItemButton());

        Bindings.bindContent(view.getItemContent().getChildren(), model.itemViewChildrenProperty());

        view.getCreateItemButton().setOnMouseClicked(this::onCreateItemButtonClick);

        model.createItem();
    }

    private void onCreateItemButtonClick(MouseEvent e) {
        model.createItem();
    }
}
