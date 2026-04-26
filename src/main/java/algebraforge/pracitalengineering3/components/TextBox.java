package algebraforge.pracitalengineering3.components;

import algebraforge.pracitalengineering3.util.Style;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class TextBox extends TextField {
    private final Background activeBackground = new Background(
            new BackgroundFill(Style.grey0, Style.normalRadius, null));

    private final Border activeBorder = new Border(
            new BorderStroke(Style.grey3, BorderStrokeStyle.SOLID, Style.normalRadius, new BorderWidths(1)));


    private final Background wrongBackground = new Background(
            new BackgroundFill(Style.red50, Style.normalRadius, null));

    private final Border wrongBorder = new Border(
            new BorderStroke(Style.red400, BorderStrokeStyle.SOLID, Style.normalRadius, new BorderWidths(1)));


    private final Border transparentBorder = new Border(
            new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, Style.normalRadius, new BorderWidths(1)));

    public void setActive(boolean isActive) {
        if (isActive) {
            setDisable(false);
            setActiveState();
            requestFocus();
            selectAll();
        } else {
            setDefaultState();
            setDisable(true);
        }
    }

    public TextBox() {
        setPadding(Style.normalPadding);
        setDefaultState();
        setFont(Style.labelLarge);
        setStyle(Style.textColor);
        setMinWidth(10);

        setActive(true);
        setFocusTraversable(false);
    }

    protected void setDefaultState() {
    }

    public void setActiveState() {
        setBackground(activeBackground);
        setBorder(activeBorder);
    }

    public void setWrongState() {
        setBackground(wrongBackground);
        setBorder(wrongBorder);
    }
}
