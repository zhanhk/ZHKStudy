/*
 * Copyright 2018 Netflix, Inc.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package com.zhk.study.zuul2.gateway.filters;

import com.netflix.zuul.context.SessionContext;
import com.netflix.zuul.filters.http.HttpInboundSyncFilter;
import com.netflix.zuul.message.http.HttpRequestMessage;
import com.netflix.zuul.netty.filter.ZuulEndPointRunner;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Routes configuration
 * <p>
 * Author: Arthur Gonigberg
 * Date: November 21, 2017
 */
public class Routes extends HttpInboundSyncFilter {

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter(HttpRequestMessage httpRequestMessage) {
        return true;
    }

    @Override
    public HttpRequestMessage apply(HttpRequestMessage request) {
        SessionContext context = request.getContext();
        String path = request.getPath();
        String host = request.getOriginalHost();

        // Route healthchecks to the healthcheck endpoint.;
        if (path.equalsIgnoreCase("/healthcheck")) {
            context.setEndpoint(Healthcheck.class.getCanonicalName());
        } else {
            if(path.contains("test")) {
                request.setPath("/getA");
                context.setEndpoint(ZuulEndPointRunner.PROXY_ENDPOINT_FILTER_NAME);
                context.setRouteVIP("zuul2-backend");
            }
        }

        return request;
    }
}
