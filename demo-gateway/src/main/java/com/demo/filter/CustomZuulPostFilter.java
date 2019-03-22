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
        log.info(String.format("serviceId in post filter run : %s ", ctx.get(SERVICE_ID_KEY)));
        return null;
    }
}
