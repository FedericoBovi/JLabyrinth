package jlabyrinth.model;

import java.awt.geom.Ellipse2D;
import java.io.IOException;

public interface IModel {
	
	public Piece[][] getBoardArray();
	
	public void initPawns();
	
	public void fillBoard() throws IOException;
	
	public void insertPiece(int a, int b);
	
	public int getBoardDimentions();
	
	public void setBoardDimentions(int num);
	
	public Piece getSpecificPiece(int i, int j);
	
	public Piece getRemovingPiece();
	
	public void selectRemovingPiece(String pieceName);
	
	public void rotateRemovingPiece() throws IOException;
	
	public Piece getPiece(int i, int j);
	
	public void setNumberTargets(int i);
	
	public int getNumberTargets();
	
	public boolean getOnTarget();
	
	public void setCurrentTarget();
	
	public void setSpecificTarget(String target);
	
	public void setOnTarget(boolean a);
	
	public String getCurrentTarget();
	
	public int getScore(int a);
	
	public void setScore(int i, int score);
	
	public void updateScore();
	
	public void setPawnCurrentPosition(int ArrayCell, int Pos);
	
	public void reachabilityFinder(int i, int j);
	
	public void setFalseAllReachability();
	
	public void setImagesDirectory(int a);
	
	///SALVATAGGIO///	
	
	public void saveModelToFile(String file) throws java.io.IOException;
	
	public void loadModelFromFile(String file) throws java.io.FileNotFoundException,java.io.IOException;
	
	public void selectBAcell(String pieceName,int i,int j);
	
	////MOVIMENTO PEDINA////
	
	public boolean isPawnMovUp(Piece onPiece, Piece toPiece);
	
	public boolean isPawnMovDown(Piece onPiece, Piece toPiece);
	
	public boolean isPawnMovRight(Piece onPiece, Piece toPiece);
	
	public boolean isPawnMovLeft(Piece onPiece, Piece toPiece);
	
	public void setPawnCurrentPositionX(int newPosX);
	
	public void setPawnCurrentPositionY(int newPosY);
	
	public int getPawnCurrentPositionX(int i);
	
	public int getPawnCurrentPositionY(int i);
	
	public Ellipse2D.Double getPawnShape();
	
	////GESTIONE TURNO////
	
	public void setTurn(int i);
	
	public int getTurn();
	
	////SETTA POSIZIONE GIOCATORE, PROVA////
	
	public void setPosPlayer1(int x, int y);
	
	public void setPosPlayer2(int x, int y);
	
	public void setPlayer1Name(String name);
	
	public void setPlayer2Name(String name);
	
	public String getPlayer1Name();
	
	public String getPlayer2Name();
	
	public int controlSamePositionX();
	
	public int controlSamePositionY();
	
	////////////////////////////////////////
	
}
