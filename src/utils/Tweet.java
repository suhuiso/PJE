package utils;

import java.util.Date;

public class Tweet {

	private Long id;

	private String twittos;

	private String msg;

	private Date date;

	private String request;

	public Tweet ( Long id, String twittos, String msg, Date date, String request ) {
		this.id = id;
		this.twittos = twittos;
		this.msg = msg;
		this.date = date;
		this.request = request;
	}

	public Long getId () {
		return this.id;
	}

	public String getTwittos () {
		return this.twittos;
	}

	public String getMsg () {
		return this.msg;
	}

	public Date getDate () {
		return this.date;
	}

	public String getRequest () {
		return this.request;
	}

	@Override
	public int hashCode () {
		return this.getId().hashCode();
	}

}
