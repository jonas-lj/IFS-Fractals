package dk.jonaslindstrom.ifs.transformations;

import dk.jonaslindstrom.ifs.kernels.ComputationKernel;
import java.util.function.BiFunction;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Transformations are operators from the plane to it self.
 */
public interface Transformation extends BiFunction<Vector2D, ComputationKernel, Vector2D> {

}
