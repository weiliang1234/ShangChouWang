package com.pdsu.wl.crowd.service.Impl;

import com.pdsu.wl.crowd.entity.po.MemberPO;
import com.pdsu.wl.crowd.entity.po.MemberPOExample;
import com.pdsu.wl.crowd.mapper.MemberPOMapper;
import com.pdsu.wl.crowd.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wl
 * @Date 2021/8/15 16:00
 */
@Service
@Transactional(readOnly = true)  // 设置事务为只读
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberPOMapper memberPOMapper;

    @Override
    public MemberPO getMemberPOLoginAcct(String loginacct) {

        final MemberPOExample memberPOExample = new MemberPOExample();
        final MemberPOExample.Criteria criteria = memberPOExample.createCriteria();
        criteria.andLoginacctEqualTo(loginacct);
        final List<MemberPO> memberPOS = memberPOMapper.selectByExample(memberPOExample);

        if (memberPOS == null || memberPOS.size() == 0) {
            return null;
        }
        return memberPOS.get(0);
    }

    // 设置一个新的事务,禁用只读事务
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class, readOnly = false)
    @Override
    public void saveMember(MemberPO memberPO) {
        memberPOMapper.insertSelective(memberPO);
    }
}
