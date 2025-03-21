package xyz.wochib70.mybatis.advanced.typehandler.typehandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import xyz.wochib70.mybatis.advanced.typehandler.entity.JsonField;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JsonFieldTypeHandler extends BaseTypeHandler<JsonField> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JsonField parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i, objectMapper.writeValueAsString(parameter));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonField getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return parseJsonField(rs.getString(columnName));
    }

    @Override
    public JsonField getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return parseJsonField(rs.getString(columnIndex));
    }

    @Override
    public JsonField getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return parseJsonField(cs.getString(columnIndex));
    }

    private JsonField parseJsonField(String jsonField) {
        try {
            return objectMapper.readValue(jsonField, JsonField.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
