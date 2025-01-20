package agh.ics.oop.presenter;

import agh.ics.oop.model.util.ConfigWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class SaveConfigPresenter {
    @FXML
    public TextField fileName, directoryName;
    @FXML
    public Label messageLabel;
    private ConfigPresenter presenter;

    public void setPresenter(ConfigPresenter presenter) {
        this.presenter = presenter;
    }

    public void onChangeDirectoryClicked() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory != null) directoryName.setText(selectedDirectory.getAbsolutePath());
    }

    public void onSaveConfigClicked() {
        ConfigWriter configWriter = new ConfigWriter();
        if (configWriter.write(this.presenter, directoryName.getText(), fileName.getText())) {
            messageLabel.setTextFill(Paint.valueOf("green"));
            messageLabel.setText("Configuration file saved!");
        }
        else {
            messageLabel.setTextFill(Paint.valueOf("red"));
            messageLabel.setText("Set directory and/or file name!");
        }
    }
}
