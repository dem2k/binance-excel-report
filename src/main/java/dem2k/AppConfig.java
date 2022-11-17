package dem2k;

import picocli.CommandLine;

public class AppConfig {

    @CommandLine.Option(names = "-key", required = true, description = "Exchange API Key")
    private String apiKey;

    @CommandLine.Option(names = "-sec", required = true, description = "Exchange API Secret")
    private String apiSecret;

    @CommandLine.Option(names = "-sym", required = true, description = "Symbol, e.g. BTCUSDT.")
    private String symbol;

    @CommandLine.Option(names = "-test", description = "Testrun.")
    private boolean test;

    @CommandLine.Option(names = {"-?", "-h"}, description = "Display this Help Message", usageHelp = true)
    private boolean usageHelpRequested = false;

    public String apiKey() {
        return apiKey;
    }

    public String apiSecret() {
        return apiSecret;
    }

    public String symbol() {
        return symbol.toUpperCase();
    }

    public boolean test() {
        return test;
    }

    public boolean isUsageHelpRequested() {
        return usageHelpRequested;
    }
}
