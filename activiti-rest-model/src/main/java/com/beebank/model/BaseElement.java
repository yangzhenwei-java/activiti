package com.beebank.model;

public class BaseElement {
	 protected String id;
	  protected int xmlRowNumber;
	  protected int xmlColumnNumber;

	  public String getId() {
	    return id;
	  }

	  public void setId(String id) {
	    this.id = id;
	  }

	  public int getXmlRowNumber() {
	    return xmlRowNumber;
	  }

	  public void setXmlRowNumber(int xmlRowNumber) {
	    this.xmlRowNumber = xmlRowNumber;
	  }

	  public int getXmlColumnNumber() {
	    return xmlColumnNumber;
	  }

	  public void setXmlColumnNumber(int xmlColumnNumber) {
	    this.xmlColumnNumber = xmlColumnNumber;
	  }
}
