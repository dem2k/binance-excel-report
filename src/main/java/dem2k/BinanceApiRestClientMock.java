package dem2k;

import java.util.Arrays;
import java.util.List;

import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.account.DepositAddress;
import com.binance.api.client.domain.account.DepositHistory;
import com.binance.api.client.domain.account.DustTransferResponse;
import com.binance.api.client.domain.account.NewOCO;
import com.binance.api.client.domain.account.NewOCOResponse;
import com.binance.api.client.domain.account.NewOrder;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.binance.api.client.domain.account.Order;
import com.binance.api.client.domain.account.OrderList;
import com.binance.api.client.domain.account.SubAccountTransfer;
import com.binance.api.client.domain.account.Trade;
import com.binance.api.client.domain.account.TradeHistoryItem;
import com.binance.api.client.domain.account.WithdrawHistory;
import com.binance.api.client.domain.account.WithdrawResult;
import com.binance.api.client.domain.account.request.AllOrderListRequest;
import com.binance.api.client.domain.account.request.AllOrdersRequest;
import com.binance.api.client.domain.account.request.CancelOrderListRequest;
import com.binance.api.client.domain.account.request.CancelOrderListResponse;
import com.binance.api.client.domain.account.request.CancelOrderRequest;
import com.binance.api.client.domain.account.request.CancelOrderResponse;
import com.binance.api.client.domain.account.request.OrderListStatusRequest;
import com.binance.api.client.domain.account.request.OrderRequest;
import com.binance.api.client.domain.account.request.OrderStatusRequest;
import com.binance.api.client.domain.general.Asset;
import com.binance.api.client.domain.general.ExchangeInfo;
import com.binance.api.client.domain.market.AggTrade;
import com.binance.api.client.domain.market.BookTicker;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.binance.api.client.domain.market.OrderBook;
import com.binance.api.client.domain.market.TickerPrice;
import com.binance.api.client.domain.market.TickerStatistics;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BinanceApiRestClientMock implements BinanceApiRestClient {
    private String json_orders;

    private String json_trades;

    public BinanceApiRestClientMock(String json_orders, String json_trades) {
        this.json_orders = json_orders;
        this.json_trades = json_trades;
    }

    @Override
    public List<Order> getAllOrders(AllOrdersRequest orderRequest) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Arrays.asList(mapper.readValue(json_orders, Order[].class));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Trade> getMyTrades(String symbol) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Arrays.asList(mapper.readValue(json_trades, Trade[].class));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TickerPrice getPrice(String symbol) {
        TickerPrice price = new TickerPrice();
        price.setPrice("0.00");
        price.setSymbol(symbol);
        
        if (symbol.equals("DYDXUSDT")) {
            price.setPrice("1.464");
        }

        return price;
    }

    @Override
    public void ping() {
    }

    @Override
    public Long getServerTime() {
        return null;
    }

    @Override
    public ExchangeInfo getExchangeInfo() {
        return null;
    }

    @Override
    public List<Asset> getAllAssets() {
        return null;
    }

    @Override
    public OrderBook getOrderBook(String symbol, Integer limit) {
        return null;
    }

    @Override
    public List<TradeHistoryItem> getTrades(String symbol, Integer limit) {
        return null;
    }

    @Override
    public List<TradeHistoryItem> getHistoricalTrades(String symbol, Integer limit, Long fromId) {
        return null;
    }

    @Override
    public List<AggTrade> getAggTrades(String symbol, String fromId, Integer limit, Long startTime, Long endTime) {
        return null;
    }

    @Override
    public List<AggTrade> getAggTrades(String symbol) {
        return null;
    }

    @Override
    public List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval, Integer limit, Long startTime, Long endTime) {
        return null;
    }

    @Override
    public List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval, Integer limit) {
        return null;
    }

    @Override
    public List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval) {
        return null;
    }

    @Override
    public TickerStatistics get24HrPriceStatistics(String symbol) {
        return null;
    }

    @Override
    public List<TickerStatistics> getAll24HrPriceStatistics() {
        return null;
    }

    @Override
    public List<TickerPrice> getAllPrices() {
        return null;
    }

    @Override
    public List<BookTicker> getBookTickers() {
        return null;
    }

    @Override
    public NewOrderResponse newOrder(NewOrder order) {
        return null;
    }

    @Override
    public void newOrderTest(NewOrder order) {

    }

    @Override
    public Order getOrderStatus(OrderStatusRequest orderStatusRequest) {
        return null;
    }

    @Override
    public CancelOrderResponse cancelOrder(CancelOrderRequest cancelOrderRequest) {
        return null;
    }

    @Override
    public List<Order> getOpenOrders(OrderRequest orderRequest) {
        return null;
    }


    @Override
    public NewOCOResponse newOCO(NewOCO oco) {
        return null;
    }

    @Override
    public CancelOrderListResponse cancelOrderList(CancelOrderListRequest cancelOrderListRequest) {
        return null;
    }

    @Override
    public OrderList getOrderListStatus(OrderListStatusRequest orderListStatusRequest) {
        return null;
    }

    @Override
    public List<OrderList> getAllOrderList(AllOrderListRequest allOrderListRequest) {
        return null;
    }

    @Override
    public Account getAccount(Long recvWindow, Long timestamp) {
        return null;
    }

    @Override
    public Account getAccount() {
        return null;
    }

    @Override
    public List<Trade> getMyTrades(String symbol, Integer limit, Long fromId, Long recvWindow, Long timestamp) {
        return null;
    }

    @Override
    public List<Trade> getMyTrades(String symbol, Integer limit) {
        return null;
    }


    @Override
    public List<Trade> getMyTrades(String symbol, Long fromId) {
        return null;
    }

    @Override
    public WithdrawResult withdraw(String asset, String address, String amount, String name, String addressTag) {
        return null;
    }

    @Override
    public DustTransferResponse dustTranfer(List<String> asset) {
        return null;
    }

    @Override
    public DepositHistory getDepositHistory(String asset) {
        return null;
    }

    @Override
    public WithdrawHistory getWithdrawHistory(String asset) {
        return null;
    }

    @Override
    public List<SubAccountTransfer> getSubAccountTransfers() {
        return null;
    }

    @Override
    public DepositAddress getDepositAddress(String asset) {
        return null;
    }

    @Override
    public String startUserDataStream() {
        return null;
    }

    @Override
    public void keepAliveUserDataStream(String listenKey) {

    }

    @Override
    public void closeUserDataStream(String listenKey) {

    }
}
