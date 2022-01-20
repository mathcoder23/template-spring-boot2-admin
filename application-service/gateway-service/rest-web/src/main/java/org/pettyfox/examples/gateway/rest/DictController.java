package org.pettyfox.examples.gateway.rest;

import cn.hutool.core.util.ClassUtil;
import org.pettyfox.base.comm.type.BaseEnum;
import org.pettyfox.base.comm.web.RestObjectResponse;
import org.pettyfox.examples.gateway.rest.data.DictData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 词典接口，将项目中使用的枚举类型转换为接口，统一供前端调用
 *
 * @author Petty Fox
 * @version 1.0
 * @date 2021/4/16 13:27
 */
@RestController
@RequestMapping("/api/constant")
public class DictController {
    public static final String basePackage = "org.pettyfox.framework.service";

    @PostMapping("dict")
    public RestObjectResponse<Map<String, List<DictData>>> getDict() {
        Map<String, List<DictData>> result = new HashMap<>(64);
        ClassUtil.scanPackageBySuper(basePackage, BaseEnum.class).forEach(r -> {
            List<DictData> dictDataList = new ArrayList<>();
            String name = r.getName().substring(r.getName().lastIndexOf(".") + 1).replace("$", "_");
            for (Object e : r.getEnumConstants()) {
                if (e instanceof BaseEnum) {
                    DictData dictData = new DictData();
                    BaseEnum ee = (BaseEnum) e;
                    dictData.setValue(String.valueOf(ee));
                    dictData.setLabel(ee.getName());
                    dictDataList.add(dictData);
                }
            }

            result.put(name, dictDataList);

        });

        return RestObjectResponse.ok(result);
    }
}
