package assignment2;
/*File name: [FileUtils.java ]
Author: [ Robin Shrestha, 040880427]
Course: CST8284 – OOP Java
Assignment: [2]
Date: [01/12/2017]
Professor: [DAVID B HOUTMAN]
Purpose: [Java file I/O code ]
*/
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/** This class is responsible for reading and writing the file
 * @author Robin Shrestha, Student Number: 040880427
 * @version Assignment 2
 * @see java.io
 * @since 1.2
 */

public class FileUtils {

	/**
	 * This is method for adding contents to arraylist file
	 * @param defaultBookmark is string object
	 * @param fileName is string object 
	 		 */
	public static void addNewDefaultOrBookmark(String defaultBookmark, String fileName) {
		try {
			File fileToRead = new File(fileName);/** getting File */
			if (!fileToRead.exists()) {
				fileToRead.createNewFile();
			}
			FileWriter wArray = new FileWriter(fileToRead, true);
			BufferedWriter bufferedWriter = new BufferedWriter(wArray);
			bufferedWriter.write(defaultBookmark); /** writing file to defaultBookmark */
			bufferedWriter.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		//JClassic
		//date-2014 
		//In need of JavaFX help writing to a .txt file Ask
		//[Webpage] 
		//https://stackoverflow.com/questions/24565539/in-need-of-javafx-help-writing-to-a-txt-file
	}

	/**
	 * This is method for getting contents to array list file
	 * @param myFile is File object
	 * @param ar is ArrayList object 
	 * @return returns an arraylist of strings
	 		 */
	public static ArrayList<String> getFileContentsAsArrayList(File myFile, ArrayList<String> ar) {
		ar = new ArrayList<String>(); /** arraylist ar defined */
		if (myFile.exists() && myFile.isFile()) {

			try { /** file reading if exists */
				Scanner fileIn = new Scanner(myFile);
				for (; fileIn.hasNext() == true;) {
					String stringName = fileIn.next() + "\n";
					if (!stringName.isEmpty()) {
						ar.add(stringName);
					}

				}

				fileIn.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
		return ar;
	}


}