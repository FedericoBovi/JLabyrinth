package jlabyrinth.model;

import java.awt.geom.Ellipse2D;
import java.io.IOException;

import jlabyrinth.controller.ControllerForView;



public class Model implements IModel {
	
	//---------------------------------------------------------------
    // STATIC CONSTANTS
	//---------------------------------------------------------------
	
	//---------------------------------------------------------------
    // STATIC FIELDS
	//---------------------------------------------------------------
	
	private static Model instance = null;
	
	//---------------------------------------------------------------
    // INSTANCE ATTRIBUTES
	//---------------------------------------------------------------
	
	private Piece[][] boardArray;
	private int boardDimentions=7;
	private Piece enteringPiece;
	private Piece removingPiece;
	private String currentTarget;
	private boolean onTarget;
	private int numberTargets;
	private Pawn player1;
	private Pawn player2;
	private int turn=1;
	private int[] pawnPositionArray;
	private int[] scoreArray= new int[2];
	private int setImages;
	private int countBACell=0;
	
	private Model() throws IOException{
	}
	
	//---------------------------------------------------------------
	// PRIVATE INSTANCE METHODS
	//---------------------------------------------------------------
	
	public void initPawns(){
		this.onTarget=true;
		setCurrentTarget();
		this.pawnPositionArray= new int[4];
		this.player1= new Pawn();
		setPawnCurrentPositionX(0);
		setPawnCurrentPositionY(0);
		setTurn(2);
		this.player2= new Pawn();
		setPawnCurrentPositionX(boardArray.length-1);
		setPawnCurrentPositionY(boardArray.length-1);
		setTurn(1);
	}
	
	public void fillBoard() throws IOException{
		Piece.initPieces(this.setImages);
		this.removingPiece=Piece.getOutPiece();
		this.boardArray=new Piece[this.boardDimentions][this.boardDimentions];
		
		//Metodo 7*7
		int counter = 0;
		////////// PEZZI FISSI //////////
		
		//Scansione degli angoli dell'array
		System.out.print("Inserimento pezzi fissi...");
	
		this.boardArray[0][0]=Piece.getPieceL();
		this.boardArray[0][0].setPieceType(0);
		this.boardArray[this.boardDimentions-1][0]=Piece.getPieceL();
		this.boardArray[this.boardDimentions-1][0].rotate(1);
		this.boardArray[this.boardDimentions-1][0].setPieceType(0);
		this.boardArray[this.boardDimentions-1][this.boardDimentions-1]=Piece.getPieceL();
		this.boardArray[this.boardDimentions-1][this.boardDimentions-1].rotate(2);
		this.boardArray[this.boardDimentions-1][this.boardDimentions-1].setPieceType(0);
		this.boardArray[0][this.boardDimentions-1]=Piece.getPieceL();
		this.boardArray[0][this.boardDimentions-1].rotate(3);
		this.boardArray[0][this.boardDimentions-1].setPieceType(0);
		
		//Riempimento lati
		//Caselle orizzontali
		
		if(this.boardDimentions == 7){
			this.boardArray[0][2]=Piece.getPieceT();
			this.boardArray[0][2].setPieceType(0);
			this.boardArray[0][4]=Piece.getPieceT();
			this.boardArray[0][4].setPieceType(0);
			this.boardArray[2][0]=Piece.getPieceT();
			this.boardArray[2][0].rotate(1);
			this.boardArray[2][0].setPieceType(0);
			this.boardArray[4][0]=Piece.getPieceT();
			this.boardArray[4][0].rotate(1);
			this.boardArray[4][0].setPieceType(0);
			this.boardArray[6][2]=Piece.getPieceT();
			this.boardArray[6][2].rotate(2);
			this.boardArray[6][2].setPieceType(0);
			this.boardArray[6][4]=Piece.getPieceT();
			this.boardArray[6][4].rotate(2);
			this.boardArray[6][4].setPieceType(0);
			this.boardArray[2][6]=Piece.getPieceT();
			this.boardArray[2][6].rotate(3);
			this.boardArray[2][6].setPieceType(0);
			this.boardArray[4][6]=Piece.getPieceT();
			this.boardArray[4][6].rotate(3);
			this.boardArray[4][6].setPieceType(0);
		}
		else{
			this.boardArray[0][2]=Piece.getPieceT();
			this.boardArray[0][2].setPieceType(0);
			this.boardArray[2][0]=Piece.getPieceT();
			this.boardArray[2][0].rotate(1);
			this.boardArray[2][0].setPieceType(0);
			this.boardArray[this.boardDimentions-1][2]=Piece.getPieceT();
			this.boardArray[this.boardDimentions-1][2].rotate(2);
			this.boardArray[this.boardDimentions-1][2].setPieceType(0);
			this.boardArray[2][this.boardDimentions-1]=Piece.getPieceT();
			this.boardArray[2][this.boardDimentions-1].rotate(3);
			this.boardArray[2][this.boardDimentions-1].setPieceType(0);
			
		}
			System.out.println(" COMPLETATO");
		
		System.out.print("Inserimento pezzi mobili...");
		for(int s=0; s<boardArray.length; s++){
			for(int j=0; j<boardArray.length; j++){
				if(boardArray[s][j] == null){
					if(this.boardDimentions == 5){
						boardArray[s][j] = Piece.getRandomPiece5();
						boardArray[s][j].randomRotation();
						boardArray[s][j].setPieceType(1);
						
					}
					else{
						boardArray[s][j] = Piece.getRandomPiece7();
						boardArray[s][j].randomRotation();
						boardArray[s][j].setPieceType(1);
					}
					
					//System.out.println("....."+counter);
					counter++;
				}
				//System.out.println("-");	
			}
			//System.out.println("--");
		}
		Piece.setOutPieceType();
		System.out.println(" COMPLETATO");
		System.out.println("BoardArray correttamente riempito -fillBoard()- ");

	}
	
	public void insertPiece(int a, int b){
		//Inserimento da ovest
		if(a==0){
			this.removingPiece= this.boardArray[a+this.boardDimentions-1][b];
			for(int i=this.boardDimentions-2; i>=0; i--){
				this.boardArray[i+1][b]=this.boardArray[i][b];	
			}
		}
		//Inserimento da nord
		if(b==0){
			this.removingPiece= this.boardArray[a][b+this.boardDimentions-1];
			for(int i=this.boardDimentions-2; i>=0; i--){
				this.boardArray[a][i+1]=this.boardArray[a][i];
				System.out.println(this.boardDimentions);
			}
		}
		//Inserimento da est
		if(a==this.boardDimentions-1){
			System.out.println("EST" + this.boardDimentions);
			this.removingPiece= this.boardArray[a-this.boardDimentions+1][b];
			for(int i=1; i<=this.boardDimentions-1; i++){
				this.boardArray[i-1][b]=this.boardArray[i][b];
			}
		}
		//Inserimento da sud
		if(b==this.boardDimentions-1){
			System.out.println("SUD" + this.boardDimentions);
			this.removingPiece= this.boardArray[a][b-this.boardDimentions+1];
			for(int i=1; i<=this.boardDimentions-1; i++){
				this.boardArray[a][i-1]=this.boardArray[a][i];
			}
		}
		this.boardArray[a][b]=Piece.getOutPiece();
		Piece.setOutPiece(removingPiece);
		
		System.out.println("\n" + "Modello del pezzo rimosso:");
		System.out.println((Piece.getOutPiece()).toString() + "\n");
	}
	
	
	public String showMatrix(){
		String string="";
		for(int i = 0; i<this.boardArray.length; i++){
			for (int j=0; j<this.boardArray.length; j++){
				if (boardArray[i][j]!= null)
					string += "piece |";
					else {
						string += "null  |";
					}
			}
			string += "\n";
		}
		return string;
	}
	
	public void selectBAcell(String pieceName,int i,int j){
		if(this.countBACell==0){
			this.boardArray=new Piece[this.boardDimentions][this.boardDimentions];
			Piece.setImgString(this.setImages);
			this.pawnPositionArray= new int[4];
			this.player1= new Pawn();
			this.player2= new Pawn();
			this.countBACell ++;
		}
		if(pieceName.equals("PieceI"+this.setImages))
			this.boardArray[i][j]=new PieceI();
		else if(pieceName.equals("PieceL"+this.setImages))
			this.boardArray[i][j]=new PieceL();
		else if(pieceName.equals("PieceLMouse"+this.setImages))
			this.boardArray[i][j]=new PieceLMouse();
		else if(pieceName.equals("PieceLOwl"+this.setImages))
			this.boardArray[i][j]=new PieceLOwl();
		else if(pieceName.equals("PieceLSalamander"+this.setImages))
			this.boardArray[i][j]=new PieceLSalamander();
		else if(pieceName.equals("PieceT"+this.setImages))
			this.boardArray[i][j]=new PieceT();
		else if(pieceName.equals("PieceTBat"+this.setImages))
			this.boardArray[i][j]=new PieceTBat();
		else if(pieceName.equals("PieceTDragon"+this.setImages))
			this.boardArray[i][j]=new PieceTDragon();
		else if(pieceName.equals("PieceTFairy"+this.setImages))
			this.boardArray[i][j]=new PieceTFairy();
		else if(pieceName.equals("PieceTGhost"+this.setImages))
			this.boardArray[i][j]=new PieceTGhost();
		if(this.setImages==0){
			System.out.println(pieceName);
			if(pieceName.equals("PieceI"))
				this.boardArray[i][j]=new PieceI();
			else if(pieceName.equals("PieceL"))
				this.boardArray[i][j]=new PieceL();
			else if(pieceName.equals("PieceLMouse"))
				this.boardArray[i][j]=new PieceLMouse();
			else if(pieceName.equals("PieceLOwl"))
				this.boardArray[i][j]=new PieceLOwl();
			else if(pieceName.equals("PieceLSalamander"))
				this.boardArray[i][j]=new PieceLSalamander();
			else if(pieceName.equals("PieceT"))
				this.boardArray[i][j]=new PieceT();
			else if(pieceName.equals("PieceTBat"))
				this.boardArray[i][j]=new PieceTBat();
			else if(pieceName.equals("PieceTDragon"))
				this.boardArray[i][j]=new PieceTDragon();
			else if(pieceName.equals("PieceTFairy"))
				this.boardArray[i][j]=new PieceTFairy();
			else if(pieceName.equals("PieceTGhost"))
				this.boardArray[i][j]=new PieceTGhost();
		}	
	}
	
public void selectRemovingPiece(String pieceName){
		if(pieceName.equals("PieceI"+this.setImages))
			this.removingPiece=new PieceI();
		else if(pieceName.equals("PieceL"+this.setImages))
			this.removingPiece=new PieceL();
		else if(pieceName.equals("PieceLMouse"+this.setImages))
			this.removingPiece=new PieceLMouse();
		else if(pieceName.equals("PieceLOwl"+this.setImages))
			this.removingPiece=new PieceLOwl();
		else if(pieceName.equals("PieceLSalamander"+this.setImages))
			this.removingPiece=new PieceLSalamander();
		else if(pieceName.equals("PieceT"+this.setImages))
			this.removingPiece=new PieceT();
		else if(pieceName.equals("PieceTBat"+this.setImages))
			this.removingPiece=new PieceTBat();
		else if(pieceName.equals("PieceTDragon"+this.setImages))
			this.removingPiece=new PieceTDragon();
		else if(pieceName.equals("PieceTFairy"+this.setImages))
			this.removingPiece=new PieceTFairy();
		else if(pieceName.equals("PieceTGhost"+this.setImages))
			this.removingPiece=new PieceTGhost();
		if(this.setImages==0){
			if(pieceName.equals("PieceI"))
				this.removingPiece=new PieceI();
			else if(pieceName.equals("PieceL"))
				this.removingPiece=new PieceL();
			else if(pieceName.equals("PieceLMouse"))
				this.removingPiece=new PieceLMouse();
			else if(pieceName.equals("PieceLOwl"))
				this.removingPiece=new PieceLOwl();
			else if(pieceName.equals("PieceLSalamander"))
				this.removingPiece=new PieceLSalamander();
			else if(pieceName.equals("PieceT"))
				this.removingPiece=new PieceT();
			else if(pieceName.equals("PieceTBat"))
				this.removingPiece=new PieceTBat();
			else if(pieceName.equals("PieceTDragon"))
				this.removingPiece=new PieceTDragon();
			else if(pieceName.equals("PieceTFairy"))
				this.removingPiece=new PieceTFairy();
			else if(pieceName.equals("PieceTGhost"))
				this.removingPiece=new PieceTGhost();
		}
		Piece.initOutboardPiece(this.removingPiece);
	}
		
	public Piece getSpecificPiece(int i, int j){
		return this.boardArray[i][j];
	}
	
	public Piece getEnteringPiece(){
		return this.enteringPiece;
	}
	
	public Piece getRemovingPiece(){
		return this.removingPiece;
	}
	
	public Piece[][] getBoardArray(){
		Piece[][] pieceBoard = new Piece[this.boardDimentions][this.boardDimentions];
		for (int i =0; i<this.boardArray.length; i++){
			for (int j=0; j< this.boardArray.length; j++){
				pieceBoard[i][j]=this.boardArray[i][j];
			}
		}
		return pieceBoard;
	}
	
	public void setBoardDimentions(int num){
		this.boardDimentions=num;
	}
	
	public  int getBoardDimentions(){
			return this.boardDimentions;
	}
	
	public void rotateRemovingPiece() throws IOException{
		this.removingPiece.rotate();
	}
	
	public Piece getPiece(int i, int j){
		Piece piece;
		if(i<this.boardArray.length && j<this.boardArray.length && i>=0 && j>=0)
			piece=this.boardArray[i][j];
		else{
			piece=null;
		}
		return piece;
	}
	
	public void setNumberTargets(int i){
		this.numberTargets=i;
	}
	
	public int getNumberTargets(){
		return this.numberTargets;
	}
	
	public void setCurrentTarget(){
		if(this.onTarget==true){
			if (this.numberTargets==7)
				this.currentTarget=Target.getRandomTarget();
			if (this.numberTargets==5)
				this.currentTarget=Target.getRandom5Target();
			System.out.println("CurrentTarget: "+this.currentTarget);
			this.onTarget=false;
		}
	}
	
	public void setSpecificTarget(String target){
		this.currentTarget = target;
	}
	
	public void setOnTarget(boolean a){
		this.onTarget=a;
	}
	public String getCurrentTarget(){
		return this.currentTarget;
	}
	
	public int getScore(int a){
		return this.scoreArray[a];
	}
	
	public void setScore(int i, int score){
			this.scoreArray[i]=score;
	}
	
	public void updateScore(){
		if(this.currentTarget.equalsIgnoreCase(this.boardArray[this.pawnPositionArray[0]][this.pawnPositionArray[1]].getName()+this.setImages)){
			this.scoreArray[0]+=100;
			this.onTarget=true;
		}
		if(this.currentTarget.equalsIgnoreCase(this.boardArray[this.pawnPositionArray[2]][this.pawnPositionArray[3]].getName()+this.setImages)){
			this.scoreArray[1]+=100;
			this.onTarget=true;
		}
	}
	
	public boolean getOnTarget(){
		return this.onTarget;
	}
	
	public void setImagesDirectory(int a){
		this.setImages=a;
	}
	
	//////////METODI MOVIMENTO PEDINA//////////
	
	
	public boolean isPawnMovUp(Piece onPiece, Piece toPiece){
		boolean canPassUp=false;
		int[][] temp1 = onPiece.getPieceShape();
		if (toPiece != null){
			int[][] temp2 = toPiece.getPieceShape();
			if (temp1[1][0]==1 && temp2[1][2]==1){
				canPassUp = true;
			}
		}
		return canPassUp;
	}
	
	public boolean isPawnMovDown(Piece onPiece, Piece toPiece){
		boolean canPassDown=false;
		int[][] temp1 = onPiece.getPieceShape();
		if (toPiece != null){
			int[][] temp2 = toPiece.getPieceShape();
			if (temp1[1][2]==1 && temp2[1][0]==1){
				canPassDown = true;
			}
		}
		return canPassDown;
	}
	
	public boolean isPawnMovLeft(Piece onPiece, Piece toPiece){
		boolean canPassLeft=false;
		int[][] temp1 = onPiece.getPieceShape();
		if (toPiece != null){
			int[][] temp2 = toPiece.getPieceShape();
			if (temp1[0][1]==1 && temp2[2][1]==1){
				canPassLeft = true;
			}
		}
		return canPassLeft;
	}
	
	public boolean isPawnMovRight(Piece onPiece, Piece toPiece){
		boolean canPassRight=false;
		int[][] temp1 = onPiece.getPieceShape();
		if (toPiece != null){
			int[][] temp2 = toPiece.getPieceShape();
			if (temp1[2][1]==1 && temp2[0][1]==1){
				canPassRight = true;
			}
		}
		return canPassRight;
	}
	
	public int controlSamePositionX(){
		int a;
		if(getTurn()==1){
			a=getPawnCurrentPositionX(2);
		}
		else{
			a=getPawnCurrentPositionX(0);
		}
		return a;
	}
	
	public int controlSamePositionY(){
		int a;
		if(getTurn()==1){
			a=getPawnCurrentPositionY(3);
		}
		else{
			a=getPawnCurrentPositionY(1);
		}
		return a;
	}
	
	public int getPawnCurrentPositionX(int i){
			return this.pawnPositionArray[i];
	}
	
	public int getPawnCurrentPositionY(int i){
		return this.pawnPositionArray[i];
	}
	
	public void setPawnCurrentPosition(int ArrayCell, int Pos){
		this.pawnPositionArray[ArrayCell]=Pos;
	}
	
	public void setPawnCurrentPositionX(int newPosX){
		if (this.turn==1){
			this.player1.setPawnCurrentPositionX(newPosX);
			this.pawnPositionArray[0]=newPosX;
		}
		else{
			this.player2.setPawnCurrentPositionX(newPosX);
			this.pawnPositionArray[2]=newPosX;
		}
	}
	
	public void setPawnCurrentPositionY(int newPosY){
		if (this.turn==1){
			this.player1.setPawnCurrentPositionY(newPosY);
			this.pawnPositionArray[1]=newPosY;
			
		}
		else{
			this.player2.setPawnCurrentPositionY(newPosY);
			this.pawnPositionArray[3]=newPosY;
		}
		
	}
	
	public Ellipse2D.Double getPawnShape(){
		if (turn==1){
			return this.player1.getPawnShape();
		}
		else{
			return this.player2.getPawnShape();
		}
	}
	
	public void reachabilityFinder(int i, int j){
		
		int[][] temp2 = new int[3][3];
		for(int q=0; q<3; q++)
			for(int t=0; t<3; t++)
				temp2[q][t]=0;
		
		//Vede se � raggiungibile il pezzo a EST
		if( i<this.boardArray.length && j<this.boardArray.length && i+1<this.boardArray.length && this.boardArray[i+1][j].getVisited()==false ){
			int[][] temp1 = this.boardArray[i][j].getPieceShape();
			this.boardArray[i][j].setVisited(true);
			System.out.println("OK est "+i+" "+j);
			if(i+1<this.boardArray.length)
				temp2 = this.boardArray[i+1][j].getPieceShape();
				if (temp1[2][1]==1 && temp2[0][1]==1){
					this.boardArray[i+1][j].setReachability(true);
					reachabilityFinder(i+1,j);
				}
		}
		this.boardArray[i][j].setVisited(false);
		
		//Vede se � raggiungibile il pezzo a OVEST
		if(  i<this.boardArray.length && j<this.boardArray.length && i-1>=0 && this.boardArray[i-1][j].getVisited()==false ){
			int[][] temp1 = this.boardArray[i][j].getPieceShape();
			this.boardArray[i][j].setVisited(true);
			System.out.println("OK ovest "+i+" "+j);
			if(i-1>=0){
				temp2 = this.boardArray[i-1][j].getPieceShape();
				if (temp1[0][1]==1 && temp2[2][1]==1){
					this.boardArray[i-1][j].setReachability(true);
					reachabilityFinder(i-1,j);
				}
			}	
		}
		this.boardArray[i][j].setVisited(false);
		
		//Vede se � raggiungibile il pezzo a SUD
		if(  i<this.boardArray.length && j<this.boardArray.length && j+1<this.boardArray.length && this.boardArray[i][j+1].getVisited()==false ){
			int[][] temp1 = this.boardArray[i][j].getPieceShape();
			this.boardArray[i][j].setVisited(true);
			System.out.println("OK sud "+i+" "+j);
			if(j+1<this.boardArray.length){
				temp2 = this.boardArray[i][j+1].getPieceShape();
				if (temp1[1][2]==1 && temp2[1][0]==1){
					this.boardArray[i][j+1].setReachability(true);
					reachabilityFinder(i,j+1);
				}
			}	
		}
		this.boardArray[i][j].setVisited(false);
		
		//Vede se � raggiungibile il pezzo a NORD
		if(  i<this.boardArray.length && j<this.boardArray.length && j-1>=0 && this.boardArray[i][j-1].getVisited()==false ){
			int[][] temp1 = this.boardArray[i][j].getPieceShape();
			this.boardArray[i][j].setVisited(true);
			System.out.println("OK nord "+i+" "+j);
			if(j-1>=0){
				temp2 = this.boardArray[i][j-1].getPieceShape();
				if (temp1[1][0]==1 && temp2[1][2]==1){
					this.boardArray[i][j-1].setReachability(true);
					reachabilityFinder(i,j-1);
				}
			}	
		}
		this.boardArray[i][j].setVisited(true);
		System.out.println("FINITO");
	}
	
	public void setFalseAllReachability(){
		for(int i =0; i<this.boardArray.length; i++){
			for(int j =0; j<this.boardArray.length; j++){
				this.boardArray[i][j].setReachability(false);
				this.boardArray[i][j].setVisited(false);
			}
		}
	}
	
	//SELEZIONE TURNO
	
	public void setTurn(int i){
		this.turn=i;
	}
	
	public int getTurn(){
		return this.turn;
	}
	
	
	public Piece getOnPiece(int i, int j){	
		return this.boardArray[i][j];	
	}
	
	public void loadModelFromFile(String file) throws java.io.FileNotFoundException,java.io.IOException{
		InputOutput.loadModelFromFile(file, this);
	}
	
	public void saveModelToFile(String file) throws java.io.IOException{
		InputOutput.saveModelToFile(file, this);
	}
	
	public String toString(){
		StringBuilder strBuild = new StringBuilder();
		strBuild.append("#JLabyrinth saving file\r\n");
		
		strBuild.append("\r\n#Basic Settings\r\n");
		strBuild.append("boardDimentions = "+this.boardDimentions+"\r\n");
		strBuild.append("setImages = " +this.setImages + "\r\n");
		
		strBuild.append("\r\n#BoardArray section\r\n");
		strBuild.append("BoardArray[0][0] = "+this.boardArray[0][0].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[0][0] = "+this.boardArray[0][0].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[0][1] = "+this.boardArray[0][1].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[0][1] = "+this.boardArray[0][1].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[0][2] = "+this.boardArray[0][2].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[0][2] = "+this.boardArray[0][2].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[0][3] = "+this.boardArray[0][3].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[0][3] = "+this.boardArray[0][3].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[0][4] = "+this.boardArray[0][4].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[0][4] = "+this.boardArray[0][4].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[1][0] = "+this.boardArray[1][0].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[1][0] = "+this.boardArray[1][0].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[1][1] = "+this.boardArray[1][1].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[1][1] = "+this.boardArray[1][1].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[1][2] = "+this.boardArray[1][2].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[1][2] = "+this.boardArray[1][2].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[1][3] = "+this.boardArray[1][3].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[1][3] = "+this.boardArray[1][3].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[1][4] = "+this.boardArray[1][4].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[1][4] = "+this.boardArray[1][4].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[2][0] = "+this.boardArray[2][0].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[2][0] = "+this.boardArray[2][0].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[2][1] = "+this.boardArray[2][1].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[2][1] = "+this.boardArray[2][1].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[2][2] = "+this.boardArray[2][2].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[2][2] = "+this.boardArray[2][2].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[2][3] = "+this.boardArray[2][3].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[2][3] = "+this.boardArray[2][3].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[2][4] = "+this.boardArray[2][4].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[2][4] = "+this.boardArray[2][4].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[3][0] = "+this.boardArray[3][0].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[3][0] = "+this.boardArray[3][0].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[3][1] = "+this.boardArray[3][1].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[3][1] = "+this.boardArray[3][1].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[3][2] = "+this.boardArray[3][2].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[3][2] = "+this.boardArray[3][2].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[3][3] = "+this.boardArray[3][3].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[3][3] = "+this.boardArray[3][3].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[3][4] = "+this.boardArray[3][4].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[3][4] = "+this.boardArray[3][4].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[4][0] = "+this.boardArray[4][0].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[4][0] = "+this.boardArray[4][0].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[4][1] = "+this.boardArray[4][1].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[4][1] = "+this.boardArray[4][1].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[4][2] = "+this.boardArray[4][2].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[4][2] = "+this.boardArray[4][2].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[4][3] = "+this.boardArray[4][3].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[4][3] = "+this.boardArray[4][3].getRotationCounter()+"\r\n");
		strBuild.append("BoardArray[4][4] = "+this.boardArray[4][4].getName()+this.setImages+"\r\n");
		strBuild.append("Rotation[4][4] = "+this.boardArray[4][4].getRotationCounter()+"\r\n");
		if(this.boardDimentions==7){
			strBuild.append("BoardArray[0][5] = "+this.boardArray[0][5].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[0][5] = "+this.boardArray[0][5].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[0][6] = "+this.boardArray[0][6].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[0][6] = "+this.boardArray[0][6].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[1][5] = "+this.boardArray[1][5].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[1][5] = "+this.boardArray[1][5].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[1][6] = "+this.boardArray[1][6].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[1][6] = "+this.boardArray[1][6].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[2][5] = "+this.boardArray[2][5].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[2][5] = "+this.boardArray[2][5].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[2][6] = "+this.boardArray[2][6].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[2][6] = "+this.boardArray[2][6].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[3][5] = "+this.boardArray[3][5].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[3][5] = "+this.boardArray[3][5].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[3][6] = "+this.boardArray[3][6].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[3][6] = "+this.boardArray[3][6].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[4][5] = "+this.boardArray[4][5].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[4][5] = "+this.boardArray[4][5].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[4][6] = "+this.boardArray[4][6].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[4][6] = "+this.boardArray[4][6].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[5][5] = "+this.boardArray[5][5].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[5][5] = "+this.boardArray[5][5].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[5][6] = "+this.boardArray[5][6].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[5][6] = "+this.boardArray[5][6].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[6][5] = "+this.boardArray[6][5].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[6][5] = "+this.boardArray[6][5].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[6][6] = "+this.boardArray[6][6].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[6][6] = "+this.boardArray[6][6].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[5][0] = "+this.boardArray[5][0].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[5][0] = "+this.boardArray[5][0].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[5][1] = "+this.boardArray[5][1].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[5][1] = "+this.boardArray[5][1].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[5][2] = "+this.boardArray[5][2].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[5][2] = "+this.boardArray[5][2].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[5][3] = "+this.boardArray[5][3].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[5][3] = "+this.boardArray[5][3].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[5][4] = "+this.boardArray[5][4].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[5][4] = "+this.boardArray[5][4].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[6][0] = "+this.boardArray[6][0].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[6][0] = "+this.boardArray[6][0].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[6][1] = "+this.boardArray[6][1].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[6][1] = "+this.boardArray[6][1].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[6][2] = "+this.boardArray[6][2].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[6][2] = "+this.boardArray[6][2].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[6][3] = "+this.boardArray[6][3].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[6][3] = "+this.boardArray[6][3].getRotationCounter()+"\r\n");
			strBuild.append("BoardArray[6][4] = "+this.boardArray[6][4].getName()+this.setImages+"\r\n");
			strBuild.append("Rotation[6][4] = "+this.boardArray[6][4].getRotationCounter()+"\r\n");
			

		}
		
		strBuild.append("\r\n#Players' names\r\n");
		strBuild.append("p1Name = " +this.player1.getPawnName() + "\r\n");
		strBuild.append("p2Name = " +this.player2.getPawnName() + "\r\n");
		
		strBuild.append("\r\n#Players' scores\r\n");
		strBuild.append("p1Score = " +this.scoreArray[0]+ "\r\n");
		strBuild.append("p2Score = " +this.scoreArray[1]+ "\r\n");
		
		strBuild.append("\r\n#Target\r\n");
		strBuild.append("currentTarget = " +this.currentTarget + "\r\n");
		strBuild.append("numberTargets = "+this.numberTargets+ "\r\n");
		
		strBuild.append("\r\n#TARGETARRAY\r\n");
		strBuild.append("TARGETARRAY[0] = " +Target.getTargetFromTargetArray(0)+ "\r\n");
		strBuild.append("TARGETARRAY[1] = " +Target.getTargetFromTargetArray(1)+ "\r\n");
		strBuild.append("TARGETARRAY[2] = " +Target.getTargetFromTargetArray(2)+ "\r\n");
		strBuild.append("TARGETARRAY[3] = " +Target.getTargetFromTargetArray(3)+ "\r\n");
		strBuild.append("TARGETARRAY[4] = " +Target.getTargetFromTargetArray(4)+ "\r\n");
		strBuild.append("TARGETARRAY[5] = " +Target.getTargetFromTargetArray(5)+ "\r\n");
		strBuild.append("TARGETARRAY[6] = " +Target.getTargetFromTargetArray(6)+ "\r\n");
		
		strBuild.append("\r\n#Turn\r\n");
		strBuild.append("turn = " +this.turn + "\r\n");
		
		strBuild.append("\r\n#Players' positions\r\n");
		strBuild.append("p1PositionX = " +this.pawnPositionArray[0]+ "\r\n");
		strBuild.append("p1PositionY = " +this.pawnPositionArray[1]+ "\r\n");
		strBuild.append("p2PositionX = " +this.pawnPositionArray[2]+ "\r\n");
		strBuild.append("p2PositionY = " +this.pawnPositionArray[3]+ "\r\n");
		
		strBuild.append("\r\n#OtherInfo\r\n");
		strBuild.append("onTarget = "+this.onTarget+"\r\n");
		strBuild.append("removingPiece = "+this.removingPiece.getName()+this.setImages+"\r\n");
		return strBuild.toString();
		
	}
	
	
	
	
	
	/////SETTA POSIZIONE GIOCATORE, PROVA/////
	
	public void setPosPlayer1(int x, int y){
		this.player1.setPawnCurrentPositionX(x);
		this.pawnPositionArray[0]=x;
		this.player1.setPawnCurrentPositionY(y);
		this.pawnPositionArray[1]=y;
	}
	
	public void setPosPlayer2(int x, int y){
		this.player1.setPawnCurrentPositionX(x);
		this.pawnPositionArray[2]=x;
		this.player1.setPawnCurrentPositionY(y);
		this.pawnPositionArray[3]=y;
	}
	
	public void setPlayer1Name(String name){
		this.player1.setPawnName(name);
	}
	
	public void setPlayer2Name(String name){
		this.player2.setPawnName(name);
	}
	
	public String getPlayer1Name(){
		return this.player1.getPawnName();
	}
	
	public String getPlayer2Name(){
		return this.player2.getPawnName();
	}
	
	//////////////////////////////////////
	
	
	
	
	//---------------------------------------------------------------
	// STATIC METHODS
	//---------------------------------------------------------------
	
	
	public static IModel getInstance() throws IOException{
		if (instance == null)
			instance = new Model();
		return instance;
	}
	
}

	
	

