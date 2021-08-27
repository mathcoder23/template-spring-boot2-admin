package org.pettyfox.framework.gateway.config.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.pagehelper.PageInfo;

import java.io.IOException;

/**
 * PageHelper插件的PageInfo实体信息太多，这里做特殊处理，显示部分重要信息
 * @author pettyfox
 */
public class PageInfoSerializer extends JsonSerializer<PageInfo<?>> {
    @Override
    public void serialize(PageInfo pageInfo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("pageNum",pageInfo.getPageNum());
        jsonGenerator.writeNumberField("pageSize",pageInfo.getPageSize());
        jsonGenerator.writeNumberField("total",pageInfo.getTotal());
        jsonGenerator.writeBooleanField("isLastPage",pageInfo.isIsLastPage());
        jsonGenerator.writeObjectField("list",pageInfo.getList());
        jsonGenerator.writeEndObject();
    }
}
