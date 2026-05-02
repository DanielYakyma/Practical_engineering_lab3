package algebraforge.pracitalengineering3.methods;

import algebraforge.pracitalengineering3.MainApplication;
import algebraforge.pracitalengineering3.components.Item;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Node;

import java.util.Comparator;
import java.util.List;

public abstract class Method {
    protected final int DELAY = 250;

    protected final Service<Solution> service = new Service<>() {
        @Override
        protected Task<Solution> createTask() {
            return new Task<>() {
                @Override
                protected Solution call() {
                    long startTime = System.nanoTime();

                    Solution solution = new Solution();

                    try {
                        solution = findSolution();
                    } catch (InterruptedException e) {
                        failed();

                        if (isCancelled()) {
                            return null;
                        }
                    }

                    solution.totalTime = (System.nanoTime() - startTime) / 1_000_000_000.0;

                    solution.optimalItems.sort(Comparator.comparing(Item::getNumber));

                    return solution;
                }
            };
        }
    };


    public Method() {
        service.setOnSucceeded(_ -> MainApplication.main.setSolution(service.getValue()));
    }

    abstract Solution findSolution() throws InterruptedException;

    public abstract Node getView();


    public final void start() {
        service.restart();
    }

    public final void cancel() {
        service.cancel();
    }
}
