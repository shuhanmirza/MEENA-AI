package meena;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 *
 * @author shuhan
 */
public class ChatGUI extends javax.swing.JFrame implements msgListener
{

    private static JFrame frame2;
    static SPPClient hand;
    static SPPClient rover;

    /**
     * Creates new form DisplayEasy
     */
    int flg = 0;
    private JPanel contentPane;
    Voice voice;
    VoiceManager voiceManager;
    equ obj;

    public ChatGUI()
    {
        obj = new equ();
        System.setProperty("mbrola.base", "D:\\Dropbox\\Programming\\JAVA\\libs\\mbrola");
        String voiceName = "mbrola_us1";
        voiceManager = VoiceManager.getInstance();
        System.out.println(voiceManager);
        voice = voiceManager.getVoice(voiceName);
        voice.setStyle("robotic");
        voice.allocate();

        initComponents();

        System.out.println("BT Remote Desktop");
        System.out.println("Connecting with Robots...");
        //connecting with hand
        hand = new SPPClient(this, "98d3317030db");
        System.out.println("HAND BT MAC:" + hand.remoteMac);
        boolean success = hand.connect();
        System.out.println("HAND Connection: " + success);
        //connecting with rover
        rover = new SPPClient(this, "98d331fc1472");
        System.out.println("Rover BT MAC:" + rover.remoteMac);
        success = rover.connect();
        System.out.println("Rover Connection: " + success);

        OutputField.append("Meena:\n Hi, I am Meena! \n\n");
        // voice.speak("Hi, I am Meena!");
        OutputField.append("You:\n");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents()
    {

        InputField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        OutputField = new javax.swing.JTextArea();
        OutputField.setToolTipText("Type a message");
        button = new javax.swing.JButton("send");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Meena AI");
        setForeground(java.awt.Color.darkGray);

        InputField.setToolTipText("Type A message");
        InputField.setName("Your Message"); // NOI18N
        InputField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                String S = InputFieldActionPerformed(evt);

                if (S.contains("rover") && S.contains("control")) {
                    dispose();
                    voice.speak("Enabling Rover Control..Please Wait..");
                    RoverGUI screen4 = new RoverGUI(rover);
                    screen4.setVisible(true);
                }

                WisePuhi lara = new WisePuhi();

                InputField.setText("");
                OutputField.append(S);
                OutputField.append("\n");
                OutputField.append("Meena: \n");

                if (flg == 1) {
                    try {
                        Files.write(Paths.get("newChat.txt"), S.getBytes(), StandardOpenOption.APPEND);
                        Files.write(Paths.get("newChat.txt"), "\n".getBytes(), StandardOpenOption.APPEND);

                        Files.write(Paths.get("newChat.txt"), "######################################".getBytes(), StandardOpenOption.APPEND);
                        Files.write(Paths.get("newChat.txt"), "\n".getBytes(), StandardOpenOption.APPEND);

                        S = "Thank you wise human :D";
                        flg = 0;
                    } catch (IOException ex) {
                        Logger.getLogger(ChatGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {

                    try {
                        S.toLowerCase();
                        if (S.contains("bring") && S.contains("glass")) {
                            hand.sendLine("b");
                            S = "ok boss";
                        } else if (S.contains("take") && S.contains("glass")) {
                            hand.sendLine("t");
                            S = "ok boss";
                        } else if (S.contains("light on")) {
                            hand.sendLine("o");
                            S = "ok boss";
                        } else if (S.contains("light off")) {
                            hand.sendLine("n");
                            S = "ok boss";
                        } else if (S.contains("enable") && S.contains("speech") && S.contains("home")) {
                            equ.HomeCommand(obj, hand);
                        } else {
                            S = lara.chat(S);
                        }
                    } catch (Exception ex) {
                        try {

                            String str = null;

                            Files.write(Paths.get("newChat.txt"), S.getBytes(), StandardOpenOption.APPEND);
                            Files.write(Paths.get("newChat.txt"), "\n".getBytes(), StandardOpenOption.APPEND);

                            S = "I didn't understand what you told me. Please teach me what to respond in the asked question.Thanks :D";
                            //voice.speak("I didn't understand what you told me. Please teach me what to respond in the asked question.Thanks");
                            flg = 1;

                        } catch (IOException ex1) {
                            Logger.getLogger(ChatGUI.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
                }

                OutputField.append(S);
                OutputField.append("\n\n");
                OutputField.append("YOU: \n");
                //voice.speak(S);

            }
        });

        OutputField.setEditable(false);
        OutputField.setColumns(20);
        OutputField.setLineWrap(true);
        OutputField.setRows(5);
        OutputField.setWrapStyleWord(true);
        OutputField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(OutputField);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(InputField)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
                        .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(InputField, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                        .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private String InputFieldActionPerformed(java.awt.event.ActionEvent evt)
    {
        String s = InputField.getText();
        InputField.setText("");
        return s;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new ChatGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTextField InputField;
    private javax.swing.JTextArea OutputField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton button;
    // End of variabes declaration     

    @Override
    public void onNewLine(String line)
    {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onJoy(boolean[] buttons, int analog)
    {
        //To change body of generated methods, choose Tools | Templates.
    }

}
