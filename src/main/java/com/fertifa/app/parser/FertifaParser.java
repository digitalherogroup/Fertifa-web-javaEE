package com.fertifa.app.parser;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FertifaParser {

    public static List<EmployeeModel> fromExcel(HttpServletRequest request,String filepath,String fileName) throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        List<EmployeeModel> employeeModelList = new ArrayList<EmployeeModel>();

        String absolutePath = request.getServletContext().getRealPath(fileName);
        String absolutePath2 = filepath + File.separator +fileName;

        OPCPackage pkg = OPCPackage.open(new File(absolutePath2));
        XSSFWorkbook wb = new XSSFWorkbook(pkg);

        int i = 0;
        int notValidCount = 0;
        for (; ; ) {
            // get values by corresponding position
            String firsName = getValue(wb, i, 0);
            String lastName = getValue(wb, i, 1);
            String email = getValue(wb, i, 2);
            i++;
            // create employee object
            EmployeeModel employeeModel = new EmployeeModel(firsName, lastName, email);
            // validate employee
            if (!employeeModel.isValid()) {
                notValidCount++;
            } else {
                employeeModelList.add(employeeModel);
            }

            // check if not validation count = 100, need execute from this method
            if (notValidCount >= 100) {
                return employeeModelList;
            }
        }
    }

    private static String getValue(XSSFWorkbook wb, int rowIndex, int cellIndex) {
        try {
            return wb.getSheetAt(0)
                    .getRow(rowIndex)
                    .getCell(cellIndex)
                    .getRichStringCellValue()
                    .getString();
        } catch (Exception e) {
            return "";
        }
    }
}