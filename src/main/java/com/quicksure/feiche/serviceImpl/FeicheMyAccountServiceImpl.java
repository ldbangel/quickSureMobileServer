package com.quicksure.feiche.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quicksure.feiche.dao.FeicheMyAccountMapper;
import com.quicksure.feiche.dao.LudifcBaseinforMapper;
import com.quicksure.feiche.entity.LudifcBaseinfor;
import com.quicksure.feiche.service.FeicheMyAccountService;
import com.quicksure.mobile.dao.WechatBindMapper;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.Userinfor;
import com.quicksure.mobile.entity.WechatBind;

@Service("feicheMyAccountService")
public class FeicheMyAccountServiceImpl implements FeicheMyAccountService {
	private final static Logger logger = Logger.getLogger(FeicheMyAccountServiceImpl.class);
	@Resource
	private  WechatBindMapper wechatBindMapper;
	@Autowired
	private FeicheMyAccountMapper myAccountMapper;
	@Resource
	private LudifcBaseinforMapper ludifcBaseinforMapper;
	
	public Map<String, Object> getMyAccountInitInfor(HttpServletRequest request) {
		String content = request.getParameter("content");
		HttpSession session=request.getSession();
		Userinfor user = (Userinfor) session.getAttribute("loginUser");
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> result = new HashMap<String, Object>();
		int bindUserId = 0 ;
		//开始获取用户绑定用户的数据
		if(user!=null){
			WechatBind wechatBind=null;
			Integer userType=user.getUsertype();
			if(userType!=null&&userType==3){//微信端获取我的订单数据
				int wechatUserId=user.getUserid();
			     wechatBind	= wechatBindMapper.selectByWechatUserId(wechatUserId);
			 if(wechatBind!=null&&wechatBind.getPhoneuserid()!=null){
				 bindUserId = wechatBind.getPhoneuserid();
			 }
			}else if(userType!=null){//移动端获取我的订单数据
				int phoneUserId=user.getUserid();
				 wechatBind = wechatBindMapper.selectByphoneUserId(phoneUserId);
				 if(wechatBind!=null&&wechatBind.getWechatuserid()!=null){
					 bindUserId = wechatBind.getWechatuserid();
				 }
			}
		}
		map.put("userid", user.getUserid());
		map.put("bindUserId", bindUserId);
		map.put("content", content);
		int count1 = myAccountMapper.getMyOrdersCount1(map);
		int count2 = myAccountMapper.getMyOrdersCount2(map);
		int count3 = myAccountMapper.getMyOrdersCount3(map);
		int count4 = myAccountMapper.getMyOrdersCount4(map);
		result.put("count1", count1);
		result.put("count2", count2);
		result.put("count3", count3);
		result.put("count4", count4);
		List<LudifcBaseinfor> baseinfor1 = myAccountMapper.getMyOrdersTopTen1(map);
		List<LudifcBaseinfor> baseinfor2 = myAccountMapper.getMyOrdersTopTen2(map);
		List<LudifcBaseinfor> baseinfor3 = myAccountMapper.getMyOrdersTopTen3(map);
		List<LudifcBaseinfor> baseinfor4 = myAccountMapper.getMyOrdersTopTen4(map);
		result.put("baseinfor1", baseinfor1);
		result.put("baseinfor2", baseinfor2);
		result.put("baseinfor3", baseinfor3);
		result.put("baseinfor4", baseinfor4);
		return result;
	}

	/**
	 * 获取每页展示的订单数据
	 */
	public Map<String, Object> getMyOrdersInfor(HttpServletRequest request) {
		String thatPage = request.getParameter("curPage");
		String index = request.getParameter("flag");
		String content = request.getParameter("content");
		int thatpage = thatPage==null?1:Integer.parseInt(thatPage);
		int tabIndex = index==null?0:Integer.parseInt(index);
		HttpSession session=request.getSession();
		Userinfor user = (Userinfor) session.getAttribute("loginUser");
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> result = new HashMap<String, Object>();
		int bindUserId = 0 ;
		//开始获取用户绑定用户的数据
		if(user!=null){
			WechatBind wechatBind=null;
			Integer userType=user.getUsertype();
			if(userType!=null&&userType==3){//微信端获取我的订单数据
				int wechatUserId=user.getUserid();
			     wechatBind	= wechatBindMapper.selectByWechatUserId(wechatUserId);
			 if(wechatBind!=null&&wechatBind.getPhoneuserid()!=null){
				 bindUserId = wechatBind.getPhoneuserid();
			 }
			}else if(userType!=null){//移动端获取我的订单数据
				int phoneUserId=user.getUserid();
				 wechatBind = wechatBindMapper.selectByphoneUserId(phoneUserId);
				 if(wechatBind!=null&&wechatBind.getWechatuserid()!=null){
					 bindUserId = wechatBind.getWechatuserid();
				 }
			}
		}
		map.put("bindUserId", bindUserId);
		map.put("userid", user.getUserid());
		map.put("currentNum", (thatpage-1)*10); //要查询的起始数据条数
		map.put("pageSize", 10); //每页展示的最多数据条数
		map.put("content", content);
		int count1 = myAccountMapper.getMyOrdersCount1(map);
		int count2 = myAccountMapper.getMyOrdersCount2(map);
		int count3 = myAccountMapper.getMyOrdersCount3(map);
		int count4 = myAccountMapper.getMyOrdersCount4(map);
		result.put("count1", count1);
		result.put("count2", count2);
		result.put("count3", count3);
		result.put("count4", count4);
		map.put("index", tabIndex); //选择的tab:0表示待支付、1表示已支付、2表示暂存、3表示已撤销
		List<LudifcBaseinfor> baseinfors = myAccountMapper.getMyOrders(map);
		result.put("baseinfors", baseinfors);
		return result;
	}

	/**
	 * 取消订单
	 */
	public String cancelOrder(String orderNo, HttpServletRequest request) {
		String msg = "";
		LudifcBaseinfor baseinfor = ludifcBaseinforMapper.selectByOrderno(orderNo);//根据orderNo查询出订单
		int orderstate = baseinfor.getOrderstate();
		if(orderstate==10 || orderstate==20 || orderstate==30){ //暂存状态的订单撤销只需要更新我们数据库就OK
			baseinfor.setOrderstate(80);//状态为关闭
			int result = ludifcBaseinforMapper.updateByPrimaryKeySelective(baseinfor);
			if(result==1){
				msg="success";
				logger.info("取消订单修改状态成功 订单号为 :" +orderNo);
			}
		}
		return msg;
	}
	
	/**
	 * 继续支付
	 */
	public String continuePay(String orderNo, HttpServletRequest request) {
		LudifcBaseinfor	baseinfor = myAccountMapper.getInsuranceByOrderNo(orderNo);
		String url = baseinfor.getPayUrl();
		logger.info("URL:"+url);
		return url;
	}

	/**
	 * 继续投保
	 */
	public String continueInsure(String orderNo, HttpServletRequest request) {
		LudifcBaseinfor	baseinfor = myAccountMapper.getInsuranceByOrderNo(orderNo);
		String prodno = baseinfor.getProdno();
		return prodno;
	}

	@Override
	public LudifcBaseinfor showOrderDetail(String orderNo,
			HttpServletRequest request) {
		LudifcBaseinfor	baseinfor = myAccountMapper.getInsuranceByOrderNo(orderNo);
		return baseinfor;
	}

}
