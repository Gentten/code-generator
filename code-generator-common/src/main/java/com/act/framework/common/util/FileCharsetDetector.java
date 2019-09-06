package com.act.framework.common.util;

import org.mozilla.intl.chardet.nsDetector;

import java.io.*;
import java.util.HashMap;

/**
 * 获取文件的编码方式，防止读取中文乱码
 *
 * @author : duanzhiqiang
 * @date : 2019-08-03 12:59
 */
public class FileCharsetDetector {
    private String encoding = null;

    private static final HashMap<String, String> CHARSET_MAP = new HashMap<>();


    public String guessFileEncoding(File file) throws IOException {
        return guessFileEncoding(file, new nsDetector());
    }


    public String guessFileEncoding(File file, int languageHint) throws IOException {
        return guessFileEncoding(file, new nsDetector(languageHint));
    }


    private String guessFileEncoding(File file, nsDetector det) throws IOException {
        det.Init(charset ->
                this.encoding = charset);

        this.encoding = "GB18030";
        BufferedInputStream imp = new BufferedInputStream(new FileInputStream(file));
        byte[] buf = new byte[1024];

        int len;

        while ((len = imp.read(buf, 0, buf.length)) != -1) {

            boolean done = det.DoIt(buf, len, false);
            if (done) {
                break;
            }
        }
        imp.close();
        det.DataEnd();

        return CHARSET_MAP.getOrDefault(this.encoding, "GB18030");
    }

}
