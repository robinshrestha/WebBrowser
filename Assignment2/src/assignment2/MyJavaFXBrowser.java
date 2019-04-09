package assignment2;
/*File name: [MyJavaFXBrowser.java ]
Author: [ Robin Shrestha, 040880427]
Course: CST8284 – OOP Java
Assignment: [2]
Date: [01/12/2017]
Professor: [DAVID B HOUTMAN]
Purpose: [getting scenes and running the webpage]
*/
import java.io.File;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.geometry.Insets;
/** This class is responsible for getting scenes and running webpage
 * @author Robin Shrestha, Student Number: 040880427
 * @version Assignment 2
 * @see javafx
 * @since 1.2
 */
public class MyJavaFXBrowser extends Application {
	HBox address;
	ArrayList<String> bookmarks, defaultWeb; /** Arraylist bookmarks, defaultWeb defined */
	private static TextField textField; /** TextField as Text defined */
	/**
	 * getter of textFileld method is made
	 * @return textField as TextField Object
	 */
	public static TextField getTextField() {
		return textField;
	}
	/**
	 * This is method for starting stage in website
	 	 		 */
	@Override
	public void start(Stage primaryStage) {
		VBox topBox = new VBox(); /** VBox object topBox is created */
		
		

		WebPage currentPage = new WebPage();
		currentPage.createWebEngine(primaryStage);
		WebView webView = currentPage.getWebView();
		WebEngine webEngine = currentPage.createWebEngine(primaryStage); /** WebEngine object is created */
		//webEngine.load("http://www.google.ca");
		

		File myFileDefault = new File("default.web"); /** New File object is made from default.web */
		File myFile = new File("bookmarks.web"); /** New File object is made from bookmarks.web */

		bookmarks = new ArrayList<String>();
		defaultWeb = new ArrayList<String>();

		bookmarks = FileUtils.getFileContentsAsArrayList(myFile, bookmarks);
		defaultWeb = FileUtils.getFileContentsAsArrayList(myFileDefault, defaultWeb);
		/**
		 * bookmarks and default Web files are called from getFileContentsAsArray from FileUtils.java class
		 	 		 */
		if (defaultWeb.size() > 0) {
			/**
			 * Website loaded from defaultWeb
			 	 		 */
			//webEngine.load(defaultWeb.get(defaultWeb.size() - 1));
			Menus.loadWebsite(webView,defaultWeb.get(defaultWeb.size()-1));
		} else {
			//webEngine.load("http://google.com");
			Menus.loadWebsite(webView,"http://google.com");
		}

		for (String string : bookmarks) {
			MenuItem menuItemBk = new MenuItem(string);
			menuItemBk.setOnAction(actionEvent -> {
				//webEngine.load(menuItemBk.getText());
				Menus.loadWebsite(webView,menuItemBk.getText());
			});
			Menus.getMnuBookmark(webView).getItems().add(menuItemBk);

		}

		MenuItem mnuItem = Menus.getMnuItmToggleAddressBar();
		// TODO: Add additional code as required
		mnuItem.setOnAction(ActionEvent -> {
			if (address == null) {
				address = new HBox();
				Label labelAddress = new Label("Enter Address");
				labelAddress.setTextAlignment(TextAlignment.CENTER);
				labelAddress.setPadding(new Insets(5, 5, 5, 5));
				textField = new TextField("");
				textField.setPromptText("address here");
				textField.setOnKeyPressed(e -> {
					if (e.getCode() == KeyCode.ENTER) {
						Menus.loadWebsite(webView, textField.getText());
						/**
						 * Enter method action for loading website
						 	 		 */
					}
					//stackoverflow
					//date-2014 
					//How do I pick up the Enter Key being pressed in JavaFX2?
					//[Webpage] 
					//https://stackoverflow.com/questions/13880638/how-do-i-pick-up-the-enter-key-being-pressed-in-javafx2/13881850
				});
				Button buttonGo = new Button("Go");
				buttonGo.setOnAction(actionEvent -> {
					Menus.loadWebsite(webView,textField.getText());
					/**
					 * Go button loads website website
					 	 		 */
					
				});
				

				HBox.setHgrow(textField, Priority.ALWAYS);
				address.getChildren().addAll(labelAddress, textField, buttonGo);
				/**
				 * button and address lebel set
				 	 		 */
				topBox.getChildren().add(address);
			} else {
				topBox.getChildren().remove(address);
				address = null;
			}
			//Oracle
			//2017
			//Class HBox
			//[webpage]
			//https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/HBox.html

		});

		
		topBox.getChildren().add(Menus.getMenuBar(webView));
		/**
		 * Setting Center, top, history and bottom pane in BorderPane
		 	 		 */
		BorderPane root = new BorderPane();
		root.setCenter(webView);
		root.setTop(topBox);
		root.setRight(Menus.getPaneHistory(webView));
		root.setBottom(Menus.loadBottomPane());
		Scene scene = new Scene(root, 800, 500);
		primaryStage.setOnCloseRequest(closeEvent -> { /** when the program is closed default.web gets updated */
			FileUtils.addNewDefaultOrBookmark("\n" + webEngine.getLocation(), "default.web");
			Platform.exit();
		});
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	/**
	 * This is main method for running application files
	 * @param args string of arrays
	 		 */
	public static void main(String[] args) {
		Application.launch(args);
	}

}
