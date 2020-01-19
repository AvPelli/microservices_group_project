package be.ugent.groep10.gamblings.domain;

public class Prediction {

	private int poinstTeamA;
	private int pointsTeamB;
	private double ratio;
	
	public Prediction(int poinstTeamA, int pointsTeamB, double ratio) {
		super();
		this.poinstTeamA = poinstTeamA;
		this.pointsTeamB = pointsTeamB;
		this.ratio = ratio;
	}

	public int getPoinstTeamA() {
		return poinstTeamA;
	}

	public void setPoinstTeamA(int poinstTeamA) {
		this.poinstTeamA = poinstTeamA;
	}

	public int getPointsTeamB() {
		return pointsTeamB;
	}

	public void setPointsTeamB(int pointsTeamB) {
		this.pointsTeamB = pointsTeamB;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	@Override
	public String toString() {
		return "Prediction [poinstTeamA=" + poinstTeamA + ", pointsTeamB=" + pointsTeamB + ", ratio=" + ratio + "]";
	}

	

}
