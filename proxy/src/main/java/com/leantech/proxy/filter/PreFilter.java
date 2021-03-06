package com.leantech.proxy.filter;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PreFilter extends ZuulFilter {

  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 1;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    log.info("Headers");
    Enumeration<String> nameHeaders = request.getHeaderNames();
    while (nameHeaders.hasMoreElements()) {
      String name = nameHeaders.nextElement();
      Enumeration<String> valueHeaders = request.getHeaders(name);
      while (valueHeaders.hasMoreElements()) {
        String value = valueHeaders.nextElement();
        log.info("Header:" + name + " value: " + value);
      }
    }
    return null;
  }

}