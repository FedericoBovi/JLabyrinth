package jlabyrinth.controller;

import java.awt.geom.Ellipse2D;
import java.io.IOException;

import jlabyrinth.model.Model;
import jlabyrinth.model.Piece;
import jlabyrinth.view.View;
import jlabyrinth.controller.ControllerForView;
import jlabyrinth.controller.IControllerForView;

public class ControllerForView implements IControllerForView {
	
	//---------------------------------------------------------------
	// STATIC FIELDS
	//---------------------------------------------------------------
	
	private ControllerForView(){
		//to-do
	}
	
	private static ControllerForView instance = null;
	
	public void openMainGUI(){
		View.getInstance().openMainGUI();
	}
	
	public void closeStartWindow(){
		View.getInstance().closeStartWindow();
	}
	
	public void closeNewGameWindow(){
		View.getInstance().closeNewGameWindow();
	}
	
	public void openNewGameWindow(){
		View.getInstance().openNewGameWindow();
	}
	
	public void openStartWindow(){
		View.getInstance().openStartWindow();
	}
	
	public void isOnTarget() throws IOException{
		Model.getInstance().updateScore();
		
		if(ControllerForView.getInstance().getScore(0)+ControllerForView.getInstance().getScore(1)==ControllerForView.getInstance().getNumberTargets()*100){
			View.getInstance().showWinnerMessage();
		}
		else{
			Model.getInstance().setCurrentTarget();
			View.getInstance().updateScoreAndTargetLabel(Model.getInstance().getScore(0), Model.getInstance().getScore(1), Model.getInstance().getCurrentTarget());
		}
	}
	
	public boolean getOnTarget() throws IOException{
		return Model.getInstance().getOnTarget();
	}
	
	public int getBoardDimentions() throws IOException{
		return Model.getInstance().getBoardDimentions();
	}
	
	public void setBoardDimentions(int num) throws IOException{
		Model.getInstance().setBoardDimentions(num);
	}
	
	public void rotateRemovingPiece() throws IOException{
		Model.getInstance().rotateRemovingPiece();
	}
	
	public void initPawns() throws IOException{
		Model.getInstance().initPawns();
	}
	
	public void fillBoard() throws IOException{
		Model.getInstance().fillBoard();
	}
	
	public Piece[][] getBoardArray() throws IOException{
		return Model.getInstance().getBoardArray();
	}
	
	public void insertPiece(int i, int j) throws IOException{
		Model.getInstance().insertPiece(i, j);
	}
	
	public Piece getPiece(int i ,int j) throws IOException{
		return Model.getInstance().getPiece(i, j);
	}
	
	public Piece getRemovingPiece() throws IOException{
		return Model.getInstance().getRemovingPiece();
	}
	
	public void setNumberTargets(int i) throws IOException{
		Model.getInstance().setNumberTargets(i);
	}
	
	public int getNumberTargets() throws IOException{
		return Model.getInstance().getNumberTargets();
	}
	
	public void setCurrentTarget() throws IOException{
		Model.getInstance().setCurrentTarget();
	}
	
	public String getCurrentTarget() throws IOException{
		return Model.getInstance().getCurrentTarget();
	}
	
	public int getScore(int a) throws IOException{
		return Model.getInstance().getScore(a);
	}
	
	public void updateScore() throws IOException{
		Model.getInstance().updateScore();
	}
	
	public void reachabilityFinder(int i, int j) throws IOException{
		Model.getInstance().reachabilityFinder(i, j);
	}
	
	public void setFalseAllReachability() throws IOException{
		Model.getInstance().setFalseAllReachability();
	}
	
	////METODI MOVIMENTO PEDINA////
	
	public boolean isPawnMovUp(Piece onPiece, Piece toPiece) throws IOException{
		return Model.getInstance().isPawnMovUp(onPiece, toPiece);
	}
	
	public boolean isPawnMovDown(Piece onPiece, Piece toPiece) throws IOException{
		return Model.getInstance().isPawnMovDown(onPiece, toPiece);
	}
	
	public boolean isPawnMovRight(Piece onPiece, Piece toPiece) throws IOException{
		return Model.getInstance().isPawnMovRight(onPiece, toPiece);
	}
	
	public boolean isPawnMovLeft(Piece onPiece, Piece toPiece) throws IOException{
		return Model.getInstance().isPawnMovLeft(onPiece, toPiece);
	}
	
	public int getPawnCurrentPositionX(int i) throws IOException {
		return Model.getInstance().getPawnCurrentPositionX( i);
	}
	
	public int getPawnCurrentPositionY(int i) throws IOException {
		return Model.getInstance().getPawnCurrentPositionY(i);
	}
	
	public void setPawnCurrentPositionX(int newPosX) throws IOException{
		Model.getInstance().setPawnCurrentPositionX(newPosX);
	}
	
	public void setPawnCurrentPositionY(int newPosY) throws IOException{
		Model.getInstance().setPawnCurrentPositionY(newPosY);
	}
	
	public void setImagesDirectory(int a) throws IOException{
		Model.getInstance().setImagesDirectory(a);
	}
	
	public Ellipse2D.Double getPawnShape() throws IOException{
		return Model.getInstance().getPawnShape();
	}
	
	////METODI GESTIONE TURNO////
	
	public void setTurn(int i) throws IOException{
		Model.getInstance().setTurn(i);
	}
	
	public int getTurn() throws IOException{
		return Model.getInstance().getTurn();
	}
	
	/////SETTA POSIZIONE GIOCATORE, PROVA/////
	
	public void setPosPlayer1(int x, int y) throws IOException{
		Model.getInstance().setPosPlayer1(x, y);
	}
	
	public void setPosPlayer2(int x, int y) throws IOException{
		Model.getInstance().setPosPlayer2(x, y);
	}
	
	public void setPlayer1Name(String name) throws IOException{
		Model.getInstance().setPlayer1Name(name);
	}
	
	public void setPlayer2Name(String name) throws IOException{
		Model.getInstance().setPlayer2Name(name);
	}
	
	public String getPlayer1Name() throws IOException{
		return Model.getInstance().getPlayer1Name();
	}
	
	public String getPlayer2Name() throws IOException{
		return Model.getInstance().getPlayer2Name();
	}
	
	public int controlSamePositionX() throws IOException{
		return Model.getInstance().controlSamePositionX();
	}
	
	public int controlSamePositionY() throws IOException{
		return Model.getInstance().controlSamePositionY();
	}
	
	//////////////////////////////////////
	
	
	
	
	
	//---------------------------------------------------------------
	// STATIC METHODS
	//---------------------------------------------------------------
	
	public static IControllerForView getInstance() {
		if (instance == null)
			instance = new ControllerForView();
		return instance;
	}
		
		


}
