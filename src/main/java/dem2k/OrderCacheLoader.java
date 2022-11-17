package dem2k;

import java.io.File;
import java.io.IOException;
import java.time.Clock;
import java.util.List;
import java.util.function.Supplier;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class OrderCacheLoader {
    private AppConfig config;

    public OrderCacheLoader(AppConfig config) {
        this.config = config;
    }

    public List<OrderDto> load(Supplier<List<OrderDto>> loader) {
        return loader.get();
    }

    private void save(List<OrderDto> orders) throws IOException {
        boolean mkdir = new File("cache").mkdir();
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String time = "-" + Clock.systemDefaultZone().millis();
        mapper.writeValue(new File("cache/" + config.symbol() + time + ".json"), orders);
    }

}
