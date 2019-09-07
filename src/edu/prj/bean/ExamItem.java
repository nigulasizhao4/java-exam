package edu.prj.bean;

public class ExamItem {
	private Integer itemId;
	private Integer examId;
	private Integer questionId;
	private String stuAnswer;
	private String stdAnswer;
	private Double stdScore;
	private Integer markResult;
	private Double gainScore;
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getExamId() {
		return examId;
	}
	public void setExamId(Integer examId) {
		this.examId = examId;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getStuAnswer() {
		return stuAnswer;
	}
	public void setStuAnswer(String stuAnswer) {
		this.stuAnswer = stuAnswer;
	}
	public String getStdAnswer() {
		return stdAnswer;
	}
	public void setStdAnswer(String stdAnswer) {
		this.stdAnswer = stdAnswer;
	}
	public Double getStdScore() {
		return stdScore;
	}
	public void setStdScore(Double stdScore) {
		this.stdScore = stdScore;
	}
	public Integer getMarkResult() {
		return markResult;
	}
	public void setMarkResult(Integer markResult) {
		this.markResult = markResult;
	}
	public Double getGainScore() {
		return gainScore;
	}
	public void setGainScore(Double gainScore) {
		this.gainScore = gainScore;
	}
	

}
