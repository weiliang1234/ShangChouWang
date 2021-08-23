package com.pdsu.wl.crowd.handle;

import com.pdsu.wl.crowd.constant.CrowdConstant;
import com.pdsu.wl.crowd.entity.po.MemberPO;
import com.pdsu.wl.crowd.service.MemberService;
import com.pdsu.wl.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wl
 * @Date 2021/8/15 15:53
 */

@RestController
public class MemberProviderHandle {

    @Autowired
    private MemberService memberService;

    @RequestMapping("/save/member/remote")
    public ResultEntity<String> saveMember(@RequestBody MemberPO memberPO) {
        
        try {
            memberService.saveMember(memberPO);
        }catch (Exception e) {
            // 账号被占用
            if (e instanceof DuplicateKeyException) {
                return ResultEntity.Failed(CrowdConstant.MESSAGE_LOGIN_ALREADY_IN_USE);
            }
            return ResultEntity.Failed(e.getMessage());
        }
        return null;
    }

    @RequestMapping("/get/memberPO/loginAcct/remote")
    ResultEntity<MemberPO> getMemberPOLoginAcctRemote(@RequestParam("loginacct") String loginacct) {

        try {
            MemberPO memberPO = memberService.getMemberPOLoginAcct(loginacct);
            return ResultEntity.successWithData(memberPO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.Failed(e.getMessage());
        }
    }

}
