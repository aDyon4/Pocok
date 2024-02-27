package com.example.pocok;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML private Pane pnJatek;
    @FXML private Label lbKiir;
    @FXML private Button tbGomb;

    private Label[][] lt = new Label[6][6];
    private int[][] t = new int[6][6];
    private Image ures = new Image(getClass().getResourceAsStream("pocok0.png"));
    private Image pocok = new Image(getClass().getResourceAsStream("pocok1.png"));

    private AnimationTimer timer;
    private long  most;
    private long tt = 0;
    private boolean go = true;

    private int elkapott = 0;
    private int kibujt = 1;

    @FXML private void initialize(){
        for(int s = 0;s<6;s++){
            for(int o = 0;o<6;o++){
                int ss = s, oo = o;
                t[s][o] = 0;
                lt[s][o] = new Label("");
                setKep(s, o, ures);
                lt[s][o].setLayoutX(10+o*128);
                lt[s][o].setLayoutY(10+s*128);
                lt[s][o].setOnMousePressed(e -> katt(ss, oo));
                pnJatek.getChildren().add(lt[s][o]);
            }
        }
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(go && now > tt){
                    tt = now + 500_000_000; // ns
                    pockok();
                }
            }
        };
        timer.start();
        randomPocok(0, 0);

    }

    private void pockok(){
        for(int s = 0;s<6;s++){
            for(int o = 0;o<6;o++){
                if(t[s][o] > 0) {
                    t[s][o]--;
                    if(t[s][o] == 0) {
                        setKep(s, o, ures);
                        randomPocok(s, o);
                    }
                }

            }
        }
    }

    private void randomPocok(int es, int eo){
        int s, o;
        do {
            s = (int)(Math.random()*6);
            o = (int)(Math.random()*6);
        }while( s == es  &&  o == eo || t[s][o] != 0 );

        setKep(s, o, pocok);
        t[s][o] = (int)(Math.random()*7)+4;

    }

    private void katt(int s, int o){
        if(t[s][o] > 0){
            setKep(s, o, ures);
            elkapott++;
            if(elkapott % 10 == 0) { randomPocok(s, o); kibujt++; }
            randomPocok(s, o);
        }
        kiir();
    }

    private void kiir(){
        lbKiir.setText(kibujt + "");
    }

    @FXML private void onKiBeClick(){
        if(go) { go = false; tbGomb.setText("Stop"); }
        else { go = true ; tbGomb.setText("Start"); }
    }

    private void setKep(int s, int o, Image nev){
        lt[s][o].setGraphic(new ImageView(nev));
        if(nev == ures) t[s][o] = 0;
        else t[s][o] = 1;
    }
}