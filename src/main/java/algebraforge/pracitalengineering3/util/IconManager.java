package algebraforge.pracitalengineering3.util;

import org.girod.javafx.svgimage.SVGImage;

import java.util.function.Consumer;

public class IconManager implements Interactable{
    private final SVGImage icon;

    public Consumer<SVGImage> setDefault=i->SVGManager.setFill(Style.grey5, i);
    public Consumer<SVGImage> setHover=i->SVGManager.setFill(Style.grey6, i);
    public Consumer<SVGImage> setActive=i->SVGManager.setFill(Style.grey7, i);
    public Consumer<SVGImage> setDisabled=i->SVGManager.setFill(Style.grey3, i);

    public IconManager(SVGImage icon){
        this.icon=icon;
    }

    public SVGImage getIcon(){
        return icon;
    }

    @Override
    public void setDefaultState() {
        setDefault.accept(icon);
    }

    @Override
    public void setHoverState() {
        setHover.accept(icon);
    }

    @Override
    public void setActiveState() {
        setActive.accept(icon);
    }

    @Override
    public void setDisabledState() {
        setDisabled.accept(icon);
    }
}
