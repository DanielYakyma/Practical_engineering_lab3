module algebraforge.pracitalengineering3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires devtoolsfx.gui;
    requires org.girod.javafx.svgimage;

    opens algebraforge.pracitalengineering3 to javafx.graphics;

    exports algebraforge.pracitalengineering3.util;
}