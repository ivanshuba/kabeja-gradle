/*
 Copyright 2005 Simon Mieth

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package org.kabeja.dxf.helpers;

import org.kabeja.dxf.DXFConstants;
import java.util.Locale;

/**
 * @author <a href="mailto:simon.mieth@gmx.de>Simon Mieth</a>
 */
public class Point {
  protected double x = 0.0;
  protected double y = 0.0;
  protected double z = 0.0;

  public Point() {}

  public Point( double x, double y, double z ) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public boolean equals( Object obj ) {
    if ( obj instanceof Point ) {
      Point p = ( Point ) obj;
      double d = DXFConstants.POINT_CONNECTION_RADIUS;
      if ( ( Math.abs( x - p.getX() ) <= d ) && ( Math.abs( y - p.getY() ) <= d ) ) {
        return Math.abs( z - p.getZ() ) <= d;
      }
    }
    return false;
  }

  public double getX() { return x; }

  public void setX( double x ) { this.x = x; }

  public double getY() { return y; }

  public void setY( double y ) { this.y = y; }

  public double getZ() { return z; }

  public void setZ( double z ) { this.z = z; }

  public String toString() {
    return String.format( Locale.US, "[%4.2f, %4.2f, %4.2f]", this.x, this.y, this.z );
  }
}
