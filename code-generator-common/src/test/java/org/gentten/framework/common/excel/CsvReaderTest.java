package org.gentten.framework.common.excel;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


/**
 * @author : duanzhiqiang
 * @date : 2019-08-03 13:33
 */
public class CsvReaderTest {

    public void getTxtData() {
        String path = "F:\\数据\\负荷数据\\2018年负荷数据\\20180102-0110.csv";
        File dataFile = new File(path);
        CsvReader reader = new CsvReader();
        try {
            DataResult dataResult = reader.getTxtData(dataFile, ",", true);
            dataResult.getColumns();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
       // Locale.
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy MMM d HH:mm:ss", Locale.ENGLISH);
        LocalDateTime localDateTime = LocalDateTime.parse("2017 Nov 16 09:44:27", dateTimeFormatter);
        System.out.println(localDateTime);

    }
}