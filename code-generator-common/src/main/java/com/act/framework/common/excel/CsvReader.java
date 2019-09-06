package com.act.framework.common.excel;

import com.act.framework.common.util.FileCharsetDetector;
import com.univocity.parsers.csv.CsvFormat;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.NoArgsConstructor;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

/**
 * csv文件读取器
 *
 * @author : duanzhiqiang
 * @date : 2019-08-03 12:59
 */
@NoArgsConstructor
public class CsvReader {

    /**
     * 读取csv 文件
     *
     * @param file          需要读取的
     * @param separator     列分割符
     * @param isFirstColumn 第一列是否为列的信息
     * @return 读取的结果 包含
     * @throws IOException io异常
     */
    public static DataResult getTxtData(File file, String separator, Boolean isFirstColumn) throws IOException {
        List<String[]> result;// = new ArrayList<>();
        FileInputStream fis = new FileInputStream(file);
        //获取编码方式
        Charset charset = Charset.forName((new FileCharsetDetector()).guessFileEncoding(file, 2));
        InputStreamReader isr = new InputStreamReader(fis, charset);
        BufferedReader reader = new BufferedReader(isr);

        CsvFormat format = new CsvFormat();
        format.setDelimiter(separator.charAt(0));
        format.setLineSeparator("\n");

        CsvParserSettings settings = new CsvParserSettings();
        settings.setFormat(format);
        settings.setEmptyValue("");
        settings.setNullValue("");
        settings.setIgnoreTrailingWhitespacesInQuotes(true);
        //设置每个列的最大宽度
        settings.setMaxCharsPerColumn(5120);
        CsvParser parser = new CsvParser(settings);

        result = parser.parseAll(reader);
        reader.close();
        return new DataResult(result, isFirstColumn);
    }

}
