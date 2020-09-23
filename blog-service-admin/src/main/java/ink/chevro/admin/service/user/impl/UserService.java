package ink.chevro.admin.service.user.impl;

import base.BaseService;
import ink.chevro.admin.entity.user.User;
import ink.chevro.admin.mybatis.mapper.user.UserMapper;
import ink.chevro.admin.service.user.IUserService;
import ink.chevro.user.UserRegisterSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  18:18 2019-11-08
 **/
@Service
public class UserService extends BaseService implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRegisterSender userRegisterSender;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(User user) {
        if (user == null) {
            return;
        }
        userMapper.insertSelective(user);
        try {
            // 新增邮件发送记录

            // 发送邮件消息队列
            int i = 1/0;
            userRegisterSender.sendMail(user.getEmail());
        } catch (Exception e) {
            // 检查邮件发送次数，如果大于3次，转入人工处理记录
            e.printStackTrace();
            // 如果邮件发送次数小于3次，则将该消息投入死信队列
            userRegisterSender.sendMailDelay(user.getEmail());
        }
        // 更新邮件发送状态（防止服务器宕机，可以写一个定时任务定时扫描邮件记录表状态，如果状态异常则重发）

        // 发送短信消息队列
        userRegisterSender.sendMsg(user.getPhone());
    }
}
