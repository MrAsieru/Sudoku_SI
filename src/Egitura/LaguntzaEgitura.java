package Egitura;

import java.util.List;

public class LaguntzaEgitura {
	public List<String> logger;
	public List<AldaketaEgitura> aldaketak;

	public LaguntzaEgitura() {}

	public LaguntzaEgitura(List<String> logger, List<AldaketaEgitura> aldaketak) {
		this.logger = logger;
		this.aldaketak = aldaketak;
	}
}
