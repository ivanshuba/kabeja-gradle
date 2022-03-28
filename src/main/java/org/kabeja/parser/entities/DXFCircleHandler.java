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

package org.kabeja.parser.entities;

import org.kabeja.dxf.DXFCircle;
import org.kabeja.dxf.DXFEntity;
import org.kabeja.dxf.helpers.Point;
import org.kabeja.parser.DXFValue;

/**
 * @author <a href="mailto:simon.mieth@gmx.de">Simon Mieth</a>
 */
public class DXFCircleHandler extends AbstractEntityHandler {
  public final static String ENTITY_NAME = "CIRCLE";
  public final static int RADIUS = 40;
  private DXFCircle circle;

  public DXFCircleHandler() {
    super();
  }

  public String getDXFEntityName() { return ENTITY_NAME; }

  public void startDXFEntity() {
    circle = new DXFCircle();
    circle.setCenterPoint( new Point() );
    circle.setDXFDocument( doc );
  }

  public void parseGroup( int groupCode, DXFValue value ) {
    switch ( groupCode ) {
      case GROUPCODE_START_X:
        circle.getCenterPoint().setX( value.getDoubleValue() );
        break;
      case GROUPCODE_START_Y:
        circle.getCenterPoint().setY( value.getDoubleValue() );
        break;
      case RADIUS:
        circle.setRadius( value.getDoubleValue() );
        break;
      default:
        super.parseCommonProperty( groupCode, value, circle );
        break;
    }
  }

  public DXFEntity getDXFEntity() { return circle; }

  public void endDXFEntity() {
  }

  public boolean isFollowSequence() { return false; }
}
