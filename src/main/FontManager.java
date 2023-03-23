package main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontManager {

    public Font quicksand_bold;

    public FontManager() {
        initQuicksandBold();
    }

    private void initQuicksandBold() {
        InputStream inputStream = Utils.getStream("resources/font/Quicksand-Bold.ttf");
        try {
            quicksand_bold = Font.createFont(Font.TRUETYPE_FONT,inputStream);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
