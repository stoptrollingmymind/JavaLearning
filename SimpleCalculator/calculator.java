import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class calculator extends JFrame 
{
	JTextField field;
	JButton b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bMultiply,bSummary,bDivide, bSubtract,bClear,bResult;
	eHandler handler = new eHandler();
	public calculator(String s) {
		super(s);
		setLayout(new FlowLayout());
		field = new JTextField("0");
		field.setColumns(10);
		bClear = new JButton("C");
		b7 = new JButton("7");
		b8 = new JButton("8");
		b9 = new JButton("9");
		bDivide = new JButton("/");
		b4 = new JButton("4");
		b5 = new JButton("5");
		b6 = new JButton("6");
		bMultiply = new JButton("*");
		b1 = new JButton("1");
		b2 = new JButton("2");
		b3 = new JButton("3");
		bSubtract = new JButton("-");
		b0 = new JButton("0");
		bSummary = new JButton("+");
		bResult = new JButton("=");
		add(field);
		add(bClear);
		add(b7);
		add(b8);
		add(b9);
		add(bDivide);
		add(b4);
		add(b5);
		add(b6);
		add(bMultiply);
		add(b1);
		add(b2);
		add(b3);
		add(bSubtract);
		add(b0);
		add(bSummary);
		add(bResult);
		b1.addActionListener(handler);
		b2.addActionListener(handler);
		b3.addActionListener(handler);
		b4.addActionListener(handler);
		b5.addActionListener(handler);
		b6.addActionListener(handler);
		b7.addActionListener(handler);
		b8.addActionListener(handler);
		b9.addActionListener(handler);
		b0.addActionListener(handler);
		bClear.addActionListener(handler);
		bMultiply.addActionListener(handler);
		bSummary.addActionListener(handler);
		bSubtract.addActionListener(handler);
		bDivide.addActionListener(handler);
		bResult.addActionListener(handler);
	}
	public class eHandler implements ActionListener
	{
		String buf, actionPrev;
		int toField, buffer;
		boolean flag=false;
		protected void scaling() {
			if(actionPrev=="+") {
				toField = buffer + Integer.parseInt(field.getText());
				field.setText(Integer.toString(toField));
			}
			if(actionPrev=="*") {
				toField = buffer * Integer.parseInt(field.getText());
				field.setText(Integer.toString(toField));
			}
			if(actionPrev=="/") {
				toField = buffer / Integer.parseInt(field.getText());
				field.setText(Integer.toString(toField));
			}
			if(actionPrev=="-") {
				toField = buffer - Integer.parseInt(field.getText());
				field.setText(Integer.toString(toField));
			}
		}
		protected void afterActionNextNumber() {
			if(flag) {
				field.setText("0");
				flag=false;
			}
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==bClear) {field.setText("0");buffer=0;}
			if(e.getSource()==b1) {
				afterActionNextNumber();
				toField = Integer.parseInt(field.getText()) * 10 + 1; field.setText(Integer.toString(toField));}
			if(e.getSource()==b2) {
				afterActionNextNumber();
				toField = Integer.parseInt(field.getText()) * 10 + 2; field.setText(Integer.toString(toField));}
			if(e.getSource()==b3) {
				afterActionNextNumber();
				toField = Integer.parseInt(field.getText()) * 10 + 3; field.setText(Integer.toString(toField));}
			if(e.getSource()==b4) {
				afterActionNextNumber();
				toField = Integer.parseInt(field.getText()) * 10 + 4; field.setText(Integer.toString(toField));}
			if(e.getSource()==b5) {
				afterActionNextNumber();
				toField = Integer.parseInt(field.getText()) * 10 + 5; field.setText(Integer.toString(toField));}
			if(e.getSource()==b6) {
				afterActionNextNumber();
				toField = Integer.parseInt(field.getText()) * 10 + 6; field.setText(Integer.toString(toField));}
			if(e.getSource()==b7) {
				afterActionNextNumber();
				toField = Integer.parseInt(field.getText()) * 10 + 7; field.setText(Integer.toString(toField));}
			if(e.getSource()==b8) {
				afterActionNextNumber();
				toField = Integer.parseInt(field.getText()) * 10 + 8; field.setText(Integer.toString(toField));}
			if(e.getSource()==b9) {
				afterActionNextNumber();
				toField = Integer.parseInt(field.getText()) * 10 + 9; field.setText(Integer.toString(toField));}
			if(e.getSource()==b0) {
				afterActionNextNumber();
				toField = Integer.parseInt(field.getText()) * 10; field.setText(Integer.toString(toField));}
			if(e.getSource()==bMultiply) {
				scaling();
				buffer = Integer.parseInt(field.getText()); 
				actionPrev = bMultiply.getText();
				flag=true;
			}
			if(e.getSource()==bSummary) 
			{
				scaling();
				buffer = Integer.parseInt(field.getText()); 
				actionPrev = bSummary.getText();
				flag=true;
			}
			if(e.getSource()==bSubtract)
			{
				scaling();
				buffer = Integer.parseInt(field.getText()); 
				actionPrev = bSubtract.getText();
				flag=true;
			}
			if(e.getSource()==bDivide) 
			{
				scaling();
				buffer = Integer.parseInt(field.getText()); 
				actionPrev = bDivide.getText();
				flag=true;
			}
			if(e.getSource()==bResult) 
			{
				scaling();				
				buffer=0;
				actionPrev="";
			}
		
		}
	}

}