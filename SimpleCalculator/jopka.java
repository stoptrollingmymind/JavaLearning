import javax.swing.JFrame;

public class jopka {
	public static void main(String args[]) {
		calculator cr = new calculator("Калькулятор");
		cr.setVisible(true);
		cr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cr.setSize(200,225);
		cr.setResizable(false);
		cr.setLocationRelativeTo(null);
	}
	
}
