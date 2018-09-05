package com.cqnu5070.model;
/**
 * Score模型类
 * @author Administrator
 *
 */
public class Score {
	private int id;
	private int stuId = -1;
	private int math;
	private int english;
	private int it;
	private int mao;
	private int ma;
	private int sport;
	
	public Score(){
		super();
	}

	/*构造方法*/
	public Score(int stuId, int math, int english, int it, int mao, int ma, int sport) {
		super();
		this.stuId = stuId;
		this.math = math;
		this.english = english;
		this.it = it;
		this.mao = mao;
		this.ma = ma;
		this.sport = sport;
	}

	/*get和set方法*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStuId() {
		return stuId;
	}

	public void setStuId(int stuId) {
		this.stuId = stuId;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public int getIt() {
		return it;
	}

	public void setIt(int it) {
		this.it = it;
	}

	public int getMao() {
		return mao;
	}

	public void setMao(int mao) {
		this.mao = mao;
	}

	public int getMa() {
		return ma;
	}

	public void setMa(int ma) {
		this.ma = ma;
	}

	public int getSport() {
		return sport;
	}

	public void setSport(int sport) {
		this.sport = sport;
	}

	

}
