import java.util.function.BiFunction;

public class CalculationNode {
    private Double operand;
    private BiFunction<Double, Double, Double> operator;

    /*
    * This BiFunction class is 'container' of any classes that implements ONLY 1 method with 2 (first and second <T>)
    * and one last <T>
    * If you will read documentation in java, so you would see that: BiFunction<T, U, R>
    * @param <T> the type of the first argument to the function
    * @param <U> the type of the second argument to the function
    * @param <R> the type of the result of the function
    * BiConsumer == (T, U) -> R
    * Example: BiConsumer<Double, Double, Double> multiplier = (x, y) -> x * y;
    * multiplier.apply(2, 3) == 6
    * multiplier.apply(7, 2) == 14
    * */

    public Double getOperand() {
        return operand;
    }

    public void setOperand(Double operand) {
        this.operand = operand;
    }

    public boolean filled (){
        return operand != null && operator != null;
    }

    public BiFunction<Double, Double, Double> getOperator() {
        return operator;
    }

    public void setOperator(BiFunction<Double, Double, Double> operator) {
        this.operator = operator;
    }
}
