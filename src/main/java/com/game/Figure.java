package com.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Figure {
        Image image;
        ImageView img;

        public Figure(Image image) {
            this.image = image;
        }

        public Image getImage() {
            return image;
        }

        public ImageView createFigure() {
            img = new ImageView(image);
            setFormattingForImg(img);
            return img;
        }

    public void setFormattingForImg(ImageView img) {
        img.setFitHeight(100.0);
        img.setFitWidth(100.0);
    }
}
