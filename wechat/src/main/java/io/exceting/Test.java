package io.exceting;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Test {

    public static void main(String[] args) throws Exception {
        MutableDataSet options = new MutableDataSet();

        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        List<String> allLines = Files.readAllLines(Paths.get("D:\\hexo-folder\\source\\_posts\\LV1-3：java中的运算符.md"));

        StringBuilder sb = new StringBuilder();
        allLines.forEach(sb::append);
        // You can re-use parser and renderer instances
        Node document = parser.parse(sb.toString());
        String html = renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
        System.out.println(html);
    }

}
