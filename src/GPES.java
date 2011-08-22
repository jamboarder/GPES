
public class GPES {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("This is the General Purpose Evolutionary System");
		Environment environment = new Environment();
		Interface iface = new Interface(environment);
		//TODO: Create application-specific translators and resource manager
		//      and add to the interface.
		iface.minInterval = 1000;
		iface.animate();
	}

}
