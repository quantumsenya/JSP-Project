package com.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberVO {
	private int mno;
	private String id;
	private String pwd;
	private String name;
	private String nickname;
	private String phone;
	private String email;
	private Date joinDate;
	private MemberGrade grade;
	
	private String origPwd;
	
	public enum MemberGrade{
		운영자, 관리자, 공식채널, 우수회원, 정회원, 준회원
	}
}
