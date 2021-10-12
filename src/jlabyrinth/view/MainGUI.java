

package jlabyrinth.view;

import ExampleImages.AppResources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import jlabyrinth.controller.ControllerForModel;
import jlabyrinth.controller.ControllerForView;
import jlabyrinth.model.Model;
import jlabyrinth.model.Piece;
import jlabyrinth.view.PreviewPanel;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public class MainGUI extends JFrame implements ComponentListener, ActionListener{
	
	//---------------------------------------------------------------
	// STATIC CONSTANTS
	//---------------------------------------------------------------
	
	private final static int WINDOW_PREFERRED_WIDTH = 870;
	private final static int WINDOW_PREFERRED_HEIGHT = 750;
	
	//---------------------------------------------------------------
	// INSTANCE ATTRIBUTES
	//---------------------------------------------------------------
	private BoardPanel boardPanel;
	private PreviewPanel previewPanel;
	
	private JButton menuBut;
	private JButton startPauseBut;
	private JButton rotateBut;
	private JButton turnBut;
	private JButton exitBut;
	private JLabel playerNameLab;
	private JLabel playerNamePrefixLab;
	private JLabel currentTargetLab;
	private JLabel player1ScoreLab;
	private JLabel player2ScoreLab;
	private JLabel playerScorePrefixLab;
	private JLabel nextPiecePrefixLab;
	private JPanel rightPanel;
	
	
	
	private boolean isGameStarted; // a game can start only once at the beginning
	private boolean isGameRunning; // a started game can be running or in pause
	private int turnCounter=ControllerForView.getInstance().getTurn();
	
	public MainGUI() throws IOException {
		super("JLabyrinth");
		this.createGUI();
		this.isGameStarted = false;
		this.isGameRunning = false;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width/2)-(this.getWidth()/2),(screenSize.height/2)-(this.getHeight()/2));
	}
	
	//---------------------------------------------------------------
	// PRIVATE INSTANCE METHODS
	//---------------------------------------------------------------
	
	private void createGUI() throws IOException{
		
		this.addComponentListener(this);
		this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(ControllerForView.getInstance().getBoardDimentions()*100+40+150,ControllerForView.getInstance().getBoardDimentions()*100+40));
		this.setResizable(false);
		this.boardPanel = new BoardPanel();
		this.setRightPanel();
		Container contPane = this.getContentPane();
		contPane.setLayout(new BorderLayout());
		contPane.add(this.boardPanel, BorderLayout.CENTER);
		contPane.add(this.rightPanel, BorderLayout.EAST);
		this.pack();
		
	}

	private void setRightPanel() throws IOException {
		this.rightPanel = new JPanel();
		this.rightPanel.setBackground(new java.awt.Color(254,86,17));
		this.rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		this.rightPanel.setBorder(BorderFactory.createEmptyBorder(10 ,10, 10, 10));
		this.rightPanel.setPreferredSize(new Dimension(180,ControllerForView.getInstance().getBoardDimentions()*100+40));
		this.previewPanel = new PreviewPanel();
		this.rotateBut = new JButton("Rotate");
		this.rotateBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rotatePreviewEvent();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		this.turnBut = new JButton("ChangeTurn");
		this.turnBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					changeTurnEvent();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				repaint();
			}
		});
		this.exitBut = new JButton("Exit");
		this.exitBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveAndExitEvent();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		this.rotateBut.setMnemonic(KeyEvent.VK_R);
		//this.previewPanel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

		this.rotateBut.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
		this.rotateBut.setAlignmentY(java.awt.Component.CENTER_ALIGNMENT);
		this.rightPanel.add(this.previewPanel);
		this.previewPanel.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
		this.previewPanel.setSize(140, HEIGHT);
		this.rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
		//this.rightPanel.add(Box.createVerticalGlue());
		this.rightPanel.add(this.rotateBut);
		this.rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
		this.rightPanel.add(this.turnBut);
		this.rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		this.currentTargetLab=new JLabel("Target: "+ControllerForView.getInstance().getCurrentTarget());
		this.rightPanel.add(currentTargetLab);
		this.rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
		this.player1ScoreLab = new JLabel((ControllerForView.getInstance().getPlayer1Name()) + " = " + ControllerForView.getInstance().getScore(0));
		if(ControllerForView.getInstance().getTurn()==1){
			this.player1ScoreLab.setForeground(Color.RED);
		}	
		this.rightPanel.add(this.player1ScoreLab);
		this.rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
		this.player2ScoreLab = new JLabel((ControllerForView.getInstance().getPlayer2Name()) + " = " + ControllerForView.getInstance().getScore(1));
		if(ControllerForView.getInstance().getTurn()==2){
			this.player2ScoreLab.setForeground(Color.GREEN);
		}	
		this.rightPanel.add(this.player2ScoreLab);
		if(ControllerForView.getInstance().getBoardDimentions()==7){
			this.rightPanel.add(Box.createRigidArea(new Dimension(0,400)));
		}
		else{
			this.rightPanel.add(Box.createRigidArea(new Dimension(0,200)));
		}
		this.rightPanel.add(this.exitBut);
	}
	
	public void rotatePreviewEvent() throws IOException{
		ControllerForView.getInstance().rotateRemovingPiece();
		this.previewPanel.repaint();
	}
	
	public void changeTurnEvent() throws IOException{
		if(boardPanel.getEndFirstPhase()==true){
			turnCounter ++;
			if (turnCounter>2){
				turnCounter=1;
			}
			ControllerForView.getInstance().isOnTarget();
			System.out.println("Player1: "+ControllerForView.getInstance().getScore(0));
			System.out.println("Player2: "+ControllerForView.getInstance().getScore(1));
			System.out.println("isOnTarget-changeTurnEvent-MainGUI");
			ControllerForView.getInstance().setTurn(turnCounter);
			System.out.println("Turn: " +turnCounter);
			boardPanel.setConditions(false);
		}else{
			//DO NOTHING
		}
	}
	
	public void saveAndExitEvent() throws IOException{
		if(!boardPanel.getEndFirstPhase() && !boardPanel.getEndSecondPhase()){
			View.getInstance().showExitMessage();
		}	
		else{
			View.getInstance().showCannotSaveNowMessage();
		}
	}
	
	public void updateScoreAndTargetLabel(int score1, int score2, String target) throws IOException{
		this.player1ScoreLab.setText(ControllerForView.getInstance().getPlayer1Name()+" = "+score1);
		if(this.turnCounter==1){
			this.player1ScoreLab.setForeground(Color.RED);
		}
		else{
			this.player1ScoreLab.setForeground(Color.BLACK);
		}
		this.player2ScoreLab.setText(ControllerForView.getInstance().getPlayer2Name()+" = "+score2);
		if(this.turnCounter==2){
			this.player2ScoreLab.setForeground(Color.GREEN);
		}
		else{
			this.player2ScoreLab.setForeground(Color.BLACK);
		}
		this.currentTargetLab.setText("Target: "+target);
	}
	
	public void insertPiece() throws IOException{
		ControllerForView.getInstance().insertPiece(0,1);
		this.boardPanel.repaint();
		this.previewPanel.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.previewPanel.repaint();
		this.boardPanel.repaint();
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public class BoardPanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener{
		
		//---------------------------------------------------------------
		// STATIC CONSTANTS
		//---------------------------------------------------------------
		private final static int CELL_SIZE = 100; // number of pixels
		
		
		private final static int X_MARGIN = 10;
		private final static int Y_MARGIN = 10;
		private final static int DX=1;
		private final static int DY=1;
		private final static int CICLE_ANIMATION=100/DX;

		//---------------------------------------------------------------
		// INSTANCE ATTRIBUTES
		//---------------------------------------------------------------
		public double uX;
		public double uY;
		private Ellipse2D.Double circle;
		private Ellipse2D.Double circle2;
		private boolean isDraggable= false;
		private boolean isInside=false;
		private boolean canPass = false;
		private boolean pawnMoved = true;
		private boolean pieceInserted = false;
		private boolean endFirstPhase = false;
		private boolean endSecondPhase = false;
		private boolean activedAnimation=false;
		private int currentX;
		private int currentY;
		AudioStream audioStream;
		
		
		
		////CHECK AGGIUNTO////
		private boolean checkPawn = false;
		//////////////////////
		

		public BoardPanel() throws IOException {
			super();
			this.circle = new Ellipse2D.Double();
			this.circle2 = new Ellipse2D.Double();
			this.setBackground(Color.white);
			this.addKeyListener(this);
			this.addMouseListener(this);
			this.addMouseMotionListener(this);
		}

		//---------------------------------------------------------------
		// PRIVATE INSTANCE METHODS
		//---------------------------------------------------------------
	
	public void setConditions(boolean condition){
		this.pieceInserted= condition;
		this.endFirstPhase=condition;
		this.endSecondPhase=condition;
		this.checkPawn = condition;
	}
	
	public boolean getEndFirstPhase(){
		return this.endFirstPhase;
	}
	
	public boolean getEndSecondPhase(){
		return this.endSecondPhase;
	}
	
	public void drawAllSigns(){
		Graphics2D g2d=(Graphics2D)this.getGraphics();
		if(this.endFirstPhase == true && this.endSecondPhase== false){
			try {
				ControllerForView.getInstance().setFalseAllReachability();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int i=0;
			int j=0;
			Piece[][] BoA=null;
			try {
				if(ControllerForView.getInstance().getTurn()==1){
					i = ControllerForView.getInstance().getPawnCurrentPositionX(0);
					j = ControllerForView.getInstance().getPawnCurrentPositionY(1);
					System.out.println("i e j :" +i+","+j);
				}
				else{
					i = ControllerForView.getInstance().getPawnCurrentPositionX(2);
					j = ControllerForView.getInstance().getPawnCurrentPositionY(3);
					System.out.println("i e j :" +i+","+j);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ControllerForView.getInstance().reachabilityFinder(i, j);
				BoA = ControllerForView.getInstance().getBoardArray();
				System.out.println("Reachability finder eseguito");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				for(int h=0; h<ControllerForView.getInstance().getBoardArray().length; h++){
					for(int k=0; k<ControllerForView.getInstance().getBoardArray().length; k++){
						if(ControllerForView.getInstance().getBoardArray()[h][k].getReachability() == true){
							try {
								//System.out.println("Piece "+h+ ","+k+" reachable ,visited= "+BoA[h][k].getVisited());
								drawSign(g2d ,h, k);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
						
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void drawImageAtCell(Graphics2D g2d, int i, int j, Piece piece){
		g2d.drawImage(piece.getImageOfPiece(), i, j, null);
	}
	
		
	public void drawBoardGame(Graphics2D g2d) throws IOException{
		Piece[][] boardGame =ControllerForView.getInstance().getBoardArray();
		for (int i=0; i<ControllerForView.getInstance().getBoardDimentions(); i++){ //Scansione Colonne
			for (int j=0; j<ControllerForView.getInstance().getBoardDimentions(); j++){ //Scansione Righe
				drawImageAtCell(g2d, i*CELL_SIZE, j*CELL_SIZE, boardGame[i][j]);
			}
		}
	}
	
	private void drawPawnOnPiece(Graphics2D g2d, int i, int j, int k, int h) throws IOException{
		//getColor metodo di pawn
			g2d.setColor(Color.RED);
			this.circle.setFrame(35+(i*100), 35+(j*100), 30, 30);
			g2d.fill(this.circle);
			g2d.draw(this.circle);
			g2d.setColor(Color.GREEN);
			this.circle2.setFrame(35+(k*100), 35+(h*100), 30, 30);
			g2d.fill(this.circle2);
			g2d.draw(this.circle2);
	}
	
	private void drawArrowOnPiece(Graphics2D g2d, int i, int j, String str) throws IOException{
		Image img = ImageIO.read(AppResources.class.getResource(str));
		i = (i*100)+10;
		j = (j*100)+10;
		g2d.drawImage(img, i, j, null);
	}
	
	private void drawSign(Graphics2D g2d, int i, int j) throws IOException{
		Image img = ImageIO.read(AppResources.class.getResource("blackball2.png"));
		i = (i*100)+30;
		j = (j*100)+30;
		g2d.drawImage(img, i, j, null);
	}
	
	private void Animation(int x, int y) throws InterruptedException, IOException, URISyntaxException{
		
		Piece[][] BoA= ControllerForView.getInstance().getBoardArray();
		Graphics2D g2d=(Graphics2D)this.getGraphics();
	    Thread t = Thread.currentThread();
	    t.setName("Thread animazione");
	    t.setPriority(10);
        playMusic("SpostamentoCelle1.wav");
	    try{
			for(int i = 0; i<CICLE_ANIMATION; i++){
//				PRIMA COLONNA
				if(x==0){
					this.drawImageAtCell(g2d, -100+DX*i, y*100, BoA[0][y] );
					this.drawImageAtCell(g2d, DX*i, y*100, BoA[1][y]);
					this.drawImageAtCell(g2d, 100+DX*i, y*100, BoA[2][y]);
					this.drawImageAtCell(g2d, 200+DX*i, y*100, BoA[3][y]);
					this.drawImageAtCell(g2d, 300+DX*i, y*100, BoA[4][y]);
					if(ControllerForView.getInstance().getBoardDimentions()==7){
						this.drawImageAtCell(g2d, 400+DX*i, y*100, BoA[5][y]);
						this.drawImageAtCell(g2d, 500+DX*i, y*100, BoA[6][y]);
						this.drawImageAtCell(g2d, 600+DX*i, y*100, ControllerForView.getInstance().getRemovingPiece());
					}
					else{
						this.drawImageAtCell(g2d, x+400+DX*i, y*100, ControllerForView.getInstance().getRemovingPiece());
					}
				}
//				ULTIMA COLONNA
				if(x==ControllerForView.getInstance().getBoardDimentions()-1){
					if(ControllerForView.getInstance().getBoardDimentions()==7){
						this.drawImageAtCell(g2d, 700-DX*i, y*100, BoA[6][y]);
						this.drawImageAtCell(g2d, 600-DX*i, y*100, BoA[5][y]);
					}
					this.drawImageAtCell(g2d, 500-DX*i, y*100, BoA[4][y]);
					this.drawImageAtCell(g2d, 400-DX*i, y*100, BoA[3][y]);
					this.drawImageAtCell(g2d, 300-DX*i, y*100, BoA[2][y]);
					this.drawImageAtCell(g2d, 200-DX*i, y*100, BoA[1][y]);
					this.drawImageAtCell(g2d, 100-DX*i, y*100, BoA[0][y]);
					this.drawImageAtCell(g2d, -DX*i, y*100, ControllerForView.getInstance().getRemovingPiece());
				}
//				PRIMA RIGA
				if(y==0){
					this.drawImageAtCell(g2d, x*100, -100+DY*i, BoA[x][0] );
					this.drawImageAtCell(g2d, x*100,DY*i, BoA[x][1]);
					this.drawImageAtCell(g2d, x*100,100+DY*i,BoA[x][2]);
					this.drawImageAtCell(g2d, x*100,200+DY*i,BoA[x][3]);
					this.drawImageAtCell(g2d, x*100,300+DY*i, BoA[x][4]);
					if(ControllerForView.getInstance().getBoardDimentions()==7){
						this.drawImageAtCell(g2d, x*100,400+DY*i, BoA[x][5]);
						this.drawImageAtCell(g2d, x*100,500+DY*i, BoA[x][6]);
						this.drawImageAtCell(g2d, x*100,600+DY*i, ControllerForView.getInstance().getRemovingPiece());
					}
					else{
						this.drawImageAtCell(g2d, x*100, 400+DY*i, ControllerForView.getInstance().getRemovingPiece());
					}
				}
//				ULTIMA RIGA
				if(y==ControllerForView.getInstance().getBoardDimentions()-1){
					if(ControllerForView.getInstance().getBoardDimentions()==7){
						this.drawImageAtCell(g2d, x*100,700-DY*i, BoA[x][6]);
						this.drawImageAtCell(g2d, x*100,600-DY*i, BoA[x][5]);
					}
					this.drawImageAtCell(g2d, x*100,500-DY*i,  BoA[x][4]);
					this.drawImageAtCell(g2d, x*100,400-DY*i,  BoA[x][3]);
					this.drawImageAtCell(g2d, x*100,300-DY*i,  BoA[x][2]);
					this.drawImageAtCell(g2d, x*100,200-DY*i,  BoA[x][1]);
					this.drawImageAtCell(g2d, x*100,100-DY*i,  BoA[x][0]);
					this.drawImageAtCell(g2d, x*100,-DY*i, ControllerForView.getInstance().getRemovingPiece());
				}
				System.out.println("Animation :" +i+", x: "+x+" y: "+y);
				System.out.println("Animation-AFTERWAIT :" +i+", x: "+x+" y: "+y);
				this.repaint();
				this.activedAnimation=false;
				t.sleep(10);
			}
	    }
	    catch (InterruptedException e){
	    	System.out.println("Thread Interrotto");
	    }
	    AudioPlayer.player.stop(audioStream);
	}
	
	
	//Tramite questo metodo avvio la riproduzione di un file mp3
	public void playMusic(String str) throws MalformedURLException, URISyntaxException {
		 try {
            InputStream inputStream = AppResources.class.getResourceAsStream(str);
            this.audioStream = new AudioStream(inputStream);
            AudioPlayer.player.start(audioStream);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Audio file not found!");
        }
    }

	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Insert here our drawing
		Graphics2D g2d = (Graphics2D)g;
		try {
			if(this.activedAnimation==false){
				this.drawBoardGame(g2d);
			}	
			if (isDraggable==false){
				if(this.pawnMoved == true){
						this.drawPawnOnPiece(g2d, ControllerForView.getInstance().getPawnCurrentPositionX(0) , ControllerForView.getInstance().getPawnCurrentPositionY(1),ControllerForView.getInstance().getPawnCurrentPositionX(2) , ControllerForView.getInstance().getPawnCurrentPositionY(3));
						//System.out.println("Nuove Coordinate1: "+ControllerForView.getInstance().getPawnCurrentPositionX(0)+" ,"+ControllerForView.getInstance().getPawnCurrentPositionY(1) + "Nuove Coordinate2: "+ControllerForView.getInstance().getPawnCurrentPositionX(2)+" ,"+ControllerForView.getInstance().getPawnCurrentPositionY(3));
					}
			    }
			else{
				if(ControllerForView.getInstance().getTurn()==1){
					g2d.setColor(Color.GREEN);
					this.circle2.setFrame(35+(ControllerForView.getInstance().getPawnCurrentPositionX(2)*100), 35+(ControllerForView.getInstance().getPawnCurrentPositionX(3)*100), 30, 30);
					g2d.fill(this.circle2);
					g2d.draw(this.circle2);
				}
				else{
					g2d.setColor(Color.RED);
					this.circle.setFrame(35+(ControllerForView.getInstance().getPawnCurrentPositionX(0)*100), 35+(ControllerForView.getInstance().getPawnCurrentPositionX(1)*100), 30, 30);
					g2d.fill(this.circle);
					g2d.draw(this.circle);
				}	
			}
			if(this.activedAnimation==true){
				try {
					Animation(currentX, currentY);
				} catch (InterruptedException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/////DISEGNO DI FRECCE PER CAPIRE L'INSERIMENTO
		
		if(this.endFirstPhase==false){
			Piece[][] BoA = null;
			try {
				BoA = ControllerForView.getInstance().getBoardArray();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(int i = 0 ; i<BoA.length; i++){
					if(BoA[0][i].getPieceType()==1 || i%2!=0){
							try {
								drawArrowOnPiece(g2d, 0, i,"freccia.png");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
			        if(BoA[i][0].getPieceType()==1 || i%2!=0 ){
						try {
							drawArrowOnPiece(g2d, i, 0,"freccia1.png");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
					if(BoA[BoA.length-1][i].getPieceType()==1 || i%2!=0 ){
						try {
							drawArrowOnPiece(g2d, (BoA.length-1), i,"freccia2.png");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(BoA[i][BoA.length-1].getPieceType()==1 || i%2!=0 ){
						try {
							drawArrowOnPiece(g2d, i, BoA.length-1,"freccia3.png");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			
			}
		}
		
		/////DISEGNO DEI PALLUCCHI PER REACHABLE
		
		if(this.endFirstPhase == true && this.endSecondPhase== false){
			try {
				ControllerForView.getInstance().setFalseAllReachability();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int i=0;
			int j=0;
			Piece[][] BoA=null;
			try {
				if(ControllerForView.getInstance().getTurn()==1){
					i = ControllerForView.getInstance().getPawnCurrentPositionX(0);
					j = ControllerForView.getInstance().getPawnCurrentPositionY(1);
					System.out.println("i e j :" +i+","+j);
				}
				else{
					i = ControllerForView.getInstance().getPawnCurrentPositionX(2);
					j = ControllerForView.getInstance().getPawnCurrentPositionY(3);
					System.out.println("i e j :" +i+","+j);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ControllerForView.getInstance().reachabilityFinder(i, j);
				BoA = ControllerForView.getInstance().getBoardArray();
				System.out.println("Reachability finder eseguito");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				for(int h=0; h<ControllerForView.getInstance().getBoardArray().length; h++){
					for(int k=0; k<ControllerForView.getInstance().getBoardArray().length; k++){
						if(ControllerForView.getInstance().getBoardArray()[h][k].getReachability() == true){
							try {
								//System.out.println("Piece "+h+ ","+k+" reachable ,visited= "+BoA[h][k].getVisited());
								drawSign(g2d ,h, k);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
						
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
		
	
	public boolean isInsertionAllowed(){
		return this.isInsertionAllowed();
	}
	
	
	//-------------------------------------------------------------------------
	// To implement the interface java.awt.event.KeyListener
	//-------------------------------------------------------------------------
		
	public void keyPressed(KeyEvent e){
		
	}
	
	public void keyReleased(KeyEvent e){
		//to do
	}
	
	public void keyTyped(KeyEvent e){
		//to do
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int posX = e.getX()/100;
		int posY =	e.getY()/100;
		this.currentX=posX;
		this.currentY=posY;
		
		////FA VEDERE QUALE E' IL PEZZO CLICCATO////
		Piece[][] bA=null;
		try {
			bA = Model.getInstance().getBoardArray();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
		System.out.println("Piece Clicked: \n" + bA[posX][posY].toString());
		/////////////////////////////////////////////
		
		
		//System.out.println("Mouse Clicked ON");
		if(this.endFirstPhase==false && this.checkPawn == false){
			//PRIMA RIGA
			if(e.getY() >= 0 && e.getY() <=100){
				if(e.getX() >= 100 && e.getX() <=200 ){
					try {
						ControllerForView.getInstance().insertPiece(posX, posY);
						this.pieceInserted=true;
						this.endFirstPhase = true;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.activedAnimation=true;
				}
				if(e.getX() >= 300 && e.getX() <=400 ){
					try {
						ControllerForView.getInstance().insertPiece(posX, posY);
						this.pieceInserted=true;
						this.endFirstPhase = true;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.activedAnimation=true;
				}
				if(e.getX() >= 500 && e.getX() <=600 ){
					try {
						ControllerForView.getInstance().insertPiece(posX, posY);
						this.pieceInserted=true;
						this.endFirstPhase = true;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.activedAnimation=true;
				}
				
				////// SPOSTAMENTO EVENTUALE DELLA PEDINA VERSO IL BASSO/////
				
				if(this.pieceInserted == true){
					try {
						if(ControllerForView.getInstance().getPiece(posX, ControllerForView.getInstance().getPawnCurrentPositionY(1)+1) != null){
							if(ControllerForView.getInstance().getPawnCurrentPositionX(0) == posX){
								ControllerForView.getInstance().setPosPlayer1(posX, ControllerForView.getInstance().getPawnCurrentPositionY(1)+1);
								System.out.println("Pedina 1 mossa verso il basso");
								this.checkPawn = true;
							}
						}
						else if(ControllerForView.getInstance().getPiece(posX, ControllerForView.getInstance().getPawnCurrentPositionY(1)+1) == null){
							if(ControllerForView.getInstance().getPawnCurrentPositionX(0) == posX){
								ControllerForView.getInstance().setPosPlayer1(posX, 0);;
								System.out.println("Pedina 1 riportata in cima");
								this.checkPawn = true;
							}
						}
						if(ControllerForView.getInstance().getPiece(posX, ControllerForView.getInstance().getPawnCurrentPositionY(3)+1) != null){
							if(ControllerForView.getInstance().getPawnCurrentPositionX(2) == posX){
								ControllerForView.getInstance().setPosPlayer2(posX, ControllerForView.getInstance().getPawnCurrentPositionY(3)+1);;
								System.out.println("Pedina 2 mossa verso il basso");
								this.checkPawn = true;
							}
						}
						else if(ControllerForView.getInstance().getPiece(posX, ControllerForView.getInstance().getPawnCurrentPositionY(3)+1) == null){
							if(ControllerForView.getInstance().getPawnCurrentPositionX(2) == posX){
								ControllerForView.getInstance().setPosPlayer2(posX, 0);
								System.out.println("Pedina 2 riportata in cima");
								this.checkPawn = true;
							}
						}
						
						this.checkPawn = true;
					
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			/////////////////////////////////////////////////////////////
				
					
			}
			//PRIMA COLONNA
			if(e.getX() >= 0 && e.getX() <=100){
				if(e.getY() >= 100 && e.getY() <=200 ){
					try {
						ControllerForView.getInstance().insertPiece(posX, posY);
						this.pieceInserted=true;
						this.endFirstPhase = true;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.activedAnimation=true;
				}
				if(e.getY() >= 300 && e.getY() <=400 ){
					try {
						ControllerForView.getInstance().insertPiece(posX, posY);
						this.pieceInserted=true;
						this.endFirstPhase = true;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.activedAnimation=true;
				}
				if(e.getY() >= 500 && e.getY() <=600 ){
					try {
						ControllerForView.getInstance().insertPiece(posX, posY);
						this.pieceInserted=true;
						this.endFirstPhase = true;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.activedAnimation=true;
				}
				
			////// SPOSTAMENTO EVENTUALE DELLA PEDINA VERSO DESTRA/////
				
			if(this.pieceInserted == true){
				try {
					if(ControllerForView.getInstance().getPiece(ControllerForView.getInstance().getPawnCurrentPositionX(0)+1, posY) != null){
						if(ControllerForView.getInstance().getPawnCurrentPositionY(1) == posY){
							ControllerForView.getInstance().setPosPlayer1(ControllerForView.getInstance().getPawnCurrentPositionX(0)+1, posY);
							System.out.println("Pedina 1 mossa verso destra");
							this.checkPawn = true;
						}
					}
					else if(ControllerForView.getInstance().getPiece(ControllerForView.getInstance().getPawnCurrentPositionX(0)+1, posY) == null){
						if(ControllerForView.getInstance().getPawnCurrentPositionY(1) == posY){
							ControllerForView.getInstance().setPosPlayer1(0, posY);
							System.out.println("Pedina 1 riportata a sinistra");
							this.checkPawn = true;
						}
					}
					if(ControllerForView.getInstance().getPiece(ControllerForView.getInstance().getPawnCurrentPositionX(2)+1, posY) != null){
						if(ControllerForView.getInstance().getPawnCurrentPositionY(3) == posY){
							ControllerForView.getInstance().setPosPlayer2(ControllerForView.getInstance().getPawnCurrentPositionX(2)+1, posY);;
							System.out.println("Pedina 2 mossa verso destra");
							this.checkPawn = true;
						}
					}
					else if(ControllerForView.getInstance().getPiece(ControllerForView.getInstance().getPawnCurrentPositionX(2)+1, posY) == null){
						if(ControllerForView.getInstance().getPawnCurrentPositionY(3) == posY){
							ControllerForView.getInstance().setPosPlayer2(0, posY);
							System.out.println("Pedina 2 riportata a sinistra");
							this.checkPawn = true;
						}
					}
					
					this.checkPawn = true;
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		    /////////////////////////////////////////////////////////////
			
			
			}
			
			//ULTIMA RIGA
			try {
				if(e.getY() >=((ControllerForView.getInstance().getBoardDimentions()-1)*100) && e.getY() <=((ControllerForView.getInstance().getBoardDimentions())*100)){
					if(e.getX() >= 100 && e.getX() <=200 ){
						try {
							ControllerForView.getInstance().insertPiece(posX, posY);
							this.pieceInserted=true;
							this.endFirstPhase = true;
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						this.activedAnimation=true;
					}
					if(e.getX() >= 300 && e.getX() <=400 ){
						try {
							ControllerForView.getInstance().insertPiece(posX, posY);
							this.pieceInserted=true;
							this.endFirstPhase = true;
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						this.activedAnimation=true;
					}
					if(e.getX() >= 500 && e.getX() <=600 ){
						try {
							ControllerForView.getInstance().insertPiece(posX, posY);
							this.pieceInserted=true;
							this.endFirstPhase = true;
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						this.activedAnimation=true;
					}
					
		////// SPOSTAMENTO EVENTUALE DELLA PEDINA VERSO L'ALTO/////
			
			if(this.pieceInserted == true){
				try {
					if(ControllerForView.getInstance().getPiece(posX, ControllerForView.getInstance().getPawnCurrentPositionY(1)-1) != null){
						if(ControllerForView.getInstance().getPawnCurrentPositionX(0) == posX){
							ControllerForView.getInstance().setPosPlayer1(posX, ControllerForView.getInstance().getPawnCurrentPositionY(1)-1);
							System.out.println("Pedina 1 mossa verso l'alto");
							this.checkPawn = true;
						}
					}
					else if(ControllerForView.getInstance().getPiece(posX, ControllerForView.getInstance().getPawnCurrentPositionY(1)-1) == null){
						if(ControllerForView.getInstance().getPawnCurrentPositionX(0) == posX){
							ControllerForView.getInstance().setPosPlayer1(posX, ControllerForView.getInstance().getBoardDimentions()-1);;
							System.out.println("Pedina 1 riportata in basso");
							this.checkPawn = true;
						}
					}
					if(ControllerForView.getInstance().getPiece(posX, ControllerForView.getInstance().getPawnCurrentPositionY(3)-1) != null){
						if(ControllerForView.getInstance().getPawnCurrentPositionX(2) == posX){
							ControllerForView.getInstance().setPosPlayer2(posX, ControllerForView.getInstance().getPawnCurrentPositionY(3)-1);;
							System.out.println("Pedina 2 mossa verso l'alto");
							this.checkPawn = true;
						}
					}
					else if(ControllerForView.getInstance().getPiece(posX, ControllerForView.getInstance().getPawnCurrentPositionY(3)-1) == null){
						if(ControllerForView.getInstance().getPawnCurrentPositionX(2) == posX){
							ControllerForView.getInstance().setPosPlayer2(posX, ControllerForView.getInstance().getBoardDimentions()-1);
							System.out.println("Pedina 2 riportata in basso");
							this.checkPawn = true;
						}
					}
					
					this.checkPawn = true;
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		/////////////////////////////////////////////////////////////
						
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		//ULTIMA COLONNA
		try {
			if(e.getX() >= ((ControllerForView.getInstance().getBoardDimentions()-1)*100) && e.getX() <=((ControllerForView.getInstance().getBoardDimentions())*100)){
				if(e.getY() >= 100 && e.getY() <=200 ){
					try {
						ControllerForView.getInstance().insertPiece(posX, posY);
						this.pieceInserted=true;
						this.endFirstPhase = true;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.activedAnimation=true;
				}
				if(e.getY() >= 300 && e.getY() <=400 ){
					try {
						ControllerForView.getInstance().insertPiece(posX, posY);
						this.pieceInserted=true;
						this.endFirstPhase = true;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.activedAnimation=true;
				}
				if(e.getY() >= 500 && e.getY() <=600 ){
					try {
						ControllerForView.getInstance().insertPiece(posX, posY);
						this.pieceInserted=true;
						this.endFirstPhase = true;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.activedAnimation=true;
				}
				
	    ///// SPOSTAMENTO EVENTUALE DELLA PEDINA VERSO SINISTRA/////
		
		if(this.pieceInserted == true){
			try {
				if(ControllerForView.getInstance().getPiece(ControllerForView.getInstance().getPawnCurrentPositionX(0)-1, posY) != null){
					if(ControllerForView.getInstance().getPawnCurrentPositionY(1) == posY){
						ControllerForView.getInstance().setPosPlayer1(ControllerForView.getInstance().getPawnCurrentPositionX(0)-1, posY);
						System.out.println("Pedina 1 mossa verso sinistra");
						this.checkPawn = true;
					}
				}
				else if(ControllerForView.getInstance().getPiece(ControllerForView.getInstance().getPawnCurrentPositionX(0)-1, posY) == null){
					if(ControllerForView.getInstance().getPawnCurrentPositionY(1) == posY){
						ControllerForView.getInstance().setPosPlayer1(ControllerForView.getInstance().getBoardDimentions()-1, posY);
						System.out.println("Pedina 1 riportata a destra");
						this.checkPawn = true;
					}
				}
				if(ControllerForView.getInstance().getPiece(ControllerForView.getInstance().getPawnCurrentPositionX(2)-1, posY) != null){
					if(ControllerForView.getInstance().getPawnCurrentPositionY(3) == posY){
						ControllerForView.getInstance().setPosPlayer2(ControllerForView.getInstance().getPawnCurrentPositionX(2)-1, posY);;
						System.out.println("Pedina 2 mossa verso sinistra");
						this.checkPawn = true;
					}
				}
				else if(ControllerForView.getInstance().getPiece(ControllerForView.getInstance().getPawnCurrentPositionX(2)-1, posY) == null){
					if(ControllerForView.getInstance().getPawnCurrentPositionY(3) == posY){
						ControllerForView.getInstance().setPosPlayer2(ControllerForView.getInstance().getBoardDimentions()-1, posY);
						System.out.println("Pedina 2 riportata a destra");
						this.checkPawn = true;
					}
				}
				
				this.checkPawn = true;
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	    /////////////////////////////////////////////////////////////
				
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("");
	    System.out.println("MOUSE CLICKED --->");
	    System.out.println("firstPhase: " + this.endFirstPhase);
	    System.out.println("");
	    System.out.println("checkPawn = " + this.checkPawn);
	    System.out.println("");
		boardPanel.repaint();
		previewPanel.repaint();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		this.pawnMoved=false;
	if(this.pieceInserted==true && this.pawnMoved==false && this.endFirstPhase==true && this.endSecondPhase == false){
		double xCord= e.getX();
		double yCord= e.getY();
		try {
			if(ControllerForView.getInstance().getTurn()==1){
				if(this.circle.contains(xCord, yCord)==true){
				this.isDraggable=true;
				this.isInside=true;
				this.canPass=true;
					
				   }
			}
			else{
				if(this.circle2.contains(xCord, yCord)==true){
					this.isDraggable=true;
					this.isInside=true;
					this.canPass=true;
			    }
			 }	
			}
		 catch (IOException e1) {
		 // TODO Auto-generated catch block
				e1.printStackTrace();
			}
		System.out.println("");
	  System.out.println(xCord +" ," +yCord+", MOUSE PRESSED --->");
	  System.out.println(" isDraggable=" +isDraggable);
	  System.out.println(" isInside=" +isInside);
	  System.out.println(" canPass=" +canPass);
	  System.out.println(" pieceInserted=" +pieceInserted);
	  System.out.println(" pawnMoved=" +pawnMoved);
	  System.out.println("");
	 }
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		int posX = e.getX()/100;
		int posY =	e.getY()/100;
			//System.out.println("Pallucca spostata in coordinate" +posX +" ;"+posY);
		this.setCursor(Cursor.getDefaultCursor());
			if(this.endSecondPhase==false){
				try {
					if (isDraggable==true && (ControllerForView.getInstance().controlSamePositionX()!=posX || ControllerForView.getInstance().controlSamePositionY()!=posY)){
						try {
							ControllerForView.getInstance().setPawnCurrentPositionX(posX);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							ControllerForView.getInstance().setPawnCurrentPositionY(posY);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							this.circle.setFrame(35+(100*ControllerForView.getInstance().getPawnCurrentPositionX(0)), 35+(100*ControllerForView.getInstance().getPawnCurrentPositionY(1)), 30, 30);
							this.circle2.setFrame(35+(100*ControllerForView.getInstance().getPawnCurrentPositionX(2)), 35+(100*ControllerForView.getInstance().getPawnCurrentPositionY(3)), 30, 30);
							this.endSecondPhase = true;
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				boardPanel.repaint();
			}
			
			System.out.println("");
			 System.out.println("MOUSE RELEASED --->");
			 System.out.println("secondPhase: " + this.endSecondPhase);
			 System.out.println("");

		this.pawnMoved = true;
		this.isDraggable=false;
		this.isInside=false;
		
		
		System.out.println("");
		System.out.println("MOUSE RELEASED --->");
		System.out.println(" isDraggable=" +isDraggable);
		  System.out.println(" isInside=" +isInside);
		  System.out.println(" canPass=" +canPass);
		  System.out.println(" pieceInserted=" +pieceInserted);
		  System.out.println(" pawnMoved=" +pawnMoved);
		  System.out.println("");
		 repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		double xCord = e.getX();
		double yCord = e.getY();
		
		if(this.isDraggable=true && this.isInside && this.canPass){
			try {
				if(ControllerForView.getInstance().getTurn()==1){
					boardPanel.repaint();
					BufferedImage bufferedImage = new BufferedImage(30,30, BufferedImage.BITMASK);
					Graphics2D g2d = bufferedImage.createGraphics();
					g2d.setColor(Color.RED);
					this.circle.setFrame(0, 0, 30, 30);
					g2d.fill(this.circle);
					g2d.draw(this.circle);
					Toolkit toolkit = Toolkit.getDefaultToolkit();
					Cursor customCursor = toolkit.createCustomCursor( bufferedImage , new Point(0,0), "customCursor");
					this.setCursor(customCursor);
				}	
				else{
					boardPanel.repaint();
					BufferedImage bufferedImage = new BufferedImage(30,30, BufferedImage.BITMASK);
					Graphics2D g2d = bufferedImage.createGraphics();
					g2d.setColor(Color.GREEN);
					this.circle2.setFrame(0, 0, 30, 30);
					g2d.fill(this.circle2);
					g2d.draw(this.circle2);
					Toolkit toolkit = Toolkit.getDefaultToolkit();
					Cursor customCursor = toolkit.createCustomCursor( bufferedImage , new Point(0,0), "customCursor");
					this.setCursor(customCursor);
					this.circle.setFrame(ControllerForView.getInstance().getPawnCurrentPositionX(0), ControllerForView.getInstance().getPawnCurrentPositionX(1), 30, 30);
				}
			} catch (HeadlessException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IndexOutOfBoundsException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			//System.out.println("Mouse Dragged ON");
			
			double eastSide = 0;
			double northSide = 0;
			
			if (xCord < 100 && yCord < 100){
				northSide = 0;
				eastSide = 100;	
			}
			if (xCord < 100 && yCord > 100){
				northSide = (Math.floor(yCord/100))*100;
				eastSide = 100;
			}
			if (xCord > 100 && yCord < 100){
				northSide = 0;
				eastSide = (Math.ceil(xCord/100))*100;
			}
			if (xCord > 100 && yCord > 100){
				northSide = (Math.floor(yCord/100))*100;
				eastSide = (Math.ceil(xCord/100))*100;
			}
			
			//System.out.println("Controllo Passaggio");
			
			int colPawnCord = (int)(eastSide/100)-1;
			int rawPawnCord = (int)(northSide/100);
			
			//CONTROLLO DEL PASSAGGIO
			if(xCord > eastSide-15 && xCord < eastSide){
				//Controlla il passaggio a dx
				try {
					if(ControllerForView.getInstance().isPawnMovRight((ControllerForView.getInstance().getPiece(colPawnCord, rawPawnCord)), (ControllerForView.getInstance().getPiece(colPawnCord+1, rawPawnCord)))==true){
						this.isDraggable=true;
						System.out.println("PASS DX");
					}
					else{
						this.isDraggable=false;
						this.canPass=false;
						this.pawnMoved= true;
						this.setCursor(Cursor.getDefaultCursor());
						boardPanel.repaint();
						System.out.println("YOU SHALL NOT PASS DX");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(yCord > northSide && yCord < northSide+15){
				//Controlla il passaggio in alto
				try {
					if(ControllerForView.getInstance().isPawnMovUp((ControllerForView.getInstance().getPiece(colPawnCord, rawPawnCord)), (ControllerForView.getInstance().getPiece(colPawnCord, rawPawnCord-1)))==true){
						this.isDraggable=true;
						System.out.println("PASS UP");
					}
					else{
						this.isDraggable=false;
						this.canPass=false;
						this.pawnMoved= true;
						this.setCursor(Cursor.getDefaultCursor());
						boardPanel.repaint();
						System.out.println("YOU SHALL NOT PASS UP");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(xCord > eastSide-100 && xCord < eastSide-85){
				//Controlla il passaggio a sx
				try {
					if(ControllerForView.getInstance().isPawnMovLeft((ControllerForView.getInstance().getPiece(colPawnCord, rawPawnCord)), (ControllerForView.getInstance().getPiece(colPawnCord-1, rawPawnCord)))==true){
						this.isDraggable=true;
						System.out.println("PASS SX");
					}
					else{
						this.isDraggable=false;
						this.canPass=false;
						this.pawnMoved= true;
						this.setCursor(Cursor.getDefaultCursor());
						boardPanel.repaint();
						System.out.println("YOU SHALL NOT PASS SX");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(yCord > northSide+85 && yCord < northSide+100){
				//Controlla il passaggio in basso
				try {
					if(ControllerForView.getInstance().isPawnMovDown((ControllerForView.getInstance().getPiece(colPawnCord, rawPawnCord)), (ControllerForView.getInstance().getPiece(colPawnCord, rawPawnCord+1)))==true){
						this.isDraggable=true;
						System.out.println("PASS DOWN");
					}
					else{
						this.isDraggable=false;
						this.canPass=false;
						this.pawnMoved= true;
						this.setCursor(Cursor.getDefaultCursor());
						boardPanel.repaint();
						System.out.println("YOU SHALL NOT PASS DOWN");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			System.out.println("");
			System.out.println("MOUSE DRAGGED --->");
			  System.out.println(" isDraggable=" +isDraggable);
			  System.out.println(" isInside=" +isInside);
			  System.out.println(" canPass=" +canPass);
			  System.out.println(" pieceInserted=" +pieceInserted);
			  System.out.println(" pawnMoved=" +pawnMoved);
			  System.out.println("");
			//System.out.println("Mouse X: "+xCord+" ,NorthSide: "+northSide);
			//System.out.println("Mouse Y: "+yCord+" ,EastSide: "+eastSide);
			//System.out.println(xCord + ", " + yCord);
		}
	}
	

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}
}
