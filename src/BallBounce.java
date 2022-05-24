
/**
 * THIS PROGRAM HAS A BALL THAT STARTS AND STOPS WHEN YOU PUSH THE 
 * START OR STOP BUTTON ON THE NEW WINDOW YOU CAN ALSO HIT CHANGE BUTTON
 * IT CHANGES THE DIRECTION AND WHENEVER IT HITS THE BOUNDRY IT CHANGES ITS 
 * DIRECTION REASONABLY.
 * 
 * ZOYACE SHRESTHA
 * CS-120 SECTION 2
 * FALL 2019
 * 
 * PROGRAMMING ASSIGNMENT 5
 * 30TH OCTOBER 2019
 * 
 *
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Random;

public class BallBounce implements ActionListener
{
	private JFrame window;
	private JButton moveButton;
	private JButton changeButton;
	private Timer timer;

	// VARIABLE DECLARARING FOR GLOBAL VARIAABLES
	private final int windowSize = 500;
	private int ballSize = 80;
	private Oval ball;
	private int direction;
	private int right = 1;
	private int left = 2;
	private int up = 3;
	private int down = 4;
	private int upright = 5;
	private int downright = 6;
	private int upleft = 7;
	private int downleft = 8;
	private int x;
	private int y;

	/**
	 * Simple initiating main().
	 *
	 * @param args Not used.
	 */

	public static void main(String[] args)
	{
		BallBounce d = new BallBounce();
		d.makeWindow();
	}

	/**
	 * Creates basic graphical window and GUI.
	 */
	private void makeWindow()
	{
		window = new JFrame();
		window.setTitle("Follow the Bouncing Blue Ball");
		window.setVisible(true);
		window.setLayout(null);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocation(50, 50);
		window.setSize(windowSize + window.getInsets().left + window.getInsets().right,
				windowSize + window.getInsets().top + window.getInsets().bottom);
		window.getContentPane().setBackground(Color.white);
		timer = new Timer(5, this);

		addButtons();
		addBall();
		setDirection();
		ball.setBackground(Color.BLUE);
		window.repaint();
	}

	/**
	 * Adds buttons to window.
	 */
	private void addButtons()
	{
		moveButton = new JButton("Move/Stop");
		moveButton.setBounds(windowSize / 2 - 150, windowSize - 35, 100, 25);
		moveButton.setText("Move/Stop");
		moveButton.addActionListener(this);
		window.add(moveButton);

		changeButton = new JButton("Change");
		changeButton.setBounds(windowSize / 2 + 50, windowSize - 35, 100, 25);
		changeButton.setText("Change");
		changeButton.addActionListener(this);
		window.add(changeButton);
	}

	/**
	 * Handles events created by the buttons and Timer object. Every time an event
	 * is received, we can use the method getSource() to check where it came from.
	 * For instance: if ( e.getSource() == timer ) will be true if and only if the
	 * timer has activated, and so any method called in response will repeat over
	 * and over as long as the timer is running.
	 */

	// TIMER USED AS SNAKE WRNGALER
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == moveButton)
		{
			if (timer.isRunning())
			{
				timer.stop();
				moveButton.setText("Move");
			} else
			{
				timer.start();
				moveButton.setText("Stop");
			}
		} else if (e.getSource() == timer)
		{
			checkBoundry();
			moveDirection();
		} else if (e.getSource() == changeButton)
		{
			setDirection();
		}
	}

	// PARAMETERS FOR THE THE OVAL
	private void addBall()
	{
		ball = new Oval(0, 0, 0, 0);
		ball.setSize(ballSize, ballSize);
		ball.setLocation(((windowSize / 2) - (ballSize / 2)), ((windowSize / 2) - (ballSize / 2)));
		ball.setBackground(Color.BLUE);
		window.add(ball);
	}

	// RANDOMLY GENRETING DIRECTION AS BY NUMMBERS AS SET AS ABOVE
	private void setDirection()
	{
		Random rand = new Random();
		direction = rand.nextInt(8) + 1;
	}

	// MOVING DIRECTION BY 1 PIXEL BY THE BALL
	private void moveDirection()
	{

		// MOVING RIGHT
		if (direction == right)
		{
			x = ballSize;
			y = ballSize;

			x = ball.getX() + 1;
			y = ball.getY();
			ball.setLocation(x, y);
		}

		// MOVING LEFT
		else if (direction == left)
		{
			x = ballSize;
			y = ballSize;

			x = ball.getX() - 1;
			y = ball.getY();
			ball.setLocation(x, y);
		}

		// MOVING UP
		else if (direction == up)
		{
			x = ballSize;
			y = ballSize;

			x = ball.getX();
			y = ball.getY() - 1;
			ball.setLocation(x, y);
		}

		// MOVING DOWN
		else if (direction == down)
		{
			x = ballSize;
			y = ballSize;

			x = ball.getX();
			y = ball.getY() + 1;
			ball.setLocation(x, y);
		}

		// MOVING UPRIGHT
		else if (direction == upright)
		{
			x = ballSize;
			y = ballSize;

			x = ball.getX() + 1;
			y = ball.getY() - 1;
			ball.setLocation(x, y);
		}

		// MOVING DOWNRIGHT
		else if (direction == downright)
		{
			x = ballSize;
			y = ballSize;

			x = ball.getX() + 1;
			y = ball.getY() + 1;
			ball.setLocation(x, y);
		}

		// MOVING UPLEFT
		else if (direction == upleft)
		{
			x = ballSize;
			y = ballSize;

			x = ball.getX() - 1;
			y = ball.getY() - 1;
			ball.setLocation(x, y);
		}

		// MOVING DOWNLEFT
		else if (direction == downleft)
		{
			x = ballSize;
			y = ballSize;

			x = ball.getX() - 1;
			y = ball.getY() + 1;
			ball.setLocation(x, y);
		}
	}

	// WHEN BALL HITS THE BOUNDRY
	private void checkBoundry()
	{
		// WHEN BALL HITS RIGHT BOUNDRY
		if ((direction == right) && (x > (windowSize - ballSize)))
		{
			direction = left;
		}

		// WHEN BALL HITS LEFT BOUNDRY
		else if ((direction == left) && (x < 0))
		{
			direction = right;
		}

		// WHEN BALL HITS UPPER BOUNDRY
		else if ((direction == up) && (y < 0))
		{
			direction = down;
		}

		// WHEN BALL HITS LOWER BOUNDRY
		else if ((direction == down) && (y > (windowSize - ballSize)))
		{
			direction = up;
		}

		// WHEN BALL HITS UPRIGHT BOUNDRY ON THE EDGE
		else if ((direction == upright) && (x > (windowSize - ballSize)) && (y < 0))
		{
			direction = downleft;
		}

		// WHEN BALL HITS UPRIGHT BOUNDRY ON Y AXIS
		else if ((direction == upright) && (x > (windowSize - ballSize)) && (y > 0))
		{
			direction = upleft;
		}

		// WHEN BALL HITS UPRIGHT BOUNDRY ON X AXIS
		else if ((direction == upright) && (x > 0) && (y < 0))
		{
			direction = downright;
		}

		// WHEN BALL HITS UPLEFT BOUNDRY ON THE EGDE
		else if ((direction == upleft) && (x < 0) && (y < 0))
		{
			direction = downright;
		}

		// WHEN BALL HITS UPLEFT BOUNDRY ON THE X AXIS
		else if ((direction == upleft) && (x > 0) && (y < 0))
		{
			direction = downleft;
		}

		// WHEN BALL HITS UPLEFT BOUNDRY ON Y AXIS
		else if ((direction == upleft) && (x < 0) && (y > 0))
		{
			direction = upright;
		}

		// WHEN BALL HITS DOWNRIGHT BOUNDRY ON EDGE
		else if ((direction == downright) && (x > windowSize) && (y > (windowSize - ballSize)))
		{
			direction = upleft;
		}

		// WHEN BALL HITS DOWNRIGHT BOUNDRY ON Y AXIS
		else if ((direction == downright) && (x > (windowSize - ballSize) && (y > 0)))
		{
			direction = downleft;
		}

		// WHEN BALL HITS DOWNRIGHT BOUNDRY ON X AXIS
		else if ((direction == downright) && (x > 0) && (y > (windowSize - ballSize)))
		{
			direction = upright;
		}

		// WHEN BALL HITS DOWNLEFT BOUNDRY ON THE EDGE
		else if ((direction == downleft) && (x < 0) && (y > (windowSize - ballSize)))
		{
			direction = upright;
		}

		// WHEN BALL HITS DOWNLEFT BOUNDRY ON THE Y AXIS
		else if ((direction == downleft) && (x < 0) && (y > 0))
		{
			direction = downright;
		}

		// WHEN BALL HITS DOWNLEFTBOUNDRY ON THE X AXIS
		else if ((direction == downleft) && (x > 0) && (y > (windowSize - ballSize)))
		{
			direction = upleft;
		}

	}
}
