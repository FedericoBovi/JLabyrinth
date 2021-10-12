package jlabyrinth.model;

public class Target {
	
	final static String[] AVAIBLE_TARGETS = new String[7];
	static{
		AVAIBLE_TARGETS[0] = "PieceLMouse"+Piece.getImgString();
		AVAIBLE_TARGETS[1] = "PieceLOwl"+Piece.getImgString();
		AVAIBLE_TARGETS[2] = "PieceTDragon"+Piece.getImgString();
		AVAIBLE_TARGETS[3] = "PieceLSalamander"+Piece.getImgString();
		AVAIBLE_TARGETS[4] = "PieceTFairy"+Piece.getImgString();
		AVAIBLE_TARGETS[5] = "PieceTGhost"+Piece.getImgString();
		AVAIBLE_TARGETS[6] = "PieceTBat"+Piece.getImgString();		
	}
	
	public static String getRandomTarget(){
		String currentTarget= null;
		int a=0;
		do{
		a = (int) (Math.random()*AVAIBLE_TARGETS.length)%AVAIBLE_TARGETS.length;
//		System.out.println("Target: "+a);
		}
		while(AVAIBLE_TARGETS[a]==null);
		currentTarget= AVAIBLE_TARGETS[a];
		System.out.println("7*7_CurrentTarget: "+AVAIBLE_TARGETS[a]);
		AVAIBLE_TARGETS[a]=null;
		return currentTarget;
	}
	
	public static String getRandom5Target(){
		AVAIBLE_TARGETS[6]= null;
		AVAIBLE_TARGETS[5]= null;
		String currentTarget= null;
		int a=0;
		do{
		a = (int) (Math.random()*AVAIBLE_TARGETS.length)%AVAIBLE_TARGETS.length;
		System.out.println("5*5Target: "+a);
		}
		while(AVAIBLE_TARGETS[a]==null);
		currentTarget= AVAIBLE_TARGETS[a];
		AVAIBLE_TARGETS[a]=null;
		return currentTarget;
	}
	
	public static String getTargetFromTargetArray(int i){
		String str="";
		str = AVAIBLE_TARGETS[i];
		return str;
	}
	
	public static void setTargetOfTargetArray(int i, String str){
		AVAIBLE_TARGETS[i] = str;
	}
}
