package edu.prj.bean;

public class Question {
	private Integer questionId;
	private Integer qTypeId;
	private String question;
	private String itemA;
	private String itemB;
	private String itemC;
	private String itemD;
	private String answer;
	private Integer subjectId;
	private String Tag;
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getItemA() {
		return itemA;
	}
	public void setItemA(String itemA) {
		this.itemA = itemA;
	}
	public String getItemB() {
		return itemB;
	}
	public void setItemB(String itemB) {
		this.itemB = itemB;
	}
	public String getItemC() {
		return itemC;
	}
	public void setItemC(String itemC) {
		this.itemC = itemC;
	}
	public String getItemD() {
		return itemD;
	}
	public void setItemD(String itemD) {
		this.itemD = itemD;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public String getTag() {
		return Tag;
	}
	public void setTag(String tag) {
		Tag = tag;
	}
	public Integer getqTypeId() {
		return qTypeId;
	}
	public void setqTypeId(Integer qTypeId) {
		this.qTypeId = qTypeId;
	}
	

}
