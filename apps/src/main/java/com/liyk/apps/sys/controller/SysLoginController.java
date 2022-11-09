package com.liyk.apps.sys.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liyk.apps.security.JwtAuthenticatioToken;
import com.liyk.apps.sys.pojo.SysUser;
import com.liyk.apps.sys.service.SysUserService;
import com.liyk.apps.sys.vo.LoginBean;
import com.liyk.apps.util.PasswordUtils;
import com.liyk.apps.util.RedisUtils;
import com.liyk.apps.util.SecurityUtils;
import com.liyk.tools.common.util.IOUtils;
import com.liyk.tools.core.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import sun.misc.BASE64Encoder;

/**
 * 登录控制器
 * @author Louis
 * @date Jan 14, 2019
 */
@RestController
@CrossOrigin
public class SysLoginController {

	@Autowired
	private Producer producer;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("kaptcha")
	public Map<String,String> getKaptcha() throws IOException {
		// 以当前毫秒数生成随机key，注意高并发情况下，这不是一种好的选择
		String key = System.currentTimeMillis()+"";
		String text = producer.createText();
		// 将生成的验证码保存到redis中，并设置有效期为1分种
		RedisUtils.set(key,text,60);
		BufferedImage image = producer.createImage(text);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(image,"png",outputStream);
		BASE64Encoder encoder = new BASE64Encoder();
		String encode = encoder.encode(outputStream.toByteArray());
		encode= "data:image/png;base64,"+encode;
		Map<String,String> codes = new HashMap<>();
		codes.put("key",key);
		codes.put("code",encode);
		return codes;
	}
	/**
	 * 登录接口
	 */
	@PostMapping(value = "/login")
	public HttpResult login(@RequestBody LoginBean loginBean, HttpServletRequest request) throws IOException {
		String username = loginBean.getAccount();
		String password = loginBean.getPassword();
		String captcha = loginBean.getCaptcha();
		String unique = loginBean.getUnique();
		/*// 从session中获取之前保存的验证码跟前台传来的验证码进行匹配
		Object kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);*/
		Object kaptcha = RedisUtils.get(unique);
		if(kaptcha == null){
			return HttpResult.error("验证码已失效");
		}
		if(!captcha.equals(kaptcha)){
			return HttpResult.error("验证码不正确");
		}
		RedisUtils.del(unique);
		// 用户信息
		SysUser user = sysUserService.findByName(username);
		// 账号不存在、密码错误
		if (user == null) {
			return HttpResult.error("账号不存在");
		}
		if (!PasswordUtils.matches(user.getSalt(), password, user.getPassword())) {
			return HttpResult.error("密码不正确");
		}
		// 账号锁定
		if (user.getStatus() == 0) {
			return HttpResult.error("账号已被锁定,请联系管理员");
		}
		// 系统登录认证
		JwtAuthenticatioToken token = SecurityUtils.login(request, username, password, authenticationManager);
		return HttpResult.ok(token);
	}

}
