package com.quicksure.mobile.sinosafefactory;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.annotation.Resource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.quicksure.mobile.dmsutility.DMSUtility;
import com.quicksure.mobile.entity.InterfaceDetails;
import com.quicksure.mobile.utility.DateFormatUtils;
@Component
public class SinosafeConnect {
	private static final Logger logger = Logger
			.getLogger(SinosafeConnect.class);
	@Resource
	private DMSUtility dmsUtility;
	
	/**
	 * Sinosafe connecttion
	 * @param strRequestxml
	 * @return
	 * @throws Exception
	 */
	 public  String sinosafeConnection(String strRequestxml,String userAciton) throws Exception {
		    logger.info("开始请求华安，请求时间为 "+DateFormatUtils.getSystemDate()+"请求数据为 \r\n"+strRequestxml);
	        String outputString = "";
	        StringBuilder response = new StringBuilder();
	        List<InterfaceDetails> interfaceDetails=null;
			Element element=null;
			String connectUrl="";
	        try {
	        	CacheManager cacheManager = CacheManager.newInstance();
	        	Cache cache = cacheManager.getCache("baseCache");
	        	if(cache!=null){
	        		element=cache.get("interfacesdetails");
	        		if(element==null){        			
	                	interfaceDetails = dmsUtility.getConnectinfor();
	                	element=new Element("interfacesdetails", (Serializable) interfaceDetails);
	                	cache.put(element);
	                }else{
	                	interfaceDetails=(List<InterfaceDetails>) element.getValue();
	                }
	        	}  
	        	if(interfaceDetails!=null && interfaceDetails.size()>0){
	        		for(InterfaceDetails interfaceDetail : interfaceDetails){
	        			if("VehicleAccidentSubmit".equals(userAciton) 
	        					|| "jiayixiansubmit".equals(userAciton)
	        					|| "junanbaosubmit".equals(userAciton)){
	        				if(interfaceDetail.getInterfaceid()==3){
		        				connectUrl = interfaceDetail.getInterfaceurl();
		        				break;
		        			}
	        			}else{
	        				if(interfaceDetail.getInterfaceid()==1){
	        					connectUrl = interfaceDetail.getInterfaceurl();
	        					break;
	        				}
	        			}
	        		}
	        	}
	            URL url = new URL(connectUrl);
	            ByteArrayOutputStream bout = new ByteArrayOutputStream();
	            byte[] buffer = new byte[strRequestxml.toString().length()];
	            buffer = strRequestxml.toString().getBytes("utf-8");
	            bout.write(buffer);
	            byte[] b = bout.toByteArray();
	            URLConnection connection = url.openConnection();
	            HttpURLConnection httpConn = (HttpURLConnection) connection;
	            // Set the appropriate HTTP parameters.
	            httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
	            httpConn.setRequestProperty("Content-Type", "application/xml; charset=utf-8");
	            httpConn.setRequestMethod("POST");
	            httpConn.setReadTimeout(900000);
	            httpConn.setConnectTimeout(600000);
	            httpConn.setDoOutput(true);
	            httpConn.setDoInput(true);
	            OutputStream out = httpConn.getOutputStream();
	            //Write the content of the request to the outputstream of the HTTP Connection.
	            out.write(b);
	            out.close();
	            //Ready with sending the request.
	            String responseString = "";
	            //Read the response.
	            InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(),"UTF-8");
	            BufferedReader in = new BufferedReader(isr);

	            //Write the SOAP message response to a String.
	            while ((responseString = in.readLine()) != null) {
	                outputString = outputString + responseString;
	            }	          
	        } catch (Exception e) {        	
	        	StringWriter sw = new StringWriter();  
				 e.printStackTrace(new PrintWriter(sw, true));  
				 String str = sw.toString();
				logger.error("华安请求异常，异常消息为"+str);
	        }
	        logger.info("华安请求结束，结束时间为 "+DateFormatUtils.getSystemDate()+"返回数据为 \r\n"+outputString);
	        return outputString;
	    }
	 /**
		 * Radarinfor connecttion
		 * @param strRequestxml
		 * @return
		 * @throws Exception
		 */
		 public  String radarinforConnection(String strRequestxml) throws Exception {
			    logger.info("开始请求雷达，请求时间为 "+DateFormatUtils.getSystemDate()+"请求数据为 \r\n"+strRequestxml);
		        String outputString = "";
		        List<InterfaceDetails> interfaceDetails=null;
				Element element=null;
				String connectUrl="http://139.219.229.31:376//Dposervice.svc";
		        try {
		        	/*CacheManager cacheManager=CacheManager.newInstance();
		        	Cache cache=cacheManager.getCache("baseCache");
		        	if(cache!=null){
		        		element=cache.get("interfacesdetails");
		        		if(element==null){        			
		                	interfaceDetails=dmsUtility.getConnectinfor();
		                	element=new Element("interfacesdetails", (Serializable) interfaceDetails);
		                	cache.put(element);
		                }else{
		                	interfaceDetails=(List<InterfaceDetails>) element.getValue();
		                }
		        	}  
		        	if(interfaceDetails!=null&&interfaceDetails.size()>0){
		        		for(InterfaceDetails interfaceDetail :interfaceDetails){
		        			if(interfaceDetail.getInterfaceid()==1){
		        			connectUrl=interfaceDetail.getInterfaceurl();
		        			break;
		        			}
		        		}
		        	}*/
		            URL url = new URL(connectUrl);
		            ByteArrayOutputStream bout = new ByteArrayOutputStream();
		            byte[] buffer = new byte[strRequestxml.toString().length()];
		            buffer = strRequestxml.toString().getBytes();
		            bout.write(buffer);
		            byte[] b = bout.toByteArray();
		            URLConnection connection = url.openConnection();
		            HttpURLConnection httpConn = (HttpURLConnection) connection;
		            // Set the appropriate HTTP parameters.
		            httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
		            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		            //httpConn.setRequestMethod("POST");
		            httpConn.setReadTimeout(300000);
		            httpConn.setConnectTimeout(20000);
		            httpConn.setDoOutput(true);
		            httpConn.setUseCaches(false); 
		            httpConn.setDoInput(true);
		            httpConn.setRequestProperty("Charset", "UTF-8");
		            OutputStream out = httpConn.getOutputStream();
		            //Write the content of the request to the outputstream of the HTTP Connection.
		            out.write(b);
		            out.close();
		            //Ready with sending the request.
		            String responseString = "";
		            //Read the response.
		            InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(),"UTF-8");
		            BufferedReader in = new BufferedReader(isr);

		            //Write the SOAP message response to a String.
		            while ((responseString = in.readLine()) != null) {
		                outputString = outputString + responseString;
		            }	          
		        } catch (Exception e) {        	
		        	StringWriter sw = new StringWriter();  
					 e.printStackTrace(new PrintWriter(sw, true));  
					 String str = sw.toString();
					logger.error("雷达请求异常，异常消息为"+str);
		        }
		        logger.info("雷达请求结束，结束时间为 "+DateFormatUtils.getSystemDate()+"返回数据为 \r\n"+outputString);
		        return outputString;
		    }


}
