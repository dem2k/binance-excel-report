package dem2k;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import picocli.CommandLine;

public class AppMain {

    public static void main(String[] args) throws Exception {

        AppConfig config = CommandLine.populateCommand(new AppConfig(), args);
        if (config.isUsageHelpRequested()) {
            CommandLine.usage(config, System.out);
            return;
        }

        BinanceApiRestClient client = BinanceApiClientFactory.newInstance(
                        config.apiKey(),
                        config.apiSecret())
                .newRestClient();

        if (config.test()) {
            client = new BinanceApiRestClientMock(
                    TestOrders.orders, TestTrades.trades);
        }

        ExchangeService exchange = new ExchangeServiceBinance(client);
        ReporterService reporter = new ReporterServiceExcel(config);
        AppController controller = new AppController(exchange, reporter, config);
        controller.start();
        System.exit(0);
    }

}
