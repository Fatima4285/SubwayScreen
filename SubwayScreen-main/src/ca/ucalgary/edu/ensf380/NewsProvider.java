package ca.ucalgary.edu.ensf380;

class NewsProvider implements Displayable{
	
	/**
	 * Represents a provider of news with a headline and content.
	 * Implements the Displayable interface to allow displaying news.
	 */
	
	private String headline;
	private String content;
	
	//constructors
	/**
     * Constructs a new NewsProvider with the specified headline and content.
     * 
     * @param headline the headline of the news
     * @param content the content of the news
     */
	public NewsProvider(String headline, String content) {
		this.headline = headline;
		this.content = content;
	}
	
	//setters
	/**
     * Sets the headline of the news.
     * 
     * @param headline the new headline of the news
     */
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	
	/**
     * Sets the content of the news.
     * 
     * @param content the new content of the news
     */
	public void setContent(String content) {
		this.content = content;
	}
	
	//getters
	/**
     * Gets the headline of the news.
     * 
     * @return the headline
     */
	public String getHeadline() {
		return this.headline;
	}
	
	 /**
     * Gets the content of the news.
     * 
     * @return the content
     */
	public String getContent() {
		return this.content;
	}
	
	/**
     * displays and defines how the news is displayed.
     * 
     * @param newsApi the NewsAPI used to fetch or display news
     */
	public void display() {
		//IMPLEMENT SOMETHING
		//implements from Displayable
	}
	
}
