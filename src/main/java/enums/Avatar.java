package enums;

import javafx.scene.image.Image;

import java.util.Random;

public enum Avatar {
    AVATAR_1("/avatars/Avatar1.png"),
    AVATAR_2("/avatars/Avatar2.png"),
    AVATAR_3("/avatars/Avatar3.png"),
    AVATAR_4("/avatars/Avatar4.png"),
    AVATAR_5("/avatars/Avatar5.png")
    ;
    public final Image image;

    Avatar(String path) {
        this.image = new Image(Avatar.class.getResource(path).toExternalForm());
    }

    public static Avatar getRandomAvatar() {
        Random random = new Random();
        return values()[random.nextInt(values().length - 1)];
    }
}
