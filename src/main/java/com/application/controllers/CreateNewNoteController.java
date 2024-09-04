package com.application.controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

public class CreateNewNoteController {

    //
    private final StyleClassedTextArea styledTextArea = new StyleClassedTextArea();

    //
    @FXML
    private VBox noteArea;
    @FXML
    private ComboBox<String> fontSize;
    @FXML
    private ComboBox<String> fontFamily;

    @FXML
    private void onChangeFontSize() {
        toggleStyle("", fontSize.getValue());
    }

    @FXML
    public void handleBoldAction() {
        toggleStyle("bold", fontSize.getValue());
    }

    @FXML
    public void handleItalicAction() {
        toggleStyle("italic", fontSize.getValue());
    }

    @FXML
    public void handleUnderlineAction() {
        toggleStyle("underline", fontSize.getValue());
    }

    public void initialize() {
        noteArea.getChildren().add(styledTextArea);
        
        //
        ObservableList<String> fontSizes = FXCollections.observableArrayList();
        for (int i = 10; i <= 110; i++) {
            fontSizes.add(Integer.toString(i));
        }
        fontSize.setItems(fontSizes);
        fontSize.getSelectionModel().selectFirst();
    }

    private void toggleStyle(String styleClass, String fontSize) {
        int start = styledTextArea.getSelection().getStart();
        int end = styledTextArea.getSelection().getEnd();
        String newFontSize = "font-size-" + fontSize;

        if (start == end) {
            // Không có văn bản được chọn
            return;
        }

        // Lấy các lớp hiện tại của đoạn văn bản được chọn
        Set<String> currentStyles = new HashSet<>();
        for (int i = start; i < end; i++) {
            currentStyles.addAll(styledTextArea.getStyleOfChar(i));
        }

        // Kiểm tra xem lớp định dạng đã tồn tại hay chưa
        boolean isActive = currentStyles.contains(styleClass);
        boolean isChangeSize = !currentStyles.contains(newFontSize);

        // Tạo StyleSpansBuilder để xây dựng các spans mới
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();

        for (int i = start; i < end; i++) {
            Set<String> styles = new HashSet<>(styledTextArea.getStyleOfChar(i));
            if (!styleClass.isEmpty()) {
                if (isActive) {
                    styles.remove(styleClass);
                } else {
                    styles.add(styleClass);
                }
            }

            if (isChangeSize) {
                styles.removeIf((style) -> style.contains("font-size-"));
                styles.add(newFontSize);
            }

            spansBuilder.add(Collections.unmodifiableSet(styles), 1);
        }

        // Áp dụng các spans mới
        StyleSpans<Collection<String>> spans = spansBuilder.create();
        styledTextArea.setStyleSpans(start, spans);
    }
}
