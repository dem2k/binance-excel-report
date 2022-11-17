package dem2k;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.OrderSide;
import com.binance.api.client.domain.OrderStatus;
import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.account.Order;
import com.binance.api.client.domain.account.Trade;
import com.binance.api.client.domain.account.request.AllOrdersRequest;

public class ExchangeServiceBinance implements ExchangeService {

    private BinanceApiRestClient client;

    public ExchangeServiceBinance(BinanceApiRestClient client) {
        this.client = client;
    }

    @Override
    public List<OrderDto> getOrders(String symbol) {
        AllOrdersRequest request = new AllOrdersRequest(symbol);
        List<Order> orders = client.getAllOrders(request);
        List<Trade> trades = client.getMyTrades(symbol);

        return orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.FILLED)
                .map(order -> mapDto(order, trades))
                .sorted(Comparator.comparing(OrderDto::getTime).reversed())
                .collect(Collectors.toList());
    }

    private OrderDto mapDto(Order order, List<Trade> trades) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getOrderId());
        dto.setPrice(new BigDecimal(order.getPrice()));
        dto.setAmount(new BigDecimal(order.getExecutedQty()));
        dto.setSide(order.getSide().toString());
        dto.setSymbol(order.getSymbol());
        dto.setTime(toLocalDate(order.getUpdateTime()));
        dto.setType(order.getType().toString());
        dto.setStatus(order.getStatus().toString());

        if (order.getType() == OrderType.MARKET) {
            dto.setPrice(fromTrades(order.getOrderId().toString(), trades));
        }

        dto.setVolume(dto.getPrice().multiply(dto.getAmount()).setScale(8, RoundingMode.DOWN));

        if (order.getSide() == OrderSide.BUY) {
            dto.setVolume(dto.getVolume().negate());
        }

        if (order.getSide() == OrderSide.SELL) {
            dto.setAmount(dto.getAmount().negate());
        }

        return dto;
    }

    private BigDecimal fromTrades(String orderId, List<Trade> trades) {
        List<Trade> orderTrades = trades.stream()
                .filter(trade -> trade.getOrderId().equals(orderId)).toList();

        BigDecimal price = BigDecimal.ZERO;
        for (Trade trade : orderTrades) {
            price = price.add(new BigDecimal(trade.getPrice()));
        }
        return price.divide(new BigDecimal(orderTrades.size()), RoundingMode.DOWN);
    }

    private LocalDateTime toLocalDate(long time) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
    }

    @Override
    public BigDecimal getPrice(String symbol) {
        return new BigDecimal(client.getPrice(symbol).getPrice());
    }
}
