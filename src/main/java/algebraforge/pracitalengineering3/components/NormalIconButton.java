package algebraforge.pracitalengineering3.components;

import algebraforge.pracitalengineering3.util.IconManager;
import algebraforge.pracitalengineering3.util.Style;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import org.girod.javafx.svgimage.SVGImage;

public class NormalIconButton extends IconButton{
    private static final Background normal=new Background(new BackgroundFill(Style.grey2, Style.normalRadius, null));
    private static final Background hover = new Background(new BackgroundFill(Style.grey3, Style.normalRadius, null));
    private static final Background active = new Background(new BackgroundFill(Style.grey4, Style.normalRadius, null));

    @Override
    int getSide() {
        return 32;
    }

    @Override
    Background getNormalBackground() {
        return normal;
    }

    @Override
    Background getHoverBackground() {
        return hover;
    }

    @Override
    Background getActiveBackground() {
        return active;
    }

    @Override
    Background getDisabledBackground() {
        return normal;
    }

    @Override
    void setAdditionalStyling() {
        setPadding(Style.normalPadding);
    }

    public NormalIconButton(String fileName, String id){
        super(fileName, id);
    }
    public NormalIconButton(SVGImage icon, String id){
        super(icon, id);
    }
    public NormalIconButton(IconManager iconManager, String id){
        super(iconManager, id);
    }
}
