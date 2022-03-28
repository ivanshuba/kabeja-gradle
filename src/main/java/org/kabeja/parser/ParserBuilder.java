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

package org.kabeja.parser;

import org.kabeja.parser.entities.*;
import org.kabeja.parser.objects.*;
import org.kabeja.parser.table.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author <a href="mailto:simon.mieth@gmx.de>Simon Mieth</a>
 */
public class ParserBuilder {
  public static Parser createDefaultParser() {
    DXFParser parser = new DXFParser();

    // the header section handler
    Handler handler = new DXFHeaderSectionHandler();
    parser.addHandler( handler );

    // the BLOCK handler
    HandlerManager dxfBlocksSectionHandlerManager = new DXFBlocksSectionHandler();
    dxfBlocksSectionHandlerManager.addHandler( new DXFPointHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFLineHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFCircleHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFArcHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFPolylineHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFLWPolylineHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFMTextHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFTextHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFInsertHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFEllipseHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFSolidHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFTraceHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFDimensionHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFHatchHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFImageHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXF3DFaceHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFRayHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFXLineHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFRegionHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFBodyHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXF3DSolidHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFSplineHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFMLineHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFLeaderHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFToleranceHandler() );
    dxfBlocksSectionHandlerManager.addHandler( new DXFViewportHandler() );
    parser.addHandler( dxfBlocksSectionHandlerManager );

    // the TABLE handler
    HandlerManager tableSectionHandlerManager = new DXFTableSectionHandler();
    tableSectionHandlerManager.addHandler( new DXFLayerTableHandler() );
    tableSectionHandlerManager.addHandler( new DXFLineTypeTableHandler() );
    tableSectionHandlerManager.addHandler( new DXFDimensionStyleTableHandler() );
    tableSectionHandlerManager.addHandler( new DXFStyleTableHandler() );
    tableSectionHandlerManager.addHandler( new DXFVPortTableHandler() );
    tableSectionHandlerManager.addHandler( new DXFViewTableHandler() );
    parser.addHandler( tableSectionHandlerManager );

    // the ENTITY section handler
    HandlerManager dxfEntitiesSectionHandlerManager = new DXFEntitiesSectionHandler();
    dxfEntitiesSectionHandlerManager.addHandler( new DXFPointHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFLineHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFArcHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFCircleHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFPolylineHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFLWPolylineHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFMTextHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFTextHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFInsertHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFEllipseHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFSolidHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFTraceHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFDimensionHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFHatchHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFImageHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXF3DFaceHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFRayHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFXLineHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFRegionHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFBodyHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXF3DSolidHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFSplineHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFMLineHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFLeaderHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFToleranceHandler() );
    dxfEntitiesSectionHandlerManager.addHandler( new DXFViewportHandler() );
    parser.addHandler( dxfEntitiesSectionHandlerManager );

    // the OBJECTS section
    HandlerManager dxfObjectsSectionHandlerManager = new DXFObjectsSectionHandler();
    dxfObjectsSectionHandlerManager.addHandler( new DXFImageDefHandler() );
    dxfObjectsSectionHandlerManager.addHandler( new DXFDictionaryHandler() );
    dxfObjectsSectionHandlerManager.addHandler( new DXFPlotsettingsHandler() );
    dxfObjectsSectionHandlerManager.addHandler( new DXFLayoutHandler() );
    dxfObjectsSectionHandlerManager.addHandler( new DXFMLineStyleHandler() );
    parser.addHandler( dxfObjectsSectionHandlerManager );

    return parser;
  }

  /**
   * @param file
   * @return the DXFParser build from the XML description file
   * @see org.kabeja.parser.SAXParserBuilder the SAXParserBuilder for XML
   * description
   */
  public static Parser buildFromXML( String file ) {
    try {
      return buildFromXML( new FileInputStream( file ) );
    } catch ( FileNotFoundException e ) {
      e.printStackTrace();
    }

    return null;
  }

  public static Parser buildFromXML( InputStream in ) {
    return SAXParserBuilder.buildFromStream( in );
  }
}
