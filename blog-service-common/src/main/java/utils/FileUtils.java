package utils;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Author li.ling
 * Description
 * Date 2021/7/15 20:27
 */
public class FileUtils {


    public static List<String> readFile(String filepath) {
        List<String> fPaths = new ArrayList<>();
        File file = new File(filepath);
        if (!file.isDirectory()) {
            System.out.println("遍历到了文件");
            System.out.println("path=" + file.getPath());
            System.out.println("absolutePath=" + file.getAbsolutePath());
            System.out.println("name=" + file.getName());
            fPaths.add(file.getAbsolutePath());
        } else if (file.isDirectory()) {
            System.out.println("遍历到了文件夹");
            String[] fileList = file.list();
            if (fileList == null) {
                return fPaths;
            }
            for (String s : fileList) {
                File readFile = new File(filepath + "\\" + s);
                if (!readFile.isDirectory()) {
                    System.out.println("path=" + readFile.getPath());
                    System.out.println("absolutePath=" + readFile.getAbsolutePath());
                    System.out.println("name=" + readFile.getName());
                    fPaths.add(readFile.getAbsolutePath());
                } else if (readFile.isDirectory()) {
                    // 递归找子文件夹
                    readFile(filepath + "\\" + s);
                }
            }
        }
        return fPaths;
    }

    /**
     * PDF转word
     * Author XiangNan.Ruan
     */
    public static void pdfToWord() {
        // 加载PDF文档
        PDDocument document = null;
        try {

            document = PDDocument.load(Files.newInputStream(Paths.get("C:/Users/asus/Downloads/资深java优化师简历模板.pdf")));

            // 创建Word文档
            XWPFDocument wordDocument = new XWPFDocument();

            // 创建段落和Run对象
            XWPFParagraph paragraph = wordDocument.createParagraph();
            XWPFRun run = paragraph.createRun();

            // 设置段落样式
            paragraph.setStyle("Normal");

            // 提取PDF文本内容
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            // 使用OCR技术识别PDF中的文本
            Tesseract tesseract = new Tesseract();
            //r gftv tesseract.setLanguage("eng"); // 设置OCR识别语言为英文
            String ocrText = tesseract.doOCR(new File("C:/Users/asus/Downloads/资深java优化师简历模板.pdf"));

            // 设置Run对象的字体样式和文本内容
            run.setFontFamily("Times New Roman");
            run.setFontSize(12);
            run.setText(ocrText);

            // 保存Word文档
            FileOutputStream out = new FileOutputStream(new File("C:/Users/asus/Downloads/TestOutput"+System.currentTimeMillis()+".docx"));
            wordDocument.write(out);
            out.close();

            // 关闭文档
            document.close();
            wordDocument.close();

            System.out.println("PDF转Word成功！");

        } catch (IOException | TesseractException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        pdfToWord();
    }
}
