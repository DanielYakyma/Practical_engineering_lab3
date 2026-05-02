package algebraforge.pracitalengineering3.main;

import algebraforge.pracitalengineering3.components.Item;
import algebraforge.pracitalengineering3.methods.Method;
import algebraforge.pracitalengineering3.methods.Solution;
import javafx.beans.binding.Bindings;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainController {
    public Parent getView() {
        return view;
    }


    public double getMaximalWeight() {
        return view.getMaxWeightInput().getDoubleValue();
    }


    private final View view = new View();


    public List<Item> getItems() {
        return model.getItems();
    }

    private final Model model = new Model(this);


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
        view.getStartButton().setOnMouseClicked(this::onStartButtonClick);
        view.getCancelButton().setOnMouseClicked(this::onCancelButtonClick);

        model.createItem();

        setSolution(new Solution());
    }

    private void onCreateItemButtonClick(MouseEvent e) {
        model.createItem();
    }


    private void onStartButtonClick(MouseEvent e) {
        model.startCurrentMethod();
    }


    private void onCancelButtonClick(MouseEvent e) {
        model.cancelCurrentMethod();
    }


    void setMethod(Method method) {
        view.setMethod(method);
    }


    public void setSolution(Solution solution) {
        view.showOptimalItems(solution.optimalItems);
        view.showTotalWeight(solution.totalWeight);
        view.showTotalValue(solution.totalValue);

        view.showTotalTime(solution.totalTime);
    }

    void setMaxWeight(Number value) {
        view.getMaxWeightInput().setValue(String.valueOf(value));
    }
}
