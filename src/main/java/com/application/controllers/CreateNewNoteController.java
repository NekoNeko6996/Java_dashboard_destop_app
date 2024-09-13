package com.application.controllers;

import com.application.main.App;
import com.application.main.CssManager;
import com.application.main.Http;
import com.application.main.LoginManager;
import com.application.main.TextFormat;
import com.application.models.Note;
import java.time.LocalDate;
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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

public class CreateNewNoteController {

    //
    private final StyleClassedTextArea styledTextArea = new StyleClassedTextArea();
    private String selectedIconURL;

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
    private ComboBox<String> priorityComboBox;
    @FXML
    private TextField addTagInputTextField;

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
    private void onClickAddTag() {
        Button tagContent = createNoteTag(addTagInputTextField.getText());
        
        tagContent.setOnAction(eh -> {
            tagFlowPane.getChildren().remove(tagContent);
        });
        
        tagFlowPane.getChildren().add(tagContent);
    }

    @FXML
    private void onSaveNote() {
        String jsonString = TextFormat.convertToJson(styledTextArea);

        List<String> tagList = new ArrayList<>();
        for(Node node : tagFlowPane.getChildren()) {
            tagList.add(((Button) node).getText());
        }

        String prioriry = priorityComboBox.getValue().toLowerCase();
        String iconUrl = selectedIconURL;
        String day = dateSelect.getValue().toString();
        int isDone = 0;

        // form data
        Note noteData = new Note(
                0,
                jsonString,
                prioriry,
                tagList,
                iconUrl,
                day,
                isDone
        );
        
        System.out.println(App.gsonBuilder.toJson(noteData, Note.class));
        
        Http.post("/saveNote", App.gson.toJson(noteData, Note.class), LoginManager.headers, (String response) -> {

            System.out.println(response);
        });
    }

    @FXML
    private void onCancel() {
        App.closeStage("createNewNote");
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

        // priority
        priorityComboBox.getItems().addAll("Default", "Very Low", "Low", "Medium", "High", "Very High");
        priorityComboBox.getSelectionModel().selectFirst();

        //
        dateSelect.setValue(LocalDate.now());
        
        
        //
        selectedIconURL = ((ImageView)((Button) chooseIconFlowPane.getChildren().get(0)).getGraphic()).getImage().getUrl();
        setupNodeSeletedIconList();
    }

    private void setupNodeSeletedIconList() {
        for (Node node : chooseIconFlowPane.getChildren()) {
            Button button = (Button) node;

            button.setOnAction(eh -> {
                ImageView seletedIcon = (ImageView) button.getGraphic();
                selectedIconURL = seletedIcon.getImage().getUrl();

                // Loại bỏ class "list-button-selected" và thêm "list-button-no-selected" cho tất cả các nút
                for (Node node_ : chooseIconFlowPane.getChildren()) {
                    node_.getStyleClass().removeAll("list-button-selected");
                    if (!node_.getStyleClass().contains("list-button-no-selected")) {
                        node_.getStyleClass().add("list-button-no-selected");
                    }
                }
                // Đặt class "list-button-selected" cho nút được nhấn
                button.getStyleClass().remove("list-button-no-selected");
                if (!button.getStyleClass().contains("list-button-selected")) {
                    button.getStyleClass().add("list-button-selected");
                }
            });
        }
    }

    private Button createNoteTag(String tagContent) {
        double height = 30;

        // tag
        Button tag = new Button(tagContent);
        tag.setCursor(Cursor.HAND);

        tag.setOnAction(eh -> {
            System.out.println("delete tag");
        });

        tag.setPrefSize(Region.USE_COMPUTED_SIZE, height);
        tag.getStyleClass().add("tag-button-container");

        return tag;
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
