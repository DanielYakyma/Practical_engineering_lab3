package algebraforge.pracitalengineering3.components;

import algebraforge.pracitalengineering3.methods.Method;
import algebraforge.pracitalengineering3.util.Style;
import javafx.beans.binding.When;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class Tab extends HBox {
    public BooleanProperty isActiveProperty() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive.set(isActive);
    }

    private final BooleanProperty isActive = new SimpleBooleanProperty(false);


    private static final Border border = new Border(new BorderStroke(
            Style.grey4,
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            new BorderWidths(0, 1, 0, 0),
            Insets.EMPTY));

    private final Text text;


    public Method getMethod(){
        return method;
    }

    private final Method method;


    public Tab(String name, Method method) {
        this.method = method;

        text = new Text(name);
        text.setFont(Style.headlineMedium);

        setSpacing(8);
        setPadding(new Insets(4, 8, 4, 8));
        setAlignment(Pos.CENTER);
        setBorder(border);
        setMaxHeight(64);

        getChildren().add(text);

        backgroundProperty().bind(new When(isActiveProperty())
                .then(Style.pageBackground)
                .otherwise(Style.surfaceBackground));
    }
}
