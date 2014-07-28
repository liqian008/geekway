package com.bruce.geekway.model;


public class KlhWallImageStatBean{

	private int imageId;
	private int browseAmount;
    private long likeAmount;
    
    public KlhWallImageStatBean(){
    	super();
    }

	public KlhWallImageStatBean(int browseAmount, long likeAmount) {
		super();
		this.imageId = imageId;
		this.browseAmount = browseAmount;
		this.likeAmount = likeAmount;
	}

	public int getBrowseAmount() {
		return browseAmount;
	}

	public void setBrowseAmount(int browseAmount) {
		this.browseAmount = browseAmount;
	}

	public long getLikeAmount() {
		return likeAmount;
	}

	public void setLikeAmount(long likeAmount) {
		this.likeAmount = likeAmount;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
    
	
   
    
}