package assignment2;
/*File name: [WebPage.java ]
Author: [David B. Houtman, Co-author- Robin Shrestha, 040880427]
Course: CST8284 – OOP Java
Assignment: [2]
Date: [01/12/2017]
Professor: [DAVID B HOUTMAN]
Purpose: [Webpage connection and getting html code]
*/
import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


/** This class is responsible for connecting web page and getting the html code
 * @author Robin Shrestha, Student Number: 040880427
 * @version Assignment 2
 * @see javafx
 * @since 1.2
 */
public class WebPage {
	
	private WebView webview = new WebView();
	private WebEngine engine;
	
	/**
	 * This is a Web Engine creating method
	 * @param stage is webEngine stage
	 * @return Stage object
		 */
	public WebEngine createWebEngine(Stage stage) {
		
		WebView wv = getWebView();
		engine = wv.getEngine();
		
		engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
			@Override
			public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {
				if (newState == Worker.State.RUNNING) {
					stage.setTitle(engine.getLocation());
				}
				if (newState == Worker.State.SUCCEEDED) {
					if(MyJavaFXBrowser.getTextField()!=null)
					MyJavaFXBrowser.getTextField().setText(engine.getLocation());
					if(Menus.getHtmlJava()!=null)
					try {
						/** setting XML text for Menus getHtmlJava Method */
		                  TransformerFactory transformerFactory = TransformerFactory
		                      .newInstance();
		                  Transformer transformer = transformerFactory.newTransformer();
		                  StringWriter stringWriter = new StringWriter();
		                  transformer.transform(new DOMSource(engine.getDocument()),
		                      new StreamResult(stringWriter));
		                  String xml = stringWriter.getBuffer().toString();
		                  Menus.getHtmlJava().setText(xml);
		                } catch (Exception e) {
		                  e.printStackTrace();
		                }
					//java2s.com
					//date-2018 
					//JavaFX Tutorial - JavaFX WebEngine
					//[Webpage] 
					//http://www.java2s.com/Tutorials/Java/JavaFX/1500__JavaFX_WebEngine.htm
				}}
		});
		return engine;
	}
	/**
	 * This is a getter method
	 * @return webview object
		 */
	public WebView getWebView() {
		return webview;
	}
	/**
	 * This is a getter method
	 * @return engine object
		 */
	public WebEngine getWebEngine() {
		return engine;
	}
}
