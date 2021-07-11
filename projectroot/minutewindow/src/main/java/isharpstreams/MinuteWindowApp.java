package isharpstreams;

import com.isharp.polozilla.vo.Capture;
import com.isharp.polozilla.vo.KeyedPoloCaptureWindow;
import com.isharp.processors.CaptureWindow;
import com.isharp.processors.TransformAndFlatten;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.time.Duration;
import java.util.function.Function;


@SpringBootApplication
public class MinuteWindowApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(MinuteWindowApp.class);
      }


    private CaptureWindow captureWindow = new CaptureWindow(Duration.ofMinutes(1), Duration.ofSeconds(30));


    @Bean
    public Function<KStream<String, Capture>, KStream<String, KeyedPoloCaptureWindow>> windowMinutes(){
        return captureWindow.process();
    }


}
