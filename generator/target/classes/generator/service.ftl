package ${package};

import com.jd.supply.chain.base.BaseService;
import ${tableClass.fullClassName};

<#assign dateTime = .now>
/**
 * ${tableClass.shortClassName}Service
 *
 * @author ${author!"chain-generator"} ${dateTime?string["yyyy-MM-dd"]}
 */
@javax.inject.Named
public class ${tableClass.shortClassName}Service extends BaseService<${tableClass.shortClassName}Manager, ${tableClass.shortClassName}> {
    /*
     * 自定义扩展
     */

}
