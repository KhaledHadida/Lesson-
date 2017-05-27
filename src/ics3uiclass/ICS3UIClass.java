package ics3uiclass;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 *
 * @author Khaled
 */
public class ICS3UIClass extends JComponent {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    
    boolean downPressed;
    boolean upPressed;
    
    boolean wPressed;
    boolean sPressed;
    
    boolean pause = false;
    
    int player1Score = 0;
    int player2Score = 0;
    
    int velocity = 4;
    
    
    
    
    //Title of the window
    String title = "sds";

    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;


    // YOUR GAME VARIABLES WOULD GO HERE

    long eventTime = 5000;
    
    Rectangle player1 = new Rectangle(0,0,50,100);
    Rectangle player2 = new Rectangle(WIDTH-50, HEIGHT-100,50,100);
    
    
    Rectangle ball = new Rectangle(WIDTH/2, HEIGHT/2, 25,25);
    
    

    // GAME VARIABLES END HERE   

    
    // Constructor to create the Frame and place the panel in
    // You will learn more about this in Grade 12 :)
    public ICS3UIClass(){
        // creates a windows to show my game
        JFrame frame = new JFrame(title);

        // sets the size of my game
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(this);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);
        
        // add listeners for keyboard and mouse
        frame.addKeyListener(new Keyboard());
        Mouse m = new Mouse();
        
        this.addMouseMotionListener(m);
        this.addMouseWheelListener(m);
        this.addMouseListener(m);
    }
    
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE
        g.drawRect(player1.x, player1.y, player1.width, player1.height);
        
        g.drawRect(player2.x, player2.y, player2.width, player2.height);
        
         g.drawRect(ball.x, ball.y, ball.width, ball.height);
        
        // GAME DRAWING ENDS HERE
    }


    // This method is used to do any pre-setup you might need to do
    // This is run before the game loop begins!
    public void  preSetup(){
       // Any of your pre setup before the loop starts should go here

       
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        preSetup();

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            
            if(!pause){
            if(upPressed){
               
                player1.y = player1.y - 5;
            }
            if(downPressed){
                player1.y = player1.y + 5;
            }
            
             if(wPressed){
               
                player2.y = player2.y - 5;
            }
            if(sPressed){
                player2.y = player2.y + 5;
            }
           
            
            if(ball.intersects(ball)){
                
            }
            
            
            ball.x += 3;
            
            
            
            if(ball.x < 0){
                System.out.println("Player 2 scores");
                resetBall();
            }
            if( ball.x > WIDTH){
                System.out.println("Player 1 Scores");
                resetBall();
            }
            }
            
            // GAME LOGIC ENDS HERE 
            // update the drawing (calls paintComponent)
            repaint();

            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            try {
                if (deltaTime > desiredTime) {
                    //took too much time, don't wait
                    Thread.sleep(1);
                } else {
                    // sleep to make up the extra time
                    Thread.sleep(desiredTime - deltaTime);
                }
            } catch (Exception e) {
            };
        }
    }

    

    // Used to implement any of the Mouse Actions
    private class Mouse extends MouseAdapter {
        // if a mouse button has been pressed down
        @Override
        public void mousePressed(MouseEvent e){
            
        }
        
        // if a mouse button has been released
        @Override
        public void mouseReleased(MouseEvent e){
            
        }
        
        // if the scroll wheel has been moved
        @Override
        public void mouseWheelMoved(MouseWheelEvent e){
            
        }

        // if the mouse has moved positions
        @Override
        public void mouseMoved(MouseEvent e){
            
        }
    }
    
    // Used to implements any of the Keyboard Actions
    private class Keyboard extends KeyAdapter{
        // if a key has been pressed down
        @Override
        public void keyPressed(KeyEvent e){
            if( e.getKeyCode() ==  KeyEvent.VK_UP){
                upPressed = true;
                
            }
             if( e.getKeyCode() ==  KeyEvent.VK_DOWN){
                downPressed = true;
            }
             
             if( e.getKeyCode() ==  KeyEvent.VK_W){
                wPressed = true;
                
            }
             if( e.getKeyCode() ==  KeyEvent.VK_S){
                sPressed = true;
            }
             
             //to toggle the pause button 
              if( e.getKeyCode() ==  KeyEvent.VK_P){
                pause = !pause;
            }
            
        }
        
        // if a key has been released
        @Override
        public void keyReleased(KeyEvent e){
             if( e.getKeyCode() ==  KeyEvent.VK_UP){
                upPressed = false;
                
            }
               if( e.getKeyCode() ==  KeyEvent.VK_DOWN){
                downPressed = false;
            }
               
                if( e.getKeyCode() ==  KeyEvent.VK_W){
                wPressed = false;
                
            }
             if( e.getKeyCode() ==  KeyEvent.VK_S){
                sPressed = false;
            }  
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates an instance of my game
        ICS3UIClass game = new ICS3UIClass();
                
        // starts the game loop
        game.run();
    }
    
    
    public void resetBall(){
        ball.x = WIDTH/2;
        ball.y = HEIGHT/2;
    }
    
    
    
    
   
}
