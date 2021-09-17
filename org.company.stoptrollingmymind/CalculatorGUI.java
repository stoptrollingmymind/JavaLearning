import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiFunction;

public class CalculatorGUI extends AutoUpdatableGUI {
    private final ArrayDeque<CalculationNode> calculationNodes = new ArrayDeque<>();
    private final Map<String, BiFunction<Double, Double, Double>> panelOperations = new HashMap<>();

    public CalculatorGUI(String title, int width, int height) {
        super(title, width, height);
        panelOperations.put("+", Double::sum); // (left + right) -> left + right
        panelOperations.put("-", (left, right) -> left - right);
        panelOperations.put("*", (left, right) -> left * right);
        panelOperations.put("/", (left, right) -> left / right);
    }

    @Override
    protected void initComponents(int width, int height) {
        width *= 0.75; // only 3/4 of width to use
        height *= 0.75; // only 3/4 of width to use

        final int stepX = (int) (height * 0.1); // 10% of square grid
        final int stepY = (int) (width * 0.1); // 10% of square grid

        final int buttonHeight = (int) (height * 0.2); // 20% of square grid
        final int buttonWidth = (int) (width * 0.2); // 20% of square grid

        int currentStartX = 0;
        int currentStartY = 0;

        // init numeric grid
        for (int i = 0; i < 3; i++) {
            currentStartY += stepY;
            for (int j = 1; j < 4; j++) {
                currentStartX += stepX;
                final int matrixGridValue = i * 3 + j;
                final JButton numericButton = new JButton(String.valueOf(matrixGridValue));
                numericButton.addActionListener(e -> addNumeric(matrixGridValue));
                numericButton.setBounds(new Rectangle(currentStartX, currentStartY, buttonWidth, buttonHeight));

                add(numericButton);
                currentStartX += buttonWidth;
            }
            currentStartX = 0;
            currentStartY += buttonHeight;
        }

        // init operations right panel
        currentStartX = width;
        currentStartY = 0;

        for (String operationName : panelOperations.keySet()) {
            currentStartY += stepY;
            final JButton operationButton = new JButton(operationName);
            operationButton.addActionListener(e -> addOperation(panelOperations.get(operationName)));
            operationButton.setBounds(new Rectangle(currentStartX, currentStartY, buttonWidth, buttonHeight));

            add(operationButton);
            currentStartY += buttonHeight;
        }

        // init result methods

        currentStartX = stepX;
        currentStartY = height;

        final JButton resultButton = new JButton("=");
        resultButton.addActionListener(e -> updateResult());
        resultButton.setBounds(new Rectangle(currentStartX, currentStartY, 2 * buttonWidth + stepX, buttonHeight));
        add(resultButton);

        currentStartX += 2 * buttonWidth + 2 * stepX;

        final JButton clearButton = new JButton("clear");
        clearButton.addActionListener(e -> {
            calculationNodes.clear();
            setTitle(Constants.TITLE);
        });
        clearButton.setBounds(new Rectangle(currentStartX, currentStartY, buttonWidth, buttonHeight));
        add(clearButton);
    }

    private void updateResult() {
        double result = 0;
        if(!calculationNodes.isEmpty()){
            final Iterator<CalculationNode> reversePropagation = calculationNodes.descendingIterator();
            while(reversePropagation.hasNext()) {
                final CalculationNode currentNode = reversePropagation.next();
                if(currentNode.filled() && reversePropagation.hasNext()){
                    result += currentNode.getOperator().apply(result, currentNode.getOperand());
                }
            }
        }
        setTitle(Constants.TITLE + " | Result: " + result);
        calculationNodes.clear();
    }

    private void addNumeric(int numeric) {
        if (calculationNodes.isEmpty() || calculationNodes.peekLast() == null || calculationNodes.peekLast().filled()) {
            final CalculationNode context = new CalculationNode();
            context.setOperand((double) numeric);
            calculationNodes.add(context);
        } else {
            final CalculationNode context = calculationNodes.peekLast();
            assert context != null;
            context.setOperand((double) numeric);
        }
    }

    private void addOperation(BiFunction<Double, Double, Double> operation) {
        if (calculationNodes.isEmpty() || calculationNodes.peekLast() == null || calculationNodes.peekLast().filled()) {
            final CalculationNode context = new CalculationNode();
            context.setOperator(operation);
            calculationNodes.add(context);
        } else {
            final CalculationNode context = calculationNodes.peekLast();
            assert context != null;
            context.setOperator(operation);
        }
    }

}