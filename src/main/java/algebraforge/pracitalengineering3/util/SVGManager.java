package algebraforge.pracitalengineering3.util;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import org.girod.javafx.svgimage.SVGImage;
import org.girod.javafx.svgimage.SVGLoader;

import java.util.function.Consumer;

public class SVGManager {
    public static void setFill(Paint paint, SVGImage image) {
        applySVGTransform(image, path -> path.setFill(paint));
    }

    public static void removeStroke(SVGImage image) {
        applySVGTransform(image, path -> path.setStrokeWidth(0));
    }

    public static SVGImage load(String fileName) {
        return SVGLoader.load(SVGManager.class.getResource("/" + fileName + ".svg"));
    }

    public static void applySVGTransform(Group image, Consumer<SVGPath> transform) {
        applyTransform(image, node->transform.accept((SVGPath) node));
    }

    public static void applyTransform(Group image, Consumer<Node> transform){
        for (Node node : image.getChildren()) {
            if (node instanceof Group) {
                applyTransform((Group) node, transform);
                return;
            }

            transform.accept( node);
        }
    }

    public static void scaleToFit(Region parent, SVGImage child) {
        double width = child.getWidth();
        double height = child.getHeight();
        double side = Math.max(width, height);

        double scale = Math.abs((parent.getWidth() - parent.getPadding().getLeft() * 2) / side);
        child.setScaleX(scale);
        child.setScaleY(scale);
    }
}
