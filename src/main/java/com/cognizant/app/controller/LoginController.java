package com.cognizant.app.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {
	
	public static String SAMPLE_XLSX_FILE_PATH = "C:\\Users\\767967\\Desktop\\PresentationTracker.xlsx";
		
	@GetMapping(value="/surprise")
	public ModelAndView hackedUrl()
	{	
	    try {
	    	
		 Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

	        // Retrieving the number of sheets in the Workbook
	        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

	        /*
	           =============================================================
	           Iterating over all the sheets in the workbook (Multiple ways)
	           =============================================================
	        */

	        // 1. You can obtain a sheetIterator and iterate over it
	        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
	        System.out.println("Retrieving Sheets using Iterator");
	        while (sheetIterator.hasNext()) {
	            Sheet sheet = sheetIterator.next();
	            System.out.println("=> " + sheet.getSheetName());
	        }

	        // 2. Or you can use a for-each loop
	        System.out.println("Retrieving Sheets using for-each loop");
	        for(Sheet sheet: workbook) {
	            System.out.println("=> " + sheet.getSheetName());
	        }

	        // 3. Or you can use a Java 8 forEach with lambda
	        System.out.println("Retrieving Sheets using Java 8 forEach with lambda");
	        workbook.forEach(sheet -> {
	            System.out.println("=> " + sheet.getSheetName());
	        });

	        /*
	           ==================================================================
	           Iterating over all the rows and columns in a Sheet (Multiple ways)
	           ==================================================================
	        */

	        // Getting the Sheet at index zero
	        Sheet sheet = workbook.getSheetAt(0);

	        // Create a DataFormatter to format and get each cell's value as String
	        DataFormatter dataFormatter = new DataFormatter();

	        // 1. You can obtain a rowIterator and columnIterator and iterate over them
	        System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
	        Iterator<Row> rowIterator = sheet.rowIterator();
	        while (rowIterator.hasNext()) {
	            Row row = rowIterator.next();

	            // Now let's iterate over the columns of the current row
	            Iterator<Cell> cellIterator = row.cellIterator();

	            while (cellIterator.hasNext()) {
	                Cell cell = cellIterator.next();
	                String cellValue = dataFormatter.formatCellValue(cell);
	                System.out.print(cellValue + "\t");
	            }
	            System.out.println();
	        }

	        // 2. Or you can use a for-each loop to iterate over the rows and columns
	        System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
	        for (Row row: sheet) {
	            for(Cell cell: row) {
	                String cellValue = dataFormatter.formatCellValue(cell);
	                System.out.print(cellValue + "\t");
	            }
	            System.out.println();
	        }

	        // 3. Or you can use Java 8 forEach loop with lambda
	        System.out.println("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n");
	        sheet.forEach(row -> {
	            row.forEach(cell -> {
	                String cellValue = dataFormatter.formatCellValue(cell);
	                System.out.print(cellValue + "\t");
	            });
	            System.out.println();
	        });

	        // Closing the workbook
	    
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EncryptedDocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return new ModelAndView("index");
	}
	
	@PostMapping(value="/uploadExcelFile")
	public String uploadFile(Model model, MultipartFile file) throws IOException {
	    InputStream in = file.getInputStream();
	    File currDir = new File(".");
	    String path = currDir.getAbsolutePath();
	    SAMPLE_XLSX_FILE_PATH = path.substring(0, path.length() - 1) + file.getOriginalFilename();
	    FileOutputStream f = new FileOutputStream(SAMPLE_XLSX_FILE_PATH);
	    int ch = 0;
	    while ((ch = in.read()) != -1) {
	        f.write(ch);
	    }
	    f.flush();
	    f.close();
	    model.addAttribute("message", "File: " + file.getOriginalFilename() 
	      + " has been uploaded successfully!");
	    return "excel in "+SAMPLE_XLSX_FILE_PATH;
	}
	

}
