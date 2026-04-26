package algebraforge.pracitalengineering3.components;

import algebraforge.pracitalengineering3.util.ProcessorData;
import algebraforge.pracitalengineering3.util.Style;
import javafx.beans.binding.When;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.HBox;

public class Item extends HBox {
    public BooleanProperty activeProperty() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive.set(isActive);
    }

    private final BooleanProperty isActive = new SimpleBooleanProperty();


    public StringProperty getNumberProperty() {
        return number.textProperty();
    }

    private final NormalText number = new NormalText();

    {
        number.setFont(Style.headlineMedium);
    }

    private final InputProcessor weightInput = new InputProcessor(ProcessorData.createPositiveProcessor("w >= 0"));
    private final InputProcessor valueInput = new InputProcessor(ProcessorData.createPositiveProcessor("v >= 0"));

    public Item() {
        getChildren().addAll(number, weightInput, valueInput);
        setSpacing(16);

        number.fillProperty().bind(new When(activeProperty())
                .then(Style.blue600)
                .otherwise(Style.grey8));
    }
}
