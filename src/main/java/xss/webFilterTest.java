//package xss;
//
//
//import oracle.jdbc.driver.Const;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//public class RequestFilter implements Filter {
//    protected FilterConfig filterConfig;
//    protected boolean filterEnabled;
//    protected int logLevel;
//    protected boolean needVCode;
//    protected List<String> noFilerList =null;
//    private CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//    public RequestFilter() {
//        this.filterConfig = null;
//        this.filterEnabled = true;
//        this.logLevel = -1;
//    }
//
//    @Override
//    public void destroy() {
//        // TODO Auto-generated method stub
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse resp,
//                         FilterChain chain) throws IOException, ServletException {
//        if(this.filterEnabled){
//            Const.SESSION_CSRFTOKEN;
//            HttpServletRequest httpReq = (HttpServletRequest)req;
//            HttpServletResponse httpResp = (HttpServletResponse)resp;
//            String ctxPath = httpReq.getContextPath();
//            String requestUri = httpReq.getRequestURI();        //请求的全路径,比如:
//            String uri = requestUri.substring(ctxPath.length());//全路径除去ctxPath
//            String tarUri = uri.trim();
//            String operatorHtmlModel = (httpReq.getHeader("referer")!=null?httpReq.getHeader("referer"):"").trim(); //获取当前页面的url，判断url是否是后台而url（后台的html就两个）
//            //不在过滤列表里的url请求,过滤列表包括t_sys_filter表中数据及visitor角色用户下的授权页面
//            if(!this.isInNoFilerList(tarUri)){
//                UserInfo uInfo = SessionUtil.getCurrentUser();
//                LoginAccount regAccout=SessionUtil.getCurrentPlateLoginAccount();
//                int type = 0 ;//平台账号未登录
//                if(operatorHtmlModel.endsWith(Const.LOGIN_TYPE_HTML[0])||operatorHtmlModel.endsWith(Const.LOGIN_TYPE_HTML[1])){
//                    type = 1;//后台账号未登录
//                }
//                //    int type = (SessionUtil.getAttribute(Const.LOGIN_TYPE)!=null)?Integer.valueOf(SessionUtil.getAttribute(Const.LOGIN_TYPE).toString()):0;
//                if(regAccout==null){//平台账号未登录
//                    if(tarUri.endsWith(".do")){
//                        httpResp.sendRedirect(ctxPath+"/"+Const.TIMEOUT_SERVICE);
//                        return;
//                    }else if(tarUri.endsWith("/")){
//                        httpResp.sendRedirect(ctxPath+"/"+Const.INDEX_PAGE);
//                        return;
//                    }
//                }else{//平台账号登录
//                    if(tarUri.endsWith("/")){
//                        httpResp.sendRedirect(ctxPath+"/error/noSecurity.htm");
//                        return;
//                    }else if(tarUri.endsWith(".do") && !isWithoutUri(tarUri)){
//                        String contentType = httpReq.getContentType();//获取请求的content-type
//                        String post_csrftoken = "";
//                        if(contentType.contains("multipart/form-data")){//文件上传请求 *特殊请求
//　　　　　　　　　　　　　　/*
//　　　　　　　　　　　　　　　　CommonsMultipartResolver 是spring框架中自带的类，使用multipartResolver.resolveMultipart(final HttpServletRequest request)方法可以将request转化为MultipartHttpServletRequest
//　　　　　　　　　　　　　　　　使用MultipartHttpServletRequest对象可以使用getParameter(key)获取对应属性的值
//　　　　　　　　　　　　　　*/
//                            MultipartHttpServletRequest multiReq = multipartResolver.resolveMultipart(httpReq);
//                            post_csrftoken=multiReq.getParameter(Const.SESSION_CSRFTOKEN);//获取参数中的token
//                            req = multiReq;//将转化后的reuqest赋值到过滤链中的参数 *重要
//                        }else{//非文件上传请求
//                            post_csrftoken=httpReq.getParameter(Const.SESSION_CSRFTOKEN);//获取参数中的token
//                        }
//                        //csrf防御：判断是否带token
//                        //post_csrftoken=httpReq.getParameter(Const.SESSION_CSRFTOKEN);
//                        String csrftoken=(String)SessionUtil.getAttribute(Const.SESSION_CSRFTOKEN);
//                        if(post_csrftoken==null || !csrftoken.equals(post_csrftoken)){
//                            //判断为不安全的访问
//                            httpResp.sendRedirect(ctxPath+"/common/goNoSecurity.do");
//                            return;
//                        }
//                    }
//                }
//                // 设定网页的到期时间，一旦过期则必须到服务器上重新调用
//                httpResp.setDateHeader("Expires", -1);
//                // Cache-Control 指定请求和响应应遵循的缓存机制 no-cache指示请求或响应消息是不能缓存的
//                httpResp.setHeader("Cache-Control", "no-cache");
//                // 用于设定禁止浏览器从本地缓存中调用页面内容，设定后一旦离开页面就无法从Cache中再调出
//                httpResp.setHeader("Pragma", "no-cache");
//            }
//            chain.doFilter(req, resp);
//        }
//    }
//
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public void init(FilterConfig cfg) throws ServletException {
//        //初始化操作
//    }
//
//
//    private Boolean isWithoutUri(String tarUri){
//        String[] withoutUriStrings = {//无需匹配token的请求
//                "/common/goNoSecurity.do",
//                "/plateFormCommon/isLoginForPlateForm.do",
//                "/supplierForPlateForm/getCompanyListByRegId.do"
//                /*,"/PfTaskFileCtrl/addOrUpdateTaskImgFile1.do"*/
//                /*,"/PfTaskFileCtrl/addOrUpdateTaskImgFileForUpdate.do"*/
//        };
//
//        for(String uri:withoutUriStrings){
//            if(uri.equals(tarUri)){
//                return true;
//            }
//        }
//        return false;
//    }
//
//}