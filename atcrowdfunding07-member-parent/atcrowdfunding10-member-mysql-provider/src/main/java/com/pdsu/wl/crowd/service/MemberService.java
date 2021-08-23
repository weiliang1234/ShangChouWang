package com.pdsu.wl.crowd.service;

import com.pdsu.wl.crowd.entity.po.MemberPO;

/**
 * @author wl
 * @Date 2021/8/15 15:57
 */
public interface MemberService {

    MemberPO getMemberPOLoginAcct(String loginacct);

    void saveMember(MemberPO memberPO);

}
