package ip;

import javax.servlet.http.HttpServletRequest;

public class ipTest {
    public static void main(String[] args) {
    String str="1.1.1.1, 127.0.0.1, 192.168.247.131, 192.168.247.132";
        String ip=getRealIpAdd(str);
        System.out.println(ip);

    }
    public static String getRealIpStringByRequest(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("X-Real-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }
    public  static String getRealIpAdd(String ip){
        if(!ip.contains(",")){
            return ip;
        }else {
            String []ipList=ip.split(",");
            return ipList[0].trim();
        }

    }

}
