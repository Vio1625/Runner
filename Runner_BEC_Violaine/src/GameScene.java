import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.geometry.Rectangle2D;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;



public class GameScene<StaticThing> extends Scene{

    public static staticThing left;
    public static staticThing right;
    public static staticThing menu;
    public static staticThing terre;
    public static staticThing etoile;
    public static staticThing levelup;
    public staticThing heart;
    private ArrayList<staticThing> hearts = new ArrayList();
    public static staticThing game_over;

    public static Label niveau;
    public static Label victory;
    public static Label pressSpace;

    public static Hero hero;
    public Foe foe;
    public Foe foe2;
    public Foe foe3;
    public static ArrayList<Foe> foes = new ArrayList();

    public static  Camera cam;

     private boolean ismenu = true;
     private static double x;
     private double past;
     private static int number_of_lives=3;
     public static int reached_level=1;
     private static double nowx;

     private boolean prevColision = false;
     private boolean nowColision = false;




     public GameScene(Group root)
     {
          super(root,800,400); // on crée une fenêtre de 800x400

          timer.start();            // on démarre le timer


          cam = new Camera(0,0, hero);
          niveau = new Label("You've reached level " + number_of_lives);
          niveau.setFont(new Font("Impact",30));
          niveau.setTextFill(Color.web("#FFFFFF"));
          niveau.setTranslateY(-400);
          niveau.setTranslateX(25);

          victory = new Label("Victory?");
          victory.setFont(new Font("Impact",50));
          victory.setTextFill(Color.web("#FFFFFF"));
          victory.setTranslateY(-400);
          victory.setTranslateX(45);

         pressSpace = new Label("Press space to jump");
         pressSpace.setFont(new Font("Impact",30));
         pressSpace.setTextFill(Color.web("#FFFFFF"));
         pressSpace.setTranslateY(150);
         pressSpace.setTranslateX(20);




         // x,y: position du héro dans la fenêtre
         //camX, camY: position de la caméra dans l'image que l'on recherche
         // width, height: largeur et hauteur du hero que l'on insère dans la fenêtre
         // vx: vitesse en x du héro
          hero= new Hero("heros.png", 630, 280, cam.getX(), cam.getY() ,85,100,200);


          menu = new staticThing("menu.png",0,0,cam.getX(),cam.getY(),800,400);
          levelup = new staticThing("Levelup.png",250,-400,0,0,300,81);
          etoile = new staticThing("etoile.png",0,0,0,0,800,250);
          terre=new staticThing("terre.png",650,30,0,0,300,200);
          left=new staticThing ("fond.png", 0, 0, cam.getX(), cam.getY(),800,400);
          right=new staticThing ("fond.png", 800, 0, cam.getX(), cam.getY(),800,400);
          game_over = new staticThing("gameover.png",0,-400,cam.getX(),cam.getY(),800,400);
          for (int i=0; i<number_of_lives; i++){
             heart=new staticThing("heart.png", 20+50*i, 10, cam.getX(), cam.getY(),50,27);
             hearts.add(heart);
          }

          // création de trois ennemis
            foe=new Foe("Foe.png",1100,310,cam.getX(),cam.getY(),40,32,80);
            foe2=new Foe("Foe2.png",2600,310,cam.getX(),cam.getY(),40,30,80);
            foe3= new Foe("foe3.png", 4900,310,cam.getX(),cam.getY(),40,30,110);
            foes.add(foe);
            foes.add(foe2);
            foes.add(foe3);


            root.getChildren().addAll(this.etoile.getImageView());
            root.getChildren().addAll(this.terre.getImageView());
            root.getChildren().addAll(this.levelup.getImageView());

            root.getChildren().addAll(this.left.getImageView());
            root.getChildren().addAll(this.right.getImageView());

            for (int i=0; i<hearts.size();i++) {
                root.getChildren().addAll(this.hearts.get(i).getImageView());
            }

            for (int i=0; i< foes.size();i++) {
            root.getChildren().addAll(foes.get(i).getImageView());
            }

            root.getChildren().addAll(this.menu.getImageView());
            root.getChildren().add(pressSpace);
            root.getChildren().addAll(this.hero.getImageView());
            root.getChildren().addAll(game_over.getImageView());
            root.getChildren().addAll(niveau);
            root.getChildren().addAll(victory);







            this.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle (KeyEvent keyEvent){

                 // press space to jump
                 if (keyEvent.getCode() == KeyCode.SPACE) {
                     if (!ismenu) {
                         if (hero.getY() > 240) {
                             hero.attitude = 1;
                             hero.setAy(3000);
                             System.out.println("jump");
                         }
                     }
                 }


                    // Press A to start
                 if (keyEvent.getCode() == KeyCode.A) {
                     menu.getImageView().setY(-400);
                     pressSpace.setTranslateY(-400);
                     hero.setX(0);
                     ismenu = false;
                     timer.stop();
                     timer.start();
                 }

                 // Press R to restart
                 if (keyEvent.getCode() == KeyCode.R) {
                     number_of_lives=3;
                     game_over.getImageView().setY(-400);
                     left.getImageView().setX(0);
                     right.getImageView().setX(800);
                     hero.getImageView().setX(100);
                     hero.setX(0);
                     cam.setX(0);
                     foe.setX(1100);
                     foe2.setX(2600);
                     foe3.setX(4900);
                     for (int i=0; i<hearts.size();i++) {
                         hearts.get(i).getImageView().setY(10);
                     }
                     hero.setisInvicible(false);
                     victory.setTranslateY(-400);
                     niveau.setTranslateY(-400);
                     timer.start();
                 }

             }
            });




     }



   public static void update(double time) {

       if(time > 1) time = 0;
       x =(x + cam.getVx() * time) % 800;
       left.getImageView().setViewport(new Rectangle2D(x, 0, 800 - x, 400));
       right.getImageView().setX(800 - x);

       if (number_of_lives==0){
           game_over.getImageView().setY(0);   // Si on perd, l'image gameOver apparaît
           nowx=hero.getX();                   // Pour avoir le score
           if (nowx>12500){
               niveau.setText("You've reached final level \nScore: " + (int)nowx);
               victory.setText("VICTORY !");

           }
           else {
               niveau.setText("You've reached level " + reached_level + "\nScore: " + (int)nowx);
               victory.setText("Game Over");
           }
           niveau.setTranslateY(170);
           victory.setTranslateY(10);
           number_of_lives=-5; // choisi arbitrairement
       }

     }


       AnimationTimer timer = new AnimationTimer() {
        @Override
           public void handle(long time) {

            double temps = (time - past) / 1000000000;
            if (ismenu) {
                hero.menuhero(temps);

            }

            else {
                prevColision = nowColision;

                nowColision = hero.getisInvicible();
                if (prevColision == false & nowColision == true) {
                    if (number_of_lives > 0) {
                        number_of_lives = number_of_lives - 1;
                        hearts.get(number_of_lives).getImageView().setY(-100);
                    }
                }

                hero.update(temps, cam.getX());

                if (!hero.getisInvicible()) {
                    if (hero.getHitBox(foe) == true) {
                        hero.setisInvicible(true);
                        foe.imageView.setY(-100);


                    }
                    if (hero.getHitBox(foe2) == true) {
                        hero.setisInvicible(true);
                        foe2.imageView.setY(-100);
                    }
                    if (hero.getHitBox(foe3) == true) {
                        hero.setisInvicible(true);
                        foe3.imageView.setY(-100);
                    }


                }
                if (hero.getisInvicible()) {
                    hero.isInvicibleFunction(temps);
                }

                GameScene.update(temps);
                cam.update(temps, hero.getX());
                foe.updatefoe1(temps, foe.getVx(), cam.getX());
                foe2.updatefoe2(temps, foe.getVx(), cam.getX());
                foe3.updatefoe3(temps, foe.getX(), cam.getX());


                if (number_of_lives == -5) {
                    timer.stop();
                }

                if (hero.islevelup()){levelup.getImageView().setY(20);}
                else {levelup.getImageView().setY(-400);}




                past = time;

            }
        }

       };

}
