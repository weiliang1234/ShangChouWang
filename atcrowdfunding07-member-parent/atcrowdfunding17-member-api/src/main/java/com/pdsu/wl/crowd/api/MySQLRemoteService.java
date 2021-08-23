package com.pdsu.wl.crowd.api;

import com.pdsu.wl.crowd.entity.po.MemberPO;
import com.pdsu.wl.crowd.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wl
 * @Date 2021/8/15 15:38
 */
@FeignClient("ShangChouWang-crowd-mysql")
public interface MySQLRemoteService {

    @RequestMapping("/get/memberPO/loginAcct/remote")
    ResultEntity<MemberPO> getMemberPOLoginAcctRemote(@RequestParam("loginacct") String loginacct);


    @RequestMapping("/save/member/remote")
    public ResultEntity<String> saveMember(@RequestBody MemberPO memberPO);


}
