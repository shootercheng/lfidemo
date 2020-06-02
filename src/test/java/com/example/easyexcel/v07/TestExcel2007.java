package com.example.easyexcel.v07;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xssf.usermodel.XSSFRelation;

import java.io.File;
import java.util.ArrayList;

/**
 * @author James
 */
public class TestExcel2007 {

    public static void main(String[] args) throws InvalidFormatException {
        String filePath = "file/test2007.xlsx";
        File file = new File(filePath);
        OPCPackage pkg = OPCPackage.open(file);
        ArrayList<PackagePart> packageParts = pkg.getPartsByContentType(XSSFRelation.SHARED_STRINGS.getContentType());

    }
}
