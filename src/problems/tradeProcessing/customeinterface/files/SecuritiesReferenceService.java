package problems.tradeProcessing.customeinterface.files;

import java.sql.SQLException;

public interface SecuritiesReferenceService {
    String getSecurityIdByCusip(String cusip) throws SQLException;
}
