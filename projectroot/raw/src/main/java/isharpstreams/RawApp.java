package isharpstreams;


import com.isharp.polozilla.vo.Capture;
import com.isharp.processors.TransformAndFlatten;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class RawApp
{

    public static void main(String[] args) {
        SpringApplication.run(RawApp.class, args);
    }

    private final TransformAndFlatten transformAndFlatten = new TransformAndFlatten();
    @Bean
    public Function<KStream<String, String>, KStream<String, Capture>> transformAndFlatten() {
        return transformAndFlatten.process();
    }


}
