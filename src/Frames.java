import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

//Class containing fields and method for implementation of GUI.
public class Frames implements ActionListener {
    //Creating Frame
    JFrame frame = new JFrame();
    //Creating Button
    JButton button;

    //This method create the frame required.
    public void openFrame() {
        //Label for the frame
        JLabel label = new JLabel("Choose JPG Image to Convert");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 15));
        this.frame.add(label, BorderLayout.PAGE_START);
        //Title for the Frame.
        this.frame.setTitle("Binary Image Converter");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        //Setting the size of the Frame.
        this.frame.setSize(400, 150);
        this.frame.setVisible(true);
        //Setting the colour of the Frame.
        this.frame.getContentPane().setBackground(new Color(217, 217, 217));
        //Calling the button builder method.
        button = frameButton();
        this.frame.add(button);
        //Setting Icon to the Frame.
        ImageIcon icon = new ImageIcon("Repository/binary-code.png");
        this.frame.setIconImage(icon.getImage());
        //Adding EvenT-Listener to the button.
        button.addActionListener(this);

    }

    //Method for creating the button for the frame.
    private JButton frameButton() {

        JButton button = new JButton();
        int buttonWidth = 140;
        int buttonHeight = 40;
        button.setVisible(true);
        //Setting the size of the button.
        button.setBounds(120, 40, buttonWidth, buttonHeight);
        //Placeholder for the button.
        button.setText("Choose Image File");
        //Setting font-style for the button.
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusable(false);
        //returning the button to the caller.
        return button;
    }

    //Overridden method to perform action based on the click of the button in the frame.
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button) {
            //This object will help in opening the File-Explorer.
            JFileChooser fileChooser = new JFileChooser();
            //Applying Extension filters.
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG",
                    "jpg");
            fileChooser.addChoosableFileFilter(filter);
            //Title of the File Explorer.
            fileChooser.setDialogTitle("Binary Image Converter");
            //Setting the default directory to open by the File-Explorer to the project's Root folder.
            fileChooser.setCurrentDirectory(new File("."));
            //Storing the integer response value.
            int response = fileChooser.showOpenDialog(button);
            //If the user chooses the file response will be 0;
            if (response == JFileChooser.APPROVE_OPTION) {
                //Created a File
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                //Creating the object of the Converter class.
                Converter convert = new Converter();
                /*Calling the main driver method for binary conversion of digital image
                by passing object of the class File.*/
                convert.driverXD(file.getAbsolutePath());
                //Calling this method for successful conversion of the Digital Image.
                this.savedAlert();
            }
        }
    }

    /*Method will create an information box that lets the user know that
    the image has been successfully converted and the text file is saved.*/
    public void savedAlert() {
        JOptionPane.showMessageDialog(null,
                "Binary File Saved Successfully",
                "File Saved",
                JOptionPane.INFORMATION_MESSAGE);
    }
}

