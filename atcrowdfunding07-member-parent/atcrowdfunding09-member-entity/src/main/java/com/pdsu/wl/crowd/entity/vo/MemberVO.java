package com.pdsu.wl.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author wl
 * @Date 2021/8/23 8:22
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {

    private String loginacct;

    private String username;

    private String userpswd;

    private String email;

    private String phoneNum;

    private String code;
}
