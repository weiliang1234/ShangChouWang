package com.pdsu.wl.crowd.Handle;

import com.pdsu.wl.crowd.api.MySQLRemoteService;
import com.pdsu.wl.crowd.api.RedisRemoteService;
import com.pdsu.wl.crowd.config.ShortMessageProperties;
import com.pdsu.wl.crowd.constant.CrowdConstant;
import com.pdsu.wl.crowd.entity.po.MemberPO;
import com.pdsu.wl.crowd.entity.vo.MemberLoginVO;
import com.pdsu.wl.crowd.entity.vo.MemberVO;
import com.pdsu.wl.crowd.util.CrowdUtil;
import com.pdsu.wl.crowd.util.ResultEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author wl
 * @Date 2021/8/22 15:12
 */
@Controller
public class MemberHandler {

    @Autowired
    private ShortMessageProperties shortMessageProperties;

    @Autowired
    private RedisRemoteService redisRemoteService;

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/auth/member/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 注销session
        return "redirect:/"; // 返回首页
    }

    /**
     * 会员登录验证
     * @param loginacct
     * @param userpswd
     * @param modelMap
     * @param session
     * @return
     */
    @RequestMapping("/auth/member/do/login")
    public String login(@RequestParam("loginacct") String loginacct,
                        @RequestParam("userpswd") String userpswd,
                        ModelMap modelMap,
                        HttpSession session) {

        final ResultEntity<MemberPO> memberPOResultEntity = mySQLRemoteService.getMemberPOLoginAcctRemote(loginacct);
        if (ResultEntity.Failed.equals(memberPOResultEntity.getResult())) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, memberPOResultEntity.getMessage());
            return "member-login";
        }

        final MemberPO data = memberPOResultEntity.getData();
        if (data == null) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_LOGIN_FAILED);
            return "member-login";
        }

        // 比较密码
        final String userpswd1 = data.getUserpswd();
        final String md5 = CrowdUtil.MD5(userpswd);
        if (Objects.equals(userpswd1, md5)) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_LOGIN_FAILED);
            final MemberLoginVO memberLoginVO = new MemberLoginVO(data.getId(), data.getUsername(), data.getEmail());
            session.setAttribute("member", memberLoginVO);
            return "redirect:/auth/member/to/center/page";
        } else {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_LOGIN_FAILED);
            return "member-login";
        }
    }

    /**
     * 会员注册验证
     * @param memberVO
     * @param modelMap
     * @return
     */
    @RequestMapping("/auth/do/member/register")
    public String register(MemberVO memberVO, ModelMap modelMap) {

        // 1.获取用户输入的手机号
        final String phoneNum = memberVO.getPhoneNum();

        // 2.拼Redis读取Key对应的值value
        String key = CrowdConstant.REDIS_CODE_PREFIX + phoneNum;

        // 3.从Redis读取Key对应的value
        final ResultEntity<String> redisStringValueByKey = redisRemoteService.getRedisStringValueByKey(key);

        // 4.检查查询操作是否有效
        if (ResultEntity.Failed.equals(redisStringValueByKey.getResult())) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, redisStringValueByKey.getMessage());
            return "member-reg";
        }

        String redisCode = redisStringValueByKey.getData();
        if (redisCode == null) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_CODE_NOT_EXISTS);
            return "member-reg";
        }
        // 5.如果能够到value则比较表单的验证码和redis的验证码
        String formCode = memberVO.getCode();
        if (!Objects.equals(formCode, redisCode)) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_CODE_INVALID);
            return "member-reg";
        }
        // 6.如果验证码一致，则从redis删除
        redisRemoteService.removeRedisByKeyRemote(key);
        // 7.执行密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String userpswd = memberVO.getUserpswd();
        String encode = passwordEncoder.encode(userpswd);
        memberVO.setUserpswd(encode);
        // 8.执行保存
        // 创建空的MemberPO对象
        MemberPO memberPO = new MemberPO();
        // 复制属性
        BeanUtils.copyProperties(memberVO, memberPO);
        // 调用远程方法
        ResultEntity<String> saveMemberResultEntity = mySQLRemoteService.saveMember(memberPO);
        if (ResultEntity.Failed.equals(saveMemberResultEntity)) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, saveMemberResultEntity.getMessage());
            return "member-reg";
        }
        return "redirect:/auth/member/to/login/page";

    }

    /**
     * 会员注册发送验证码
     * @param phoneNum
     * @return
     */
    @ResponseBody
    @RequestMapping("/auth/member/send/short/message.json")
    public ResultEntity<String> sendMessage(@RequestParam("phoneNum") String phoneNum) {

        // 1.发送验证码到 phoneNum 手机
        final ResultEntity<String> sendShortMessageResult = CrowdUtil.sendShortMessage(
                shortMessageProperties.getHost(),
                shortMessageProperties.getPath(),
                shortMessageProperties.getMethod(),
                phoneNum,
                shortMessageProperties.getAppcode(),
                shortMessageProperties.getSign(),
                shortMessageProperties.getSkin());

        // 2.判断验证码是否发送成功
        if (ResultEntity.SUCCESS.equals(sendShortMessageResult.getResult())) {

            // 3.如果发送成功将验证码存入到Redis
            String code = sendShortMessageResult.getData();
            String key = CrowdConstant.REDIS_CODE_PREFIX + phoneNum;

            final ResultEntity<String> resultEntity = redisRemoteService.setRedisKeyValueRemoteWithTimeOut(key, code, 5, TimeUnit.MINUTES);

            if (ResultEntity.SUCCESS.equals(resultEntity.getResult())) {
                return ResultEntity.successWithoutData();
            } else {
                return resultEntity;
            }
        } else {
            return sendShortMessageResult;
        }
    }
}
