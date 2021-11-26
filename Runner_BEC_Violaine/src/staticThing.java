import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;


public class staticThing {
    private double x;
    private double y;
    private ImageView imageView;
    private String fileName;
    private Image image;

    public staticThing(String fileName, double x, double y, double camX, double camY, double width, double height) {
        this.fileName = fileName;

        image=new Image(fileName); // on peut aussi écrire Image(filename, requestedWidth, requestedHeight, true, true)
        imageView= new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setViewport(new Rectangle2D(camX,camY,width,height)); // min x, min y , width, height

    }

    public ImageView getImageView(){
      return imageView;
    }

}

