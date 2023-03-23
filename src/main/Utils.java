package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public abstract class Utils {

    public static Image resizeImage(int width, int height, String pathFile) {
        Image img;

        try {
            img = ImageIO.read(new File(pathFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return img.getScaledInstance(width,height,Image.SCALE_FAST);
    }

}
