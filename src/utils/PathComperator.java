package utils;

import java.util.Comparator;

import coords.Path;

public class PathComperator implements Comparator<Path> {

	@Override
	public int compare(Path arg0, Path arg1) {		
		return (int) (arg0 - arg1);
	}

}
