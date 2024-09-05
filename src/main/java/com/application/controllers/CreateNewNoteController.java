package com.application.controllers;

import com.application.main.CssManager;
import com.application.main.TextFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import javafx.scene.web.WebView;

public class CreateNewNoteController {

    //
    private final StyleClassedTextArea styledTextArea = new StyleClassedTextArea();

    @FXML
    private WebView web;

    //
    @FXML
    private Pane noteArea;
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

    @FXML
    private void onSaveNote() {
        TextFormat.converToStyleClassedTextArea(styledTextArea, "[{\"text\":\"He\",\"bold\":false,\"italic\":false,\"underline\":false,\"fontSize\":12,\"start\":0,\"end\":2},{\"text\":\"llo Th\",\"bold\":true,\"italic\":true,\"underline\":false,\"fontSize\":15,\"start\":2,\"end\":8},{\"text\":\"is\",\"bold\":true,\"italic\":true,\"underline\":true,\"fontSize\":15,\"start\":8,\"end\":10},{\"text\":\" Is Tes\",\"bold\":false,\"italic\":false,\"underline\":true,\"fontSize\":12,\"start\":10,\"end\":17},{\"text\":\"t \",\"bold\":false,\"italic\":false,\"underline\":true,\"fontSize\":12,\"start\":17,\"end\":19},{\"text\":\"Formater\",\"bold\":false,\"italic\":false,\"underline\":true,\"fontSize\":15,\"start\":19,\"end\":27},{\"text\":\"Hel\",\"bold\":false,\"italic\":false,\"underline\":false,\"fontSize\":12,\"start\":27,\"end\":30},{\"text\":\"lo T\",\"bold\":true,\"italic\":false,\"underline\":false,\"fontSize\":12,\"start\":30,\"end\":34},{\"text\":\"h\",\"bold\":false,\"italic\":false,\"underline\":false,\"fontSize\":12,\"start\":34,\"end\":35},{\"text\":\"is Is Lin\",\"bold\":false,\"italic\":false,\"underline\":true,\"fontSize\":12,\"start\":35,\"end\":44},{\"text\":\"e Formater\",\"bold\":false,\"italic\":false,\"underline\":false,\"fontSize\":12,\"start\":44,\"end\":54}]"); 
                
        System.out.println(TextFormat.convertToJson(styledTextArea));
    }

    public void initialize() {
        noteArea.getChildren().add(styledTextArea);
        styledTextArea.setPrefSize(noteArea.getPrefWidth(), noteArea.getPrefHeight());
        styledTextArea.setStyle("-fx-background-color: transparent;");
        styledTextArea.setWrapText(true);

        fontSize.setItems(FXCollections.observableArrayList(CssManager.getFontSizeList()));
        fontSize.getSelectionModel().select("12");

        fontFamily.setItems(FXCollections.observableArrayList(CssManager.getFontFamilyList()));
        fontFamily.getSelectionModel().selectFirst();
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
