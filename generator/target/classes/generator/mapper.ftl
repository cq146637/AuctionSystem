package ${package};

import ${tableClass.fullClassName};
import ${baseMapper};

<#assign dateTime = .now>
/**
 * ${tableClass.shortClassName}${mapperSuffix!"Dao"} -> ${tableClass.tableName}
 *
 * @author ${author!"chain-generator"} ${dateTime?string["yyyy-MM-dd"]}
 */
@javax.inject.Named
public interface ${tableClass.shortClassName}${mapperSuffix} {
    /*
     * 自定义扩展
     */

}
