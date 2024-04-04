import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovingBall extends JFrame {
    private JPanel ballPanel;
    private JButton moveButton;
    private JButton stopButton;
    private JButton colorButton;
    private Ball ball;

    public MovingBall() {
        setTitle("Moving Ball");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ballPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (ball != null) {
                    g.setColor(ball.getColor());
                    g.fillOval(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());
                }
            }
        };

        moveButton = new JButton("Move");
        moveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ball != null && !ball.isMoving()) {
                    ball.startMoving();
                    moveBall();
                }
            }
        });

        stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ball != null && ball.isMoving()) {
                    ball.stopMoving();
                }
            }
        });

        colorButton = new JButton("Change Color");
        colorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ball != null) {
                    Color selectedColor = JColorChooser.showDialog(MovingBall.this, "Choose Color", ball.getColor());
                    if (selectedColor != null) {
                        ball.setColor(selectedColor);
                        ballPanel.repaint();
                        ColorFrame colorFrame = new ColorFrame(ball);
                        colorFrame.setVisible(true);
                    }
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(moveButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(colorButton);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(ballPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void moveBall() {
        Timer timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ball != null && ball.isMoving()) {
                    ball.move();
                    ballPanel.repaint();
                }
            }
        });
        timer.start();
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public static void main(String[] args) {
        MovingBall frame = new MovingBall();
        Ball ball = new Ball(10, 10, 30, Color.RED);
        frame.setBall(ball);
        frame.setVisible(true);
    }
}

class Ball {
    private int x;
    private int y;
    private int diameter;
    private Color color;
    private int xSpeed;
    private int ySpeed;
    private boolean isMoving;

    public Ball(int x, int y, int diameter, Color color) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.color = color;
        this.xSpeed = 1 + (int) (Math.random() * 5);
        this.ySpeed = 1 + (int) (Math.random() * 5);
        this.isMoving = false;
    }

    public void move() {
        x += xSpeed;
        y += ySpeed;

        if (x <= 0 || x >= (400 - diameter)) {
            xSpeed *= -1;
        }

        if (y <= 0 || y >= (400 - diameter)) {
            ySpeed *= -1;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDiameter() {
        return diameter;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void startMoving() {
        isMoving = true;
    }

    public void stopMoving() {
        isMoving = false;
    }

}

class ColorFrame extends JFrame {
    private Ball ball;

    public ColorFrame(Ball ball) {
        this.ball = ball;

        setTitle("Moving Ball - New Color");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel ballPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(ball.getColor());
                g.fillOval(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());
            }
        };

        Container contentPane = getContentPane();
        contentPane.add(ballPanel);

        Timer timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ball.move();
                ballPanel.repaint();
            }
        });
        timer.start();
    }
}
