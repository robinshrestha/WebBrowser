/*File name: [Menus.java ]
Author: [ Robin Shrestha, 040880427]
Course: CST8284 – OOP Java
Assignment: [2]
Date: [01/12/2017]
Professor: [DAVID B HOUTMAN]
Purpose: [Menu Items creation and their actions]
*/
package assignment2;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;


import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import javafx.scene.input.KeyCombination;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


import java.net.MalformedURLException;
import java.net.URL;


import javafx.application.Platform;
import javafx.collections.ObservableList;

import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
/** This class deals with menu item in the web explorer project
 * @author Robin Shrestha, Student Number: 040880427
 * @version Assignment 2
 * @see javafx
 * @since 1.2
 */

public class Menus {

	private static MenuBar menuBar; /** File Menu Bar */
	private static Menu mnuFile; /** File Menu */
	private static Menu mnuSettings;/** Setting Menu */
	private static MenuItem mnuItmToggleAddressBar;/** Toggle Address Bar Menu Item */
	private static Menu mnuHelp;/** Help Menu */
	private static Menu mnuBookmark;/** Bookmark Menu */
	private static MenuItem mnuItmRefresh;/** Refresh Menu Item */
	private static MenuItem mnuItmExit;/** Exit Menu Item */
	private static MenuItem mnuItmChangeStartup;/** Change Startup Menu Item */
	private static MenuItem mnuItemAddBookmark;/** Add Bookmark Menu Item */
	private static MenuItem mnuItemAbout;/** About Menu Item */
	private static MenuItem mnuItemHistory;/** History Menu Item */
	private static MenuItem mnuItemDisplayCode;/** Display Code Menu Item */
	private static VBox rightPaneHistory;/** VBox for RightPanel History */
	private static boolean openHistory = true;/** Open History Boolean , True, False */
	private static boolean openDisplayCode = true;/** Boolean for Display Code */
	private static VBox toggleHistoryBox;/** VBox for toggle History */
	private static HBox bottomPane;/** HBox for bottomPane */
	private static Text htmlJava;/** Text Object htmlJava */
	

	/**
	 * Gets all menu items to Menu Bars
	 * @param wv- WebView engine
	 * @return mnuBar
	 */
	public static MenuBar getMenuBar(WebView wv) {
		menuBar = new MenuBar();
		menuBar.getMenus().addAll(getMnuFile(wv), getMnuSettings(wv), getMnuBookmark(wv), getMnuHelp());
		return menuBar;
	}

	/**
	 * Gets Menu File and sets accelerator key
	 * @param wv -WebView engine
	 * @return mnuFile File 
	 */
	public static Menu getMnuFile(WebView wv) {
		mnuFile = new Menu("_File");
		mnuFile.setAccelerator(KeyCombination.keyCombination("Ctrl+F"));

		mnuFile.getItems().addAll(getMnuItmRefresh(wv), getMnuItmExit(wv));

		return mnuFile;
	}

	/**
	 * Menu Item refresh is made and set to action and sets accelerator key
	 * @param wv -WebView engine
	 * @return mnuItmRefresh Refresh
	 */
	public static MenuItem getMnuItmRefresh(WebView wv) {
		mnuItmRefresh = new MenuItem("_Refresh");
		mnuItmRefresh.setAccelerator(KeyCombination.keyCombination("Ctrl+R"));
		mnuItmRefresh.setOnAction(actionEvent -> {
			wv.getEngine().reload(); /** reloads the website */
		});
		return mnuItmRefresh;

	}

	/**
	 * Menu Item exit is made and set to action and sets accelerator key
	 * @param wv- WebView engine
	 * @return mnuItmExit exit
	 */
	public static MenuItem getMnuItmExit(WebView wv) {
		mnuItmExit = new MenuItem("_Exit");
		mnuItmExit.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));
		mnuItmExit.setOnAction(actionEvent -> {

			FileUtils.addNewDefaultOrBookmark("\n" + wv.getEngine().getLocation(), "default.web");
			Platform.exit();

		});
		return mnuItmExit;
	}

	/**
	 * Menu Settings is made and set to action and sets accelerator key
	 * @param wv- WebView engine
	 * @return mnuSettings settings
	 */
	public static Menu getMnuSettings(WebView wv) {
		mnuSettings = new Menu("_Settings");
		mnuSettings.setAccelerator(KeyCombination.keyCombination("Ctrl+M"));
		mnuSettings.getItems().addAll(getMnuItmToggleAddressBar(), getMnuItmChangeStartup(wv), getMnuItmHistory(wv),
				getMnuItmDisplayCode(wv));
		return mnuSettings;
	}

	/**
	 * Menu Item Change Startup is made and set to action and sets accelerator key
	 * @param wv- WebView engine
	 * @return mnuItmChangeStartup Change Start-up page
	 */
	public static MenuItem getMnuItmChangeStartup(WebView wv) {
		mnuItmChangeStartup = new MenuItem("_Change Start-up Page");
		mnuItmChangeStartup.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
		mnuItmChangeStartup.setOnAction(actionEvent -> {
			FileUtils.addNewDefaultOrBookmark("\n" + wv.getEngine().getLocation(), "default.web");
/** Start up we page address */
		});
		return mnuItmChangeStartup;
	}

	/**
	 * Menu Item Toggle Address Bar is made and set to action and sets accelerator key
	 * @return mnuItmToggleAddressBar Toggle Address Bar
	 */
	public static MenuItem getMnuItmToggleAddressBar() {
		if (mnuItmToggleAddressBar == null) {
			mnuItmToggleAddressBar = new MenuItem("_Toggle Address Bar");
			mnuItmToggleAddressBar.setAccelerator(KeyCombination.keyCombination("Ctrl+T"));

		}

		return mnuItmToggleAddressBar;
	}

	/**
	 * Menu Item is History is made and set to action and sets accelerator key
	 * @param wv-WebView engine
	 * @return mnuItemHistory History
	 */
	public static MenuItem getMnuItmHistory(WebView wv) {

		mnuItemHistory = new MenuItem("_History");
		mnuItemHistory.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
		mnuItemHistory.setOnAction(actionEvent -> {

			// rightPaneHistory.setVisible(openHistory);

			// callHistoryList(wv);
			if (openHistory)
				rightPaneHistory.getChildren().add(toggleHistoryBox);
			else
				rightPaneHistory.getChildren().remove(toggleHistoryBox);
			openHistory = !openHistory;
			/** Toggle History */
		});
		return mnuItemHistory;
	}

	/**
	 * Menu Item Display Code is made and set to action and sets accelerator key
	 * @param wv- WebView engine
	 * @return mnuItemDisplayCode Display Code
	 */
	public static MenuItem getMnuItmDisplayCode(WebView wv) {

		mnuItemDisplayCode = new MenuItem("_Display Code");
		mnuItemDisplayCode.setAccelerator(KeyCombination.keyCombination("Ctrl+D"));
		mnuItemDisplayCode.setOnAction(actionEvent -> {

			// rightPaneHistory.setVisible(openHistory);

			// callHistoryList(wv);
			if (openDisplayCode)
				bottomPane.getChildren().add(htmlJava);
			// sp.setContent(getHtmlJava());

			else
				bottomPane.getChildren().remove(htmlJava);
			// sp.setContent(null);
			openDisplayCode = !openDisplayCode;
			/** Toggle Display Code */
		});
		return mnuItemDisplayCode;
	}

	/**
	 * HBox load Bottom Pane is made and a text file object is made
	 * @return bottomPane HBox buttom Pane
	 */
	public static HBox loadBottomPane() {
		if (bottomPane == null) {

			htmlJava = new Text();
			bottomPane = new HBox();
			// bottomPane.setPadding(new Insets(10, 10, 10, 10));
			bottomPane.setMaxHeight(100);
		}

		return bottomPane;
	}
	

	/**
	 * htmlJava getter is made
	 * @return htmlJava Text
	 */
	public static Text getHtmlJava() {
		return htmlJava;
	}

	/**
	 * Menu for bookmark
	 * @param wv -WebView engine
	 * @return mnuBookmark Bookmark menu
	 */
	public static Menu getMnuBookmark(WebView wv) {
		if (mnuBookmark == null) {
			mnuBookmark = new Menu("_Bookmark");
			mnuBookmark.setAccelerator(KeyCombination.keyCombination("Ctrl+B"));
			mnuBookmark.getItems().add(getMnuItemAddBookmark(wv));
		}

		return mnuBookmark;
	}

	/**
	 * Menu Item Add bookmark is made and is set on action
	 * @param wv-WebView engine
	 * @return mnuItemAddBookmark as Bookmark item
	 */
	public static MenuItem getMnuItemAddBookmark(WebView wv) {
		if (mnuItemAddBookmark == null) {
			mnuItemAddBookmark = new MenuItem("_Add Bookmark");
			mnuItemAddBookmark.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
			mnuItemAddBookmark.setOnAction(actionEvent -> {
				FileUtils.addNewDefaultOrBookmark("\n" + wv.getEngine().getLocation(), "bookmarks.web");
				MenuItem newBM = new MenuItem();
				newBM.setText(wv.getEngine().getLocation());
				
				mnuBookmark.getItems().add(newBM);
				/** add webaddress to bookmark array */
			});
		}

		return mnuItemAddBookmark;
	}
	

	/**
	 * Menu Item menu help is made and sets accelerator key
	 * @return mnuHelp as help item
	 */
	public static Menu getMnuHelp() {
		mnuHelp = new Menu("_Help");
		mnuHelp.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
		mnuHelp.getItems().add(getMnuItemAbout());
		return mnuHelp;
	}

	/**
	 * Menu Item mnu about is made and sets accelerator key
	 * @return mnuItemAbout as About
	 */
	public static MenuItem getMnuItemAbout() {

		mnuItemAbout = new MenuItem("_About");
		mnuItemAbout.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
		mnuItemAbout.setOnAction(ActionEvent -> {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Robin Shrestha, Student Number: 040880427");

			alert.showAndWait();
			/** showing about item action */
		});
		return mnuItemAbout;
	}
	
	/**
	 * VBox is made
	 * @param wv-  WebView engine
	 * @return rightPaneHistory as History List
	 */
	public static VBox getPaneHistory(WebView wv) {

		rightPaneHistory = new VBox();
		callHistoryList(wv);

		return rightPaneHistory;

	}
	/**
	 * WebHistory object is made
	 * @param wv-  WebView engine
	 * ArrayList is set in the pane
	 */
	public static void callHistoryList(WebView wv) {
		
		final WebHistory rightHistory = wv.getEngine().getHistory();
		
		ObservableList<WebHistory.Entry> entryList = rightHistory.getEntries();
		
		/** Getting History Items */
		// }
		ListView<WebHistory.Entry> urlHistoryList = new ListView<>(entryList);
		/** Setting action of back button */
		Button buttonBack = new Button("Back");
		buttonBack.setOnAction(e -> {

			int currentIndex = rightHistory.getCurrentIndex();

			Platform.runLater(() -> // reference 1
			{
				rightHistory.go(entryList.size() > 1 && currentIndex > 0 ? -1 : 0);
			});
		});
		/** Setting action of Forward button */
		Button buttonForward = new Button("Forward");
		if (rightHistory.getCurrentIndex() == rightHistory.getMaxSize()) {
			buttonForward.setDisable(true);
		}
		;
		{

			buttonForward.setOnAction(e -> {

				int currentIndex = rightHistory.getCurrentIndex();

				Platform.runLater(() -> {
					rightHistory.go(entryList.size() > 1 && currentIndex < entryList.size() - 1 ? 1 : 0);
				});
				//stackoverflow
				//date-2013 
				//How to program Back and Forward buttons in JavaFX with WebView and WebEngine?
				//[Webpage] 
				//https://stackoverflow.com/questions/18928333/how-to-program-back-and-forward-buttons-in-javafx-with-webview-and-webengine
			});

			
			HBox buttonAdd = new HBox();
			buttonAdd.getChildren().addAll(buttonBack, buttonForward);
			toggleHistoryBox = new VBox();

			toggleHistoryBox.getChildren().addAll(urlHistoryList, buttonAdd);
			VBox.setVgrow(urlHistoryList, Priority.ALWAYS);
		}
		/** button and history list added */
	}
	/**
	 /**
	 * @throws StringIndexOutOfBoundsException  If an input or output exception occurred
	 * @throws IllegalArgumentException   If an input or output exception occurred                     
 	 * @param wv-  WebView engine
	 * @param website is String Object
		 */
	public static void loadWebsite(WebView wv, String website)
			throws StringIndexOutOfBoundsException, IllegalArgumentException 
			/** throws exception for StringIndexOutOfBoundsExcepton, IllegalArgumentException */
	{
		try {// see this code
			new URL(website);
			wv.getEngine().load(website);
		} catch (MalformedURLException | StringIndexOutOfBoundsException | IllegalArgumentException e) {
			int i = 0;
			i++;
		}
		int j = 0;
		j++;
	}

}
