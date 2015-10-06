package org.kuali.ole.deliver.notice.valuesFinder;

import org.kuali.ole.OLEConstants;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maheswarang on 10/6/15.
 */
public class OleNoticeItemFieldLabelMappingKeyValuesFinder extends KeyValuesBase {


    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue(OLEConstants.TITLE_ITEM_INFORMATION,OLEConstants.TITLE_ITEM_INFORMATION));
        keyValues.add(new ConcreteKeyValue(OLEConstants.CIRCULATION_LOCATION_LIBRARY_NAME,OLEConstants.CIRCULATION_LOCATION_LIBRARY_NAME));
        keyValues.add(new ConcreteKeyValue(OLEConstants.CIRCULATION_REPLY_TO_EMAIL,OLEConstants.CIRCULATION_REPLY_TO_EMAIL));
        keyValues.add(new ConcreteKeyValue(OLEConstants.NOTICE_TITLE,OLEConstants.NOTICE_TITLE));
        keyValues.add(new ConcreteKeyValue(OLEConstants.NOTICE_AUTHOR,OLEConstants.NOTICE_AUTHOR));
        keyValues.add(new ConcreteKeyValue(OLEConstants.NOTICE_COPY_NUMBER,OLEConstants.NOTICE_COPY_NUMBER));
        keyValues.add(new ConcreteKeyValue(OLEConstants.NOTICE_ENUMERATION,OLEConstants.NOTICE_ENUMERATION));
        keyValues.add(new ConcreteKeyValue(OLEConstants.NOTICE_CHRONOLOGY,OLEConstants.NOTICE_CHRONOLOGY));
        keyValues.add(new ConcreteKeyValue(OLEConstants.NOTICE_CALL_NUMBER,OLEConstants.NOTICE_CALL_NUMBER));
        keyValues.add(new ConcreteKeyValue(OLEConstants.NOTICE_ITEM_BARCODE,OLEConstants.NOTICE_ITEM_BARCODE));
        keyValues.add(new ConcreteKeyValue(OLEConstants.ORIGINAL_DUE_DATE,OLEConstants.ORIGINAL_DUE_DATE));
        keyValues.add(new ConcreteKeyValue(OLEConstants.NEW_DUE_DATE,OLEConstants.NEW_DUE_DATE));
        keyValues.add(new ConcreteKeyValue(OLEConstants.ITEM_WAS_DUE,OLEConstants.ITEM_WAS_DUE));
        keyValues.add(new ConcreteKeyValue(OLEConstants.HOLD_EXPIRATION_DATE,OLEConstants.HOLD_EXPIRATION_DATE));
        keyValues.add(new ConcreteKeyValue(OLEConstants.LIBRARY_SHELVING_LOCATION,OLEConstants.LIBRARY_SHELVING_LOCATION));
        return keyValues;
    }
}