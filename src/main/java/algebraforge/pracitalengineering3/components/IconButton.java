package algebraforge.pracitalengineering3.components;

import algebraforge.pracitalengineering3.util.*;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import org.girod.javafx.svgimage.SVGImage;


public abstract class IconButton extends VBox implements Interactable {
    abstract int getSide();

    abstract Background getNormalBackground();
    abstract Background getHoverBackground();
    abstract Background getActiveBackground();
    abstract Background getDisabledBackground();

    private final IconManager iconManager;

    public IconButton(String fileName, String id){
        this(fileName);
        setId(id);
    }

    private IconButton(String fileName){
        this(SVGManager.load(fileName));
    }

    public IconButton(SVGImage icon, String id){
        this(icon);
        setId(id);
    }

    public IconButton(IconManager iconManager, String id){
        this.iconManager=iconManager;

        initialize(iconManager.getIcon());

        setId(id);
    }

    private IconButton(SVGImage icon){
        iconManager=new IconManager(icon);

        initialize(icon);
    }

    private void initialize(SVGImage icon){
        StateManager.attachInteraction(this);

        setMinSize(getSide(), getSide());
        setMaxSize(getSide(), getSide());

        setAdditionalStyling();

        setAlignment(Pos.CENTER);

        getChildren().add(icon);

        SVGManager.scaleToFit(this, icon);

        setDefaultState();
    }

    abstract void setAdditionalStyling();

    @Override
    public void setDefaultState(){
        setBackground(getNormalBackground());
        iconManager.setDefaultState();
    }

    @Override
    public void setHoverState(){
        setBackground(getHoverBackground());
        iconManager.setHoverState();
    }

    @Override
    public void setActiveState(){
        setBackground(getActiveBackground());
        iconManager.setActiveState();
    }

    @Override
    public void setDisabledState(){
        setBackground(getDisabledBackground());
        iconManager.setDisabledState();
    }
}
