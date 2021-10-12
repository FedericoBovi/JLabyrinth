package jlabyrinth.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Piece{
	
	final static Piece[] AVAIBLE_PIECES=new Piece[49];
	static Piece OUTBOARD_PIECE;
	static String IMG_STRING="";
	
	
	final int CLOSED_WAY = 0;
	final int OPEN_WAY = 1;
	final int FIX_PIECE = 0;
	final int MOVABLE_PIECE = 1;
	final int REACHABLE = 0;
	final int UNREACHABLE = 1;
	
	/////ATTRIBUTI/////
	
	protected int shapeArray[][];
	protected String pieceName;
	protected int pieceType;
	protected boolean isTarget; 
	protected int score;
	protected File file;
	protected BufferedImage filteredImage;
	protected int rotationCounter;
	protected Image img;
	protected boolean occupiedPiece;
	protected boolean reachable=false;
	protected boolean visited;
	
	/////COSTRUTTORE/////
	
	protected Piece(){
		//Do Nothing
	}
	
	protected void rotate() throws IOException{
		 
		//Rotazione in senso orario del pezzo rappresentato dall'array
		int temp = shapeArray[1][0];
		shapeArray[1][0] = shapeArray[0][1]; 
		shapeArray[0][1] = shapeArray[1][2];
		shapeArray[1][2] = shapeArray[2][1];
		shapeArray[2][1] = temp;
		
		//Rotazione dell'immagine relativa al pezzo
		BufferedImage imgTemp = (BufferedImage) this.img;
		int grade=0;
		this.filteredImage = new BufferedImage(imgTemp.getWidth(), imgTemp.getHeight(), imgTemp.getType());
		
		
			grade = 90;
			AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(grade), imgTemp.getWidth()/2, imgTemp.getHeight()/2);
			AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
			op.filter(imgTemp, this.filteredImage);
			this.img = (Image) this.filteredImage;
			this.rotationCounter++;
			
			if(this.rotationCounter == 4){
				this.rotationCounter=0;
			}
	}
	
	public void rotate(int index) throws IOException{
		for(int i = 0; i<index; i++){
			this.rotate();
		}
	}
	
	public void randomRotation() throws IOException{
		int randomicIndex = (int) ((Math.random()*4)%4);
		this.rotate(randomicIndex);
	}
	
	public int getRotationCounter(){
		return this.rotationCounter;
	}
	
	public BufferedImage showImage(){
		BufferedImage buffered= (BufferedImage) this.img;
		return buffered;
	}
	
	protected String getName() {
		return this.pieceName;
		
	}
	
	protected int[][] getPieceShape(){
		return this.shapeArray;
	}
	
	protected void setReachability(boolean condition){
		this.reachable=condition;
	}
	
	public boolean getReachability(){
		return this.reachable;
	}
	
	protected void setVisited(boolean condition){
		this.visited=condition;
	}
	
	public boolean getVisited(){
		return this.visited;
	}
	
	public Image getImageOfPiece(){
		return this.img;
	}
	
	
	public  Graphics2D drawPiece( Graphics2D g, int x, int y) {
		g.drawImage(img, x, y, null);
		return g;
	}
	
	public String toString(){
		String str = "|";
		for(int i = 0; i<this.shapeArray.length; i++){
			for(int j=0; j<this.shapeArray.length; j++){
				str += shapeArray[j][i] + "|";
			}
			str += "\n" + "|";
		}
		
		return str;
	}
	
	public boolean isOccupied(Piece piece){
		return occupiedPiece;
	}
	
	
		
	//---------------------------------------------------------------
	// STATIC METHODS
	//---------------------------------------------------------------
	
	static void setOutPieceType(){
		OUTBOARD_PIECE.setPieceType(1);
	}
	
	static Piece getPieceL(){
		int index_found = -1;
		int i =13;		
		Piece currentPiece = null;
		while(i < 31 && index_found == -1){
			if(AVAIBLE_PIECES[i] != null){
				currentPiece = AVAIBLE_PIECES[i];
				AVAIBLE_PIECES[i]=null;
				index_found = 1;			
			}else{
				i++;
			}	
		}
		return currentPiece;	
	} 
	
	static Piece getPieceT(){
		int index_found = -1;
		int i =0;		
		Piece currentPiece = null;
		while(i < 13 && index_found == -1){
			if(AVAIBLE_PIECES[i] != null){
				currentPiece = AVAIBLE_PIECES[i];
				AVAIBLE_PIECES[i]=null;
				index_found = 1;			
			}else{
				i++;
			}	
		}
		return currentPiece;
	}
	
	static Piece getPieceI(){
		int index_found = -1;
		int i =31;		
		Piece currentPiece = null;
		while(i < 42 && index_found == -1){
			if(AVAIBLE_PIECES[i] != null){
				currentPiece = AVAIBLE_PIECES[i];
				AVAIBLE_PIECES[i]=null;
				index_found = 1;			
			}else{
				i++;
			}	
		}
		return currentPiece;	
	}
	
	static Piece getRandomPiece7() throws IOException{
		Piece currentPiece = null;
		int pieceIndex = 0;
		
		do{
		pieceIndex= (int)((Math.random()*AVAIBLE_PIECES.length) % AVAIBLE_PIECES.length);
		}
		while(AVAIBLE_PIECES[pieceIndex] == null);
		
		currentPiece = AVAIBLE_PIECES[pieceIndex];
		AVAIBLE_PIECES[pieceIndex] = null;
		return currentPiece;
	}
	
	static Piece getRandomPiece5() throws IOException{
		Piece currentPiece = null;
		int pieceIndex = 0;

		for(int t =5; t<13; t++){
			AVAIBLE_PIECES[t]=null;
		}
		for(int l = 19; l<31; l++){
			AVAIBLE_PIECES[l]=null;
		}
		for(int i = 38; i<42; i++){
			AVAIBLE_PIECES[i]=null;
		}
		
		do{
		pieceIndex= (int)((Math.random()*AVAIBLE_PIECES.length) % AVAIBLE_PIECES.length);
		}
		while(AVAIBLE_PIECES[pieceIndex] == null);
		
		currentPiece = AVAIBLE_PIECES[pieceIndex];
		AVAIBLE_PIECES[pieceIndex] = null;
		return currentPiece;
	}
	
	static Piece getOutPiece(){
		Piece tempPiece= OUTBOARD_PIECE;
		return tempPiece;
	}
	
	public static void setOutPiece(Piece piece){
		OUTBOARD_PIECE = piece;
	}
	
	public void setPieceType(int Type){
		this.pieceType=Type;
	}
	
	public int getPieceType(){
		return this.pieceType;
	}
	
	public static String printPieceArray(){
		String str = "";
		for(int i =0; i<AVAIBLE_PIECES.length; i++){
			if (AVAIBLE_PIECES[i]!= null){
				str += "pezzo, ";
			}
			else{
				str += "null, ";
			}
			
	}
		return str;
	}
	
	public static void setImgString(int a){
		if (a==0){
			System.out.println("Stringa Immagine: "+IMG_STRING);
		}if (a==1){
			IMG_STRING="1";
		}
		if (a==2){
			IMG_STRING="2";
		}
	}
	
	public static String getImgString(){
		return IMG_STRING;
	}
	
	public static void initOutboardPiece(Piece a){
		OUTBOARD_PIECE= a;
	}
	
	public static void initPieces(int a){
		IMG_STRING=new String();
		setImgString(a);
		OUTBOARD_PIECE= new PieceL();
		
		//Caricamento dei pezzi t nell'array
		for(int t=0; t<13;t++){
			AVAIBLE_PIECES[t]=new PieceT();
		}
		//Caricamento dei pezzi l nell'array
		for(int l=13; l<31; l++){
			AVAIBLE_PIECES[l] = new PieceL();
		}
		//Caricamento dei pezzi i nell'array
		for (int i=31; i<42; i++){
			AVAIBLE_PIECES[i]= new PieceI();
		}
		//Caricamento dei pezzi "obiettivo" nell'array
		AVAIBLE_PIECES[42]= new PieceTDragon();
		AVAIBLE_PIECES[43]= new PieceTBat();
		AVAIBLE_PIECES[44]= new PieceTFairy();
		AVAIBLE_PIECES[45]= new PieceTGhost();
		AVAIBLE_PIECES[46]= new PieceLOwl();
		AVAIBLE_PIECES[47]= new PieceLMouse();
		AVAIBLE_PIECES[48]= new PieceLSalamander();
	}
	
}
