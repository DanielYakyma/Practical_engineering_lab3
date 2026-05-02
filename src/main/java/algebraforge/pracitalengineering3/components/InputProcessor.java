package algebraforge.pracitalengineering3.components;

import algebraforge.pracitalengineering3.util.ProcessorData;
import algebraforge.pracitalengineering3.util.Style;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class InputProcessor extends VBox {

    public boolean isOk() {
        return isOK;
    }

    private boolean isOK = false;

    public double getDoubleValue() {
        return convertedValue.doubleValue();
    }

    public int getIntegerValue() {
        return convertedValue.intValue();
    }

    private Number convertedValue;

    private final Text message = new Text();

    {
        message.setVisible(false);
        message.setFont(Style.labelSmall);
        message.setFill(Style.red800);
    }

    public void setValue(String string) {
        inputField.setText(string);
    }

    private final TextBox inputField = new TextBox();

    public InputProcessor(ProcessorData data) {
        this.message.setText(data.message);

        GridPane.setHgrow(this, Priority.ALWAYS);

        inputField.setText("0");

        inputField.textProperty().subscribe(s -> {
            try {
                convertedValue = data.process(s);
                inputField.setActiveState();
                this.message.setVisible(false);
                isOK = true;
            } catch (RuntimeException e) {
                inputField.setWrongState();
                this.message.setVisible(true);
                isOK = false;
            }
        });

        setSpacing(8);
        getChildren().addAll(inputField, this.message);
    }
}
