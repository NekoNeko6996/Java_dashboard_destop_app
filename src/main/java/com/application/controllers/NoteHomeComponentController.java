package com.application.controllers;

import com.application.main.TextFormat;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import org.fxmisc.richtext.StyleClassedTextArea;

public class NoteHomeComponentController {
    //
    private final String content;
    private final String day;
    private final String noteIconUrl;
    private final List<String> tag;
    private final StyleClassedTextArea contentView = new StyleClassedTextArea();

    public NoteHomeComponentController(String content, String day, String noteIconUrl, List<String> tag) {
        this.content = content;
        this.day = day;
        this.noteIconUrl = noteIconUrl;
        this.tag = tag;
    }

    //
    @FXML
    private Label dayView;

    @FXML
    private Pane noteContentPane;

    @FXML
    private ImageView noteIcon;

    @FXML
    private Label noteShareView;

    @FXML
    private FlowPane tagFlowPane;

    @FXML
    void onClickMore(ActionEvent event) {

    }

    @FXML
    void onClickPin(ActionEvent event) {
        
    }

    public void initialize() {
        // tag
        tagFlowPane.getChildren().clear();
        for (String nameTag : tag) {
            Label tagLabel = new Label(nameTag);
            tagLabel.setPadding(new Insets(3, 5, 3, 5));
            tagLabel.setPrefWidth(Region.USE_COMPUTED_SIZE);
            tagFlowPane.getChildren().add(tagLabel);
        }

        // time
        dayView.setText(day);

        // icon
        noteIcon.setImage(new Image(noteIconUrl));

        // content
        contentView.setWrapText(true);
        contentView.setPrefSize(noteContentPane.getPrefWidth(), noteContentPane.getPrefHeight());
        noteContentPane.getChildren().add(contentView);
        TextFormat.convertToStyleClassedTextArea(contentView, content);
    }
}
