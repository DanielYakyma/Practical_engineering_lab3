package algebraforge.pracitalengineering3.util;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Style {
    public static final double statementScale = 2;
    public static final double moduleScale = 12d / 16d;
    public static final double systemConnectionWidth = 6;

    public static final Insets largePadding = new Insets(16);
    public static final Insets normalPadding = new Insets(8);
    public static final Insets smallPadding = new Insets(4);

    public static final Color grey0 = createColor(247);
    public static final Color grey1 = createColor(240);
    public static final Color grey2 = createColor(214);
    public static final Color grey3 = createColor(189);
    public static final Color grey4 = createColor(163);
    public static final Color grey5 = createColor(138);
    public static final Color grey6 = createColor(112);
    public static final Color grey7 = createColor(87);
    public static final Color grey8 = createColor(61);

    public static final Color green300 = createColor(97, 229, 119);
    public static final Color green400 = createColor(66, 224, 93);
    public static final Color green500 = createColor(19, 216, 52);
    public static final Color green600 = createColor(17, 197, 47);
    public static final Color green700 = createColor(13, 153, 37);
    public static final Color green800 = createColor(10, 119, 29);
    public static final Color green900 = createColor(8, 91, 22);

    public static final Color yellow400 = createColor(221, 224, 66);
    public static final Color yellow500 = createColor(213, 216, 19);
    public static final Color yellow600 = createColor(194, 197, 17);
    public static final Color yellow700 = createColor(151, 153, 13);
    public static final Color yellow800 = createColor(117, 119, 10);
    public static final Color yellow900 = createColor(89, 91, 8);

    public static final Color red50 = createColor(251, 231, 231);
    public static final Color red100 = createColor(243, 182, 182);
    public static final Color red400 = createColor(224, 66, 66);
    public static final Color red500 = createColor(216, 19, 19);
    public static final Color red600 = createColor(197, 17, 17);
    public static final Color red700 = createColor(153, 13, 13);
    public static final Color red800 = createColor(119, 10, 10);
    public static final Color red900 = createColor(91, 8, 8);

    public static final Color orange400 = createColor(224, 150, 66);
    public static final Color orange500 = createColor(216, 124, 19);
    public static final Color orange600 = createColor(197, 113, 17);
    public static final Color orange700 = createColor(153, 88, 13);
    public static final Color orange800 = createColor(119, 68, 10);

    public static final Color blue50 = createColor(231, 231, 251);
    public static final Color blue100 = createColor(182, 182, 243);
    public static final Color blue200 = createColor(143, 143, 237);
    public static final Color blue300 = createColor(97, 97, 229);
    public static final Color blue400 = createColor(66, 66, 224);
    public static final Color blue500 = createColor(19, 19, 216);
    public static final Color blue600 = createColor(17, 17, 197);
    public static final Color blue700 = createColor(13, 13, 153);
    public static final Color blue800 = createColor(10, 10, 119);
    public static final Color blue900 = createColor(8, 8, 91);

    public static final Color cyan400 = createColor(64, 224, 221);
    public static final Color cyan500 = createColor(19, 216, 213);
    public static final Color cyan600 = createColor(17, 197, 194);
    public static final Color cyan700 = createColor(13, 153, 151);
    public static final Color cyan800 = createColor(10, 119, 117);
    public static final Color cyan900 = createColor(8, 91, 89);

    public static final Color violet400 = createColor(173, 66, 224);
    public static final Color violet500 = createColor(153, 19, 216);
    public static final Color violet600 = createColor(139, 17, 197);
    public static final Color violet700 = createColor(109, 13, 153);
    public static final Color violet800 = createColor(84, 10, 119);

    public static final Font labelSmall = Font.font("Roboto", FontWeight.MEDIUM, 10);
    public static final Font labelLarge = Font.font("Roboto", FontWeight.MEDIUM, 14);
    public static final Font headlineMedium = Font.font("Roboto", FontWeight.NORMAL, 28);
    public static final Font displaySmall = Font.font("Roboto", FontWeight.NORMAL, 36);

    public static final String textColor = """
            -fx-text-fill: #3D3D3D;
            -fx-opacity: 1.0;""";

    public static final CornerRadii largeRadius = new CornerRadii(16);
    public static final CornerRadii normalRadius = new CornerRadii(8);
    public static final CornerRadii smallRadius = new CornerRadii(4);

    public static final Background pageBackground = new Background(new BackgroundFill(grey1, null, null));
    public static final Background surfaceBackground = new Background(new BackgroundFill(Style.grey2, null, null));

    public static Background createBackgroundFill(Paint paint) {
        return new Background(new BackgroundFill(paint, null, null));
    }


    public static final Border normalBorder = new Border(new BorderStroke(
            Style.grey4,
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            new BorderWidths(1, 1, 1, 1),
            Insets.EMPTY));

    private static Color createColor(double r, double g, double b) {
        return Color.color(r / 255d, g / 255d, b / 255d);
    }

    private static Color createColor(double all) {
        return Color.color(all / 255d, all / 255d, all / 255d);
    }

    public static Color changeOpacity(Color color, double opacity) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity);
    }
}
