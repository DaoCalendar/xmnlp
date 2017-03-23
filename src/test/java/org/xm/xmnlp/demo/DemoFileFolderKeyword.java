/*
 * <author>Xu Ming</author>
 * <email>huluobo624@gmail.com</email>
 * <create-date>2017</create-date>
 * <copyright>
 *  Copyright (c) 2017. All Right Reserved, http://www.pycredit.cn/
 *  This source is subject to the Apache 2.0 License.
 *  Please contact http://www.pycredit.cn/ to get more information.
 * </copyright>
 *
 */

package org.xm.xmnlp.demo;

import org.xm.xmnlp.Xmnlp;
import org.xm.xmnlp.util.FileFinder;
import org.xm.xmnlp.util.IOUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 对文件夹的所有文件提取关键词
 *
 * @author xuming
 */
public class DemoFileFolderKeyword {
    static String rootPath = "data/";
    static String fileFolderPath = "test/news";

    public static void main(String[] args) throws IOException {
        List<File> files = FileFinder.getFileList(rootPath + fileFolderPath, "txt");
        FileOutputStream fos = new FileOutputStream(new File(rootPath + "/Keyword-Result.txt"));
        long start = System.currentTimeMillis();
        for (File file : files) {
            List<String> result = Xmnlp.extractKeyword(IOUtil.readTxt(file.getAbsolutePath()).toString(), 5);
            String keywords = "";
            for (String s : result) {
                keywords += (s + "\t");
            }
            fos.write(file.getName().getBytes());
            fos.write("\n".getBytes());
            fos.write(keywords.getBytes());
            fos.write("\n".getBytes());
            fos.write("\n".getBytes());
        }
        fos.flush();
        fos.close();
        long end = System.currentTimeMillis();
        System.out.println("共用时:" + (end - start) + "ms");

    }

}
