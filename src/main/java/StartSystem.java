import com.google.inject.Guice;
import guice.AkkaModule;

public class StartSystem {
	public static void main(String[] args) {

		Guice
			.createInjector(new AkkaModule())
			.getInstance(SampleHttpApplication.class)
			.run();
    }
}

