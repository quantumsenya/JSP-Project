package com.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardVO {
	private int bno;
	private String category;
	private String title;
	private String writer;
	private String writernick;
	private String content;
	private String imageFile;
	private Date writeDate;
	private String isend;
	private int suggest;
	private int views;
	private int replyCount;
}
