package dem2k;

import java.math.BigDecimal;
import java.util.List;

import com.binance.api.client.domain.general.SymbolFilter;

public interface ExchangeService {

    List<OrderDto> getOrders(String symbol);

    BigDecimal getPrice(String symbol);
}
