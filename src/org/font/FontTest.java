package org.font;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class FontTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new FontFrame();
			frame.setTitle("FontTest");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}

/**
 * A frame with a text message component
 */
@SuppressWarnings("serial")
class FontFrame extends JFrame {
	public FontFrame() {
		add(new FontComponent());
		pack();
	}
}

/**
 * A component that shows a centered message in a box
 */
@SuppressWarnings("serial")
class FontComponent extends JComponent {
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 300;
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		String message = "Hello World!";
		
		Font f = new Font("Serif", Font.BOLD, 65);
		g2.setFont(f);
		
		// measure the size of the message
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D bounds = f.getStringBounds(message, context);
		
		//  set (x, y) = top left corner of text
		double x = (getWidth() - bounds.getWidth()) / 2;
		double y = (getHeight() - bounds.getHeight()) / 2;
		
		// add ascent to y to reach the baseline
		double ascent = -bounds.getY();
		double baseY =  y + ascent;
		
		// set color to blue
		g2.setColor(Color.BLUE);

		// draw the message
		g2.drawString(message, (int) x, (int) baseY);
		
		// set color to red
		g2.setColor(Color.RED);
		
		// draw the baseline
		g2.draw(new Line2D.Double(x, baseY, x + bounds.getWidth(), baseY));
		
		// draw the enclosing rectangle 
		Rectangle2D rect = new Rectangle2D.Double(x, y, bounds.getWidth(), bounds.getHeight());
		g2.draw(rect);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}