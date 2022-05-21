package javagraphics;

import java.awt.*;  

/*******************************************************************************
 * 3) Define the Sprite Class
 ******************************************************************************/
class Sprite {
     // Class variables 
    public int xx, yy, width, height; // Use an rectangle for illustration
    public double dx = 0, dy = 0;
    Color color = Color.BLACK; // Color of the object
    Rectangle rect;

    //----------------------------------------
    // 3.1) Constructor
    //----------------------------------------
    public Sprite(int x, int y, int width, int height, Color clr) {
       this.xx = x;
       this.yy = y;
       this.width = width;
       this.height = height;
       this.color = clr;
    }
    
    public void InitMove(int dx, int dy) {              // add in 2017-07-10
        this.dx = dx;
        this.dy = dy;
    }
    
    public void ChangeDir(int dirX,int dirY)   {        // add in 2017-07-10
        dx *= dirX;
        dy *= dirY;
        if(dx > 100 || dx < -100)
            dx /= 2;
    }
    
    public Rectangle GetRect()   {                      // add in 2017-07-10
        return rect;
    }
    //---------------------------------------------------------------
    // 3.2) define a rectangle for repaint, which moves an object
    //---------------------------------------------------------------
    public Rectangle moveObj()    {                     // add in 2017-07-10
        return moveObj(dx, dy);
    }
    
    public Rectangle moveObj(double moveX, double moveY)    {
        int X0 = xx;
        int Y0 = yy;
        double ww = 0, hh = 0;
        
        if(moveX != 0)  {
            if(moveX < 0)   {   // move left
                xx += moveX;
                X0 = xx;
                ww = -moveX;
            }
            else    {           // move right
                X0 = xx;
                xx += moveX; 
                ww = moveX;
            }
        }        
        if(moveY != 0) {
            if(moveY <  0)  {   // move up
                yy += moveY;
                Y0 = yy;
                hh = -moveY;
            }
            else    {           // move down
                Y0 = yy;
                yy += moveY; 
                hh = moveY;                
            }
        }
        rect = new Rectangle(X0, Y0, (int)(width + ww), (int)(height + hh));
        return rect;
    }
    //----------------------------------------------------------
    // 3.3) Paint itself given the Graphics context
    //----------------------------------------------------------
    public void paint(Graphics g) {
       g.setColor(color);
       //g.fillRect(xx, yy, width, height); // Fill a rectangle
       g.fillOval(xx - width/2, yy - height/2, width, height); // Fill a rectangle
    }  
}
