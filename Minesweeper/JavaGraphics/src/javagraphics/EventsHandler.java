/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagraphics;

import java.awt.event.*;
import javax.swing.event.*;
import java.awt.*;    // Using Swing's components and containers

//class MouseHandler  implements MouseListener, MouseMotionListener   {
class MouseHandler extends MouseInputAdapter    {
    private DrawCanvas canvas; // the custom drawing canvas (a Panel class extends JPanel)
    
    public MouseHandler(DrawCanvas canvas)  {
        this.canvas = canvas;
    }

    //------------------------------
    // MouseListener events
    //------------------------------
    @Override
    public void mouseClicked(MouseEvent e) {    // MouseListener event
        //Point pt = e.getPoint();
        //String msg = String.format("x = %d, y = %d, pt %d, %d", e.getX(), e.getY(), pt.x, pt.y);
        //System.out.println("Clicked " + msg );
        //canvas.moveobj(pt);
    }

    @Override
    public void mousePressed(MouseEvent e) {    // MouseListener event
        Point pt = e.getPoint();
        System.out.println("CLICKED" + pt);
        canvas.moveobj(pt); 
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {    // MouseListener event
        //System.out.println("Released");
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {    // MouseListener event
       // System.out.println("Entered");        
    }
    
    @Override
    public void mouseExited(MouseEvent e) {     // MouseListener event
       // System.out.println("mouseExited");        
    }
    
    //------------------------------
    // MouseMotionListener events
    //------------------------------
    @Override
    public void mouseDragged(MouseEvent e) {    // MouseMotionListener event
        //System.out.println("Dragged");                
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {      // MouseMotionListener event
        //System.out.println("Moved");
        Point pt2 = e.getPoint();
        System.out.println("moved" + pt2);
        canvas.colorchange(pt2);
    }    
}

class KeyHandler extends KeyAdapter {
    private DrawCanvas canvas; // the custom drawing canvas (a Panel class extends JPanel)
    
    public KeyHandler(DrawCanvas canvas)  {
        this.canvas = canvas;
    }

    @Override
    public void keyPressed(KeyEvent evt) {
        switch(evt.getKeyCode()) {
            case KeyEvent.VK_LEFT:  
                System.out.println("VK_LEFT");                
                //canvas.moveLeft();  
                break;

            case KeyEvent.VK_RIGHT: 
                System.out.println("VK_RIGHT");                
                //canvas.moveRight(); 
                break;

            case KeyEvent.VK_UP: 
                System.out.println("VK_UP");                
                //canvas.moveUp();
                break;

            case KeyEvent.VK_DOWN: 
                System.out.println("VK_DOWN");                
                //canvas.moveDown();
                break;
        }
    }    
}
