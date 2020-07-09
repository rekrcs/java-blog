package com.sbs.java.blog.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Member;

public class MemberController extends Controller {

	public MemberController(Connection dbConnection, String actionMethodName, HttpServletRequest request,
			HttpServletResponse response) {
		super(dbConnection, actionMethodName, request, response);
	}

	@Override
	public String doAction() {
		switch (actionMethodName) {
		case "join":
			return doActionJoin(request, response);
		case "doJoin":
			return doActionDoJoin(request, response);
		}
		return "";

	}

	private String doActionDoJoin(HttpServletRequest request, HttpServletResponse response) {
		List<Member> members = memberService.getForPrintMembers();
		
		String loginId = request.getParameter("loginId");
		String name = request.getParameter("name");
		String nickName = request.getParameter("nickName");
		String loginPw = request.getParameter("loginPw");
		String loginPwConfirm = request.getParameter("loginPwConfirm");
		
		for(Member member : members) {
			if(member.getLoginId().equals(loginId)) {
				return "html:<script> alert('이미 존재하는 아이디 입니다.'); location.replace('join'); </script>"; 
			}
		}
		
		for(Member member : members) {
			if(member.getNickName().equals(nickName)) {
				return "html:<script> alert('이미 존재하는 닉네임 입니다.'); location.replace('join'); </script>"; 
			}
		}
		
		if (!loginPw.equals(loginPwConfirm)) {
			return "html:<script> alert('비번과 비번 확인이 일치 하지 않습니다.'); location.replace('join'); </script>";

		} else {
			int id = memberService.join(loginId, name, nickName, loginPw);
			return "html:<script> alert('" + id + "번 회원님이 가입 했습니다.'); location.replace('join'); </script>";
		}
	}

	private String doActionJoin(HttpServletRequest request, HttpServletResponse response) {
		return "member/join.jsp";
	}

}
