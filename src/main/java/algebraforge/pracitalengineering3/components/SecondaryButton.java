package algebraforge.pracitalengineering3.components;

import algebraforge.pracitalengineering3.util.Interactable;
import algebraforge.pracitalengineering3.util.StateManager;
import algebraforge.pracitalengineering3.util.Style;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class SecondaryButton extends Button implements Interactable {
    private static Background generateBackground(Paint paint) {
        return new Background(new BackgroundFill(paint, Style.normalRadius, null));
    }

    private static Border generateBorder(Paint paint) {
        return new Border(new BorderStroke(paint, BorderStrokeStyle.SOLID, Style.normalRadius, new BorderWidths(1)));
    }

    private static final Background defaultBackground = generateBackground(Color.TRANSPARENT);
    private static final Background hoverBackground = generateBackground(Style.red50);
    private static final Background activeBackground = generateBackground(Style.red100);

    private static final Border defaultBorder = generateBorder(Style.red700);
    private static final Border activeBorder = generateBorder(Style.red800);


    public SecondaryButton(String text) {
        setText(text);
        setTextFill(Style.red800);
        setFont(Style.labelLarge);

        setPadding(Style.normalPadding);

        setDefaultState();

        StateManager.attachInteraction(this);
    }

    @Override
    public void setDefaultState() {
        setBackground(defaultBackground);
        setBorder(defaultBorder);
    }

    @Override
    public void setHoverState() {
        setBackground(hoverBackground);
        setBorder(defaultBorder);
    }

    @Override
    public void setActiveState() {
        setBackground(activeBackground);
        setBorder(activeBorder);
    }

    @Override
    public void setDisabledState() {

    }
}
