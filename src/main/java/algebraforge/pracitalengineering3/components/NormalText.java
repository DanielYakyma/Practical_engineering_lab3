package algebraforge.pracitalengineering3.components;

import algebraforge.pracitalengineering3.util.Style;
import javafx.scene.text.Text;

public class NormalText extends Text {
    public NormalText(String text) {
        super(text);
        setFont(Style.labelLarge);
        setFill(Style.grey8);
    }

    public NormalText() {
        this("");
    }
}
