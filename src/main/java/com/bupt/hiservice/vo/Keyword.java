package com.bupt.hiservice.vo;

import java.io.Serializable;

public class Keyword implements Serializable {

	private static final long serialVersionUID = 6921352976292525392L;
	
	// 关键词文本
	private String text;
	// 关键词得分
	private float score;
	
	public Keyword() {}

	public Keyword(String text, float score) {
		super();
		this.text = text;
		this.score = score;
	}

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public float getScore() {
		return score;
	}
	
	public void setScore(float score) {
		this.score = score;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Keyword other = (Keyword) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Keyword [text=" + text + ", score=" + score + "]";
	}

}
