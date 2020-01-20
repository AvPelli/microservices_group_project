package be.ugent.groep10.gamblings.domain;

public class Score {

	private int scoreA;
	private int scoreB;
	private boolean isSet;
	

	public Score() {
		this.isSet = false;
	}
	
	public Score(int scoreA, int scoreB) {
		super();
		this.scoreA = scoreA;
		this.scoreB = scoreB;
		this.isSet = true;
	}

	public boolean isSet() {
		return isSet;
	}

	public void setSet(boolean isSet) {
		this.isSet = isSet;
	}

	public int getScoreA() {
		return scoreA;
	}

	public void setScoreA(int scoreA) {
		this.scoreA = scoreA;
	}

	public int getScoreB() {
		return scoreB;
	}

	public void setScoreB(int scoreB) {
		this.scoreB = scoreB;
	}

	@Override
	public String toString() {
		return "Score [scoreA=" + scoreA + ", scoreB=" + scoreB + ", isSet=" + isSet + "]";
	}

	

}
