package Odtwarzacz;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class MainWindowController implements Initializable {
    @FXML
    private Label titleLabel;
    @FXML
    private Slider volumeSlider;

    @FXML
    private ProgressBar progressBar;

    private ListaOdtwarzania listaodtw;
    private Media media;
    private MediaPlayer mediaPlayer;

    private Timer timer;
    private TimerTask task;
    private boolean running;

    public void initialize(URL url, ResourceBundle resourceBundle) {

        listaodtw = new ListaOdtwarzania();

        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {

                if(listaodtw.getSize()==0)return;
                else {
                    mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
                }
            }
        });

    }

    public void playMusic(){
        if(listaodtw.getSize()!=0){
            beginTimer();
            mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
            mediaPlayer.play();
        }
    }

    public void pauseMusic(){
        if(listaodtw.getSize()!=0){
            cancelTimer();
            mediaPlayer.pause();
        }
    }

    public void resetMusic(){
        if(listaodtw.getSize()!=0){
            progressBar.setProgress(0);
            mediaPlayer.seek(Duration.seconds(0));
        }
    }

    public void previousMusic(){
        if(listaodtw.getSize()>1) {
            if (listaodtw.getSongNumber() > 0) { listaodtw.setSongNumber(listaodtw.getSongNumber() - 1);}
            else {listaodtw.setSongNumber(listaodtw.getSize()-1);}
            changeMusic();
        }
    }

    public void nextMusic(){
        if(listaodtw.getSize()>1) {
            if (listaodtw.getSongNumber() < listaodtw.getSize() - 1) {listaodtw.setSongNumber(listaodtw.getSongNumber() + 1);}
            else {listaodtw.setSongNumber(0);}
            changeMusic();
        }

    }

    public void beginTimer(){

        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                progressBar.setProgress(current/end);

                if(current / end == 1){
                    cancelTimer();
                }
            }
        };
        timer.schedule(task,0, 1000);
    }

    public void setMusic()
    {
        media = new Media(listaodtw.getSong());
        mediaPlayer = new MediaPlayer(media);

        titleLabel.setText(listaodtw.getName());
    }

    public void changeMusic()
    {
        mediaPlayer.stop();
        if(running) { cancelTimer();}
        setMusic();
        playMusic();
    }

    public void cancelTimer(){
        timer.cancel();
        running = false;
    }

    public void addToPlaylist() {
        int size = listaodtw.getSize();
        listaodtw.add();
        if (listaodtw.getSize() == 1 && size == 0) {
            media = new Media(listaodtw.getSong());
            mediaPlayer = new MediaPlayer(media);
            titleLabel.setText(listaodtw.getName());
            playMusic();
        }
    }


}
