package algebraforge.pracitalengineering3.components;

import algebraforge.pracitalengineering3.util.Style;
import javafx.scene.text.Text;

public class LargeText extends Text {
    public LargeText(String text) {
        super(text);
        setFont(Style.headlineMedium);
        setFill(Style.grey8);
    }

    public LargeText() {
        this("");
    }
}
