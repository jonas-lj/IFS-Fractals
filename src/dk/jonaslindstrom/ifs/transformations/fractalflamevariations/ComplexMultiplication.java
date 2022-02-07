package dk.jonaslindstrom.ifs.transformations.fractalflamevariations;

import dk.jonaslindstrom.ifs.kernels.ComputationKernel;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import dk.jonaslindstrom.ifs.transformations.Transformation;

public class ComplexMultiplication implements Transformation {

  private Complex a;

  public ComplexMultiplication(Complex a) {
    this.a = a;
  }

  @Override
  public Vector2D apply(Vector2D v, ComputationKernel kernel) {
    Complex r = a.multiply(new Complex(v.getX(), v.getY()));
    return new Vector2D(r.getReal(), r.getImaginary());
  }

}
