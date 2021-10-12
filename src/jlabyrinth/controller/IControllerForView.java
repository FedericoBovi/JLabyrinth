package jlabyrinth.controller;

import java.awt.geom.Ellipse2D;
import java.io.IOException;

import jlabyrinth.model.Model;
import jlabyrinth.model.Piece;

public interface IControllerForView {

	public void openMainGUI();
	
	public void closeStartWindow();
	
	public void closeNewGameWindow();
	
	public void openNewGameWindow();
	
	public void openStartWindow();
	
	public void isOnTarget() throws IOException;
	
	public boolean getOnTarget() throws IOException;
	
	public void rotateRemovingPiece() throws IOException;
	
	public int getBoardDimentions() throws IOException;
	
	public void setBoardDimentions(int num) throws IOException;
	
	public void initPawns() throws IOException;
	
	public void fillBoard() throws IOException;
	
	public Piece[][] getBoardArray() throws IOException;
	
	public void insertPiece(int i ,int j) throws IOException;
	
	public Piece getPiece(int i ,int j) throws IOException;
	
	public Piece getRemovingPiece() throws IOException;
	
	public void setNumberTargets(int i) throws IOException;
	
	public int getNumberTargets() throws IOException;
	
	public void setCurrentTarget() throws IOException;
	
	public String getCurrentTarget() throws IOException;
	
	public int getScore(int a) throws IOException;
	
	public void updateScore() throws IOException;
	
	public void setImagesDirectory(int a) throws IOException;
	
	////METODI MOVIMENTO PEDINA////
	
	public boolean isPawnMovUp(Piece onPiece, Piece toPiece) throws IOException;
	
	public boolean isPawnMovDown(Piece onPiece, Piece toPiece) throws IOException;
	
	public boolean isPawnMovRight(Piece onPiece, Piece toPiece) throws IOException;
	
	public boolean isPawnMovLeft(Piece onPiece, Piece toPiece) throws IOException;
	
	public int getPawnCurrentPositionX(int i) throws IOException;
	
	public int getPawnCurrentPositionY(int i) throws IOException;
	
	public void setPawnCurrentPositionX(int newPosX) throws IOException;
	
	public void setPawnCurrentPositionY(int newPosY) throws IOException;
	
	public Ellipse2D.Double getPawnShape() throws IOException;
	
	public void reachabilityFinder(int i, int j) throws IOException;
	
	public void setFalseAllReachability() throws IOException;
	
	////METODI GESTIONE TURNO////
	
	public void setTurn(int i) throws IOException;
	
	public int getTurn() throws IOException;
	
	/////SETTA POSIZIONE GIOCATORE, PROVA/////
	
	public void setPosPlayer1(int x, int y) throws IOException;
	
	public void setPosPlayer2(int x, int y) throws IOException;
	
	public void setPlayer1Name(String name) throws IOException;
	
	public void setPlayer2Name(String name) throws IOException;
	
	public String getPlayer1Name() throws IOException;
	
	public String getPlayer2Name() throws IOException;
	
	public int controlSamePositionX() throws IOException;
	
	public int controlSamePositionY() throws IOException;
	
	//////////////////////////////////////
	

}
