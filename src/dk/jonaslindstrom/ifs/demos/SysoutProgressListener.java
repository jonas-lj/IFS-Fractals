package dk.jonaslindstrom.ifs.demos;

import dk.jonaslindstrom.ifs.ProgressListener;

public class SysoutProgressListener implements ProgressListener {

	@Override
	public void updateProgress(double progress) {
		System.out.print((int) (100.0 * progress) + "%\r");
	}

}
