import com.google.inject.Injector;
import http.HTTPListener;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SampleHttpApplication {
	private final Injector injector;

    @Inject
    public SampleHttpApplication(Injector injector) {
		this.injector = injector;
	}

	void run() {
        injector.getInstance(HTTPListener.class);
    }
}
