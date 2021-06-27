package com.weiyx.nos.iaas.gateway.contraoler;

import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/gw")
public class GatewayController {
    /**
     * 获取当前系统的限流策略
     * @return
     */
    @GetMapping("/flow/rules")
    public Set<GatewayFlowRule> getCurrentGatewayFlowRule(){
        return GatewayRuleManager.getRules();
    }

    /**
     * 获取自定义api分组
     * @return
     */
    @GetMapping("/api/groups")
    public Set<ApiDefinition> getApiGroup(){
        return GatewayApiDefinitionManager.getApiDefinitions();
    }
}
