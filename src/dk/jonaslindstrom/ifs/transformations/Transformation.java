package dk.jonaslindstrom.ifs.transformations;

import java.util.function.UnaryOperator;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Transformations are operators from the plane to it self.
 * 
 * @author Jonas Lindstrøm (jonas.lindstrom@alexandra.dk)
 *
 */
public interface Transformation extends UnaryOperator<Vector2D> {

}
