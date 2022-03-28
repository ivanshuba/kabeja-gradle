package org.kabeja.parser.entities;

import org.kabeja.dxf.DXFEntity;
import org.kabeja.dxf.DXFPoint;
import org.kabeja.parser.DXFValue;

public class DXFPointHandler extends AbstractEntityHandler {
  public final static String ENTITY_NAME = "POINT";
  private DXFPoint point;

  public DXFPointHandler() {
    super();
  }

  @Override
  public void startDXFEntity() {
    point = new DXFPoint();
    point.setDXFDocument( doc );
  }

  @Override
  public void parseGroup( int groupCode, DXFValue value ) {
    switch ( groupCode ) {
      case GROUPCODE_START_X:
        point.setX( value.getDoubleValue() );
        break;
      case GROUPCODE_START_Y:
        point.setY( value.getDoubleValue() );
        break;
      default:
        super.parseCommonProperty( groupCode, value, point );
        break;
    }
  }

  @Override
  public DXFEntity getDXFEntity() { return point; }

  @Override
  public void endDXFEntity() {

  }

  @Override
  public boolean isFollowSequence() { return false; }

  @Override
  public String getDXFEntityName() { return ENTITY_NAME; }

}
