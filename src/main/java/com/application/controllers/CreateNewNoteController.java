package com.application.controllers;

import com.application.main.App;
import com.application.main.CssManager;
import com.application.main.Http;
import com.application.main.LoginManager;
import com.application.main.TextFormat;
import com.application.models.Note;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

public class CreateNewNoteController {

    //
    private final StyleClassedTextArea styledTextArea = new StyleClassedTextArea();

    //
    @FXML
    private Pane noteArea;
    @FXML
    private ComboBox<String> fontSize;
    @FXML
    private FlowPane tagFlowPane;
    @FXML
    private DatePicker dateSelect;
    @FXML
    private FlowPane chooseIconFlowPane;

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
        TextFormat.converToStyleClassedTextArea(styledTextArea, "[{\"text\":\"Hel\",\"bold\":true,\"italic\":false,\"underline\":false,\"fontSize\":12,\"start\":0,\"end\":3},{\"text\":\"oo\",\"bold\":true,\"italic\":false,\"underline\":false,\"fontSize\":16,\"start\":3,\"end\":5},{\"text\":\"w \",\"bold\":false,\"italic\":false,\"underline\":false,\"fontSize\":16,\"start\":5,\"end\":7},{\"text\":\"T\",\"bold\":false,\"italic\":true,\"underline\":true,\"fontSize\":16,\"start\":7,\"end\":8},{\"text\":\"his I\",\"bold\":false,\"italic\":true,\"underline\":true,\"fontSize\":12,\"start\":8,\"end\":13},{\"text\":\"s T\",\"bold\":true,\"italic\":true,\"underline\":true,\"fontSize\":12,\"start\":13,\"end\":16},{\"text\":\"e\",\"bold\":false,\"italic\":true,\"underline\":true,\"fontSize\":12,\"start\":16,\"end\":17},{\"text\":\"st N\",\"bold\":false,\"italic\":false,\"underline\":false,\"fontSize\":12,\"start\":17,\"end\":21},{\"text\":\"ote Co\",\"bold\":false,\"italic\":false,\"underline\":true,\"fontSize\":21,\"start\":21,\"end\":27},{\"text\":\"ntent!\",\"bold\":false,\"italic\":false,\"underline\":true,\"fontSize\":12,\"start\":27,\"end\":33}]"); 
                
//        System.out.println(TextFormat.convertToJson(styledTextArea));
//        String jsonString = TextFormat.convertToJson(styledTextArea);
//        
//        List<String> tag = new ArrayList<>();
//        tag.add("Holiday");
//        tag.add("Beach");
//        
//        Note noteData = new Note(0, jsonString, "normal", App.gson.toJson(tag, List.class), "http://thisisiconlink.com/icon", 0);
//        Http.post("/saveNote", App.gson.toJson(noteData, Note.class), LoginManager.headers, (String response) -> {
//            
//            System.out.println(response);
//        });
    }

    public void initialize() {
        noteArea.getChildren().add(styledTextArea);
        styledTextArea.setPadding(new Insets(10));
        styledTextArea.setPrefSize(noteArea.getPrefWidth(), noteArea.getPrefHeight());
        styledTextArea.setWrapText(true);
        styledTextArea.setStyle("-fx-font-size: 12pt; -fx-background-color: transparent;");
        styledTextArea.setCursor(Cursor.TEXT);

        fontSize.setItems(FXCollections.observableArrayList(CssManager.getFontSizeList()));
        fontSize.getSelectionModel().select("12");
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
