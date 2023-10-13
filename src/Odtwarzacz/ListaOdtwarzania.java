package Odtwarzacz;

import javafx.stage.FileChooser;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class ListaOdtwarzania {

    ArrayList<File> lista = new ArrayList<File>();
    private int songNumber;

    public void add()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        File file = fileChooser.showOpenDialog(null);
        if(file!=null)lista.add(file);
    }

    public String getSong() {
        return lista.get(songNumber).toURI().toString();
    }

    public String getName()
    {
        return lista.get(songNumber).getName();
    }

    public int getSongNumber()
    {
        return songNumber;
    }

    public int getSize()
    {
        return lista.size();
    }

    public void setSongNumber(int number){ songNumber = number;}


}
