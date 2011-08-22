
public class Info {
	public int s;
	public int p;
	public int o;
	
	Info() {
		s = 0;
		p = 0;
		o = 0;
	}
	
	public boolean isRelated(Info info) {
		if (s == info.o || o == info.s) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean equals(Info info) {
		if (s == info.s && p == info.p && o == info.o) {
			return true;
		} else {
			return false;
		}
	}
}
