package algebraforge.pracitalengineering3.methods;

import algebraforge.pracitalengineering3.MainApplication;
import algebraforge.pracitalengineering3.components.Item;
import javafx.scene.Node;

import java.util.List;

public class RecursiveMethod extends Method{
    @Override
    Solution findSolution() {
        Solution solution = new Solution();

        List<Item> items = MainApplication.main.getItems();
        double maxWeight = MainApplication.main.getMaximalWeight();


        return solution;
    }

    @Override
    public Node getView() {
        return null;
    }
}
