package cn.edu.brs._2021.service.utility;

import cn.edu.brs._2021.dao.IUserDao;
import cn.edu.brs._2021.entity.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class StudentListImporter {
    public static void main(String[] args) throws IOException {
        Workbook workbook = new XSSFWorkbook(new FileInputStream(new File("/Users/heyi/Desktop/Development/Local/GraduationWeekApplication/src/main/resources/学号.xlsx")));
        Sheet targetSheet = workbook.getSheetAt(0);
        int studentCount = targetSheet.getLastRowNum();
        ArrayList<User> students = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            Row row = targetSheet.getRow(i);
            students.add(new User(Long.parseLong(row.getCell(1).getStringCellValue()), null, row.getCell(2).getStringCellValue(), null, (int) row.getCell(0).getNumericCellValue(), null, null, null, null));
        }
        System.out.println(students);

        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        IUserDao userDao = ac.getBean(IUserDao.class);

        for (User student : students) {
            userDao.update(student);
        }

    }
}
