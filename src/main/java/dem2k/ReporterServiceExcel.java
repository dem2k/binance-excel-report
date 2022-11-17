package dem2k;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

public class ReporterServiceExcel implements ReporterService {

    private static final org.slf4j.Logger LOG =
            org.slf4j.LoggerFactory.getLogger(ReporterServiceExcel.class);

    private final AppConfig config;

    public ReporterServiceExcel(AppConfig config) {
        this.config = config;
    }

    @Override
    public void report(BigDecimal price, List<OrderDto> orders) throws Exception {
        InputStream is = Stream.of(
                        getInputStream("/" + config.symbol() + ".xlsx"),
                        getInputStream("/common.xlsx"))
                .filter(Objects::nonNull).findFirst()
                .orElseThrow(() -> new FileNotFoundException("template not found."));

        String reportFile = config.symbol() + ".xlsx";
        OutputStream os = new FileOutputStream(reportFile);

        Context context = new Context();
        context.putVar("price", price);
        context.putVar("orders", orders);
        JxlsHelper.getInstance().processTemplate(is, os, context);

        os.close();
        is.close();
        
        LOG.info("Report created: {}", reportFile);
    }

    private InputStream getInputStream(String file) {
        return ReporterServiceExcel.class.getResourceAsStream(file.toLowerCase());
    }
}
