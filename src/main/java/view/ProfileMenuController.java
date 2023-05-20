package view;

import controller.ProfileController;
import controller.Utils.UserUtils;
import enums.Avatar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProfileMenuController {
    private Desktop desktop = Desktop.getDesktop();
    private final ProfileController profileController = new ProfileController();
    public TextField newUsername;
    public PasswordField newPassword;

    public void changeUsername(MouseEvent mouseEvent) throws Exception{
        Pane pane = FXMLLoader.load(ProfileMenu.class.getResource("/FXML/ChangeUsername.fxml"));
        Scene scene = new Scene(pane);
        LoginMenu.stage.setScene(scene);
        LoginMenu.stage.show();
    }

    public void changePassword(MouseEvent mouseEvent) throws Exception{
        Pane pane = FXMLLoader.load(ProfileMenu.class.getResource("/FXML/ChangePassword.fxml"));
        Scene scene = new Scene(pane);
        LoginMenu.stage.setScene(scene);
        LoginMenu.stage.show();
    }

    public void logout(MouseEvent mouseEvent) throws Exception {
        Alert alert = UserUtils.logoutConfirmation();
        alert.showAndWait().ifPresent(response -> {
            if(response == ButtonType.OK) {
                UserUtils.userLogout();
                Alert alert1 = UserUtils.logoutMessage();
                alert1.show();
                try {
                    new LoginMenu().start(LoginMenu.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void deleteAccount(MouseEvent mouseEvent) throws Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Do you want to delete your account?");
        alert.showAndWait().ifPresent(response -> {
            if(response == ButtonType.OK)
                profileController.removeCurrentUser();
        });
        new LoginMenu().start(LoginMenu.stage);
    }

    public void chooseAvatar(MouseEvent mouseEvent) throws Exception{
        BorderPane pane = FXMLLoader.load(ProfileMenu.class.getResource("/FXML/ChooseAvatar.fxml"));

        ArrayList<ImageView> imageViews = createImageView();
        Alert alert = getAlertForChoosingAvatar();
        ArrayList<Button> buttons = createButton(imageViews, alert);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(imageViews.get(0), imageViews.get(1), imageViews.get(2), imageViews.get(3), imageViews.get(4),
                buttons.get(0), buttons.get(1), buttons.get(2), buttons.get(3), buttons.get(4));

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.BASELINE_LEFT);
        vBox.setSpacing(15);

        vBox.getChildren().add(hBox);
        pane.getChildren().add(vBox);

        Scene scene = new Scene(pane);
        LoginMenu.stage.setScene(scene);
        LoginMenu.stage.show();
    }

    public FileChooser getFileChooser() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        fileChooser.setTitle("Open File");
        return fileChooser;
    }

    public Alert getAlertForChoosingAvatar() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Do you want to pick this avatar?");
        return alert;
    }

    public ArrayList<ImageView> createImageView() {

        ImageView imageView1 = new ImageView(Avatar.AVATAR_5.image);
        ImageView imageView2 = new ImageView(Avatar.AVATAR_3.image);
        ImageView imageView3 = new ImageView(Avatar.AVATAR_1.image);
        ImageView imageView4 = new ImageView(Avatar.AVATAR_4.image);
        ImageView imageView5 = new ImageView(Avatar.AVATAR_2.image);

        imageView1.setFitWidth(85);
        imageView1.setPreserveRatio(true);
        imageView1.setSmooth(true);
        imageView1.setCache(true);

        imageView2.setFitWidth(84);
        imageView2.setPreserveRatio(true);
        imageView2.setSmooth(true);
        imageView2.setCache(true);

        imageView3.setFitWidth(84);
        imageView3.setPreserveRatio(true);
        imageView3.setSmooth(true);
        imageView3.setCache(true);

        imageView4.setFitWidth(84);
        imageView4.setPreserveRatio(true);
        imageView4.setSmooth(true);
        imageView4.setCache(true);

        imageView5.setFitWidth(84);
        imageView5.setPreserveRatio(true);
        imageView5.setSmooth(true);
        imageView5.setCache(true);

        return new ArrayList<ImageView>(List.of(
                imageView1, imageView2, imageView3, imageView4, imageView5));
    }

    public ArrayList<Button> createButton(ArrayList<ImageView> imageViews, Alert alert) {
        Button button1 = new Button();
        button1.setGraphic(imageViews.get(0));
        button1.setOnAction(value -> {
            alert.showAndWait().ifPresent(response -> {
                if(response == ButtonType.OK) {
                    profileController.setNewAvatar(Avatar.AVATAR_5);
                }
            });
        });

        Button button2 = new Button();
        button2.setGraphic(imageViews.get(1));
        button2.setOnAction(value -> {
            alert.showAndWait().ifPresent(response -> {
                if(response == ButtonType.OK) {
                    profileController.setNewAvatar(Avatar.AVATAR_3);
                }
            });
        });

        Button button3 = new Button();
        button3.setGraphic(imageViews.get(2));
        button3.setOnAction(value -> {
            alert.showAndWait().ifPresent(response -> {
                if(response == ButtonType.OK) {
                    profileController.setNewAvatar(Avatar.AVATAR_1);
                }
            });
        });

        Button button4 = new Button();
        button4.setGraphic(imageViews.get(3));
        button4.setOnAction(value -> {
            alert.showAndWait().ifPresent(response -> {
                if(response == ButtonType.OK) {
                    profileController.setNewAvatar(Avatar.AVATAR_4);
                }
            });
        });

        Button button5 = new Button();
        button5.setGraphic(imageViews.get(4));
        button5.setOnAction(value -> {
            alert.showAndWait().ifPresent(response -> {
                if(response == ButtonType.OK) {
                    profileController.setNewAvatar(Avatar.AVATAR_2);
                }
            });
        });

        return new ArrayList<Button>(List.of(
                button1, button2, button3, button4, button5));
    }

    public void enterMainMenu(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(LoginMenu.stage);
    }

    public void enterProfileMenu(MouseEvent mouseEvent) throws Exception {
        new ProfileMenu().start(LoginMenu.stage);
    }

    public void setNewUsername(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch (profileController.setNewUsername(newUsername)) {
            case SUCCESS:
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Successful");
                alert.setContentText("Username changed successfully!");
                alert.show();
                newUsername.clear();
                break;
            case USERNAME_EXISTS:
                alert.setHeaderText("Fail");
                alert.setContentText("Username already exists!");
                alert.show();
                break;
            case EMPTY_FIELD:
                alert.setHeaderText("Fail");
                alert.setContentText("Username field is empty!");
                alert.show();
                break;
        }
    }

    public void setNewPassword(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch (profileController.setNewPassword(newPassword)) {
            case SUCCESS:
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Successful");
                alert.setContentText("Password changed successfully!");
                alert.show();
                newPassword.clear();
                break;
            case WEAK_PASSWORD:
                alert.setHeaderText("Fail");
                alert.setContentText("Your password is weak!");
                alert.show();
                break;
            case EMPTY_FIELD:
                alert.setHeaderText("Fail");
                alert.setContentText("Password field is empty!");
                alert.show();
                break;
        }
    }

    public void chooseFile(MouseEvent mouseEvent) {
        FileChooser fileChooser  = getFileChooser();
        File file = fileChooser.showOpenDialog(LoginMenu.stage);
        if(file != null) {
            profileController.setAvatarFromChooseFile(file.getAbsolutePath());
        }
    }
}
