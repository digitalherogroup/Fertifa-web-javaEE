package com.fertifa.app.parser;

import com.opencsv.CSVReader;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AnotherMethod {
    private static List<EmployeeModel> employeeModelList = new ArrayList<>();

    /**
     * Readig xls
     *
     * @param filePath
     * @param fileName
     * @return
     * @throws IOException
     */
    public static List<EmployeeModel> Readingxls(String filePath, String fileName) throws IOException {
        employeeModelList = new ArrayList<>();
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file + File.separator + fileName);

        HSSFWorkbook wb = new HSSFWorkbook(fis);

        HSSFSheet sheet = wb.getSheetAt(0);

        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
        for (Row row : sheet) {
            for (Cell cell : row) {
                switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t\t");
                        break;
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue() + "\t\t");
                        break;
                }
            }
            System.out.println();
        }
        return employeeModelList;
    }

    /**
     * Reading xlsx
     *
     * @param filePath
     * @param fileName
     * @return
     * @throws IOException
     */
    public static List<EmployeeModel> Readingxlsx(String filePath, String fileName) throws IOException {
        employeeModelList = new ArrayList<>();
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file + File.separator + fileName);

        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        Iterator<Row> itr = sheet.iterator();
        while (itr.hasNext()) {
            Row row = itr.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue() + "\t\t\t");
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t\t\t");
                        break;
                    default:
                }
            }
            System.out.println();
        }
        return employeeModelList;
    }

    /**
     * Reading CSV
     *
     * @param filePath
     * @param fileName
     * @return
     * @throws IOException
     */
    public static List<EmployeeModel> ReadingCSV(String filePath, String fileName) throws IOException {
        List<EmployeeModel> employeeModelListCSV = new ArrayList<>();
        File file = new File(filePath);
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(file + File.separator + fileName));
            String[] line;
            while ((line = reader.readNext()) != null ) {
                if(!checkImputLine(line)) {
                    if(line[0].equals("") || line[0].equals(null) || line[0].isEmpty()){
                        return employeeModelListCSV;
                    }else if(line[1].equals("") || line[1].equals(null) || line[1].isEmpty()){
                        return employeeModelListCSV;
                    }else if(line[2].equals("") || line[2].equals(null) || line[2].isEmpty()){
                        return employeeModelListCSV;
                    }else {
                        employeeModelListCSV.add(new EmployeeModel(line[0], line[1], line[2]));
                        System.out.println("[" + line[0] + " " + line[1] + " " + line[2] + "]");
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
        file.delete();
        return employeeModelListCSV;
    }

    private static boolean checkImputLine(String[] line) {
        return line[0].equals("");
    }


}

