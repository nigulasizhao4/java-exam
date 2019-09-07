package edu.prj.bean;

public class Paper {
	private Integer paperId;
	private String paperName;
	private Double totalScore;
	private Double perScore;
	private Integer questionNum;
	private Integer examMinute;
	private String startOn;
	private String endOn;
	private Integer hasGenerate;
	private Integer subjectId;
	public Integer getPaperId() {
		return paperId;
	}
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public Double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}
	public Double getPerScore() {
		return perScore;
	}
	public void setPerScore(Double perScore) {
		this.perScore = perScore;
	}
	public Integer getQuestionNum() {
		return questionNum;
	}
	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}
	public Integer getExamMinute() {
		return examMinute;
	}
	public void setExamMinute(Integer examMinute) {
		this.examMinute = examMinute;
	}
	public String getStartOn() {
		return startOn;
	}
	public void setStartOn(String startOn) {
		this.startOn = startOn;
	}
	public String getEndOn() {
		return endOn;
	}
	public void setEndOn(String endOn) {
		this.endOn = endOn;
	}
	public Integer getHasGenerate() {
		return hasGenerate;
	}
	public void setHasGenerate(Integer hasGenerate) {
		this.hasGenerate = hasGenerate;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	

}
