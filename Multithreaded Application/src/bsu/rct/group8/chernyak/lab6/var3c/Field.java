package bsu.rct.group8.chernyak.lab6.var3c;

import java.awt.Color; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.util.ArrayList; 
import javax.swing.JPanel; 
import javax.swing.Timer; 
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

@SuppressWarnings("serial")
public class Field extends JPanel {

	// ���� ������������������ �������� 
	private boolean paused; 

	// ������������ ������ �������� ����� 
	private ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10);
	
	private double dragOffsetX;
	private double dragOffsetY;
	
	boolean isDragged;
	
	// ����� ������ �������� �� ���������� ��������� ������� ActionEvent 
	// ��� �������� ��� ���������� ������������ ��������� �����, 
	// ����������� ��������� ActionListener 
	private Timer repaintTimer = new Timer(10, new ActionListener() { 
		public void actionPerformed(ActionEvent event) { 
			// ������ ����������� ������� ActionEvent - ����������� ���� 
			repaint(); 
		} 
	});
		
	// ����������� ������ Field 
	public Field() { 
		// ���������� ���� ������� ���� ����� 
		setBackground(Color.WHITE); 
		// ��������� ������ 
		repaintTimer.start(); 
	}
	
	// �������������� �� JPanel ����� ����������� ���������� 
	public void paintComponent(Graphics g) { 
		// ������� ������ ������, �������������� �� ������ 
		super.paintComponent(g); 
		Graphics2D canvas = (Graphics2D) g; 
		// ��������������� ��������� ���������� �� ���� ����� �� ������ 
		for (BouncingBall ball : balls)  
			ball.paint(canvas); 
		
		Graphics2D path = (Graphics2D) g;
		Obstacle.paint(path);
	}
	
	// ����� ���������� ������ ���� � ������ 
	public void addBall() { 
		//����������� � ���������� � ������ ������ ���������� BouncingBall 
		// ��� ������������� ���������, ��������, �������, ����� 
		// BouncingBall ��������� ��� � ������������ 
		balls.add(new BouncingBall(this)); 
		addMouseMotionListener(new MouseMotionHandler());
		addMouseListener(new MouseHandler());
	}
	
	// ����� ������������������, �.�. ������ ���� ����� ����� 
	// ������������ ���� ������ 
	public synchronized void pause() { 
		// �������� ����� ����� 
		paused = true; 
	}
	
	// ����� ������������������, �.�. ������ ���� ����� ����� 
	// ������������ ���� ������ 
	public synchronized void resume() { 
		// ��������� ����� ����� 
		paused = false; 
		// ����� ��� ��������� ����������� ������ 
		notifyAll(); 
	}
	
	// ������������������ ����� ��������, ����� �� ��� ��������� 
	// (�� ������� �� ����� �����?) 
	public synchronized void canMove(BouncingBall ball) throws InterruptedException { 
		if (paused) { 
			// ���� ����� ����� �������, �� �����, �������� 
			// ������ ������� ������, �������� 
			wait(); 
		}
		
	}
	
	public class MouseHandler extends MouseAdapter {
		
		public MouseHandler() {
			
		}
		
		public void mouseClicked(MouseEvent e) {
			
		}
		
		public void mouseEntered(MouseEvent e) {
			
		}
		
		public void mouseExited(MouseEvent e) {
			
		}
		
		public void mousePressed(MouseEvent e) {
			if ((e.getModifiers() & MouseEvent.BUTTON2_MASK) == 0)
				if(Obstacle.contains(e.getX(), e.getY())) {
					isDragged = true;
					dragOffsetX = e.getX()-Obstacle.getX();
					dragOffsetY = e.getY()-Obstacle.getY();
				}
				else 
					isDragged = false;
			repaint();
		}
		
		public void mouseReleased(MouseEvent e) {
			
			}
	}
		
	
	public class MouseMotionHandler implements MouseMotionListener {
				
		public void mouseDragged(MouseEvent e){
			if (isDragged) {
			
				// ������������� ���� ��������������
				Obstacle.setPos(e.getX()-dragOffsetX, e.getY()-dragOffsetY);

			}
		}
		
		public void mouseMoved(MouseEvent e) {
			
		} 
		
	}

	
}
