package com.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

@Slf4j
@Component
public class CustomZuulRouteFilter extends ZuulFilter {

    @Override
    public int filterOrder() {
        return SIMPLE_HOST_ROUTING_FILTER_ORDER - 1;
    }

    @Override
    public String filterType() {
        return ROUTE_TYPE;
    }

    @Override
    public boolean shouldFilter() {
        log.info("serviceId : " + RequestContext.getCurrentContext().get(SERVICE_ID_KEY));
        return RequestContext.getCurrentContext().getRouteHost() != null
                && RequestContext.getCurrentContext().sendZuulResponse();
    }
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("request uri in route filter run : " + request.getRequestURI());
        log.info("serviceId in route filter run : " + ctx.get(SERVICE_ID_KEY));
        return null;
    }
}
