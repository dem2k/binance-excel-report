package dem2k;

import java.math.BigDecimal;
import java.util.List;

public class AppController {

    private ExchangeService exchange;
    private ReporterService reporter;
    private AppConfig config;

    public AppController(ExchangeService exchange, ReporterService reporter, AppConfig config) {
        this.exchange = exchange;
        this.reporter = reporter;
        this.config = config;
    }

    public void start() throws Exception {
        OrderCacheLoader cache = new OrderCacheLoader(config);
        List<OrderDto> orders = 
                cache.load(() -> exchange.getOrders(config.symbol()));
        BigDecimal price = exchange.getPrice(config.symbol());
        reporter.report(price, orders);
    }

}
