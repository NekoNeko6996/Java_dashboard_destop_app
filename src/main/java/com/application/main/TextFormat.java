package com.application.main;

import com.application.models.textFormat.StyledTextSegment;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.fxmisc.richtext.model.StyleSpans;

public class TextFormat {

    public static String convertToJson(StyleClassedTextArea textArea) {
        List<StyledTextSegment> segments = new ArrayList<>();

        // Get StyleSpans for the entire content
        StyleSpans<Collection<String>> styleSpans = textArea.getStyleSpans(0, textArea.getLength());

        // Iterate over the spans and extract text and style
        int start = 0;
        for (int i = 0; i < styleSpans.getSpanCount(); i++) {
            // Get style for the current span
            Collection<String> styles = styleSpans.getStyleSpan(i).getStyle();
            String styleString = String.join(" ", styles);

            int end = start + styleSpans.getStyleSpan(i).getLength();

            // Extract the text for this span
            String text = textArea.getText().substring(start, end);

            // Check for styling
            boolean isBold = styleString.contains("bold");
            boolean isItalic = styleString.contains("italic");
            boolean isUnderline = styleString.contains("underline");

            // Assuming default fontSize or extract from styles if possible
            int fontSize = 12;

            Pattern pattern = Pattern.compile("font-size-(\\d+)");
            Matcher matcher = pattern.matcher(styleString);

            if (matcher.find()) {
                // Lấy nhóm 1, chính là phần số 12
                fontSize = Integer.parseInt(matcher.group(1));
                System.out.println("Font size: " + fontSize);
            } else {
                System.out.println("Không tìm thấy font size");
            }

            // Create a StyledTextSegment for this span
            segments.add(new StyledTextSegment(text, isBold, isItalic, isUnderline, fontSize, start, end));

            start += styleSpans.getStyleSpan(i).getLength();
        }

        return App.gson.toJson(segments, List.class);

    }

    public static void converToStyleClassedTextArea(StyleClassedTextArea textArea, String json) {
        
        // convert json string to Class Data
        List<StyledTextSegment> segments;
        Type listType = new TypeToken<List<StyledTextSegment>>() {}.getType();

        segments = App.gsonBuilder.fromJson(json, listType);
        
        // load and setStyle
        for (StyledTextSegment segment : segments) {
            textArea.appendText(segment.getText()); // Thêm văn bản

            // Áp dụng các thuộc tính cho đoạn văn bản
            int start = textArea.getLength() - segment.getText().length();
            int end = textArea.getLength();

            List<String> styles = new ArrayList<>();
            if (segment.isBold()) {
                styles.add("bold");
            }
            if (segment.isItalic()) {
                styles.add("italic");
            }
            if (segment.isUnderline()) {
                styles.add("underline");
            }

            System.out.println(segment.getFontSize());
            styles.add("font-size-" + segment.getFontSize());

            // add style
            textArea.setStyle(start, end, styles);
        }
    }
}
