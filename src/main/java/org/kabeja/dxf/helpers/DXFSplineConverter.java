/*
   Copyright 2008 Simon Mieth

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

import org.kabeja.dxf.DXFPolyline;
import org.kabeja.dxf.DXFSpline;
import org.kabeja.dxf.DXFVertex;
import org.kabeja.math.NURBS;
import org.kabeja.math.NURBSFixedNTELSPointIterator;
import java.util.ArrayList;
import java.util.Iterator;

public class DXFSplineConverter {
  public static DXFPolyline toDXFPolyline( DXFSpline spline ) {
    DXFPolyline polyline = new DXFPolyline();
    polyline.setDXFDocument( spline.getDXFDocument() );

    if ( ( spline.getDegree() > 0 ) && ( spline.getKnots().length > 0 ) ) {
      Iterator<Object> nurbsPointIterator = new NURBSFixedNTELSPointIterator( toNurbs( spline ), 30 );
      while ( nurbsPointIterator.hasNext() ) {
        polyline.addVertex( new DXFVertex( ( Point ) nurbsPointIterator.next() ) );
      }
    } else {
      // the curve is the control point polygon
      Iterator<SplinePoint> splinePointIterator = spline.getSplinePointIterator();
      while ( splinePointIterator.hasNext() ) {
        SplinePoint sp = ( SplinePoint ) splinePointIterator.next();
        if ( sp.isControlPoint() ) {
          polyline.addVertex( new DXFVertex( sp ) );
        }
      }
    }

    if ( spline.isClosed() ) {
      polyline.setFlags( 1 );
    }

    return polyline;
  }

  public static NURBS toNurbs( DXFSpline spline ) {
    Iterator<SplinePoint> it = spline.getSplinePointIterator();
    ArrayList<Point> list = new ArrayList<Point>();

    while ( it.hasNext() ) {
      SplinePoint sp = ( SplinePoint ) it.next();
      if ( sp.isControlPoint() ) {
        list.add( ( Point ) sp );
      }
    }

    NURBS nurbs = new NURBS( ( Point[] ) list.toArray( new Point[ list.size() ] ),
      spline.getKnots(), spline.getWeights(), spline.getDegree() );
    nurbs.setClosed( spline.isClosed() );

    return nurbs;
  }
}
