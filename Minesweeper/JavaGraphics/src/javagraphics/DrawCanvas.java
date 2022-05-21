
package javagraphics;

import java.awt.*;  //
//import javax.swing.*;    // Using Swing's components and containers
//import java.awt.event.*; // Using AWT's event classes and listener interfaces
import javax.swing.JPanel;

/*******************************************************************************
 * 2) Define the Panel Class: "DrawCanvas", extends JPanel
 ******************************************************************************/
class DrawCanvas   extends JPanel {
    //-----------------------------------------------------
    //2.0) Setup canvas parameters, width, height, color
    //-----------------------------------------------------
    static final int CANVAS_WIDTH = 1800;
    static final int CANVAS_HEIGHT = 1000;
    static Color CANVAS_BG_COLOR = Color.black;
    Point pt;
    Point pt2;
    boolean tryagain = false;
    int score = 0;
    int hiscore = 0;
    int opInx = 1;
    int opInx0 = 0;
    int opInx1 = 2;
    int explosion = 0;
    int blast = 200;
    int flag = 0;
    Color clr = Color.white;
    boolean gameover = false;
    ObjProperty[] op = {    new ObjProperty(100, 10, 20, 20, 10, 1, Color.red),
                            new ObjProperty(10, 10, 20, 20, -10, 3, Color.red),
                            new ObjProperty(500, 10, 20, 20, 5, 4, Color.red),
                            new ObjProperty(750, 10, 20, 20, -5, 2, Color.red),
                            new ObjProperty(1000, 10, 20, 20, 3, 3, Color.red),
                            new ObjProperty(1500, 10, 20, 20, -3, 4, Color.red),
                            new ObjProperty(250, 10, 20, 20, 1, 3, Color.red),
                            new ObjProperty(1250, 10, 20, 20, -1, 2, Color.red),
                        };
    //--------------------------------------------------------
    //2.1) define variables
    //--------------------------------------------------------
    int moveStep = 2;
    int moveDir = 1;
    Sprite target;     // the moving object
    Sprite target0;
    Sprite target1;
    Sprite missile;  // the moving object
    
    boolean flgStart = false;
    //--------------------------------------------------------
    // 2.2) Constructor of DrawCanvas
    //--------------------------------------------------------
    public DrawCanvas() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        // Construct a sprite given x, y, width, height, color
        target = makeObj(opInx++);
        target0 = makeObj(opInx0++);
        target1 = makeObj(opInx1++);
        missile = new Sprite(CANVAS_WIDTH / 2, CANVAS_HEIGHT - 10, 10, 10, Color.white); // to test repaint
    }
        
    // Function to construct a sprite given x, y, width, height, color
    private Sprite makeObj(int index)  {
        int inx = index % op.length;
        int X0 = op[inx].x0;        //CANVAS_WIDTH / 2 - 10;
        int Y0 = op[inx].y0;    //CANVAS_HEIGHT / 2 - 10;
        Sprite obj = new Sprite(X0, Y0,op[inx].width, op[inx].height, op[inx].color);
        obj.InitMove(op[inx].dx, op[inx].dy);    
        return obj;
    }    
    //--------------------------------------------------------
    // 2.3) Define moving methods
    //--------------------------------------------------------
    public void moveAround()    {          // called  by timer every 25ms
        if(flgStart == true)
            runObj();
        else
            repaint();
    }
    
    void runObj()   {
        Rectangle rect = target.moveObj();  // 1. define drawing rectangle
        if(!couldMove(target))   {
            target = makeObj(opInx++);             // 3. create a new object
        }
        else    {
            collision(target);
            Gameover(target);
        }           

        rect = target0.moveObj();  // 1. define drawing rectangle
        if(!couldMove(target0))  {
            target0 = makeObj(opInx++);             // 3. create a new object
        }
        else    {
            collision(target0);
            Gameover(target0);
        }

         rect = target1.moveObj();  // 1. define drawing rectangle
        if(!couldMove(target1))  {
            target1 = makeObj(opInx++);             // 3. create a new object
        }
        else    {
            collision(target1);
            Gameover(target1);
        }
        
        rect = missile.moveObj();  // 1. define drawing rectangle
        if(!couldMove(missile)){
            missile = new Sprite(CANVAS_WIDTH / 2, CANVAS_HEIGHT - 10, 10, 10, Color.white);
        }
        repaint();
    }
     
    public void startmenu (Graphics g){
            g.setColor(clr);
        g.setFont(new Font("Monospaced", Font.PLAIN, 100));
        g.drawString("Start", CANVAS_WIDTH * 3/7, CANVAS_HEIGHT/2);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.PLAIN, 125));
        g.drawString("Missile Command Clone", 120, CANVAS_HEIGHT/4);
    }
    
   public boolean couldMove(Sprite obj)   {
        difficulty_level(score);
        boolean moveOK = true;
        Rectangle rect = obj.GetRect();
        int dir = (int)obj.dx;
        
        if(rect.y - obj.height > CANVAS_HEIGHT)  {
            moveOK = false;
        }
        else if (rect.y + obj.height < 0){
            moveOK = false;
        }
        else if(dir > 0 && (rect.x + rect.width > CANVAS_WIDTH)) {
            obj.ChangeDir(-1, 1);               // change x direction
        }
        else if(dir < 0 && rect.x < 0) {
            obj.ChangeDir(-1, 1);               // change x direction
        }
        else if (pt != null && obj == missile){
            if(missile.yy <= pt.y){
                missile = new Sprite(missile.xx, missile.yy, 10 + explosion, 10 + explosion, Color.orange);
                if (flag == 0){
                    missile.color = Color.orange;
                }
                if (flag == 1){
                    missile.color = Color.yellow;
                }
                if (flag == 2){
                    missile.color = Color.green;
                }
                if (flag == 3){
                    missile.color = Color.cyan;
                }
                if (flag == 4){
                    missile.color = Color.blue;
                }
                if (flag == 5){
                    missile.color = Color.magenta;
                }
                explosion += 20;
                if(explosion > blast)   { 
                    moveOK = false;
                }
            }
        }
        else if (gameover == true){
            
            if (tryagain == true){
                moveOK = false;
                if (obj == target1){
                    score = 0;
                    flag = 0;
                    gameover = false;
                    tryagain = false;
                    blast = 200;
                }
            }
        }
          
        return moveOK;
    }
    public void difficulty_level (int score){
        if (score > 100){
            blast = 180;
            flag = 1;            
        }   
        if (score > 200){
            blast = 160;
            flag = 2;           
        }
        if (score > 300){
            blast = 140;
            flag = 3;            
        }
        if (score > 400){
            blast = 120;
            flag = 4;           
        }
        if (score > 500){
            blast = 100;
            flag = 5;
        }
    }
    
    public void collision (Sprite tgt) {
        int X = tgt.xx - missile.xx;
        int Y = tgt.yy - missile.yy;
        double distance = Math.sqrt( X * X + Y * Y);
        if (distance <= (tgt.width /2) + (missile.width/2)){
            if (tgt.color != Color.black){    
                score += 10;
            }
            tgt.color = Color.black;
            tgt.InitMove(0, 20);
            //System.out.println(score);
            //flag = 1;
            pt.x = tgt.xx;
            pt.y = tgt.yy;
        }
    }
    
    //--------------------------------------------------------
    // 2.4) the Painting method, called by JPanel
    //--------------------------------------------------------    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.black);
        if(flgStart == true)
            PaintObj(g);
        else{
            startmenu(g);
        }
    }
    
    void PaintObj(Graphics g)    {
        target.paint(g);  // the sprite paints itself
        target0.paint(g);  // the sprite paints itself
        target1.paint(g);
        missile.paint(g);  // the sprite paints itself
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.PLAIN, 30));
        g.drawString("Score:" + score, 20, 30);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.PLAIN, 30));
        g.drawString("High Score:" + hiscore, 20, 70);
        
        if (gameover == true) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Monospaced", Font.PLAIN, 200));
            g.drawString("You Lose", CANVAS_WIDTH/2 - 500, CANVAS_HEIGHT/2);
            target.InitMove(0, 0);
            target0.InitMove(0, 0);
            target1.InitMove(0, 0);
            missile.InitMove(0, 0);
            g.setColor(
                    
                    clr);
            g.setFont(new Font("Monospaced", Font.PLAIN, 75));
            g.drawString("Try Again?", CANVAS_WIDTH/2 - 500, CANVAS_HEIGHT * 3/5);
            if (score > hiscore){
            hiscore = score;
            }
        }
    }
    
    public void Gameover(Sprite tgt){
        
        if (tgt.yy >= CANVAS_HEIGHT - 10 && tgt.color != Color.black){
            //gameover = true;
//            target = new Sprite(tgt.xx, tgt.yy, 10 + explosion, 10 + explosion, Color.red);
//            explosion++;
            gameover = true;
//            if (explosion > 20000){
//                
//            }
        }
    }
    
    public void Tryagain(){
    if (gameover == true){
        if ((pt.x >= 405 && pt.x <= 855) && (pt.y >= 585 && pt.y <= 635)){
            
            tryagain = true;
            }
        }
    }
    
    public void moveobj(Point pt){
        this.pt = pt;
        if (flgStart == true){
            if (gameover == false){
                    int xx = pt.x - missile.xx;
                    int yy = missile.yy - pt.y;
                    int dx = xx * 50 /Math.abs(yy); 
                    missile.InitMove(dx, - 50);
                    explosion = 0;
                    //missile.InitMove(-10, -10);
            }
            else
                Tryagain();
        } 
        else if((pt.x >= 785 && pt.x <= 1070) && (pt.y >= 465 && pt.y <= 535)){
                flgStart = true;
        }
    }
    
public void colorchange(Point pt2){
    if (gameover == false){
        if ((pt2.x >= 785 && pt2.x <= 1070) && (pt2.y >= 465 && pt2.y <= 535)){
                clr = Color.red;     
        }
        else{
        clr = Color.white;
            }
        }
    if (gameover == true){
        if ((pt2.x >= 405 && pt2.x <= 855) && (pt2.y >= 585 && pt2.y <= 635)){
                clr = Color.red;     
        }
        else{
        clr = Color.white;
            }
        }
    }    
}




//===================================================================
class ObjProperty    {
    public int x0, y0, width, height;   // Use an rectangle for illustration
    public int dx = 0, dy = 0;
    public Color color = Color.RED;     // Color of the object  
    //-----------------------------------------------
    // Class Constructor
    //-----------------------------------------------
    public ObjProperty(int x, int y, int w, int h, int dx, int dy, Color clr)    {
        x0 = x;
        y0 = y;
        width = w;
        height = h;
        this.dx = dx;
        this.dy = dy;
        color = clr;        
    }
}
