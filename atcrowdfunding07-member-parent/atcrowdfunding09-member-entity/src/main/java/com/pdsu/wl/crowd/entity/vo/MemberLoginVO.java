package com.pdsu.wl.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author wl
 * @Date 2021/8/23 11:07
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginVO {

    private Integer id;

    private String username;

    private String email;
}
