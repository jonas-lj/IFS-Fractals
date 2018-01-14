package dk.jonaslindstrom.fractalflame.transformations.variations;

import java.util.LinkedList;
import java.util.List;

import dk.jonaslindstrom.fractalflame.transformations.Transformation;

public class VariationsFactory {

	private List<Transformation> transformations;;

	private void add(Transformation function) {
		transformations.add(function);
	}
	
	public List<Transformation> getVariations(double[] affineParameters) {
		if (this.transformations != null) {
			return transformations;
		}
		
		this.transformations = new LinkedList<Transformation>();
		add(new Sinusodial());
		add(new Spherical());
		add(new Swirl());
		add(new Horseshoe());
		add(new Polar());		
		add(new Handkerchief());
		add(new Spiral());

		return transformations;

	}

}
