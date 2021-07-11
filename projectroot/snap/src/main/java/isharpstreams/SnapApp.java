package isharpstreams;


import com.isharp.polozilla.vo.Capture;
import com.isharp.polozilla.vo.KeyedPoloCaptureWindow;
import com.isharp.polozilla.vo.Snap;
import com.isharp.processors.Snapper;
import com.isharp.processors.TransformAndFlatten;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Windowed;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.util.function.Function;

@SpringBootApplication
public class SnapApp
{

    public static void main(String[] args) {
        SpringApplication.run(SnapApp.class, args);
    }

    private final Snapper snapper = new Snapper(Duration.ofSeconds(60), Duration.ofSeconds(15));
    @Bean
    public Function<KStream<String, KeyedPoloCaptureWindow>, KStream<Windowed<String>, Snap>> snapMinutes(){
        return snapper.process();
    }



}



