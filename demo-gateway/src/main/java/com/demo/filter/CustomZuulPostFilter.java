package com.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

@Slf4j
public class CustomZuulPostFilter extends ZuulFilter {

    @Override
    public int filterOrder() {
        return SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public boolean shouldFilter() {
       return true;
    }
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        log.info("serviceId in post filter run : " + ctx.get(SERVICE_ID_KEY)); // http://uri/유레카서비스명/api 형태로 호출시만 읽어온다.
        return null;
    }
}
