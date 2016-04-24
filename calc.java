import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.WritableObjectValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class calc extends Application {
	
	private TextField textField = new TextField(); 
	int mode;
	Float DoubleX;
	Float DoubleY;
	@Override
	public void start(Stage primaryStage) {
	
		List<String> buttons = Arrays.asList("7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", "=", "/","c","del",".","+/-");
	
	    FlowPane pane = new FlowPane();
	    pane.setAlignment(Pos.CENTER);
	    pane.setPadding(new Insets(30, 20, 30, 20));
	    pane.setHgap(5);
	    pane.setVgap(5);
	    pane.setMinWidth(400);
	    pane.setPrefWidth(400);
	    pane.setMaxWidth(400);
	    MenuBar menuBar = new MenuBar();
	   
	    // --- Menu File
	    Menu menuFile = new Menu("View");
	
	    // --- Menu Edit
	    Menu menuEdit = new Menu("Edit");
	
	    // --- Menu View
	    Menu menuView = new Menu("說明");
	    
	    MenuItem add = new MenuItem("標準型");    
	    MenuItem add1 = new MenuItem("工程型");   
	    MenuItem add2 = new MenuItem("程式設計師");   
	    MenuItem add3 = new MenuItem("統計資料");   
	    menuFile.getItems().addAll(add,add1,add2,add3);
	    
	    menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
	    pane.getChildren().add(menuBar);
	    textField.setEditable(false);
	    textField.setAlignment(Pos.CENTER);
	    textField.setMinSize(336, 40);
	    // Rezultat.textProperty().bind(Bindings.format("%.0f", value));
	    pane.getChildren().add(textField);
	
	    for (String button : buttons) {
	        Button b = new Button(button);
	        b.setMinSize(80, 80);
	        pane.getChildren().add(b);
	        b.setOnAction((e) -> doSomething(b.getText()));
	    }
	
	    Scene scene = new Scene(pane);
	    scene.setFill(Color.OLDLACE);
	    primaryStage.setTitle("Calculator");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	
	}
	
	private void doSomething(String text) {
	
	    if (text.equalsIgnoreCase("=")) {
	    	//exceut Operation
	        String y = textField.getText();
	        DoubleY = Float.parseFloat(y);
	        System.out.println("x = "+DoubleX);
	        System.out.println("y = "+DoubleY);
	        switch (mode) {
			case 1://pius
				DoubleX+=DoubleY;
				break;
			case 2://Subtract
				DoubleX-=DoubleY;
				break;
			case 3://Multiply
				DoubleX*=DoubleY;
				break;
			case 4://Divide
				DoubleX/=DoubleY;
				break;
			default:
				break;
			}
	        textField.setText(String.valueOf(round(DoubleX,getTargetNumber(String.valueOf(DoubleX)),BigDecimal.ROUND_UP)));
	    }else if (text.equalsIgnoreCase("+") || text.equalsIgnoreCase("-") || text.equalsIgnoreCase("*") || text.equalsIgnoreCase("/")) {
	        String x = textField.getText();
	        DoubleX = Float.parseFloat(x);
	        if(text.equalsIgnoreCase("+")){
	        	mode = 1 ;
	        }else if(text.equalsIgnoreCase("-")){
	        	mode = 2 ;
	        }else if(text.equalsIgnoreCase("*")){
	        	mode = 3 ;
	        }else if(text.equalsIgnoreCase("/")){
	        	mode = 4 ;
	        }else{
	        	System.out.println("Operation error");
	        }
	        textField.setText("");
	    }
	    else if(text.equalsIgnoreCase("c")){//clean
	    	textField.setText("");
	    	DoubleX = (float) 0 ;
	    	DoubleY = (float) 0 ;
	    }
	    else if(text.equalsIgnoreCase("del")){//del
	    	textField.setText(textField.getText().substring(0,textField.getText().length()-1));
	    }
	    else if(text.equalsIgnoreCase(".")){//dot
	    	if(textField.getText().indexOf(".")<0){
	    		textField.appendText(".");
	    	}
	    }
	    else if(text.equalsIgnoreCase("+/-")){//change +/-	
	    	String z = textField.getText();
	        Float DoubleZ = Float.parseFloat(z);
	        DoubleZ = -DoubleZ;
	        textField.setText(String.valueOf(round(DoubleZ,getTargetNumber(String.valueOf(DoubleZ)),BigDecimal.ROUND_UP)));
	    }
	    else {
	        // numeric
	        textField.appendText(text);
	    }
	}
	public static double round(double value, int scale, int roundingMode) {
		//number's Decimals round;
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, roundingMode);
		double d = bd.doubleValue();
		bd = null;
		return d;
	}
	public static int getTargetNumber(String targetStr) {
		//get the number's Decimals 
		int i;
		int length = 0;
		char[] charX = targetStr.toCharArray();
	    for (i = 0; i < charX.length; i++) {
	      if (charX[i] == '.') {
	    	  length = charX.length - (i+1) ;
	      }
	    }
	    return length;
	 }
	public static void main(String[] args) {
	    Application.launch(args);
	
	}

}