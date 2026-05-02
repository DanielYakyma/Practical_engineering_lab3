package algebraforge.pracitalengineering3.methods;

import algebraforge.pracitalengineering3.components.LargeText;
import algebraforge.pracitalengineering3.util.ItemBinding;
import javafx.application.Platform;
import javafx.scene.layout.VBox;

class TextSolution {
    private final LargeText optimalItemsText = new LargeText();
    private final LargeText totalWeightText = new LargeText();
    private final LargeText totalValueText = new LargeText();

    private final String firstString;

    TextSolution(String firstString) {
        this.firstString = firstString;

        setSolution(new Solution());
    }


    void setSolution(Solution solution) {
        Platform.runLater(()->{
            optimalItemsText.setText(firstString + ItemBinding.convertItemsToString(solution.optimalItems));

            totalWeightText.setText("Вага: " + solution.totalWeight);
            totalValueText.setText("Цінність: " + solution.totalValue);
        });
    }

    public final VBox content = new VBox(optimalItemsText, totalWeightText, totalValueText);
}
