package agh.ics.oop.presenter;

import agh.ics.oop.model.Utilities.ConfigWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveConfigPresenter {
    @FXML
    public TextField fileName, directoryName;
    @FXML
    public Label messageLabel;
    private ConfigPresenter presenter;
    @FXML
    public void initialize() {
        Path path = Paths.get("");
        directoryName.setText(path.toAbsolutePath() + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "configs");
    }

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
