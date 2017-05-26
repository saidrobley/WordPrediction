import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class TrieMain extends JFrame {
    JLabel label;
    private static JTextField textField;
    private static JTextArea textArea;
    JPanel panel = new JPanel();
    private static TrieDictionary t;

    TrieMain(){
        setLocationRelativeTo(null);
        getContentPane().setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label = new JLabel("Enter a prefix", JLabel.RIGHT);
        //text field for to type a prefix of a word
        textField = new JTextField(10);
        //text area will have up to 10 words that match
        //the prefix from the text field
        textArea = new JTextArea(10, 20);
        panel.add(label);
        panel.add(textField);
        panel.add(textArea);
        getContentPane().add(label);
        getContentPane().add(textField);
        getContentPane().add(textArea);
        setSize(300, 270);
        setVisible(true);
        textArea.setVisible(true);
        //adding a text field listener to monitor what
        //the user types
        textField.addKeyListener(new KeyAdapter(){
            //Handles the key released event from the text field
            public void keyReleased(KeyEvent e){
                //get input from the text field
                String prefix = textField.getText();
                //do a search in the tree.
                ArrayList<String> words = t.searchWithPrefix(prefix);

                textArea.setText("");
                for (String word : words) {
                    //write the result in the te
                    textArea.append(word + "\n");

                }

            }

        });

    }

    //*****************************************************************
    public static void main(String[] args) {

        t=new TrieDictionary();
        String fileName = "words.txt";

        try{
            Scanner input = new Scanner(new File(fileName));
            while(input.hasNextLine()){
                //get each word in the file
                String word = input.nextLine().toLowerCase();
                //put the word in the tree
                t.insert(word);
            }
            input.close();

        } catch(FileNotFoundException ex){
            System.out.println("Unable to open file " + fileName);
        }

        new TrieMain().setVisible(true);

    }
//*****************************************************************





}