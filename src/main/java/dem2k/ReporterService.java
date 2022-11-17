package dem2k;

import java.math.BigDecimal;
import java.util.List;

public interface ReporterService {
    
    void report(BigDecimal price, List<OrderDto> orders) throws Exception;
    
}
