package algebraforge.pracitalengineering3.util;

public abstract class ProcessorData {
    public final String message;

    public ProcessorData(String message) {
        this.message = message;
    }

    public abstract Number process(String input);


    public static ProcessorData createPositiveProcessor(String message) {
        return new ProcessorData(message) {
            @Override
            public Number process(String input) {
                double value = Double.parseDouble(input);

                if (value < 0)
                    throw new RuntimeException();

                return value;
            }
        };
    }
}
