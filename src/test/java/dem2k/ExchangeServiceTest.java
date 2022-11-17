package dem2k;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.binance.api.client.BinanceApiRestClient;

public class ExchangeServiceTest {

    @Test
    public void buy_limit_order() {
        String json_orders = """
                [{
                    "symbol": "LTCUSDT",
                    "orderId": 3029561568,
                    "orderListId": -1,
                    "clientOrderId": "x-SGZ1QV0A-tp-68169",
                    "price": "52.28000000",
                    "origQty": "0.20500000",
                    "executedQty": "0.20500000",
                    "cummulativeQuoteQty": "10.71740000",
                    "status": "FILLED",
                    "timeInForce": "GTC",
                    "type": "LIMIT",
                    "side": "BUY",
                    "stopPrice": "0.00000000",
                    "icebergQty": "0.00000000",
                    "time": 1662235904101,
                    "updateTime": 1663535507398,
                    "isWorking": true,
                    "origQuoteOrderQty": "0.00000000"
                  }]""";
        String json_trades = """
                [{
                    "symbol": "LTCUSDT",
                    "id": 252655854,
                    "orderId": 3029561568,
                    "orderListId": -1,
                    "price": "52.28000000",
                    "qty": "0.20500000",
                    "quoteQty": "10.71740000",
                    "commission": "0.00020500",
                    "commissionAsset": "LTC",
                    "time": 1663535507398,
                    "isBuyer": true,
                    "isMaker": true,
                    "isBestMatch": true
                  }]""";
        BinanceApiRestClient client = new BinanceApiRestClientMock(json_orders, json_trades);
        ExchangeService service = new ExchangeServiceBinance(client);
        List<OrderDto> orders = service.getOrders(null);
        assertEquals(1, orders.size());
        OrderDto order = orders.get(0);
        System.out.println(order);
        assertEquals("52.28000000", order.getPrice().toPlainString());
        assertEquals("0.20500000", order.getAmount().toPlainString());
        assertEquals("-10.71740000", order.getVolume().toPlainString());
    }

    @Test
    public void sell_limit_order() {
        String json_orders = """
                [{
                    "symbol": "LTCUSDT",
                    "orderId": 2791179731,
                    "orderListId": -1,
                    "clientOrderId": "x-SGZ1QV0A-tp-63492",
                    "price": "127.30000000",
                    "origQty": "0.15700000",
                    "executedQty": "0.15700000",
                    "cummulativeQuoteQty": "19.98610000",
                    "status": "FILLED",
                    "timeInForce": "GTC",
                    "type": "LIMIT",
                    "side": "SELL",
                    "stopPrice": "0.00000000",
                    "icebergQty": "0.00000000",
                    "time": 1648938123851,
                    "updateTime": 1648992320372,
                    "isWorking": true,
                    "origQuoteOrderQty": "0.00000000"
                }]
                """;
        String json_trades = """
                [{
                    "symbol": "LTCUSDT",
                    "id": 237198642,
                    "orderId": 2791179731,
                    "orderListId": -1,
                    "price": "127.30000000",
                    "qty": "0.15700000",
                    "quoteQty": "19.98610000",
                    "commission": "0.00003348",
                    "commissionAsset": "BNB",
                    "time": 1648992320372,
                    "isBuyer": false,
                    "isMaker": true,
                    "isBestMatch": true
                }]
                """;
        BinanceApiRestClient client = new BinanceApiRestClientMock(json_orders, json_trades);
        ExchangeService service = new ExchangeServiceBinance(client);
        List<OrderDto> orders = service.getOrders(null);
        assertEquals(1, orders.size());
        OrderDto order = orders.get(0);
        System.out.println(order);
        assertEquals("127.30000000", order.getPrice().toPlainString());
        assertEquals("-0.15700000", order.getAmount().toPlainString());
        assertEquals("19.98610000", order.getVolume().toPlainString());
    }

    @Test
    public void buy_market_order() {
        String json_orders = """
                [{
                    "symbol": "LTCUSDT",
                    "orderId": 2870788323,
                    "orderListId": -1,
                    "clientOrderId": "x-SGZ1QV0A-tp-66363",
                    "price": "0.00000000",
                    "origQty": "0.24400000",
                    "executedQty": "0.24400000",
                    "cummulativeQuoteQty": "14.98160000",
                    "status": "FILLED",
                    "timeInForce": "GTC",
                    "type": "MARKET",
                    "side": "BUY",
                    "stopPrice": "0.00000000",
                    "icebergQty": "0.00000000",
                    "time": 1654790012911,
                    "updateTime": 1654790012911,
                    "isWorking": true,
                    "origQuoteOrderQty": "0.00000000"
                }]
                """;
        String json_trades = """
                [{
                    "symbol": "LTCUSDT",
                    "id": 242053224,
                    "orderId": 2870788323,
                    "orderListId": -1,
                    "price": "61.30000000",
                    "qty": "0.10000000",
                    "quoteQty": "14.98160000",
                    "commission": "0.00001732",
                    "commissionAsset": "BNB",
                    "time": 1654790012911,
                    "isBuyer": true,
                    "isMaker": false,
                    "isBestMatch": true
                }, {
                    "symbol": "LTCUSDT",
                    "id": 242053225,
                    "orderId": 2870788323,
                    "orderListId": -1,
                    "price": "61.50000000",
                    "qty": "0.14400000",
                    "quoteQty": "14.98160000",
                    "commission": "0.00002132",
                    "commissionAsset": "BNB",
                    "time": 1654790012911,
                    "isBuyer": true,
                    "isMaker": false,
                    "isBestMatch": true
                }]
                """;
        BinanceApiRestClient client = new BinanceApiRestClientMock(json_orders, json_trades);
        ExchangeService service = new ExchangeServiceBinance(client);
        List<OrderDto> orders = service.getOrders(null);
        assertEquals(1, orders.size());
        OrderDto order = orders.get(0);
        System.out.println(order);
        assertEquals("61.40000000", order.getPrice().toPlainString());
        assertEquals("0.24400000", order.getAmount().toPlainString());
        assertEquals("-14.98160000", order.getVolume().toPlainString());
    }
    
}
