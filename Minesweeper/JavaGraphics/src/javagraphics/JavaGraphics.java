package javagraphics;

import java.awt.*;  
import java.awt.event.*; // Using AWT's event classes and listener interfaces
import javax.swing.*;    // Using Swing's components and containers
/*******************************************************************************
 * 1) Define the Frame Class: "JavaGraphics", extends JFrame
 ******************************************************************************/
public class JavaGraphics extends JFrame    {
    private DrawCanvas canvas; // the custom drawing canvas (a Panel class extends JPanel)
 
    //----------------------------------------------------------------
    // 1.1) Constructor to set up the GUI components and event handlers
    //----------------------------------------------------------------
    public JavaGraphics() {
        // 1.1.1) Set up the custom drawing canvas (JPanel)
        canvas = new DrawCanvas();
    
        // 1.1.2) Set up a panel for the buttons
        JPanel btnPanel = new JPanel(new FlowLayout());
        
        // 1.1.3) Setup left button & add it to btnPanel
        JButton btnLeft = new JButton("Try Again?");
        btnPanel.add(btnLeft);
        
        // 1.1.4) Setup left button event response
        ActionListener actLeft =  new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //canvas.moveLeft();
                //canvas = new DrawCanvas();
                requestFocus(); // change the focus to JFrame to receive KeyEvent
            }
        };
        btnLeft.addActionListener(actLeft);
        
        // 1.1.5) Setup right button & add it to btnPanel
        JButton btnRight = new JButton("Move Right");
        btnPanel.add(btnRight);

        // 1.1.6) Setup right button event response
        ActionListener actRight = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //canvas.moveRight();
                requestFocus(); // change the focus to JFrame to receive KeyEvent
            }
        };
        btnRight.addActionListener(actRight);
        
        // 1.1.7) Add both panels to this JFrame
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(canvas, BorderLayout.CENTER);    // add canvas to JFrame
        cp.add(btnPanel, BorderLayout.SOUTH);   // add btnPanel to JFrame

        // 1.1.8) Setup the Frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sprite Moving 1.2");  // two moving obj; add event class
        pack();            // pack all the components in the JFrame
        setVisible(true);  // show it
        requestFocus();    // "super" JFrame requests focus to receive KeyEvent

        // 1.1.9) Setup mouse, key, timer event handler
        InitEventHandlers();
    }
    
    //-----------------------------------------------------
    // 1.2) Add Mouse, Keyboard, Timer event handlers
    //-----------------------------------------------------
    private void InitEventHandlers()   {
        // Add mouse event handler
        MouseHandler mouseHandler = new MouseHandler(canvas);        
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        
        // Add key event handler
        KeyHandler actKey = new KeyHandler(canvas);
        addKeyListener(actKey); 
        
        // Add timer event handler
        ActionListener updateTask = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                   canvas.moveAround();
            }
        };
        new Timer(25, updateTask).start();    // Allocate a Timer to run updateTask
    }
    
    //-----------------------------------------------------
    // 1.3) The entry main() method
    //-----------------------------------------------------
    public static void main(String[] args) {
        //JavaGraphics g = new JavaGraphics();
        // Run Frame construction on the Event-Dispatching Thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               new JavaGraphics(); // Let the constructor do the job
            }
        });
    }    
}
