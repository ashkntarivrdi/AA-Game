package view;

import controller.ProfileController;
import controller.Utils.UserUtils;
import enums.Avatar;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class ProfileMenuController {
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
        UserUtils.userLogout();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Logout Successful");
        alert.setContentText("User logged out successfully");
        alert.show();
        new LoginMenu().start(LoginMenu.stage);
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
        Pane pane = FXMLLoader.load(ProfileMenu.class.getResource("/FXML/ChooseAvatar.fxml"));

        Image image1 = Avatar.AVATAR_5.image;
        Image image2 = Avatar.AVATAR_3.image;
        Image image3 = Avatar.AVATAR_1.image;
        Image image4 = Avatar.AVATAR_4.image;
        Image image5 = Avatar.AVATAR_2.image;

        VBox vBox = new VBox();
        HBox hBox = new HBox();
        ImageView imageView1 = new ImageView();
        ImageView imageView2 = new ImageView();
        ImageView imageView3 = new ImageView();
        ImageView imageView4 = new ImageView();
        ImageView imageView5 = new ImageView();

        imageView1.setImage(image1);
        imageView1.setFitWidth(85);
        imageView1.setPreserveRatio(true);
        imageView1.setSmooth(true);
        imageView1.setCache(true);

        imageView2.setImage(image2);
        imageView2.setFitWidth(84);
        imageView2.setPreserveRatio(true);
        imageView2.setSmooth(true);
        imageView2.setCache(true);

        imageView3.setImage(image3);
        imageView3.setFitWidth(84);
        imageView3.setPreserveRatio(true);
        imageView3.setSmooth(true);
        imageView3.setCache(true);

        imageView4.setImage(image4);
        imageView4.setFitWidth(84);
        imageView4.setPreserveRatio(true);
        imageView4.setSmooth(true);
        imageView4.setCache(true);

        imageView5.setImage(image5);
        imageView5.setFitWidth(84);
        imageView5.setPreserveRatio(true);
        imageView5.setSmooth(true);
        imageView5.setCache(true);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Do you want to pick this avatar?");
        Button button1 = new Button();
        button1.setGraphic(imageView1);
        button1.setOnAction(value -> {
            alert.showAndWait().ifPresent(response -> {
                if(response == ButtonType.OK) {
                    profileController.setNewAvatar(Avatar.AVATAR_5);
                }
            });
        });

        Button button2 = new Button();
        button2.setGraphic(imageView2);
        button2.setOnAction(value -> {
            alert.showAndWait().ifPresent(response -> {
                if(response == ButtonType.OK) {
                    profileController.setNewAvatar(Avatar.AVATAR_3);
                }
            });
        });

        Button button3 = new Button();
        button3.setGraphic(imageView3);
        button3.setOnAction(value -> {
            alert.showAndWait().ifPresent(response -> {
                if(response == ButtonType.OK) {
                    profileController.setNewAvatar(Avatar.AVATAR_1);
                }
            });
        });

        Button button4 = new Button();
        button4.setGraphic(imageView4);
        button4.setOnAction(value -> {
            alert.showAndWait().ifPresent(response -> {
                if(response == ButtonType.OK) {
                    profileController.setNewAvatar(Avatar.AVATAR_4);
                }
            });
        });

        Button button5 = new Button();
        button5.setGraphic(imageView5);
        button5.setOnAction(value -> {
            alert.showAndWait().ifPresent(response -> {
                if(response == ButtonType.OK) {
                    profileController.setNewAvatar(Avatar.AVATAR_2);
                }
            });
        });

        hBox.getChildren().addAll(imageView1, imageView2, imageView3, imageView4, imageView5, button1, button2, button3, button4, button5);

        Text text = new Text(360, 50, "Pick an Avatar");
        text.setFill(Color.BLACK);
        Font font = Font.font("sanserif", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 25);
        text.setFont(font);
        vBox.setAlignment(Pos.BASELINE_LEFT);

        vBox.setSpacing(15);
        vBox.getChildren().addAll(text, hBox);
        pane.getChildren().add(vBox);
        Scene scene = new Scene(pane);
        LoginMenu.stage.setScene(scene);
        LoginMenu.stage.show();
    }

    public void enterMainMenu(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(LoginMenu.stage);
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
}
