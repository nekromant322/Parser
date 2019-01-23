
import javax.swing.*;
import java.io.File;
public class MyFrame extends JFrame
{
    MyFrame()
    {
        setBounds(0,0,500,500);
        JFileChooser dialog = new JFileChooser();
        dialog.showOpenDialog(this);
        File file =dialog.getSelectedFile();
        setVisible(true);
    }
}