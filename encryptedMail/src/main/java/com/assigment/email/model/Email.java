package com.assigment.email.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.assigment.email.util.AppUtil;


public class Email {

	private String from;

	private List<String> to;

	private List<String> cc;

	private String subject;

	private String message;

	private boolean isHtml;

	private boolean isOutsideOrg;

	private boolean retryEnabled;

	private boolean isDESEncrypted;

	private boolean isAESEncrypted;

	public Email() {
		this.to = new ArrayList<String>();
		this.cc = new ArrayList<String>();
	}

	public Email(String from, String toList, String subject, String message) {
		this();
		this.from = from;
		this.subject = subject;
		this.message = message;
		this.to.addAll(Arrays.asList(splitByComma(toList)));
	}

	public Email(String from, String toList, String subject, String message, boolean isHtml, boolean isOutsideOrg, boolean retryEnabled, boolean isDESEncrypted, boolean isAESEncrypted) {
		this();
	    this.from = from;
		this.subject = subject;
		this.message = message;
		this.to.addAll(Arrays.asList(splitByComma(toList)));
		this.isHtml = isHtml;
		this.isOutsideOrg = isOutsideOrg;
		this.retryEnabled = retryEnabled;
		this.isDESEncrypted = isDESEncrypted;
		this.isAESEncrypted = isAESEncrypted;
	}


	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public List<String> getCc() {
		return cc;
	}

	public void setCc(List<String> cc) {
		this.cc = cc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isHtml() {
		return isHtml;
	}

	public void setHtml(boolean isHtml) {
		this.isHtml = isHtml;
	}

	public boolean isOutsideOrg() {
		return isOutsideOrg;
	}

	public void setOutsideOrg(boolean isOutsideOrg) {
		this.isOutsideOrg = isOutsideOrg;
	}

	public boolean retryEnabled() {
		return retryEnabled;
	}

	public void setRetry(boolean retryEnabled) {
		this.retryEnabled = retryEnabled;
	}

	public boolean isDESEncrypted() {
		return isDESEncrypted;
	}

	public void setDesEncryption(boolean isDESEncrypted) {
		this.isDESEncrypted = isDESEncrypted;
	}

	public boolean isAESEncrypted() {
		return isAESEncrypted;
	}

	public void setAesEncryption(boolean isAESEncrypted) {
		this.isAESEncrypted = isAESEncrypted;
	}

	private String[] splitByComma(String toMultiple) {
		String[] toSplit = toMultiple.split(",");
		return toSplit;
	}

	public String getToAsList() {
		return AppUtil.concatenate(this.to, ",");
	}

	@Override
	public String toString() {
		return "Email{" +
				"from='" + from + '\'' +
				", to='" + to + '\'' +
				", cc='" + cc + '\'' +
				", subject=" + subject +
				", message=" + message +
				", isHtml=" + isHtml +
				'}';
	}
}
