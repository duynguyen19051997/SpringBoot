package duynguyen.transaction.mapper;

import duynguyen.transaction.model.BankAccountInfor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountMapper implements RowMapper<BankAccountInfor> {
    @Override
    public BankAccountInfor mapRow(ResultSet rs, int i) throws SQLException {
        return new BankAccountInfor(rs.getInt("id"),
                rs.getString("full_name"), rs.getDouble("balance"));
    }
}
