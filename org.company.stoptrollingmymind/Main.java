import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        CalculatorGUI calculatorGUI = new CalculatorGUI(Constants.TITLE, Constants.WIDTH, Constants.HEIGHT);
        calculatorGUI.setVisible(true);
        calculatorGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculatorGUI.setLocationRelativeTo(null);
    }

}
