import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

public class Controller {

    @FXML
    private Button start;

    @FXML
    private Button chfile;

    @FXML
    void StartAction(ActionEvent event) throws IOException
    {
        System.out.println("start pushed");
        String inputpath = "C:\\MyFiles\\Программирование\\Java\\test.xlsx";
        String outputpath = "C:\\MyFiles\\Программирование\\Java";
        InputExcel input = new  InputExcel(inputpath);
        PrimaryParsing pp = new PrimaryParsing(input.PrimaryRef,outputpath);
        pp.SearchSecRef();
        pp.ShowLists();

        pp.FinalParsing(input.KeyWords);
    }

    @FXML
    void ChooseAction(ActionEvent event)
    {
        /*Stage stage = new Stage();
        System.out.println("action pushed");
       // MyFrame fr = new MyFrame();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(stage);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*");

        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null)
        {
           System.out.println(selectedFile.toString());
        }*/

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("choosertitle");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        FileFilter filter = new FileNameExtensionFilter("Excel file", "xls", "xlsx");
        chooser.addChoosableFileFilter(filter);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
        }
        else
        {
            System.out.println("No Selection ");
        }




    }

    public static void main(String[] args)
    {

    }

}