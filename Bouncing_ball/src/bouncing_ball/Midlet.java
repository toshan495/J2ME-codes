/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bouncing_ball;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
/////////////////////////////////////////
public class Midlet extends MIDlet 
{
 
public void startApp() 
{
  Simulation game = new Simulation(); 
  Display.getDisplay(this).setCurrent(game);
  new Thread(game).start(); 
}
 
public void pauseApp() { }
public void destroyApp(boolean unconditional) { }
//////////////////////////////////////////////////////////////
   class Simulation extends GameCanvas implements Runnable {

      private int x,y;        // (x,y) co ordinates of center of box
      private int horvel = 3,  vervel = 3;  // velocity in x and y direction
      private int SIDE = 20; // side of box
      private int flag=0,key=0;
      public Simulation() 
      {super(false);}
///////////////////////////////////////////////// 
        protected void keyPressed(int keyCode) //function to control actions on key press
        {
       
        key = Integer.parseInt(getKeyName(keyCode));
        
            if(keyCode==KEY_NUM1) 
            {if(vervel>0 &&horvel>0)flag=1; 
            else if(vervel<0 &&horvel>0)flag=7; 
            else if(vervel>0 &&horvel<0) flag=3; 
            else flag=9; }
            else if(keyCode==KEY_NUM2) 
            {if(vervel>0 &&horvel>0)flag=2; 
            else if(vervel<0 &&horvel>0)flag=8; 
            else if(vervel>0 &&horvel<0) flag=2; 
            else flag=8; }
            else if(keyCode==KEY_NUM3) 
            {if(vervel>0 &&horvel>0)flag=3; 
            else if(vervel<0 &&horvel>0)flag=9; 
            else if(vervel>0 &&horvel<0) flag=1; 
            else flag=7; }
            else if(keyCode==KEY_NUM4) 
            {if(vervel>0 &&horvel>0)flag=4; 
            else if(vervel<0 &&horvel>0)flag=4; 
            else if(vervel>0 &&horvel<0) flag=6; 
            else flag=6; }
            else if(keyCode==KEY_NUM5) 
            {flag=5;}
            else if(keyCode==KEY_NUM6) 
            {if(vervel>0 &&horvel>0)flag=6; 
            else if(vervel<0 &&horvel>0)flag=6; 
            else if(vervel>0 &&horvel<0) flag=4; 
            else flag=4; }
            else if(keyCode==KEY_NUM7) 
            {if(vervel>0 &&horvel>0)flag=7; 
            else if(vervel<0 &&horvel>0)flag=1; 
            else if(vervel>0 &&horvel<0) flag=9; 
            else flag=3; }
            else if(keyCode==KEY_NUM8) 
            {if(vervel>0 &&horvel>0)flag=8; 
            else if(vervel<0 &&horvel>0)flag=2; 
            else if(vervel>0 &&horvel<0) flag=8; 
            else flag=2; }
            else if(keyCode==KEY_NUM9) 
            {if(vervel>0 &&horvel>0)flag=9; 
            else if(vervel<0 &&horvel>0)flag=3; 
            else if(vervel>0 &&horvel<0) flag=7; 
            else flag=1;}
            else {}
        }
      
 /////////////////////////////////////////////////     
      public void run() // Running box game
      {
         // find centre of screen:
         x = getWidth() / 2;   
         y = getHeight() / 2;

         //Draw graphics after retriving graphics buffer
         Graphics M = getGraphics();
         
         while (true) // Box movement loop
         { 
            int keyState = getKeyStates();
            if ((keyState & RIGHT_PRESSED) != 0) 
            {SIDE++;}//for increasing size of box
            if ((keyState & LEFT_PRESSED) != 0) 
            {SIDE--;}//for decreasing size of box
            if ((keyState & UP_PRESSED) != 0) 
            {if(vervel>0) {vervel++;} else vervel--; 
            if(horvel>0) {horvel++;} else horvel--;}//for increasing speed of box
            if ((keyState & DOWN_PRESSED) != 0) 
            {if(vervel>0) {vervel--;} else vervel++; 
            if(horvel>0) {horvel--;} else horvel++;}//for decreasing spped of box
            
         // minimum and maximum boundaries of the center of box
         int lowX = SIDE;
         int lowY = SIDE + 20; // 20=> height of info display area
         int highX = getWidth() - 1 - SIDE;
         int highY = getHeight() - 1 - SIDE;
            
            if(flag==1)
            {
              x -= horvel;
              y -= vervel;
            }
            if(flag==2)
            {y -= vervel;}
            
            if(flag==3)
            {
              x += horvel;
              y -= vervel;
            }
            if(flag==4)
            {x -= horvel;}
            
            if(flag==5) //place box at centre of screen
            {
              x =getWidth() / 2; 
              y = getHeight() / 2;
            }
            if(flag==6)
            {x += horvel;}
            
            if(flag==7)
            {                                 
              x -= horvel;
              y += vervel;
            }
            if(flag==8)
            {y += vervel; }
            
            if(flag==9)
            {
              x += horvel;
              y += vervel;
            }
            
// elastic collision of ball with boundary and subsequent reflection:
            if (x > highX) 
            {
               x = highX;
               horvel = -horvel;
            } 
            else if (x < lowX) 
            {
               x = lowX;
               horvel = -horvel;
            }
            if (y > highY) 
            {
               y = highY;
               vervel = -vervel;
            } 
            else if (y < lowY) 
            {
               y = lowY;
               vervel = -vervel;
            }
            // Draw mobile screen
            M.setColor(0x00FFFF);
            M.fillRect(0, 0, getWidth(), getHeight());
            // Display coordinates of box centre
            M.setColor(0xDDA0DD);  
            M.fillRect(0, 0, getWidth(), 20);// 20=> height of info display area
            M.setColor(0x8B0000);
            M.drawString("( LOCATION OF BOX:" + (x-SIDE) + "," + (y-(SIDE+20)) + ")", 4, 0, Graphics.TOP | Graphics.LEFT);
            // Draw box on screen:
            M.setColor(0xFF0000);
            M.drawRect( (x - SIDE)-1, (y - SIDE)-1, (2 * SIDE)+2, (2 * SIDE)+2 );
            M.setColor(0xADFF2F);
            M.fillRect(x - SIDE,y - SIDE, 2 * SIDE, 2 * SIDE);  
            
            flushGraphics();
 
       // delay time to refresh game information:
            try 
            {Thread.sleep(30);} // 30=> game update interval in milliseconds
            catch (InterruptedException e) {}
         }
      }
   }
}
