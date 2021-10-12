package jlabyrinth.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JPanel;

import jlabyrinth.model.Model;
import jlabyrinth.model.Piece;
import jlabyrinth.controller.ControllerForView;

public class PreviewPanel extends JPanel implements KeyListener{
	
	//---------------------------------------------------------------
	// STATIC CONSTANTS
	//---------------------------------------------------------------
	
	private final static Dimension PREFERRED_SIZE = new Dimension(100 ,100);
	
	//---------------------------------------------------------------
	// INSTANCE ATTRIBUTES
	//---------------------------------------------------------------
	
	public PreviewPanel(){
		super();
		this.addKeyListener(this);
	}
	
	//---------------------------------------------------------------
	// PRIVATE INSTANCE METHODS
	//---------------------------------------------------------------
	
	//---------------------------------------------------------------
	// PUBLIC INSTANCE METHODS
	//---------------------------------------------------------------
	
	public void drawPreviewPiece(Graphics2D g2d) throws IOException {
		Piece preview = Model.getInstance().getRemovingPiece();
		//System.out.println(preview.toString());
		g2d.drawImage(preview.getImageOfPiece(), 0 , 0 , null);
		System.out.println(preview.toString());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Insert here our drawing
		Graphics2D g2d = (Graphics2D)g;
		try {
			this.drawPreviewPiece(g2d);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Dimension getMaximumSize() {
		return PREFERRED_SIZE;
	}

	@Override
	public Dimension getPreferredSize() {
		return PREFERRED_SIZE;
	}
	
	//-------------------------------------------------------------------------
	// To implement the interface java.awt.event.KeyListener
	//-------------------------------------------------------------------------
	/* Invoked when a key has been pressed. */

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_R:
			try {
				ControllerForView.getInstance().rotateRemovingPiece();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				System.out.println("Pressed key: VK_R");
				this.repaint();
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
